# SystemServer进程的启动

## ZygoteInit#startSystemServer()

```java
private static boolean startSystemServer(String abiList,String socketName){
    //
    int pid;
    try{
        parsedArgs = new ZygoteConnection.Arguments(args);
        ZygoteConnection.applyDebuggerSystemProperty(parsedArgs);
        ZygoteConnection.applyInvokeWithSystemProperty(parsedArgs);
        
        //1
        //请求fork SystemServer进程
        pid = Zygote.forkSystemServer(
        	parsedArgs.uid,parsedArgs.gid,
            parsedArgs.gids,
            parsedArgs.debugFlags,
            null,
            parsedArgs.permittedCapabilities,
            parsedArgs.effectiveCapabilities);
    }catch(..){
        //
    }
    
    //pid为0表示子进程,即SystemServer进程,从此SystemService进程与Zygote进程分道扬镳
    if(pid == 0){
        if(hasSecondZygote(abiList)){
            waitForSecondaryZygote(socketName);
        }
        handleSystemServerProcess(parsedArgs);	//2
    }
        
    return true;
}
```

- 注释1..调用Zygote的forkSystemServer,主要通过fork函数在当前进程创建一个子进程(也就是SystemServer进程),如果返回的pid是0,也就表示在新创建的子进程中进行的
- 注释2..handleSystemServerProcess,启动SystemServer进程

## ZygoteInit#handleSystemServerProcess()

```java
private static void handleSystemServerProcess(ZygoteConnection.Arguments parsedArgs){
    closeServerSocket();	//1
    
    if(parsedArgs.niceName != null){
        Process.setArgV0(parsedArgs.niceName);	//2
    }
    
    ///
    
    if(parsedArgs.invokeWith != null){
        String[] args = parsedArgs.remainingArgs;
        //If we have a non-null system server class path,we'll have to duplicate the existing arguments and append the classpath to it..ART will handle the classpath correctly when we exec a new process
        if(systemServerClasspath != null){
            String[] amendedArgs = new String[args.length +2];
            amendedArgs[0] = "-cp";
            amendedArgs[1] = systemServerClasspath;
            System.arraycopy(parsedArgs.remainingArgs,0,amendedArgs,2,
            parsedArgs.remainingArgs.length);
        }   							        
        WrapperInit.execApplication(parsedArgs.invokeWith,                                    parsedArgs.niceName,parsedArgs.targetSdkVersion,                              VMRuntime.getCurrentInstructionSet(),null,args);
    }else{
        ClassLoader cl = null;
        if(systemServerClasspath != null){
            cl = createSystemServerClassLoader(systemServerClasspath,
                                              parsedArgs.targetSdkVersion);
            
            Thread.currentThread().setContextClassLoader(cl);
        }
        
        /* Pass the remaining arguments to SystemServer */
        RuntimeInit.zygoteInit(parsedArgs.targetSdkVersion,parsedArgs.remainingArgs,cl);	//3
    }
    
    /* should never reach here */
}
```

- 注释1..SystemServer进程是赋值了Zygote进程的地址空间,因此也会得到Zygote进程创建的Socket,这个Socket对于SystemServer没有用处,所以调用closeServerSocket关闭它
- 注释2..Arguments封装函数,parsedArgs.niceName=system_server,在这里调用Process.setArgV0()设置进程名为system_server
- 注释3..parsedArgs.invokeWith属性默认为null,最后调用RuntimeInit.zygoteInit来进一步启动SystemServer

## RuntimeInit#zygoteInit()

```java
public static final void zygoteInit(int targetSdkVersion,String[] argv,ClassLoader classLoader){
    if(DEBUG) SLog.d(TAG,"RuntimeInit:Starting application from zygote");
    
    Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER,"RuntimeInit");
    //重定向Log输出
    redirectLogStreams();
    //初始化运行环境
    commonInit();
    //启动Binder线程池
    nativeZygoteInit();	//1
    //调用程序入口函数
    applicationInit(targetSdkVersion,argv,classLoader);	//2
}
```

