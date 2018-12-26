package com.di5cheng.customview.view1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by zhoul on 2018/10/30.
 */

public class PieView extends View {

    public static final String TAG = PieView.class.getSimpleName();
    private float mStartAngle = 0f;
    private List<PieData> mData;
    private int mWidth;
    private int mHeight;
    private Paint mPaint;

    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5f);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData == null) {
            return;
        }

        canvas.translate(mWidth / 2, mHeight / 2);
        float currentAngle = mStartAngle;
        float totalCount = 0;
        for (PieData pieData : mData) {
            totalCount += pieData.getCount();
        }
        RectF rectF = new RectF(-200, -200, 200, 200);
        Log.d(TAG, "onDraw: " + totalCount);
        for (PieData pieData : mData) {
            mPaint.setColor(pieData.getColor());
            float sweepAngle = pieData.getCount() / totalCount * 360;
            canvas.drawArc(rectF, currentAngle, sweepAngle, true, mPaint);
            currentAngle += sweepAngle;
        }
    }

    public void setStartAngle(float startAngle) {
        this.mStartAngle = startAngle;
        invalidate();
    }

    public void setData(List<PieData> data) {
        this.mData = data;
        invalidate();
    }

}
