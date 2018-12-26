package com.di5cheng.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by zhoul on 2018/12/11.
 */

public class ViewGroup1 extends RelativeLayout {

    public static final String TAG = "AAAViewGroup1";

    public ViewGroup1(Context context) {
        super(context);
    }

    public ViewGroup1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.d(TAG, "dispatchTouchEvent: 经理说做个淘宝");
//        Log.d(TAG, "dispatchTouchEvent: 经理说改个按钮");
        Log.d(TAG, "dispatchTouchEvent: 项目进度");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        Log.d(TAG, "onInterceptTouchEvent: 不是我做");
//        Log.d(TAG, "onInterceptTouchEvent: 不是我做");
        Log.d(TAG, "onInterceptTouchEvent: 进度我知道");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.d(TAG, "onTouchEvent: 开发说做不了");
//        Log.d(TAG, "onTouchEvent: 开发说改好了");
        Log.d(TAG, "onTouchEvent: 马上搞完了");
        return true;
    }
}