- 注释1..调用nativeZygoteInit函数,一看函数的名称就知道调用了Native层的代码,它是由从来启动BInder线程池的
- 注释2..调用了applicationInit函数启动应用程序

## Binder线程池启动过程

## RuntimeInit#applicationInit()

```java
private static void applicationInit(int targetSdkVersion,String[] argv,ClassLoader classLoader){
    ///
    
    //初始化虚拟机环境
    VMRuntime.getRuntime().setTargetHeapUtilization(0.75f);
    VMRuntime.getRuntime().setTargetSdkVersion(targetSdkVersion);
    
    final Arguments args;
    try{
        args = new Arguments(argv);
    }catch(..){
        //
        return;
    }
    
    //Remaining arguments are passed to the start class's static main
    invokeStaticMain(args.startClass,args.startArgs,classLoader);  //1
}
```

## RuntimeInit#invokeStaticMain()

```java
private static void invokeStaticMain(String className,String[] argv,ClassLoader classLoader){
    Class<?> cl;
    
    try{
        cl = Class.forName(className,true,classLoader);	//1
    }catch(..){
        //
    }
    
    Method m;
    try{
        //获取main方法
        m = cl.getMethod("main",new Class[]{String[].class});	//2
    }catch(..){
        //
    }
    
    //判断修饰符
    int modifiers = m.getModifiers();	//3
    if(!(Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers))){
        throw new Exception(..);
    }
    
    throw new ZygoteInit.MethodAndArgsCaller(m,argv);	//4
}
```

- 注释1..传入的className就是com.android.server.SystemServer,因此通过反射返回的cl为SystemServer类
- 注释2..获取main方法
- 注释3..判断修饰符,必须是static而且必须是public类型
- 注释4..将找到的main函数传入到MethodAndArgsCaller异常并抛出该异常.这个异常在ZygoteInit#main方法中捕获.这么做的作用是清除应用程序进程创建过程的调用栈

### ZygoteInit#main()

```java
public static void main(String[] args){
    try{
        ///
        startSystemServer(abiList,socketName);
        ///
    }catch(MethodAndArgsCaller caller){
        caller.run();	//1
    }
}
```

### MethodAndArgsCaller

```java
public static class MethodAndArgsCaller extends Exception implements Runnable{
    /* method to call */
    private final Method mMethod;
    
    /* argument array */
    private final String[] mArgs;
    
    public MethodAndArgsCaller(Method method,String[] args){
        mMethod = method;
        mArgs = args;
    }
    
    public void run(){
        try{
            mMethod.invoke(null,new Object[]{mArgs});	//1
        }catch(..){
            //
        }
    }
}
```

- 注释1..通过反射调用了com.android.server.SystemServer#main(String[] args).至此,Zygote进程fork出SystemServer进程,并成功调用SystemServer#main()

# 解析SystemServer进程

## SystemServer#main

```java
public static void main(String[] args){
    new SystemServer().run();//1
}
```

## SystemServer#run()

```java
private void run(){
    //
    System.loadLibrary("android_servers");	//1
    //
    mSystemServiceManager = new SystemServiceManager(mSystemContext); //2
    LocalServices.addService(SystemServiceManager.class,
                             mSystemServiceManager);
    //
    try{
        startBootstrapServices();	//3
        startCoreServices();		//4
        startOtherServices();		//5
    }catch(..){
        //
    }
}
```

- 注释1..加载了libandroid_services.so

- 注释2..创建SystemServiceManager,他会对系统的服务进行创建,启动和生命周期管理..启动系统的各种服务

- 注释3..startBootstrapServices函数中用SystemServiceManager启动了ActivityManagerService,PowerManagerService,PackageManagerService等服务

- 注释4..启动了BatteryService,UsageStatsService,和WebViewUpdateService

- 注释5..startOtherServcie启动了CameraService,AlarmManagerService,VrManagerService等服务,这些服务的父类为SystemService

