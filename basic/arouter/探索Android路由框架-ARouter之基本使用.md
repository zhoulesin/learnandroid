# 探索Android路由框架-ARouter之基本使用(一)

https://www.jianshu.com/p/6021f3f61fa6

ARouter这款路由框架可以为我们的应用开发提供更好更丰富的跳转方案.比如支持解析标准URL进行跳转,并自动注入参数到目标页面中;支持添加多个拦截器,自定义拦截器顺序(满足拦截器设置的条件才允许跳转,所以这一特性对于某些问题又提供了新的解决思路)

原生路由方案使用耦合严重或者协作非常困难,而且在跳转过程中无法控制问题,因为一旦使用了startactivity就无法插手其中任何环节了,只能交给系统去管理,这就导致了在跳转失败的情况下无法降级,而会直接抛出运营级的异常.这时候使用自定义的路由组件就可以解决以上问题,比如通过URL索引就可以解决类依赖的问题;通过分布式管理页面配置可以解决隐式intent中集中式管理path的问题;自己实现整个路由过程也可以拥有良好的扩展性,还可以通过aop的方式解决跳转过程无法控制的问题,与此同时也能够提供非常灵活的降级方式.

## 添加依赖

在app.gradle中

```java
javaCompileOptions{
    annotationProcessorOptions{
        arguments = [ module : project.getName() ]
    }
}

compile 'com.alibaba:arouter-api:1.3.1'
annotationProcessor 'com.alibaba:arouter-compile:1.1.4'
```

## 初始化

```java
public class HomeApplication extends Application{
    private boolean isDebugARouter = true;
    @Override
    public void onCreate(){
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
```

## 开始使用

- 在Activity/Fragment类上添加Route注解

  这里的路径至少需要两级  /xx/xx

- 在Activity/Fragment类中进行ARouter注入

  (若有参数传递进来需要调用)

  ARouter.getInstance().inject(this)

```java
public class MainActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle saveInstanceState){
        setContentView(R.layout.activity_main);
        
        findviewById(R.id.jump).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ARouter.getInstance().build("/app/SimpleActivity")
                    .withString("name","sdsd")
                    .navigation();
            }
        })
    }
}

@Route(path="/app/SimpleActivity")
public class SimpleActivity extends BaseActivity{
    @Autowired
    String name;
    
    onCreate(){
        ARouter.getInstance().inject(this);
    }
}
```

一般我们使用一个类统一管理和维护路径标签,在BaseActivity统一添加ARouter.getInstance().inject(this);

- 注意:ARouter.getInstance().destroy() 要在Application中结束

## 页面跳转

### 简单跳转

```java
ARouter.getInstance().build("/app/SimpleActivity").navigation();
```

### 带参数跳转

```java
ARouter.getInstance().build("/app/SimpleActivity")
    			.withString("name","asd")
    			.withParcelable("bean",new JavaBean("asds",22))
    			.navigation();
```

## 接收传递的值

```java
@Autowired
String name;

//这里两种写法,①使用name属性获取
@Autowired(name="bean")
JavaBean asd;
//②变量名与参数key相同,会自动注入
@Autowired
JavaBean bean;


```

## 界面跳转动画

```java
ARouter.getInstance().build("/app/SimpleActivity")
    			.withTransition(enterAnim,exitAnim)
    			.navigation();
```

## Fragment跳转

实际就是获取fragment

```java
Fragment fragment = (Fragment)ARouter.getInstance()
    		.build("/app/SimpleFragment").navigation();
```

# ARouter基本使用之------拦截器

## 创建拦截器

```java
@Interceptor(priority = 1)
public class UserOneInterceptor implements IInterceptor{
    ///
}
```

- 必须实现IInterceprot接口
- 必须加上@Interceptor注解,priority值越小,优先级越高(先触发)
- priority不能相同,否则会编译报错
- 不拦截调用callback的onContinue(postcard)方法

# ARouter之路径的组 group

## 自定义分组,实现界面跳转

```java
@Route(path="/app/aa",group="group_a")
public class AActivity extends BaseActivity{
    
}

ARouter.getInstance().build("app/aa","group_a").navigation();
```

