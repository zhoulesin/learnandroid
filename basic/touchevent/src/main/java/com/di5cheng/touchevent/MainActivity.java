package com.di5cheng.touchevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "AAAActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }
//        Log.d(TAG, "dispatchTouchEvent: 做个淘宝");
//        Log.d(TAG, "dispatchTouchEvent: 按钮改亮" + ev.getAction());
        Log.d(TAG, "dispatchTouchEvent: app做的怎么样了");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.d(TAG, "onTouchEvent: 这都做不聊????");
//        Log.d(TAG, "onTouchEvent: 还行");
        Log.d(TAG, "onTouchEvent: 1111");
        return super.onTouchEvent(event);
    }
}
