package com.zhoulesin.retrofitdemo.bean.music;

/**
 * Created by zhoul on 2018/10/8.
 */
public class Song{
    private String all_rate;
    private int charge;
    private int method;
    private String artist;
    private String thumb;
    private String all_artist_id;
    private String resource_type;
    private int havehigh;
    private String title;
    private String songid;
    private String artist_id;
    private int flow;

    public String getAll_rate() {
        return all_rate;
    }

    public void setAll_rate(String all_rate) {
        this.all_rate = all_rate;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getAll_artist_id() {
        return all_artist_id;
    }

    public void setAll_artist_id(String all_artist_id) {
        this.all_artist_id = all_artist_id;
    }

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public int getHavehigh() {
        return havehigh;
    }

    public void setHavehigh(int havehigh) {
        this.havehigh = havehigh;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public Song(String all_rate, int charge, int method, String artist, String thumb, String all_artist_id, String resource_type, int havehigh, String title, String songid, String artist_id, int flow) {

        this.all_rate = all_rate;
        this.charge = charge;
        this.method = method;
        this.artist = artist;
        this.thumb = thumb;
        this.all_artist_id = all_artist_id;
        this.resource_type = resource_type;
        this.havehigh = havehigh;
        this.title = title;
        this.songid = songid;
        this.artist_id = artist_id;
        this.flow = flow;
    }

    public Song() {

    }
}
