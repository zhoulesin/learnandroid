# UI跳转

## 以实现功能

- 按组件区分host,增加URL的可读性
- 自动注册路由,不在需要手动编写代码进行路由分发
- 按需加载,只有实际跳转到某个组件时,该组件的路由表才会加载
- 自动生成路由表文件,方便组件开发团队之间调用
- 参数支持依赖注入,不需要编写参数的解析代码

## URL结构

组件之间的UI跳转是基于标准的URL来实现的,先看一下URL的基本构成

```java
<scheme>://<host>/<path>?<query>
```

这是最简单的一个模型,不过用于我们UI跳转协议已经足够了.

```java
DDComp://share/shareBook?bookName=Gone with the Wind
```

结合我们的组件化框架

- seheme对应的是DDComp.这个在JIMU没有限制,使用时可以自己自由设置,一般为了从应用外跳转进来,会在manifests的入口activity添加以下配置

  ```java
  <intent-filter>
  	<data android:scheme="DDComp"/>
  	<action android:naem="android:intent.action.VIEW"/>
  	<category android:name="android.intent.category.VIEW"/>
  	<category android:name="android.intent.category.DEFAULT"/>
  	<category android:name="android.intent.category.BROWSABLE"/>
  </intent-filter>
  ```

- host对应的是share..在组件化框架中,每个组件对应一个唯一的host,例如分享组件的host就是share,读书组件的host是reader等等

  - host是路由分发的第一级,根据host可以定位到每个组件
  - host还可以对所有的路由URL进行一个分组,只有调用到该分组的路由的时候,组内的路由才会被加载进内存

- path对应的是sharebook.它对应的是具体的每个具体的页面,例如shareBook对应的就是ShareActivity.在一个组件中,path是不能重复的.

- qurey对应的是bookName=Gone with the Wind.他表示要跳转到ShareActivity需要传入的参数

## 组件添加必要的依赖

在组件的build.gradle中添加依赖

```java
annotationProcessor 'com.luojilab.ddcomponent:router-anno-compile:1.0.0'
```

同时添加

```java
defaultConfig{
    javaCompileOptions{
        annotationProcessorOptions{
            arguments = [host:"share"]
        }
    }
}
```

此处的share是跳转URI中的host,每个组件需要设置不同的host

### 注册组件到UIRouter中

在组件的生命周期类ApplicationLike中,添加注册和反注册代码

```java
public class ShareAppLike implements IApplicationLike{
    UIRouter uiRouter = UIRouter.getInstance();
    @Override
    public void onCreate(){
        uiRouter.registerUI("share");
    }
    
    @Override
    public void onStop(){
        uiRouter.unregisterUI("share");
    }
}
```

### 目标页面添加注解

首先在跳转的目标Activity上添加RouteNote注解

```java
@RouteNote(path="/shareBook",desc="分享书籍页面")
public class ShareActivity extends AppCompatActivity{
    
}
```

如果需要传入参数,在具体的参数定义上增加AutoWired注解:

```java
@Autowired
String bookName;
@Autowires
String author;
```

注意此处的参数不能是private,负责编译会直接报错.这里的原因是在于依赖注入的时候没有使用反射,而是直接调用了改参数,所以需要参数至少是包可见的

### 依赖注入

如果想使用自动状态功能,需要在activity的oncreate中调用方法

```java
AutowiredService.Factoty.getInstance().create().autowire(this);
```

建议该方法在基类Activity中调用

### build项目

项目执行build,会生成apt文件,具体可在build目录下面查看

同时还会在根目录生成UIRouterTable文件夹,里面会列出每个组件向外提供的路由表

```java
auto generated,do not change !!!

HOST : share

分享杂志页面
/shareMagazine
author:com.luojilab.componentservice.share.bean.Author
bookName:String

分享书籍页面
/shareBook
author:com.luojilab.componentservice.share.bean.Author
bookName:String
```

### 跳转

在发起跳转页面,有三种方式可以跳转到目的页面

#### Bundle方式

```java
//UI transfer with Bundle
private void goToShareActivityWithBundle(){
    Author author = new Author();
    author.setName("fdsf");
	author.setCountry("USA");
    Bundle bundle = new Bundle();
    bundle.putString("bookName","Gone with the Wind");
    bundle.putString("author",JsonService.Factoty.getInstance().create().toJsonString(author));
    UIRouter.getInstance().openUri(getActivity(),"DDComp://share/sahreBook",bundle);
}
```

#### URI方式

```java
//UI transder with URI
private void goToShareActivityWithUri(){
    Author author = new Author();
    author.setName("fdsf");
	author.setCountry("USA");
    final String URI_LEGAL = "DDComp://share/shareMagazine?bookName=FSDF&amp;author=";
    UIRouter.getInstance().openUri(getActivity(),
                                  URI_LEGAL+JsonService.Factory.getInstance().create().toJsonString(author),null);
}
```

#### startActivityForResult

```java
public void goToShareActivityForResult(){
     Author author = new Author();
    author.setName("fdsf");
	author.setCountry("USA");
    UIRouter.getInstance().openUri(getActivity(),"DDComp://share/shareBook?bookName=FDS&amp;author"+JsonService.Factory.getInstance().create().toJsonString(author),null,7777);
}
```

