package com.jskj.course.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;

import com.jskj.course.R;

/**
 * Created by cui on 2017/6/18.
 */

public class HotHolder extends RecyclerView.ViewHolder {
    public GridView mGridView;
    public HotHolder(View itemView) {
        super(itemView);
        mGridView = itemView.findViewById(R.id.hot_grid_view);
    }
}
