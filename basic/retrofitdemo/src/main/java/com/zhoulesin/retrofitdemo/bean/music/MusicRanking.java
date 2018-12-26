package com.zhoulesin.retrofitdemo.bean.music;

import java.util.List;

/**
 * Created by zhoul on 2018/9/30.
 */

public class MusicRanking {
    /*{
            "pic_s210": "http://business.cdn.qianqian.com/qianqian/pic/bos_client_734232335ef76f5a05179797875817f3.jpg",
            "web_url": "",
            "pic_s444": "http://hiphotos.qianqian.com/ting/pic/item/c83d70cf3bc79f3d98ca8e36b8a1cd11728b2988.jpg",
            "name": "热歌榜",
            "count": 4,
            "comment": "该榜单是根据千千音乐平台歌曲每周播放量自动生成的数据榜单，统计范围为千千音乐平台上的全部歌曲，每日更新一次",
            "type": 2,
            "pic_s192": "http://business.cdn.qianqian.com/qianqian/pic/bos_client_1452f36a8dc430ccdb8f6e57be6df2ee.jpg",
            "content": [{
                "all_rate": "flac,320,128,224,96",
                "song_id": "601427388",
                "rank_change": "0",
                "biaoshi": "lossless",
                "author": "火箭少女101",
                "album_id": "601427384",
                "title": "卡路里（电影《西虹市首富》插曲）",
                "album_title": "卡路里（电影《西虹市首富》插曲）"
            }, {
                "all_rate": "96,128,224,320,flac",
                "song_id": "602870189",
                "rank_change": "0",
                "biaoshi": "lossless",
                "author": "许巍",
                "album_id": "602870186",
                "title": "我的爱（慕思《觉/醒》视频主题曲）",
                "album_title": "我的爱（慕思《觉/醒》视频主题曲）"
            }, {
                "all_rate": "96,128,224,320,flac",
                "song_id": "598740690",
                "rank_change": "0",
                "biaoshi": "lossless",
                "author": "张杰,张碧晨",
                "album_id": "598740686",
                "title": "只要平凡",
                "album_title": "只要平凡"
            }, {
                "all_rate": "96,128,224,320,flac",
                "song_id": "569080829",
                "rank_change": "0",
                "biaoshi": "first,lossless",
                "author": "王菲",
                "album_id": "569080827",
                "title": "无问西东",
                "album_title": "无问西东"
            }],
            "pic_s260": "http://hiphotos.qianqian.com/ting/pic/item/838ba61ea8d3fd1f1326c83c324e251f95ca5f8c.jpg"
    }*/
    private String pic_s210;
    private String web_url;
    private String pic_s444;
    private String name;
    private int count;
    private String comment;
    private int type;
    private String pic_s192;
    private List<MusicRankingSong> content;
    private String pic_s260;

    public MusicRanking(String pic_s210, String web_url, String pic_s444, String name, int count, String comment, int type, String pic_s192, List<MusicRankingSong> content, String pic_s260) {
        this.pic_s210 = pic_s210;
        this.web_url = web_url;
        this.pic_s444 = pic_s444;
        this.name = name;
        this.count = count;
        this.comment = comment;
        this.type = type;
        this.pic_s192 = pic_s192;
        this.content = content;
        this.pic_s260 = pic_s260;
    }

    public MusicRanking() {

    }

    public String getPic_s210() {

        return pic_s210;
    }

    public void setPic_s210(String pic_s210) {
        this.pic_s210 = pic_s210;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getPic_s444() {
        return pic_s444;
    }

    public void setPic_s444(String pic_s444) {
        this.pic_s444 = pic_s444;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPic_s192() {
        return pic_s192;
    }

    public void setPic_s192(String pic_s192) {
        this.pic_s192 = pic_s192;
    }

    public List<MusicRankingSong> getContent() {
        return content;
    }

    public void setContent(List<MusicRankingSong> content) {
        this.content = content;
    }

    public String getPic_s260() {
        return pic_s260;
    }

    public void setPic_s260(String pic_s260) {
        this.pic_s260 = pic_s260;
    }
}
