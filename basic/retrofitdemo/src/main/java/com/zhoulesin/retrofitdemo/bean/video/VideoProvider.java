package com.zhoulesin.retrofitdemo.bean.video;

/**
 * Created by zhoul on 2018/10/8.
 */

public class VideoProvider {
    private String name;
    private String icon;
    private String alias;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public VideoProvider(String name, String icon, String alias) {

        this.name = name;
        this.icon = icon;
        this.alias = alias;
    }

    public VideoProvider() {

    }
}
