package com.zhoulesin.retrofitdemo.bean.music;

import java.util.List;

/**
 * Created by zhoul on 2018/9/30.
 */

public class MusicBroadcastDetail {
    private String channel;
    private String count;
    private String ch_name;
    private String artistid;
    private String avatar;
    private List<Song> songlist;
    private String channelid;

    public MusicBroadcastDetail() {
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getArtistid() {
        return artistid;
    }

    public void setArtistid(String artistid) {
        this.artistid = artistid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Song> getSonglist() {
        return songlist;
    }

    public void setSonglist(List<Song> songlist) {
        this.songlist = songlist;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getCh_name() {
        return ch_name;
    }

    public void setCh_name(String ch_name) {
        this.ch_name = ch_name;
    }

    public MusicBroadcastDetail(String channel, String count, String ch_name, String artistid, String avatar, List<Song> songlist, String channelid) {
        this.channel = channel;
        this.count = count;
        this.ch_name = ch_name;
        this.artistid = artistid;
        this.avatar = avatar;
        this.songlist = songlist;
        this.channelid = channelid;
    }




}
