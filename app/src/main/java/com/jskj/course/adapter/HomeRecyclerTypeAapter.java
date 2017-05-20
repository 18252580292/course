package com.jskj.course.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jskj.course.R;
import com.jskj.course.bean.HomeResultInfo;
import com.jskj.course.constant.HttpConstants;

import java.util.List;

public class HomeRecyclerTypeAapter extends BaseAdapter {
    private List<HomeResultInfo.Result.ChannelInfo> channelInfos;

    public HomeRecyclerTypeAapter(List<HomeResultInfo.Result.ChannelInfo> channelInfos) {
        this.channelInfos = channelInfos;
    }

    @Override
    public int getCount() {
        if (channelInfos != null && !channelInfos.isEmpty()) {
            return channelInfos.size();
        }
        return 0;
    }

    @Override
    public HomeResultInfo.Result.ChannelInfo getItem(int position) {
        return channelInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.type_grid_item, null);
        }
        MyViewHolder holder = MyViewHolder.getHolder(convertView);
        holder.tv_name.setText(getItem(position).channel_name);
        Glide.with(parent.getContext()).load(HttpConstants.BASE_URL + HttpConstants.BASE_IMG + getItem(position).image)
                .into(holder.iv_icon);
        return convertView;
    }

    static class MyViewHolder {
        public TextView tv_name;
        public ImageView iv_icon;

        public MyViewHolder(View itemView) {
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
        }

        public static MyViewHolder getHolder(View itemView) {
            MyViewHolder holder = (MyViewHolder) itemView.getTag();
            if (holder == null) {
                holder = new MyViewHolder(itemView);
                itemView.setTag(holder);
            }
            return holder;
        }

    }
}
