package com.zhoulesin.retrofitdemo.bean.video;

/**
 * Created by zhoul on 2018/10/8.
 */

public class VideoCover {
    private String feed;
    private String detail;
    private String sharing;
    private String blurred;
    private String homepage;

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSharing() {
        return sharing;
    }

    public void setSharing(String sharing) {
        this.sharing = sharing;
    }

    public String getBlurred() {
        return blurred;
    }

    public void setBlurred(String blurred) {
        this.blurred = blurred;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public VideoCover(String feed, String detail, String sharing, String blurred, String homepage) {

        this.feed = feed;
        this.detail = detail;
        this.sharing = sharing;
        this.blurred = blurred;
        this.homepage = homepage;
    }

    public VideoCover() {

    }
}
