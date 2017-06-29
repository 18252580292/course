package com.jskj.course.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jskj.course.R;
import com.youth.banner.Banner;

/**
 * Created by xuecheng.cui on 2017/6/20.
 */

public class BannerHolder extends RecyclerView.ViewHolder {
    public Banner banner;
    public BannerHolder(View itemView) {
        super(itemView);
        banner = itemView.findViewById(R.id.banner);
    }

}
