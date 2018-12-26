package com.zhoulesin.retrofitdemo.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhoulesin.retrofitdemo.R;
import com.zhoulesin.retrofitdemo.api.RetrofitUtil;
import com.zhoulesin.retrofitdemo.api.VideoInterface;
import com.zhoulesin.retrofitdemo.bean.RetroResult;
import com.zhoulesin.retrofitdemo.bean.music.Music;
import com.zhoulesin.retrofitdemo.bean.video.HomeTab;
import com.zhoulesin.retrofitdemo.bean.video.TodayVideo;
import com.zhoulesin.retrofitdemo.bean.video.TodayVideoData;
import com.zhoulesin.retrofitdemo.bean.video.VideoCategory;
import com.zhoulesin.retrofitdemo.bean.video.VideoContent;
import com.zhoulesin.retrofitdemo.bean.video.VideoContentData;
import com.zhoulesin.retrofitdemo.callback.BaseCallback;
import com.zhoulesin.retrofitdemo.util.ToastUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zhoul on 2018/10/8.
 */

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initViews();
    }
    private EditText et;
    private TextView tv;


    private void initViews() {
        et = ((EditText) findViewById(R.id.et));
        tv = ((TextView) findViewById(R.id.tv));
    }

    public void reqTodayVideo(View view) {
        VideoInterface videoInterface = RetrofitUtil.getVideoInterface();
        final Call<RetroResult<List<TodayVideo>>> todayVideo = videoInterface.getTodayVideo();
        todayVideo.enqueue(new BaseCallback<RetroResult<List<TodayVideo>>>() {
            @Override
            public void onResponse(Call<RetroResult<List<TodayVideo>>> call, Response<RetroResult<List<TodayVideo>>> response) {
                if (response.body().getCode() == 200) {
                    List<TodayVideo> result = response.body().getResult();
                    if (result != null && !result.isEmpty()) {
                        TodayVideo todayVideo1 = result.get((int) (Math.random() * result.size()));
                        List<VideoContentData.PlayInfo> playInfo = todayVideo1.getData().getContent().getData().getPlayInfo();
                        VideoContentData.PlayInfo playInfo1 = playInfo.get((int) (Math.random() * playInfo.size()));
                        tv.setText(playInfo1.getName() + playInfo1.getUrl());
                    } else {
                        ToastUtil.showToast(getString(R.string.search_empty));
                    }
                } else {
                    //请求失败
                    ToastUtil.showToast(getString(R.string.request_err));
                }
            }
        });
    }

    /**
     * 获取首页tab
     * @param view
     */
    public void getHomeTab(View view){
        VideoInterface videoInterface = RetrofitUtil.getVideoInterface();
        final Call<RetroResult<List<HomeTab>>> todayVideo = videoInterface.getHomeTab();
        todayVideo.enqueue(new BaseCallback<RetroResult<List<HomeTab>>>() {
            @Override
            public void onResponse(Call<RetroResult<List<HomeTab>>> call, Response<RetroResult<List<HomeTab>>> response) {
                if (response.body().getCode() == 200) {
                    List<HomeTab> result = response.body().getResult();
                    if (result != null && !result.isEmpty()) {
                        HomeTab homeTab = result.get((int) (Math.random() * result.size()));
                        tv.setText(homeTab.getName() + homeTab.getApiUrl());
                    } else {
                        ToastUtil.showToast(getString(R.string.search_empty));
                    }
                } else {
                    //请求失败
                    ToastUtil.showToast(getString(R.string.request_err));
                }
            }
        });
    }

    public void getVideoCategory(View view){
        VideoInterface videoInterface = RetrofitUtil.getVideoInterface();
        Call<RetroResult<VideoCategory>> videoCategory =
                videoInterface.getVideoCategory();
        videoCategory.enqueue(new BaseCallback<RetroResult<VideoCategory>>() {
            @Override
            public void onResponse(Call<RetroResult<VideoCategory>> call, Response<RetroResult<VideoCategory>> response) {
                if (response.body().getCode() == 200) {
                    VideoCategory result = response.body().getResult();
                    List<VideoCategory.Category> itemList = result.getItemList();
                    VideoCategory.Category category = itemList.get((int) (Math.random() * itemList.size()));
                    String description = category.getData().getDescription();
                    String actionUrl = category.getData().getActionUrl();
                    tv.setText(description +"---" +actionUrl);
                } else {
                    //请求失败
                    ToastUtil.showToast(getString(R.string.request_err));
                }
            }
        });
    }

    public void getVideoCategoryDetails(View view) {
    }
}
