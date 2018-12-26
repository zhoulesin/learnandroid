# Launcher启动过程

SystemService进程主要用户启动系统的各种服务,其中包含了Launcher服务,LauncherAppService.

Android系统默认第一个启动的应用程序是Home应用程序,这个应用程序用来显示系统中已经安装的应用程序,这个Home应用程序就叫做launcher.应用程序launcher在启动过程中会请求packagemanagerservice返回系统中已经安装的应用程序的信息,并将这些信息封装成一个快捷图标列表显示在系统屏幕上,这样用户可以通过点击这些快捷图标来启动相应的应用程序

## launcher启动过程

SystemService 的startOtherService方法中,

```java
private void startOtherServices(){
    //...1
    mActivityManagerService.systemReady(new Runnable(){
       @Override
        public void run(){
            /**
             * 执行各种SystemService的启动方法,各种SystemService的systemReady方法
             */
            mSystemServiceManager.startBootPhase(SystemService.PHASE_ACTIVITY_MANAGER_READY);
            ///
        }
    });
    ///    
}
```

- 注释1..调用ActivityManagerService的systemReady函数

## ActivityManagerService#systemReady()

```java
public void systemReady(final Runnable goingCallback){
    ///
    //Start up initial activity
    mBooting = false;
    //Enable home activity for system user,so that the system can always boot
    if(UserManager.isSplitSystemUser()){
        ComponentName cName = new ComponentName(mContext,SystemUserHomeActivity.class);
        try{
            AppGlobals.getPackageManager().setComponentEnabledSetting(cName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,0,UserHandle.USER_SYSTEM);
            
        }catch(..){
            //
        }
    }
    ///
    startHomeActivityLocked(currentUserId,"systemReady");	//1
}
```

- 注释1,调用了startHomeActivityLocked方法,说明开始执行启动homeActivity的操作

## ActivityManagerService#startHomeActivityLocked()

```java
boolean startHomeActivityLocked(int userId,String reason){
    if(mFactoryTest == Factoty.FACTORY_TEST_LOW_LEVEL && mTopAction == null){	//1
        //we are running in factory test mode,but unable to find the factory
        //test app,so just sit around displaying the error message and dont'
        //try to start anything
        return false;
    }
    Intent intent = getHomeIntent();	//2
    ActivityInfo aInfo = resolveActivityInfo(intent,STOCK_PM_FLAGS,userId);
    if(aInfo != null){
        intent.setComponent(enw ComponentName(aInfo.applicationInfo.packageName,aInfo.name));
        //Don't do this if the home app is currently being instrumented
        aInfo = new ActivityInfo(aInfo);
        aInfo.applicationInfo = getAppInfoForUser(aInfo.applicationInfo,userId);
        ProcessRecord app = getProcessRecordLocked(aInfo.processName,aInfo.applicationInfo.uid,true);
        if(app == null || app.instrumentationClass == null){
            intent.setFlag(intent.getFlags()|Intent.FLAG_ACTIVITY_NEW_TASK);
            mActivityStarter.startHomeActivityLocked(intent,aInfo,reason);	//3
        }
    }else{
        
    }
    return true;    
}
```

- 注释1..mFactoryTest代表系统的运行模式,系统的运行模式分为三种,分别是非工厂模式.低级工厂模式和高级工厂模式,mTopAction则用来描述第一个被启动Activity组件的Action,它的值为Intent.ACTION_MAIN,因此这里的代码意思就是mFactoryTest为FactoryTest.FACTORY_TEST_LOW_LEVEL(低级工厂模式)并且mTopAction =null时,直接返回false 

## ActivityManagerService#getHomeIntent

```java
Intent getHomeIntent(){
    Intent intent = new Intent(mTopAction,mTopData != null ? Uri.parse(mTopData) : null); //1
    intent.setComponent(mTopComponent);
    intent.addFlags(Intent.FLAG_DEBUG_TRIAGED_MISSING);
    if(mFactoryTest != FactoryTest.FACTORY_TEST_LOW_LEVEL){
        intent.addCategory(Intent.CATEGORY_HOME);	//2
    }
    return intent;
}
```

