package com.jskj.course.adapter;

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
    protected View getItemView(int i, ViewGroup viewGroup) {
        View v = View.inflate(viewGroup.getContext(), R.layout.channel_item, null);
        ImageView img = v.findViewById(R.id.channel_img);
        TextView tv = v.findViewById(R.id.channel_tv);
        Glide.with(viewGroup.getContext()).load(HttpConstants.BASE_IMG_URL + homeBean.getResult().
                getChannel_info().get(i).getImage()).into(img);
        tv.setText(homeBean.getResult().getChannel_info().get(i).getChannel_name());
        return v;
    }

    @Override
    protected int getItemCount() {
        return homeBean.getResult().getChannel_info().size();
    }
}
