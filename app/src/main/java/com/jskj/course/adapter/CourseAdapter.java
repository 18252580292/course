package com.jskj.course.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jskj.course.R;
import com.jskj.course.bean.CourseBean;
import com.jskj.course.constant.HttpConstants;

public class CourseAdapter extends RecyclerView.Adapter {
    private CourseBean courseBean;
    private Context mContext;

    public CourseAdapter(Context context, CourseBean courseBean) {
        this.courseBean = courseBean;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CourseViewHolder(View.inflate(mContext, R.layout.course_item_view, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CourseViewHolder courseViewHolder = (CourseViewHolder) holder;
        courseViewHolder.mTvName.setText(courseBean.getCourse().get(position).getName());
        Glide.with(mContext).load(HttpConstants.BASE_IMG_URL +
                courseBean.getCourse().get(position).getFigure()).into(courseViewHolder.mImg);
        courseViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return courseBean.getCourse().size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName;
        private ImageView mImg;
        //        private CardView cardView;
        private LinearLayout linearLayout;

        public CourseViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.course_tv_name);
            mImg = itemView.findViewById(R.id.course_img);
//            cardView = itemView.findViewById(R.id.card_view);
            linearLayout = itemView.findViewById(R.id.course_ll);
        }
    }
}
