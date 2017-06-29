package com.jskj.course.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jskj.course.bean.HomeBean;

public class HomeRecyclerAdapter extends RecyclerView.Adapter {
    private HomeBean homeBean;

    public HomeRecyclerAdapter(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
//        textView.setText("hello,world");

        return new HomeHolder(textView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeHolder homeHolder = (HomeHolder) holder;
        homeHolder.textView.setText("hello,world" + position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class HomeHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public HomeHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
