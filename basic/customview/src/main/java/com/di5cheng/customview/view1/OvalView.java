package com.di5cheng.customview.view1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by zhoul on 2018/10/30.
 */

public class OvalView extends BaseView {
    public OvalView(Context context) {
        super(context);
    }

    public OvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(100,100,200,300,mPaint);

        RectF rectf = new RectF(300,300,400,600);
        canvas.drawOval(rectf,mPaint);
    }
}
