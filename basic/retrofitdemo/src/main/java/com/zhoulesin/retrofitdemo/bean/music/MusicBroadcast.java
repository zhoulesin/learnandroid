package com.zhoulesin.retrofitdemo.bean.music;

import java.util.List;

/**
 * Created by zhoul on 2018/9/30.
 */

public class MusicBroadcast {

    private String title;
    private int cid;
    private List<Channel> channellist;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public List<Channel> getChannellist() {
        return channellist;
    }

    public void setChannellist(List<Channel> channellist) {
        this.channellist = channellist;
    }

    public MusicBroadcast(String title, int cid, List<Channel> channellist) {

        this.title = title;
        this.cid = cid;
        this.channellist = channellist;
    }

    public MusicBroadcast() {

    }

}
