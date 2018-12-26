package com.di5cheng.customview.BezierView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhoul on 2018/10/31.
 */

public class Bezier3View extends View {

    private Paint mPaint;
    private Point data1 = new Point();
    private Point data2 = new Point();
    private Point control1 = new Point();
    private Point control2 = new Point();
    private boolean currentControl1 = true;
    private int mWidth;
    private int mHeight;

    public Bezier3View(Context context) {
        super(context);
    }

    public Bezier3View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);

        data1.x = -300;
        data1.y = 0;

        data2.x = 300;
        data2.y = 0;
    }

    public Bezier3View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;

        control1.x = -300;
        control1.y = -300;

        control2.x = +300;
        control2.y = -300;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        Path path = new Path();
        path.moveTo(data1.x, data1.y);
        path.lineTo(control1.x, control1.y);
        path.lineTo(control2.x, control2.y);
        path.lineTo(data2.x, data2.y);

        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.RED);
        path.moveTo(data1.x, data1.y);
        path.cubicTo(control1.x, control1.y, control2.x, control2.y, data2.x, data2.y);

        canvas.drawPath(path, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (currentControl1) {
            control1.x = (int) event.getX() - mWidth / 2;
            control1.y = (int) event.getY() - mHeight / 2;
        } else {
            control2.x = (int) event.getX() - mWidth / 2;
            control2.y = (int) event.getY() - mHeight / 2;
        }
        invalidate();
        return true;
    }

    public void chooseControl1(boolean flag){
        this.currentControl1 = flag;
    }
}
