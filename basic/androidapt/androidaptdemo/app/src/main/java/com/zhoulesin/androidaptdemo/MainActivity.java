package com.zhoulesin.androidaptdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.zhoulesin.apt_annotation2.BindView;
import com.zhoulesin.apt_library.BindViewTools;
import com.zhoulesin.androidaptdemo.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_main)
    public TextView textView;
    @BindView(R.id.btn_main)
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BindViewTools.bind(this);

        textView.setText("bind tv success");
        button.setText("bind button success");


    }
}
