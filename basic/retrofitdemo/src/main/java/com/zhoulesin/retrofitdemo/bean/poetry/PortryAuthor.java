package com.zhoulesin.retrofitdemo.bean.poetry;

/**
 * Created by zhoul on 2018/9/30.
 */

public class PortryAuthor {
    private String name;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public PortryAuthor() {

    }

    public PortryAuthor(String name, String desc) {

        this.name = name;
        this.desc = desc;
    }
}
