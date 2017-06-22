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

public class HotAdapter extends com.jskj.course.adapter.BaseAdapter {

    protected HotAdapter(HomeBean homeBean) {
        super(homeBean);
    }

    @Override
    protected View getItemView(int i, ViewGroup viewGroup) {
        View hotView = View.inflate(viewGroup.getContext(), R.layout.common_item, null);
        TextView tv = hotView.findViewById(R.id.common_tv);
        ImageView iv = hotView.findViewById(R.id.common_img);
        tv.setText(homeBean.getResult().getHot_info().get(i).getName());
        Glide.with(viewGroup.getContext()).load(HttpConstants.BASE_IMG_URL + homeBean.getResult().getHot_info().get(i).getFigure())
                .into(iv);
        return hotView;
    }

    @Override
    protected int getItemCount() {
        return homeBean.getResult().getHot_info().size();
    }

}
