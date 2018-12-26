package com.di5cheng.customview.checkbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.di5cheng.customview.R;

/**
 * Created by zhoul on 2018/10/30.
 */

public class CheckBoxView extends View {
    public static final String TAG = CheckBoxView.class.getSimpleName();
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private Context mContext;
    private Rect src;
    private Rect dst;
    private Bitmap bitmap;
    private int width;
    private int height;
    private int left;

    public CheckBoxView(Context context) {
        super(context);
    }

    public CheckBoxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
    }

    public CheckBoxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);

        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.checkmark);
        width = bitmap.getWidth() / 13;
        height = bitmap.getHeight();
        left = -width;
        Log.d(TAG, "onDraw: " + bitmap + ",width:" + width + ",height:" + height);
        src = new Rect(left, 0, left + width, height);
        dst = new Rect(-width / 3, -height / 3, width / 3, height / 3);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawCircle(0, 0, 100, mPaint);

        canvas.drawBitmap(bitmap, src, dst, null);
    }

    private boolean flag = true;

    public void setCheck() {
        flag = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    post(new Runnable() {
                        @Override
                        public void run() {
                            src.offset(width, 0);
                            if (src.left < bitmap.getWidth()) {
                                invalidate();
                            } else {
                                src.offset(-width, 0);
                                flag = false;
                            }
                        }
                    });
                }
            }
        }).start();
    }

    public void setUncheck() {
        flag = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    post(new Runnable() {
                        @Override
                        public void run() {
                            src.offset(-width, 0);
                            if (src.left >= -width) {
                                invalidate();
                            } else {
                                src.offset(width, 0);
                                flag = false;
                            }
                        }
                    });
                }
            }
        }).start();
    }
}
