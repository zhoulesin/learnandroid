# Android状态栏

https://www.jianshu.com/p/dc20e98b9a90

测试手机 华为荣耀8 7.0

## Android状态栏的现状
- API19(Android4.4之前),默认的
- API21(Android5.0之后),会默认覆盖一层半透明遮罩
- API19-21 可以操作

## 沉浸式状态栏
### 方法一  通过设置Theme主题状态栏透明
使用三份style文件，即默认的values(不设置状态栏透明),values-v19,values-21(需解决半透明遮罩的问题)

```xml
<!-- values -->
<style name="TranslucentTheme" parent="AppTheme">
</style>

<!-- values-v19
    v19开始有android:windowTranslucentStatus这个属性
 -->
<style name="TranslucentTheme" parent="Theme.AppCompat.Light.NoActionBar">
    <item name="android:windowTranslucentStatus">true</item>
    <item name="android:windowTranslucentNavigation">true</item>
</style>

<!-- values-v21
    5.0以上提供了 setStatusBarColor() 方法设置状态栏属性
 -->
<style name="TranslucentTheme" parent="Theme.AppCompat.Light.NoActionBar">
    <item name="android:windowTranslucentStatus">false</item>
    <item name="android:windowTranslucentNavigation">true</item>
    
    <item name="android:statusBarColor">@android:color/transparent</item>
</style>

```

#### 预留状态栏的高度
- 设置fitsSystemWindows属性
当设置该属性为true时，会在屏幕最上方预留出状态栏的高度padding
在布局最外层设置android:fitsSystemWindows="true"
也可通过代码设置

```java

public static void setFitsSystemWindows(Activity activity,boolean value){
    ViewGroup contentFrameLayout = (ViewGroup)activity.findViewbyId(android.R.id.content);
    View parentView = contentFrameLayout.getChildAt(0);
    if(parentView != null && Build.VERSION.SDK_INT >= 14){
        parentView.setFitsSystemWindows(value);
    }
}

```

通过该设置保留状态栏高度的paddingTop后，在设置状态栏的颜色。就可以达到设想的效果。
要设置状态栏的颜色，只能通过设置最外层布局的背景色来实现，那么，整个布局也都变了颜色，只能再在下方的布局内容里另外设置背景，这样就
过度绘制了。而且设置了fitSystemWindows属性的页面，在点击Edittext调出软键盘时，整个视图都会被顶上去。

#### 布局里添加占位状态栏
- 在跟布局添加一个占位状态栏
```xml
<View 
android:id="@+id/statusBarView"
android:background="@color/blue"
android:layout_width="match_parent"
android:layout_height="wrap_content"/>
```

通过反射获取状态栏的高度

```java

public int getStatusBarHeight(){
    int result = 0;
    int resourceId = getResources().getIdentifier("status_bar_height","dimen","android");
    if(resourceId > 0){
        result = getResources.getDimensionPixelSize(resourceId);
    }
    return result;
}
```

设置展位试图高度

```java
    View statusBar = findViewbyId(R.id.statusBarView);
    ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
    layoutParams.height = getStatusBarHeight();
```

- 同样也可以在代码中添加

```java
    private void addStatusViewWithColor(Activity activity,int color){
        ViewGroup contentView = activity.findViewById(android.R.id.content);
        View statusBarView = new View(activity);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            getStatusBarHeight(activity));
        statusBarView.setBackgroundColor(color);
        contentView.addView(statusBarView,lp);
    }
```

#### 代码中设置paddingTop并添加占位状态栏
手动给根试图设置一个paddingTop，高度为状态栏高度，相当于手动实现了fitsSystemWindows的效果，然后再再根视图加入一个
占位视图，其高度也设置为状态栏高度
```java

ViewGroup rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
rootView.setPadding(0,getStatusBarHeight(activity),0,0);
if(Build.VERSION.SDK_INT >= BUILD.VERSION_CODES.LOLLIPOP){
    //5.0以上直接设置状态栏颜色
    activity.getWindow().setStatusBarColor(color);
}else{
    //跟布局添加占位栏
    ViewGroup decorView = activity.getWindow().getDecorView();
    View statusBarView = new View(activity);
    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        getStatusBarHeight(actiivty));
    statusBarView.setBackgroundColor(color);
    decorView.addView(statusBarView,lp);
    
}


```

### 在代码中设置
通过在代码中设置，实现方法Theme主题样式里设置的属性
```java
if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
    int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
    int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.flags |= flagTranslucentNavigation;
        window.setAttributes(attributes);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }else{
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.flags |= flagTranslucentStatus | flagTranslucentNavigation;
        window.setAttributes(attributes);
    }
}
```

- 方案一会导致一个问题，就是导航栏颜色变灰。在5.x以下导航栏透明时可以生效的，但5.x以上导航栏会变灰色(正常情况下我们期望导航栏保持默认颜色黑色不变)，
但设置了FLAG_TRANSLUCENT_NAVIGATION,所以即使代码中设置getWindow().setNavigationBarColor(Color.BLACK);也是不起作用的。但如果不设置
该FLAG，状态栏又无法被设置为隐藏和透明。

