package com.di5cheng.customview.BezierView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.di5cheng.customview.R;

/**
 * Created by zhoul on 2018/10/31.
 */

public class BezierActivity extends AppCompatActivity {

    private Bezier3View bv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);
        bv = ((Bezier3View) findViewById(R.id.bv));
    }

    public void control1(View view) {
        bv.chooseControl1(true);
    }

    public void control2(View view) {
        bv.chooseControl1(false);
    }
}
