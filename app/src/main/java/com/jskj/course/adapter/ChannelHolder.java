package com.jskj.course.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;

import com.jskj.course.R;

/**
 * Created by cui on 2017/6/18.
 */

public class ChannelHolder extends RecyclerView.ViewHolder {

    public GridView gridView;

    public ChannelHolder(View itemView) {
        super(itemView);
        gridView = itemView.findViewById(R.id.grid_view);
    }
}
