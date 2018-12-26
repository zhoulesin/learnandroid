package com.di5cheng.customview.BezierView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhoul on 2018/10/31.
 *
 * 二阶贝塞尔曲线实现
 */

public class BezierView extends View {
    private int mWidth;
    private int mHeight;
    private float x;
    private float y;
    private Paint mPaint = new Paint();
    private Paint mPaint2 = new Paint();

    public BezierView(Context context) {
        super(context);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5f);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint2.setColor(Color.RED);
        mPaint2.setStrokeWidth(5f);
        mPaint2.setStyle(Paint.Style.STROKE);

    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        x = mWidth / 2;
        y = mHeight / 2 - 400;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        float v = x - mWidth / 2;
        float v1 = y - mHeight / 2;

        Path path = new Path();
        path.moveTo(-400, 0);
        path.lineTo(v, v1);
        path.lineTo(400, 0);

        canvas.drawPath(path, mPaint);

        Path p = new Path();
        p.moveTo(-400, 0);
        p.quadTo(v, v1, 400, 0);

        canvas.drawPath(p, mPaint2);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        invalidate();
        return true;
    }
}
