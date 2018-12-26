package com.zhoulesin.arouterdemo;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

@Interceptor(priority = 3)
public class UserSecondInterceptro implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.d(TAG, "process: UserSecondInterceptor");
        callback.onContinue(postcard);

    }

    @Override
    public void init(Context context) {
        Log.d(TAG, "init: UserSecondInterceptor");
    }

    public static final String TAG = "UserSecondInterceptor";
}
