package com.zhoulesin.retrofitdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhoul on 2018/9/30.
 */

public class App extends Application {
    private static Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        this.mContext = base;
    }

    public static Context getContext() {
        return mContext;
    }
}
