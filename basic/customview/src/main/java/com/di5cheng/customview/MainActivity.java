package com.di5cheng.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.di5cheng.customview.base.BaseActivity;
import com.di5cheng.customview.view1.View1Activity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private List<String> mData = new ArrayList<>();
    private Class[] mClz = new Class[]{BaseActivity.class, View1Activity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        rv = ((RecyclerView) findViewById(R.id.rv));
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyQuickAdapter(android.R.layout.simple_list_item_1, mData, mClz));

    }

    private void initData() {
        mData.add("base");
        mData.add("view1");
    }

}
