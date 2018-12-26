package com.zhoulesin.arouterdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/jump/bean")
public class BeanActivity extends AppCompatActivity {

    @Autowired
    JavaBean bean;

    @Autowired(name = "test")
    JavaBean aaaa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bean);

        ARouter.getInstance().inject(this);

        ((TextView) findViewById(R.id.tv)).setText(bean.getName());

        ((TextView) findViewById(R.id.tv2)).setText(aaaa.getName());

    }
}