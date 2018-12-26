package com.zhoulesin.retrofitdemo.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhoulesin.retrofitdemo.R;
import com.zhoulesin.retrofitdemo.api.MusicInterface;
import com.zhoulesin.retrofitdemo.api.RetrofitUtil;
import com.zhoulesin.retrofitdemo.bean.music.Channel;
import com.zhoulesin.retrofitdemo.bean.music.Music;
import com.zhoulesin.retrofitdemo.bean.music.MusicBroadcast;
import com.zhoulesin.retrofitdemo.bean.music.MusicBroadcastDetail;
import com.zhoulesin.retrofitdemo.bean.music.MusicRanking;
import com.zhoulesin.retrofitdemo.bean.music.MusicRankingSong;
import com.zhoulesin.retrofitdemo.bean.RetroResult;
import com.zhoulesin.retrofitdemo.bean.music.Song;
import com.zhoulesin.retrofitdemo.callback.BaseCallback;
import com.zhoulesin.retrofitdemo.util.ToastUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zhoul on 2018/10/8.
 */

public class MusicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        initViews();
    }

    private EditText et;
    private TextView tv;


    private void initViews() {
        et = ((EditText) findViewById(R.id.et));
        tv = ((TextView) findViewById(R.id.tv));
    }

    /**
     * 音乐搜索
     *
     * @param view
     */
    public void reqMusic(View view) {
        String searchStr = et.getText().toString().trim();
        MusicInterface musicInterface = RetrofitUtil.getMusicInterface();
        Call<RetroResult<List<Music>>> resultCall = musicInterface.reqMusic(searchStr);
        resultCall.enqueue(new BaseCallback<RetroResult<List<Music>>>() {
            @Override
            public void onResponse(Call<RetroResult<List<Music>>> call, Response<RetroResult<List<Music>>> response) {
                if (response.body().getCode() == 200) {
                    List<Music> result = response.body().getResult();
                    if (result != null && !result.isEmpty()) {
                        String title = result.get(0).getTitle();
                        String link = result.get(0).getLink();
                        ToastUtil.showToast(title + link);
                        tv.setText(title + link);
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
     * 音乐电台接口
     *
     * @param view
     */
    public void reqMusicBroadcasting(View view) {
        MusicInterface musicInterface = RetrofitUtil.getMusicInterface();
        Call<RetroResult<List<MusicBroadcast>>> musicBroadcastList = musicInterface.getMusicBroadcastList();
        musicBroadcastList.enqueue(new BaseCallback<RetroResult<List<MusicBroadcast>>>() {
            @Override
            public void onResponse(Call<RetroResult<List<MusicBroadcast>>> call, Response<RetroResult<List<MusicBroadcast>>> response) {
                if (response.body().getCode() == 200) {
                    List<MusicBroadcast> result = response.body().getResult();
                    if (result != null && !result.isEmpty()) {
                        MusicBroadcast musicBroadcast = result.get(0);
                        String title = musicBroadcast.getTitle();
                        Channel channel = musicBroadcast.getChannellist().get(0);
                        ToastUtil.showToast(title + channel.getName());
                        tv.setText(title + channel.getName());
                    } else {
                        ToastUtil.showToast(getString(R.string.search_empty));
                    }

                } else {
                    ToastUtil.showToast(getString(R.string.request_err));
                }
            }
        });
    }

    /**
     * cid = 1
     * title = 公共频道
     * ch_name
     * 请求电台详情
     * @param view
     */
    public void getMusicBroadcastDetail(View view){
        final String searchStr = "public_yuzhong_huayu";
        MusicInterface musicInterface = RetrofitUtil.getMusicInterface();
        Call<RetroResult<MusicBroadcastDetail>> musicBroadcastDetail =
                musicInterface.getMusicBroadcastDetail(searchStr);
        musicBroadcastDetail.enqueue(new BaseCallback<RetroResult<MusicBroadcastDetail>>() {
            @Override
            public void onResponse(Call<RetroResult<MusicBroadcastDetail>> call, Response<RetroResult<MusicBroadcastDetail>> response) {
                if (response.body().getCode() == 200) {
                    MusicBroadcastDetail result = response.body().getResult();
                    Song song =
                            result.getSonglist().get(0);
                    ToastUtil.showToast(result.getCh_name() + song.getTitle());
                    tv.setText(result.getCh_name() + song.getTitle());

                } else {
                    ToastUtil.showToast(getString(R.string.request_err));
                }
            }
        });
    }

    /**
     * 请求排行榜
     * @param view
     */
    public void getMusicRankings(View view){
        final MusicInterface musicInterface = RetrofitUtil.getMusicInterface();
        Call<RetroResult<List<MusicRanking>>> musicRankings =
                musicInterface.getMusicRankings();
        musicRankings.enqueue(new BaseCallback<RetroResult<List<MusicRanking>>>() {
            @Override
            public void onResponse(Call<RetroResult<List<MusicRanking>>> call, Response<RetroResult<List<MusicRanking>>> response) {
                if (response.body().getCode() == 200) {
                    List<MusicRanking> result = response.body().getResult();
                    MusicRanking musicRanking = result.get(0);
                    String title = musicRanking.getContent().get(0).getTitle();
                    tv.setText(musicRanking.getName() + title);
                } else {
                    ToastUtil.showToast(getString(R.string.request_err));
                }
            }
        });
    }

    /**
     * 请求排行榜详情
     * @param view
     */
    public void getMusicRankingsDetails(View view) {
        MusicInterface musicInterface = RetrofitUtil.getMusicInterface();
        Call<RetroResult<List<MusicRankingSong>>> musicRankingsDetails = musicInterface.getMusicRankingsDetails(1);
        musicRankingsDetails.enqueue(new BaseCallback<RetroResult<List<MusicRankingSong>>>() {
            @Override
            public void onResponse(Call<RetroResult<List<MusicRankingSong>>> call, Response<RetroResult<List<MusicRankingSong>>> response) {
                if (response.body().getCode() == 200) {
                    List<MusicRankingSong> result = response.body().getResult();
                    MusicRankingSong musicRankingSong = result.get((int) (Math.random()*result.size()));
                    tv.setText(musicRankingSong.getTitle());
                } else {
                    ToastUtil.showToast(getString(R.string.request_err));
                }
            }
        });
    }
}
