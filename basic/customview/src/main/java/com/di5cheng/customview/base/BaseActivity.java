package com.di5cheng.customview.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.di5cheng.customview.R;

/**
 * Created by zhoul on 2018/10/30.
 * <p>
 * 查看log 的打印
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        CustomViewGroup customViewGroup = new CustomViewGroup(this);
        ((LinearLayout) findViewById(R.id.ll)).addView(customViewGroup);
    }
}
