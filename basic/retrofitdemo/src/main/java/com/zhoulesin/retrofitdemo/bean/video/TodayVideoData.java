package com.zhoulesin.retrofitdemo.bean.video;

/**
 * Created by zhoul on 2018/10/8.
 */

public class TodayVideoData {
    private String dataType;
    private VideoHeader header;
    private VideoContent content;
    private String adTrack;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public VideoHeader getHeader() {
        return header;
    }

    public void setHeader(VideoHeader header) {
        this.header = header;
    }

    public VideoContent getContent() {
        return content;
    }

    public void setContent(VideoContent content) {
        this.content = content;
    }

    public String getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(String adTrack) {
        this.adTrack = adTrack;
    }

    public TodayVideoData(String dataType, VideoHeader header, VideoContent content, String adTrack) {

        this.dataType = dataType;
        this.header = header;
        this.content = content;
        this.adTrack = adTrack;
    }

    public TodayVideoData() {

    }
}
