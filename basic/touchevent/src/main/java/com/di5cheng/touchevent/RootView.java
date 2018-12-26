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

public class RootView extends RelativeLayout{

    public static final String TAG = "AAArootView";

    public RootView(Context context) {
        super(context);
    }

    public RootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.d(TAG, "dispatchTouchEvent: 老板说做个淘宝");
//        Log.d(TAG, "dispatchTouchEvent: 老板说按钮改亮");
        Log.d(TAG, "dispatchTouchEvent: 老板在问项目进度");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        Log.d(TAG, "onInterceptTouchEvent: 反正不是我做");
//        Log.d(TAG, "onInterceptTouchEvent: 反正不是我做");
        Log.d(TAG, "onInterceptTouchEvent: 我不懂");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.d(TAG, "onTouchEvent: 技术说做不了");
//        Log.d(TAG, "onTouchEvent: 技术改好了");
        Log.d(TAG, "onTouchEvent: sfdf");
        return super.onTouchEvent(event);
    }

}
