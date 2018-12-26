package com.di5cheng.customview.view1;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by zhoul on 2018/10/30.
 */

public class PointView extends BaseView {


    public PointView(Context context) {
        super(context);
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(100, 100, mPaint);

        canvas.drawPoints(new float[]{
                200, 200,
                200, 300
        }, mPaint);
    }
}
