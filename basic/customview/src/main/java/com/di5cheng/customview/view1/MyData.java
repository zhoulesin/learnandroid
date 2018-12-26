package com.di5cheng.customview.view1;

/**
 * Created by zhoul on 2018/10/30.
 */

public class MyData implements PieData {

    private String desc;
    private float count;
    private int color;

    public MyData(String desc, float count, int color) {
        this.desc = desc;
        this.count = count;
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public float getCount() {
        return count;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
