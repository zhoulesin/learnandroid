# ARouter之源码分析

https://www.jianshu.com/p/5b35309e9bb2

ARouter是通过APT生成代码在框架内部进行操作.

## 注解分析

@Route

```java
@Target({ElementType.TYEP})
@Retention({RetentinoPolicy.CLASS})
public @interface Route{
    String path();
    String group() default "";
    String name() default "undefined";
    int extras() default Integer.MIN_VALUE;
    int priority() default -1;
}
```

