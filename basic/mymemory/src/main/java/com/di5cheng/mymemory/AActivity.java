package com.di5cheng.mymemory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhoul on 2018/11/19.
 */

public class AActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
        for (int i=0;i<5000;i++){
            new SonMessage();
            new Message();
        }
    }
}
