package com.zhoulesin.retrofitdemo.api;

import com.zhoulesin.retrofitdemo.bean.poetry.AncientPoetry;
import com.zhoulesin.retrofitdemo.bean.poetry.AncientPortry2;
import com.zhoulesin.retrofitdemo.bean.poetry.PortryAuthor;
import com.zhoulesin.retrofitdemo.bean.RetroResult;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by zhoul on 2018/9/30.
 */

public interface PoetryInterface {

    /**
     * 随机单句诗词
     * Retrofit最基本
     */
    @GET("singlePoetry")
    Call<RetroResult<AncientPoetry>> getRandomAncientPoetry();

    /**
     * 随机一首诗词
     *
     * @return Retrofit最基本
     */
    @GET("recommendPoetry")
    Call<RetroResult<AncientPortry2>> getRandomAncientPeotry2();

    /**
     * (根据标题(精确搜索))搜索诗词
     *
     * @param name
     * @return Retrofit 请求参数的replacement block
     */
    @GET("searchPoetry")
    Call<RetroResult<List<AncientPortry2>>> searchPortry(@Query("name") String name);

    //下面的使用是***错误***的，@Path只能用在请求路径上面，不能用在请求参数上面
//    @GET("searchPoetry?name={name}")
//    Call<RetroResult<List<AncientPortry2>>> searchPortry(@Path("name") String name);

    /**
     * 根据姓名搜索诗人
     *
     * @param name
     * @return
     * Retrofit 请求参数的 @Query 注解
     */
    @GET("searchAuthors")
    Call<RetroResult<List<PortryAuthor>>> searchAuthors(@Query("name") String name);


    /**
     * 模糊搜索
     * @param name
     * @return
     * Retrofit 请求参数的 @Query 注解
     */
    @GET("likePoetry")
    Call<RetroResult<List<AncientPortry2>>> likePoetry(@Query("name") String name);

    /**
     * 获取宋朝诗词
     * @param page 第一页
     * @param count 每页多少个
     * @return
     * Retrofit 请求参数的多个 @Query 注解
     */
    @GET("getSongPoetry")
    Call<RetroResult<List<AncientPortry2>>> getSongPoetry(@Query("page") int page,@Query("count") int count);

    /**
     * 获取唐朝诗词
     * @param pageAndCount
     * @return
     * Retrofit 请求参数的 @QueryMap 注解
     */
    @GET("getTangPoetry")
    Call<RetroResult<List<AncientPortry2>>> getTangPoetry(@QueryMap Map<String,String> pageAndCount);


}
