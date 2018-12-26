# 阿里巴巴ARouter基本使用方法

https://www.jianshu.com/p/6808d4e54d4a

阿里巴巴开源的路由:[https://github.com/alibaba/ARouter](https://link.jianshu.com/?t=https%3A%2F%2Fgithub.com%2Falibaba%2FARouter) 

ARouter在模块化的开发中尤为的重要,为了解耦不在需要依赖另一个模块又能实现模块间的跳转,本文只介绍简单的使用方法.

## 原生跳转方式的不足

- 显示跳转,`Intent intent = new Intent(activity,XXActivity.class);`

由于需要直接持有对应class,从而导致了强依赖关系,提高了耦合度.

- 隐式跳转,譬如`Intent intent = new Intent;`

`intent.setAction("com.android.activity.MY_ACTION");`

action等属性的定义在Manifest中,导致了扩展性较差,规则集中式管理,导致协作变得非常困难

## ARouter的使用

### 添加依赖和配置

```java
android{
    defaultConfig{
        ///
        javaCompileOptions{
            annotationProcessorOptions{
                arguments = [moduleName : project.getName()]
            }
        }
    }
}

dependencies{
    compile 'com.alibaba:arouter-api:x.x.x'
    annotationProcessor 'com.alibaba:arouter-compiler:x.x.x'
    ///
}
```

### 添加注解

//在支持路由的页面上添加注解(必选)

//这里的路径需要注意的是至少需要有两级,/xx/xx

```java
@Route(path="/test/activity")
public class YourActivity extend Activity{
    ///
}
```

### 初始化SDK

```java
if(isDebug()){
    ARouter.openLog();
    ARouter.openDebug();
}
ARouter.init(mApplication);
```

### 发起路由操作

- 应用内简单的跳转

  ```java
  ARouter.getInstance().build("/test/activity").navigation();
  ```

- 跳转并携带参数

  ```java
  ARouter.getInstance().build("/test/1")
      	.withString("name","111")
      	.withInt("age",33)
      	.navigation();
  ```

### 接受参数

在Activity中获取参数有两种方式,一种是普通Activity那样getIntent().getXXX

另一种是使用@Autowired注解的方式

```java
@Autowired
public String name;
@Autowired
public int age;

//在onCreate中添加
ARouter.getInstance().inject(this);
```

### 跳转传递对象

```java
public class TestObj implements Serializable{
    public String name;
    public int id;
    public TestObj(){}
    public TestObj(String name,int id){
        this.name = name ;
        this.id = id;
    }    
}


//跳转并携带参数
List<TestObj> list = new ArrayList<>();
for(int i = 0;i<5;i++){
    TestObj testObj = new TestObj("aaa"+i,777);
    list.add(testObj);
}
ARouter.getInstance().build(ARouterManager.BModuleActivity)
    	.withString("name","老王")
    	.withInt("age",12)
    	.withString("url","http://a.b.c")
    	.withSerializable("pac",(Serializable)list)
    	.navigation();

//接受参数
List<TestObj> chooseList = (List<TestObj>)getIntent().getSErializableExtra("pac");
txt.setText("name:"+chooseList.size());
```

```java
上面是按照原生的getXXX获取跳转参数
另一种得到参数方式
和上述方式一样添加
@Autowired
TestObj obj

ARouter.getInstance().inject(this);
```

- 在需要得到参数的Module里面定义,可完全copy过去,表示采用什么方式去转换参数,本文采用Gson,写完后在跳转便可得到参数,而且JsonServiceImpl会打印参数

  ```java
  @Router(path = "/service/json")
  public class JsonServiceImpl implements SerializationService{
      @Override
      public void init(Context context){}
      
      @Override
      public <T> T json2Object(String text,Class<T> clazz){
          reurn new Gson().fromJson(text,clazz);
      }
      
      @Override
      public String object2Json(Object instance){
          return new Gson().toJson(instance);
      }
  }
  ```

  