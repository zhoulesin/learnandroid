package com.di5cheng.customview.view1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.di5cheng.customview.MyQuickAdapter;
import com.di5cheng.customview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoul on 2018/10/30.
 * <p>
 * todo 一个activity,显示不同的自定义view
 */

public class View1Activity extends AppCompatActivity {
    private RecyclerView rv;
    private List<String> mData = new ArrayList<>();
    private Class[] mClz = new Class[]{PointActivity.class, LineActivity.class,
            RectActivity.class, RoundRectActivity.class, OvalActivity.class, CircleActivity.class,
            ArcActivity.class,PieActivity.class};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view1);
        initData();
        rv = ((RecyclerView) findViewById(R.id.rv));
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyQuickAdapter(android.R.layout.simple_list_item_1, mData, mClz));
    }

    private void initData() {
        mData.add("point");
        mData.add("line");
        mData.add("rect");
        mData.add("roundrect");
        mData.add("oval");
        mData.add("circle");
        mData.add("arc");
        mData.add("pie");
    }
}
