package com.jskj.course.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jskj.course.R;
import com.jskj.course.bean.HomeBean;
import com.jskj.course.constant.HttpConstants;

/**
 * Created by cui on 2017/6/18.
 */

public class ChannelAdapter extends com.jskj.course.adapter.BaseAdapter {
    protected ChannelAdapter(HomeBean homeBean) {
        super(homeBean);
    }

    @Override
    protected View getItemView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.channel_item, null);
        }
        ChannelViewHolder holder = ChannelViewHolder.getHolder(view);
        holder.loadData(viewGroup.getContext(), homeBean, i);
        return view;
    }

    @Override
    protected int getItemCount() {
        return homeBean.getResult().getChannel_info().size();
    }

    static class ChannelViewHolder {
        private TextView tv;
        private ImageView iv;

        private ChannelViewHolder(View view) {
            tv = view.findViewById(R.id.channel_tv);
            iv = view.findViewById(R.id.channel_img);
        }

        public static ChannelViewHolder getHolder(View view) {
            ChannelViewHolder holder = (ChannelViewHolder) view.getTag();
            if (holder == null) {
                holder = new ChannelViewHolder(view);
                view.setTag(holder);
            }
            return holder;
        }

        public void loadData(Context context, HomeBean homeBean, int i) {
            Glide.with(context).load(HttpConstants.BASE_IMG_URL + homeBean.getResult().
                    getChannel_info().get(i).getImage()).into(this.iv);
            tv.setText(homeBean.getResult().getChannel_info().get(i).getChannel_name());
        }

    }
}
