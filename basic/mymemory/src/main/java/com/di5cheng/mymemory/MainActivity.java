package com.di5cheng.mymemory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn1(View view) {
        startActivity(new Intent(this,AActivity.class));
    }

    public void btn2(View view) {
        startActivity(new Intent(this,BActivity.class));
    }
}
