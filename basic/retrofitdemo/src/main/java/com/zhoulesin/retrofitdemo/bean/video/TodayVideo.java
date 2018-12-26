package com.zhoulesin.retrofitdemo.bean.video;

/**
 * Created by zhoul on 2018/10/8.
 */

public class TodayVideo {
    private TodayVideoData data;
    private int adIndex;
    private String id;
    private String type;

    public TodayVideoData getData() {
        return data;
    }

    public void setData(TodayVideoData data) {
        this.data = data;
    }

    public int getAdIndex() {
        return adIndex;
    }

    public void setAdIndex(int adIndex) {
        this.adIndex = adIndex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TodayVideo() {

    }

    public TodayVideo(TodayVideoData data, int adIndex, String id, String type) {

        this.data = data;
        this.adIndex = adIndex;
        this.id = id;
        this.type = type;
    }
}
