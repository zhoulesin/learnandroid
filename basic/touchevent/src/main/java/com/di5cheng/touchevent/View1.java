package com.di5cheng.touchevent;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhoul on 2018/12/11.
 */

public class View1 extends View {

    public static final String TAG = "AAAView1";

    public View1(Context context) {
        super(context);
    }

    public View1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//        Log.d(TAG, "dispatchTouchEvent: 做淘宝??");
//        Log.d(TAG, "dispatchTouchEvent: 改按钮??");
        Log.d(TAG, "dispatchTouchEvent: 项目进度?????111111");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.d(TAG, "onTouchEvent: 做利马");
//        Log.d(TAG, "onTouchEvent: 改好了");
        Log.d(TAG, "onTouchEvent: 早搞完了,别问我");
        return super.onTouchEvent(event);
    }
}
