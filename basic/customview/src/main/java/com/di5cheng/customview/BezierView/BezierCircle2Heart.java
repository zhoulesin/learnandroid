package com.di5cheng.customview.BezierView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhoul on 2018/10/31.
 */

public class BezierCircle2Heart extends View {
    public static final float C = 0.551915024494f;

    private Paint mPaint;
    private int mCenterX, mCenterY;
    private Point mCenter = new Point(0, 0);
    private float mCircleRadius = 200;
    private float mDifferent = mCircleRadius * C; //圆控制点和数据点的差值

    private float[] mData = new float[8];
    private float[] mCtrl = new float[16];

    public BezierCircle2Heart(Context context) {
        super(context);
    }

    public BezierCircle2Heart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5f);
        mPaint.setStyle(Paint.Style.STROKE);

        mData[0] = 0;
        mData[1] = mCircleRadius;

        mData[2] = mCircleRadius;
        mData[3] = 0;

        mData[4] = 0;
        mData[5] = -mCircleRadius;

        mData[6] = -mCircleRadius;
        mData[7] = 0;

        mCtrl[0] = mData[0]

    }

    public BezierCircle2Heart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
