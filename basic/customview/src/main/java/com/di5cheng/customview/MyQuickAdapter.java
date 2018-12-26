package com.di5cheng.customview;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by zhoul on 2018/10/30.
 */
public class MyQuickAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Class[] mClz;

    public MyQuickAdapter(@LayoutRes int layoutResId, @Nullable List<String> data, Class[] mClz) {
        super(layoutResId, data);
        this.mClz = mClz;
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {
        helper.setText(android.R.id.text1, item);
        helper.getView(android.R.id.text1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, mClz[helper.getLayoutPosition()]));
            }
        });
    }
}