- 注释1..创建了intent,并将mTopAction和mTopData传入.mTopAction的值为Intent.ACTION_MAIN
- 注释2..如果系统运行模式不是低级工厂模式则将intent的category设置为Intent.CATEGORY_HOME.之后被启动的应用程序就是Launcher,因为Launcher的Manifest文件中的intent-filter标签匹配了Action为Intent.ACTION_MAIN,Category为Intent.CATEGORY_HOME.launcher的manifest文件

## ActivityStarter#startHomeActivityLocked

```java
void startHomeActivityLocked(Intent intent,ActivityInfo aInfo,String reason){
    mSupervisor.moveHomeStackTaskToTop(HOME_ACTIVITY_TYPE,reason);
    startActivityLocked(null,intent,null,null,aInfo,null,null,null,null,null,null,0,0,0,null,0,0,0,null,false,false,null,null,null);
    if(mSupervisor.inResumeTopActivity){
        mSupervisor.scheduleResumeTopActivities();	//1
    }
}
```

- 注释1..scheduleResumeTopActivities方法,这个方法其实是关于Activity的启动流程的逻辑了,这里我们就不详细说明了,关于Activity的启动流程

  这样launcher就会被启动起来,并执行它的onCreate函数

  ![](./image/20171002163016384)

# Android应用程序安装

Android系统在启动的过程中,Zygote进程启动SystemService进程,SystemService启动PackageManagerService服务,这个服务负责扫描系统中特定的目录,找到里面的应用程序文件,即以apk为后缀的文件,然后对这些文件进行解析(其实就是解析应用程序配置文件AndroidManifest.xml的过程),并从里面得到应用程序的相关信息,例如得到应用程序的组件package,activity,service,broadcast receiver和content provider等信息,保存早PackagemanagerService的mPackages.mActivities,mservices,mreceivers等成员变量hashmap中,得到应用程序的相关信息之后,完成应用程序的安装过程

这些应用程序只是相当于在packagemanagerservice服务注册好了,如果我们想要在android桌面上看到这些应用程序,还需要有一个home应用程序(android系统默认的home应用程序就是launcher),负责从packagemanagerservice服务中把这些安装好的应用程序取出来,并以友好的方式在桌面上展现出来,例如:快捷方式..

## launcher中应用图标显示流程

Launcher#onCreate()

```java
@Override
protected void onCreate(Bundle saveInstanceState){
    //
    LauncherAppState app = LauncherAppState.getInstance(); //1
    mDeviceProfile = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? app.getInvariantDeviceProfile().landscapeProfile : app.getInvariantDeviceProfile().protraitProfile;
    mSharedPrefs = Utilities.getPrefs(this);
    mIsSafeModeEnabled = getPackageManager().isSafeMode();
    mModel = app.setLauncher(this); //2
    ///
    if(!mRestoring){
        if(DISABLE_SYNCHRONOUS_BINDING_CURRENT_PAGE){
            mModel.startLoader(PagedView.INVALID_RESTORE_PAGE); //3
        }else{
            mModel.startLoader(mWorkspace.getRestorePage());
        }
    }
    ///
}
```

- 注释1..获取launcherappstate的实例
- 注释2..调用它的setlauncher函数并将launcher对象传入

##  LauncherAppState#setLauncher()

```java
LauncherModel setLauncher(Launcher launcher){
    getLuancherProvider().setLauncherProviderChangeListener(launcher);
    mModel.initialize(launcher);//1
    mAccessibilityDelegate = ((launcher != null) && Utilities.ATLEAST_LOLLIPOP) ? new LauncherAccessibilityDelegate(launcher);
    return mModel;
}
```

## LauncherModel#initialize

```java
public void initialize(Callbacks callbacks){
    synchronized(mLock){
        unbindItemInfosAndClearQueuedBindRunnables();
        mCallbacks = new WeakRefrence<Callback>(callbacks);
    }
}
```

在initialize函数中会将callbacks,也就是传入的launcher封装成一个弱引用对象.因此我们得知mCallbacks变量指的就是封装成弱引用对象的launche

























