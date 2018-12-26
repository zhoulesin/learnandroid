package com.zhoulesin.retrofitdemo.bean.music;

/**
 * Created by zhoul on 2018/9/30.
 */

public class Music {
    /**
     *
     * {
     "author": "群星",
     "link": "http://music.163.com/#/song?id=5232294",
     "pic": "http://p2.music.126.net/6BWV-cXn6Eec6NqCEoVzzw==/68169720935942.jpg?param=300x300",
     "type": "netease",
     "title": "菊花台",
     "lrc": "[00:24.20]MUSIC\n[00:39.09]你的泪光 柔弱中带伤\n[00:45.59]惨白的月弯弯勾住过往\n[00:52.92]夜太漫长 凝结成了霜\n[00:59.71]是谁在阁楼上冰冷地绝望\n[01:06.83]雨轻轻弹 朱红色的窗\n[01:13.87]我一生在纸上被风吹乱\n[01:20.93]梦在远方 化成一缕香\n[01:27.87]随风飘散你的模样\n[01:33.91]菊花残 满地伤\n[01:40.58]你的笑容已泛黄\n[01:45.08]花落人断肠 我心事静静淌\n[01:51.20]北风乱 夜未央\n[01:54.77]你的影子剪不断\n[01:58.89]徒留我孤单　在湖面成双\n[02:19.00]\n[02:34.05]花已伤完　飘落了灿烂\n[02:41.00]凋谢的世道上命运不堪\n[02:47.81]愁莫渡江　秋心拆两半\n[02:54.72]怕你上不了岸 一辈子摇晃\n[03:01.97]谁的江山 马蹄声狂乱\n[03:08.98]我一身的戎装 呼啸沧桑\n[03:15.96]天微微亮 你轻声的叹\n[03:22.80]一夜惆怅如此委婉\n[03:31.15]菊花残满地伤 你的笑容已泛黄\n[03:40.04]花落人断肠 我心事静静淌\n[03:46.42]被风乱 夜未央\n[03:49.45]你的影子剪不断\n[03:53.92]徒留我孤单　在湖面成双\n[04:03.83]菊花残满地伤 你的笑容已泛黄\n[04:11.08]花落人断肠 我心事静静淌\n[04:17.64]北风乱 夜未央\n[04:21.28]你的影子剪不断\n[04:27.52]徒留我孤单　在湖面成双\n",
     "songid": 5232294,
     "url": "http://music.163.com/song/media/outer/url?id=5232294.mp3"
     }
     *
     *
     */

    private String author;
    private String link;
    private String pic;
    private String type;
    private String title;
    private String lrc;
    private int songid;
    private String url;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Music(String author, String link, String pic, String type, String title, String lrc, int songid, String url) {

        this.author = author;
        this.link = link;
        this.pic = pic;
        this.type = type;
        this.title = title;
        this.lrc = lrc;
        this.songid = songid;
        this.url = url;
    }

    public Music() {

    }
}
