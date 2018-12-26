package com.zhoulesin.retrofitdemo.bean.music;

/**
 * Created by zhoul on 2018/10/8.
 */
public class Channel {
    /**
     * "thumb": "http://hiphotos.qianqian.com/ting/pic/item/d50735fae6cd7b89e7e067e40c2442a7d8330ecc.jpg",
     "name": "粤语",
     "cate_name": "yuzhong",
     "cate_sname": "语种频道",
     "ch_name": "public_yuzhong_yueyu",
     "value": 19,
     "channelid": "18"
     "artistid": "77",
     "avatar": "http://qukufile2.qianqian.com/data2/pic/246709988/246709988.jpg@s_0,w_120"
     */
    private String name;
    private String artistid;
    private String avatar;
    private String thumb;
    private String cate_name;
    private String cate_sname;
    private String ch_name;
    private int value;
    private String channelid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Channel(String name, String artistid, String avatar, String thumb, String cate_name, String cate_sname, String ch_name, int value, String channelid) {
        this.name = name;
        this.artistid = artistid;
        this.avatar = avatar;
        this.thumb = thumb;
        this.cate_name = cate_name;
        this.cate_sname = cate_sname;
        this.ch_name = ch_name;
        this.value = value;
        this.channelid = channelid;
    }

    public String getThumb() {

        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public String getCate_sname() {
        return cate_sname;
    }

    public void setCate_sname(String cate_sname) {
        this.cate_sname = cate_sname;
    }

    public String getCh_name() {
        return ch_name;
    }

    public void setCh_name(String ch_name) {
        this.ch_name = ch_name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public Channel() {

    }
}
