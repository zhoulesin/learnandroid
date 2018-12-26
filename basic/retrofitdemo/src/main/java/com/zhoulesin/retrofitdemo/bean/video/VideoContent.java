package com.zhoulesin.retrofitdemo.bean.video;

/**
 * Created by zhoul on 2018/10/8.
 */

public class VideoContent {
    private VideoContentData data;
    private int adIndex;
    private String tag;
    private int id;
    private String type;

    public VideoContentData getData() {
        return data;
    }

    public void setData(VideoContentData data) {
        this.data = data;
    }

    public int getAdIndex() {
        return adIndex;
    }

    public void setAdIndex(int adIndex) {
        this.adIndex = adIndex;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public VideoContent(VideoContentData data, int adIndex, String tag, int id, String type) {

        this.data = data;
        this.adIndex = adIndex;
        this.tag = tag;
        this.id = id;
        this.type = type;
    }

    public VideoContent() {

    }
}
