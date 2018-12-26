package com.zhoulesin.retrofitdemo.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhoulesin.retrofitdemo.R;

public class MainActivity extends AppCompatActivity {

    private static final String[] LIST_DATA = {"古诗", "音乐", "视频"};
    private static final Class[] ACTIVITY_DATA = {AncientPoetryActivity.class, MusicActivity.class, VideoActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    /**
     * 初始化Views
     */
    private void initViews() {
        RecyclerView rv = ((RecyclerView) findViewById(R.id.rv));
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RetroAdapter(MainActivity.this));
    }


    private static class RetroAdapter extends RecyclerView.Adapter<RetroAdapter.RetroHolder> {

        private Context mContext;

        RetroAdapter(Context context){
            this.mContext = context;
        }

        @Override
        public RetroHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RetroHolder(LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(RetroHolder holder, final int position) {
            holder.tv.setText(LIST_DATA[position]);
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext,ACTIVITY_DATA[position]));
                }
            });
        }

        @Override
        public int getItemCount() {
            return LIST_DATA.length;
        }

        public static class RetroHolder extends RecyclerView.ViewHolder {

            private TextView tv;

            public RetroHolder(View itemView) {
                super(itemView);
                tv = ((TextView) itemView.findViewById(android.R.id.text1));
            }
        }
    }
}
