package com.di5cheng.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;


/**
 * Created by zhoul on 2018/11/9.
 */

public class MyApplication extends Application{
    public static final String TAG = MyApplication.class.getSimpleName();
    
    @Override
    public void onCreate() {
        super.onCreate();
        //程序创建
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //程序终止
        Log.d(TAG, "onTerminate: ");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //低内存
        Log.d(TAG, "onLowMemory: ");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        //内存清理
        //home键退出应用,长按menu键打开recent task都会执行
        Log.d(TAG, "onTrimMemory: ");
    }
}
