package com.zhoulesin.statusbar;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhoul on 2018/10/9.
 */

public class ActivityUtil {
    /**
     * 设置activity根布局 fitSystemWindows
     * <p>
     * fitSystemWindows
     * - 界面跟布局会留出statusBarView的padding
     * - EditText弹出软键盘时候会将View向上顶
     *
     * @param activity
     * @param value
     */
    public static void setFitsSystemWindows(Activity activity, boolean value) {
        ViewGroup contentFrameLayout = activity.findViewById(android.R.id.content);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(value);
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 添加状态栏并设置颜色
     *
     * @param activity
     * @param color
     */
    public static void addStatusBarViewWithColor(Activity activity, int color) {
        ViewGroup contentView = activity.findViewById(android.R.id.content);
        View statusBarView = new View(activity);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(activity));
        statusBarView.setBackgroundColor(color);
        contentView.addView(statusBarView, lp);
    }

    /**
     * 设置padding和添加状态栏占位View
     * @param activity
     * @param color
     */
    public static void addPaddingAndStatusBarViewWithColor(Activity activity, int color) {
        ViewGroup rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.setPadding(0, getStatusBarHeight(activity), 0, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上直接设置状态栏颜色
            activity.getWindow().setStatusBarColor(color);
        } else {
            //添加占位状态栏
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            View statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(activity));
            statusBarView.setBackgroundColor(color);
            decorView.addView(statusBarView, lp);
        }
    }
}
