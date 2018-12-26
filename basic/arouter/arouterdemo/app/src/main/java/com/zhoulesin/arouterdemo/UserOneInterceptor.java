package com.zhoulesin.arouterdemo;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

@Interceptor(priority = 1)
public class UserOneInterceptor implements IInterceptor {

    public static final String TAG = "UserOneInterceptor";

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (postcard.getPath().equals("/jump/simple")) {
            Log.d(TAG, "process: UserOneInterceptor 拦截处理");
        }
        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {
        Log.d(TAG, "init: UserOneInterceptor");
    }
}
