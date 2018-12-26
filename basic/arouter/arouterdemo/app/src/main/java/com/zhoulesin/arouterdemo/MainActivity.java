package com.zhoulesin.arouterdemo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int enter;
    int exit;
    public static final String TAG = "MainActivity";

    public void jumpSimple(View view) {
        ARouter.getInstance().build("/jump/simple","group_a")
                .navigation(this, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        Log.d(TAG, "onArrival: ");
                    }

                    @Override
                    public void onFound(Postcard postcard) {
                        Log.d(TAG, "onFound: ");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Log.d(TAG, "onLost: ");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.d(TAG, "onInterrupt: ");
                    }
                });
    }

    public void jumpWithString(View view) {
        ARouter.getInstance().build("/jump/simpleString")
                .withString("name","zhangsan")
                .navigation();
    }

    public void jumpWrong(View view) {
        ARouter.getInstance().build("/jump/wrong")
                //界面跳转动画
                .withTransition(enter,exit)
                .navigation();
    }

    public void jumpBean(View view) {
        ARouter.getInstance().build("/jump/bean")
                .withParcelable("bean",new JavaBean("aaa",34))
                .withParcelable("test",new JavaBean("bbb",24))
                .navigation();
    }

    //可以强转成fragment
    public void jumpFragment(){
        Fragment fragment = (Fragment) ARouter.getInstance().build("/jump/fragment/simple").navigation();
    }

    public void jumpCommon(View view) {
        startActivity(new Intent(this,SimpleActivity.class));
    }
}
