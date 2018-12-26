package com.di5cheng.customview.base;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by zhoul on 2018/10/30.
 */

public class CustomViewGroup extends ViewGroup {
    public static final String TAG = CustomViewGroup.class.getSimpleName();
    private Context mContext;


    public CustomViewGroup(Context context) {
        super(context);
    }

    //1.初始化
    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        Log.d(TAG, "CustomViewGroup: ");
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //2.测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: ");
    }

    //3.确定控件大小
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged: ");
    }

    //4.布局子view
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "onLayout: ");
    }

    //5.具体绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");
        CustomView customView = new CustomView(mContext);
        this.addView(customView);
    }

    //6.自定义方法
    public void bb() {
        Log.d(TAG, "bb: ");
    }
}