- 注释3,4,5可以看出,官方把系统服务分为了3中类型,分别是引导服务,核心服务和其他服务,其中其他服务为一些非紧要和一些不需要立即启动的服务..系统服务大约有80多个..这里列出部分;;

  | 引导服务               | 作用                                                         |
  | ---------------------- | ------------------------------------------------------------ |
  | Installer              | 系统安装apk时的一个服务类,启动完成Installer服务之后才能启动其他的系统服务 |
  | ActivityManagerService | 负责四大组件的启动,切换,调度                                 |
  | PowerManagerService    | 计算系统中和power相关的计算,然后决策系统应该如何反应         |
  | LightsService          | 管理和显示背光LED                                            |
  | DisplayManagerService  | 用来管理所有显示设备                                         |
  | UserManagerService     | 多用户模式管理                                               |
  | SensorService          | 为系统提供各种感应器服务                                     |
  | PackageManagerService  | 用来对apk进行安装,解析,删除,卸载等等操作                     |

  | 核心服务             | 作用                                 |
  | -------------------- | ------------------------------------ |
  | BatteryService       | 管理电池相关的服务                   |
  | UsageStatsService    | 收集用户使用每一个app的频率,使用时长 |
  | WebViewUpdateService | WebView更新服务                      |

  | 其他服务                     | 作用               |
  | ---------------------------- | ------------------ |
  | CameraService                | 摄像头相关服务     |
  | AlarmManagerService          | 全局定时器管理服务 |
  | InputManagerService          | 管理输入事件       |
  | WindowManagerService         | 窗口管理服务       |
  | VrManagerService             | VR模式管理服务     |
  | BluetoothService             | 蓝牙管理服务       |
  | NotificationManagerService   | 通知管理服务       |
  | DeviceStrorageMonitorService | 存储相关管理服务   |
  | LocationManagerService       | 定位管理服务       |
  | AudioService                 | 音频相关管理服务   |

## SystemServer#startBootstrapServices()

```java
private void startBootstrapServices(){
    ///
    mPowerManagerService = mSystemServiceManager.startService(PowerManagerService.class);
    ///
    mPackageManagerService = PackageManagerService.main(mSystemContext,installer,mFactoryTestMode != FactoryTest.FACTORY_TEST_OFF,mOnlyCore);
    ///
}
```

### SystemServiceManager#startService()

```java
public <T extends SystemService> T startService(Class<T> serviceClass){
    try{
        final String name = serviceClass.getName();
        ///
        
        final T service;
        try{
            Constructor<T> constructor = serviceClass.getConstructor(Context.class);
            service = constructor.newInstance(mContext);	//1
        }catch(..){
            //
        }
        
        mServices.add(service);	//2
        
        try{
            service.onStart();	//3
        }
        
        return service;
    }
}
```

- 注释1..通过构造器创建SystemService,这里的SystemService就是PowerManagerService
- 注释2..将PowerManagerService添加到mServices中,这里mService就是一个存储SystemService类型的ArrayList
- 注释3..接着调用PowerManagerService的onStart函数启动PowerManagerService并返回,这样就完成了PowerManagerService启动的过程

### PackageManagerService#main

```java
public static PackageManagerService main(Context context,Installer installer,boolean factoryTest,boolean onlyCore){
    PackageManagerServiceCompilerMapper.checkProperties();
    
    PackageManagerService m = new PackageManagerService(context,installer,factoryTest,onlyCore);	//1
    m.enableSystemUserPackages();
    
    //Disable any carrier apps,wo do this very early in boot to pervent the apps from being disabled after already being started
    CarrierAppUtils.disableCarrierAppsUntilPrivileged(context.getOpPackageName(),m,UserHandle.USER_SYSTEM);
    ServiceManager.addService("package",m);	//2
    return m;
}
```

- 注释1..创建PackageManagerService实例
- 注释2..将PackageManagerService实例注册到serviceManager中.serviceManager用来管理系统中的各种service,用系统c/s架构中的binder机制通信,client端要使用某个service,则需要先到servicemanager查询service的相关信息,然后根据service的相关信息与services所在的service进程建立通讯通路,这样client端就可以使用service了

# 总结SystemServer进程

SystemServer在启动做人流如下工作

- 启动binder线程池,这样就可以与其他进程进行通讯
- 创建SystemServiceManager用于对系统的服务进行创建,启动,和生命周期管理
- 启动各种系统服务















