package com.jskj.course.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;

import com.jskj.course.R;

/**
 * Created by cui on 2017/6/18.
 */

public class RecommendHolder extends RecyclerView.ViewHolder {
    public GridView mGridView;

    public RecommendHolder(View itemView) {
        super(itemView);
        mGridView = itemView.findViewById(R.id.recommend_grid_view);
    }
}
