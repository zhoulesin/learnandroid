package com.zhoulesin.retrofitdemo.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhoulesin.retrofitdemo.R;
import com.zhoulesin.retrofitdemo.api.RetrofitUtil;
import com.zhoulesin.retrofitdemo.bean.poetry.AncientPoetry;
import com.zhoulesin.retrofitdemo.bean.poetry.AncientPortry2;
import com.zhoulesin.retrofitdemo.bean.poetry.PortryAuthor;
import com.zhoulesin.retrofitdemo.bean.RetroResult;
import com.zhoulesin.retrofitdemo.callback.BaseCallback;
import com.zhoulesin.retrofitdemo.util.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zhoul on 2018/10/8.
 */

public class AncientPoetryActivity extends AppCompatActivity {
    private EditText et;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poetry);
        initViews();
    }

    private void initViews() {
        et = ((EditText) findViewById(R.id.et));
        tv = ((TextView) findViewById(R.id.tv));
    }

    /**
     * 请求随机一句古诗
     *
     * @param view
     */
    public void requestAncientPoetry(View view) {
        Call<RetroResult<AncientPoetry>> randomAncientPoetry = RetrofitUtil.getPoetryInterface().getRandomAncientPoetry();

        randomAncientPoetry.enqueue(new BaseCallback<RetroResult<AncientPoetry>>() {
            @Override
            public void onResponse(Call<RetroResult<AncientPoetry>> call, Response<RetroResult<AncientPoetry>> response) {
                if (response.body().getCode() == 200) {
                    String content = response.body().getResult().getContent();
                    ToastUtil.showToast(content);
                    tv.setText(content);
                } else {
                    ToastUtil.showToast(getString(R.string.request_err));
                }
            }
        });
    }

    /**
     * 请求随机一首诗
     *
     * @param view
     */
    public void requestAncientPoetry2(View view) {
        Call<RetroResult<AncientPortry2>> randomAncientPeotry2 = RetrofitUtil.getPoetryInterface().getRandomAncientPeotry2();
        randomAncientPeotry2.enqueue(new BaseCallback<RetroResult<AncientPortry2>>() {
            @Override
            public void onResponse(Call<RetroResult<AncientPortry2>> call, Response<RetroResult<AncientPortry2>> response) {
                if (response.body().getCode() == 200) {
                    String content = response.body().getResult().getContent();
                    ToastUtil.showToast(content);
                    tv.setText(content);
                } else {
                    ToastUtil.showToast(getString(R.string.request_err));
                }
            }
        });
    }

    /**
     * 通过题目精确搜索
     *
     * @param view
     */
    public void searchTitle(View view) {
        String searchStr = et.getText().toString().trim();

        Call<RetroResult<List<AncientPortry2>>> searchPortry =
                RetrofitUtil.getPoetryInterface().searchPortry(searchStr);
        searchPortry.enqueue(new BaseCallback<RetroResult<List<AncientPortry2>>>() {
            @Override
            public void onResponse(Call<RetroResult<List<AncientPortry2>>> call, Response<RetroResult<List<AncientPortry2>>> response) {
                if (response.body().getCode() == 200) {
                    List<AncientPortry2> result = response.body().getResult();
                    if (result != null && !result.isEmpty()) {
                        AncientPortry2 ancientPortry2 = result.get((int) (Math.random() * result.
                                size()));
                        String content = ancientPortry2.getContent();
                        ToastUtil.showToast(content);
                        tv.setText(content);
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
     * 通过作者搜索
     *
     * @param view
     */
    public void searchAuthor(View view) {
        String searchStr = et.getText().toString().trim();
        Call<RetroResult<List<PortryAuthor>>> searchAuthors = RetrofitUtil.getPoetryInterface().searchAuthors(searchStr);
        searchAuthors.enqueue(new BaseCallback<RetroResult<List<PortryAuthor>>>() {
            @Override
            public void onResponse(Call<RetroResult<List<PortryAuthor>>> call, Response<RetroResult<List<PortryAuthor>>> response) {
                if (response.body().getCode() == 200) {
                    List<PortryAuthor> result = response.body().getResult();
                    if (result != null && !result.isEmpty()) {
                        String desc = result.get((int) (Math.random() * result.
                                size())).getDesc();
                        ToastUtil.showToast(desc);
                        tv.setText(desc);
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
     * 模糊查询
     *
     * @param view
     */
    public void likeSearch(View view) {
        String searchStr = et.getText().toString().trim();
        Call<RetroResult<List<AncientPortry2>>> retroResultCall = RetrofitUtil.getPoetryInterface().likePoetry(searchStr);
        retroResultCall.enqueue(new BaseCallback<RetroResult<List<AncientPortry2>>>() {
            @Override
            public void onResponse(Call<RetroResult<List<AncientPortry2>>> call, Response<RetroResult<List<AncientPortry2>>> response) {
                if (response.body().getCode() == 200) {
                    List<AncientPortry2> result = response.body().getResult();
                    if (result != null && !result.isEmpty()) {
                        AncientPortry2 ancientPortry2 = result.get((int) (Math.random() * result.
                                size()));
                        String content = ancientPortry2.getContent();
                        ToastUtil.showToast(content);
                        tv.setText(content);
                    }
                } else {
                    ToastUtil.showToast(getString(R.string.request_err));
                }
            }
        });
    }

    /**
     * 请求唐诗
     *
     * @param view
     */
    public void reqTang(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("page", "2");
        map.put("count", "21");
        Call<RetroResult<List<AncientPortry2>>> tangPoetry = RetrofitUtil.getPoetryInterface().getTangPoetry(map);
        tangPoetry.enqueue(new BaseCallback<RetroResult<List<AncientPortry2>>>() {

            @Override
            public void onResponse(Call<RetroResult<List<AncientPortry2>>> call, Response<RetroResult<List<AncientPortry2>>> response) {
                if (response.body().getCode() == 200) {
                    List<AncientPortry2> result = response.body().getResult();
                    if (result != null && !result
                            .isEmpty()) {
                        AncientPortry2 ancientPortry2 = result.get((int) (Math.random() * result.size()));
                        tv.setText(ancientPortry2.getContent());
                    }
                } else {
                    ToastUtil.showToast(getString(R.string.request_err));
                }
            }
        });
    }

    /**
     * 请求宋词
     *
     * @param view
     */
    public void reqSong(View view) {
        Call<RetroResult<List<AncientPortry2>>> songPoetry = RetrofitUtil.getPoetryInterface().getSongPoetry(1, 12);
        songPoetry.enqueue(new BaseCallback<RetroResult<List<AncientPortry2>>>() {

            @Override
            public void onResponse(Call<RetroResult<List<AncientPortry2>>> call, Response<RetroResult<List<AncientPortry2>>> response) {
                if (response.body().getCode() == 200) {
                    List<AncientPortry2> result = response.body().getResult();
                    if (result != null && !result
                            .isEmpty()) {
                        AncientPortry2 ancientPortry2 = result.get((int) (Math.random() * result.size()));
                        tv.setText(ancientPortry2.getContent());
                    }
                } else {
                    ToastUtil.showToast(getString(R.string.request_err));
                }
            }
        });
    }
}
