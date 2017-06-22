package com.jskj.course.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jskj.course.R;
import com.jskj.course.bean.CourseBean;
import com.jskj.course.constant.HttpConstants;

/**
 * Created by xuecheng.cui on 2017/6/21.
 */

public class ListVewAdapter extends android.widget.BaseAdapter {
    private CourseBean courseBean;

    public ListVewAdapter(CourseBean courseBean) {
        this.courseBean = courseBean;
    }


    @Override
    public int getCount() {
        return courseBean.getCourse().size();
    }

    @Override
    public CourseBean.Course getItem(int i) {
        return courseBean.getCourse().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CourseViewHolder holder;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.course_item_view, null);
            holder = new CourseViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (CourseViewHolder) view.getTag();
        }
        holder.loadData(getItem(i), viewGroup.getContext());

        return view;
    }


    class CourseViewHolder {
        private TextView tv_name;
        private ImageView iv_img;

        public CourseViewHolder(View itemView) {
            tv_name = itemView.findViewById(R.id.course_tv_name);
            iv_img = itemView.findViewById(R.id.course_img);
        }

        public void loadData(CourseBean.Course course, Context context) {
            tv_name.setText(course.getName());
            Glide.with(context).load(HttpConstants.BASE_IMG_URL + course.getFigure())
                    .into(iv_img);
        }

    }
}
