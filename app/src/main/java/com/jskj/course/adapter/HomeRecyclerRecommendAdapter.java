package com.jskj.course.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jskj.course.MCApplication;
import com.jskj.course.R;
import com.jskj.course.bean.HomeResultInfo;
import com.jskj.course.constant.HttpConstants;

import java.util.List;

public class HomeRecyclerRecommendAdapter extends HomeRecyclerBaseAdapter<HomeResultInfo.Result.RecommendInfo> {
    private View view;

    public HomeRecyclerRecommendAdapter(List<HomeResultInfo.Result.RecommendInfo> infos) {
        this.list = infos;
    }

    @Override
    protected View getConvertView() {
        view = View.inflate(MCApplication.mContext, R.layout.recommend_grid_item, null);
        return view;
    }

    @Override
    protected void showUI(View convertView, int position) {
        MyViewHolder holder = MyViewHolder.getHolder(convertView);
        holder.tv_name.setText(getItem(position).name);
        Glide.with(MCApplication.mContext).load(HttpConstants.BASE_URL + HttpConstants.BASE_IMG + getItem(position).figure)
        .into(holder.iv_icon);
    }

    private static class MyViewHolder {
        private TextView tv_name;
        private ImageView iv_icon;

        public MyViewHolder(View itemView) {
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
        }

        public static MyViewHolder getHolder(View itemView) {
            MyViewHolder holder = (MyViewHolder) itemView.getTag();
            if (holder == null) {
                holder = new MyViewHolder(itemView);
            }
            return holder;
        }
    }
}
