package com.zhoulesin.retrofitdemo.util;

import android.widget.Toast;

import com.zhoulesin.retrofitdemo.App;

/**
 * Created by zhoul on 2018/9/30.
 */

public class ToastUtil {

    private static Toast toast;

    public static void showToast(String msg){
        if (toast == null) {
            toast = Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
