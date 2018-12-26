package com.zhoulesin.retrofitdemo.api;

import com.zhoulesin.retrofitdemo.bean.RetroResult;
import com.zhoulesin.retrofitdemo.bean.video.HomeTab;
import com.zhoulesin.retrofitdemo.bean.video.TodayVideo;
import com.zhoulesin.retrofitdemo.bean.video.VideoCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhoul on 2018/10/8.
 */

public interface VideoInterface {

    @GET("todayVideo")
    Call<RetroResult<List<TodayVideo>>> getTodayVideo();

    @GET("videoHomeTab")
    Call<RetroResult<List<HomeTab>>> getHomeTab();

    @GET("videoCategory")
    Call<RetroResult<VideoCategory>> getVideoCategory();

    @GET("videoCategoryDetails")
    Call<RetroResult<>> getVideoCategoryDetails(@Query("id") int id);
}
