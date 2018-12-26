package com.zhoulesin.retrofitdemo.callback;


import com.zhoulesin.retrofitdemo.App;
import com.zhoulesin.retrofitdemo.R;
import com.zhoulesin.retrofitdemo.util.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhoul on 2018/9/30.
 */

public abstract class BaseCallback<T> implements Callback<T> {

    public abstract void onResponse(Call<T> call, Response<T> response);

    public void onFailure(Call<T> call, Throwable t) {
        ToastUtil.showToast(App.getContext().getString(R.string.network_err));
    }
}
