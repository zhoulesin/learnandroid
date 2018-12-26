package com.di5cheng.customview.view1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by zhoul on 2018/10/30.
 */

public class LineView extends BaseView {

    public LineView(Context context) {
        super(context);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(100, 100, 200, 300, mPaint);
        canvas.drawLines(new float[]{
                200,100,400,400,
                300,300,600,600
        }, mPaint);
    }
}
