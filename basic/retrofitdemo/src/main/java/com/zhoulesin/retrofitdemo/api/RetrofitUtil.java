package com.zhoulesin.retrofitdemo.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhoul on 2018/9/30.
 */

public class RetrofitUtil {

    private static final String BASE_URL = "http://api.apiopen.top/";
//    private static Retrofit retrofit;
    private static PoetryInterface retrofitInterface;

    private static MusicInterface musicInterface;

    private static VideoInterface videoInterface;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(PoetryInterface.class);
        musicInterface = retrofit.create(MusicInterface.class);
        videoInterface = retrofit.create(VideoInterface.class);
    }

    public static PoetryInterface getPoetryInterface(){
        return retrofitInterface;
    }

    public static MusicInterface getMusicInterface(){
        return musicInterface;
    }

    public static VideoInterface getVideoInterface(){
        return videoInterface;
    }
}
