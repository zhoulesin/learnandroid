package com.di5cheng.customview.checkbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.di5cheng.customview.R;

/**
 * Created by zhou on 2018/10/30.
 */

public class CheckBoxActivity extends AppCompatActivity {

    private CheckBoxView cbv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);
        cbv = ((CheckBoxView) findViewById(R.id.cbv));
    }

    public void btnUncheck(View view) {
        cbv.setUncheck();
    }

    public void btnCheck(View view) {
        cbv.setCheck();
    }
}
