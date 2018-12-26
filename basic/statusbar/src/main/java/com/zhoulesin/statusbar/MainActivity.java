package com.zhoulesin.statusbar;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActivityUtil.setFitsSystemWindows(this,true);


//        View statusBarView = findViewById(R.id.statusBarView);
//        ViewGroup.LayoutParams layoutParams = statusBarView.getLayoutParams();
//        layoutParams.height = ActivityUtil.getStatusBarHeight(this);

//        ActivityUtil.addStatusBarViewWithColor(this, Color.BLUE);

//        ActivityUtil.addPaddingAndStatusBarViewWithColor(this,Color.GREEN);

        init();
    }

    /**
     * ①xml中设置fitSystemWindows
     * ②代码设置setFitSystemWindows
     *
     *
     * ③xml中添加View占位statusBarView，代码设置高度
     * ④代码中直接添加statusBarView
     *
     * ⑤代码中设置paddingTop并添加占位状态栏
     */


    public void init(){
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
    }

}
