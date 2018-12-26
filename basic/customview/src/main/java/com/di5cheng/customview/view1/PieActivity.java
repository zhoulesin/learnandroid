package com.di5cheng.customview.view1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.di5cheng.customview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoul on 2018/10/30.
 */

public class PieActivity extends AppCompatActivity {

    private List<PieData> pieDataList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        initData();

        PieView pie = (PieView) findViewById(R.id.pie);
        pie.setData(pieDataList);
    }

    private void initData() {
        pieDataList.add(new MyData("1", 105f, Color.BLACK));
        pieDataList.add(new MyData("2", 105f, Color.GRAY));
        pieDataList.add(new MyData("3", 205f, Color.YELLOW));
        pieDataList.add(new MyData("4", 230f, Color.RED));
        pieDataList.add(new MyData("5", 231f, Color.GREEN));
        pieDataList.add(new MyData("6", 131f, Color.LTGRAY));
        pieDataList.add(new MyData("7", 101f, Color.BLUE));
    }
}
