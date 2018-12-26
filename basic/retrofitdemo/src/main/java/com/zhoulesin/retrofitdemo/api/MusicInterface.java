package com.zhoulesin.retrofitdemo.api;

import com.zhoulesin.retrofitdemo.bean.music.Music;
import com.zhoulesin.retrofitdemo.bean.music.MusicBroadcast;
import com.zhoulesin.retrofitdemo.bean.music.MusicBroadcastDetail;
import com.zhoulesin.retrofitdemo.bean.music.MusicRanking;
import com.zhoulesin.retrofitdemo.bean.music.MusicRankingSong;
import com.zhoulesin.retrofitdemo.bean.RetroResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhoul on 2018/9/30.
 */

public interface MusicInterface {

    /**
     * 搜索音乐
     * @return
     */
    @GET("searchMusic")
    Call<RetroResult<List<Music>>> reqMusic(@Query("name") String name);

    /**
     * 获取音乐电台列表
     *
     * @return
     */
    @GET("musicBroadcasting")
    Call<RetroResult<List<MusicBroadcast>>> getMusicBroadcastList();

    /**
     * 获取电台详情
     *
     * @param channelname
     * @return
     */
    @GET("musicBroadcastingDetails")
    Call<RetroResult<MusicBroadcastDetail>> getMusicBroadcastDetail(@Query("channelname") String channelname);


    /**
     * 音乐详情
     * todo 接口不明
     * @return
     */
    @GET("musicDetails")
    Call<RetroResult<Music>> getMusicDetail();

    /**
     * 获取排行榜列表
     * @return
     */
    @GET("musicRankings")
    Call<RetroResult<List<MusicRanking>>> getMusicRankings();

    /**
     * 获取排行榜详情
     * @param type
     * @return
     */
    @GET("musicRankingsDetails")
    Call<RetroResult<List<MusicRankingSong>>> getMusicRankingsDetails(@Query("type") int type);


}
