package com.jskj.course.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.jskj.course.R;
import com.jskj.course.adapter.ListVewAdapter;
import com.jskj.course.bean.CourseBean;
import com.jskj.course.callback.HttpCallback;
import com.jskj.course.constant.HttpConstants;
import com.jskj.course.constant.MsgConstant;
import com.jskj.course.ui.PlayActivity;
import com.jskj.course.util.OkhttpUtils;
import com.jskj.course.util.ToastUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class CourseFragment extends BaseFragment {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MsgConstant.MSG_ERROR:
                    ToastUtils.showToast(getActivity(), "出错了!!!");
                    break;
                case MsgConstant.MSG_SUCCESS:
                    final CourseBean courseBean = (CourseBean) msg.obj;
//                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//                    mRecyclerView.setAdapter(new CourseAdapter(getActivity(), courseBean));
                    mRecyclerView.setAdapter(new ListVewAdapter(courseBean));
                    mRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity(), PlayActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("url", HttpConstants.BASE_URL + courseBean.getCourse().get(i).getUrl());
                            bundle.putString("name", courseBean.getCourse().get(i).getName());
                            intent.putExtra("bundle", bundle);
                            getActivity().startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };

    private ListView mRecyclerView;

    @Override
    protected View createView() {

        return View.inflate(getActivity(), R.layout.fragment_course, null);
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = view.findViewById(R.id.course_recycler_view);
    }

    @Override
    protected void initEvent() {
        OkhttpUtils.get(HttpConstants.COURSE_URL, new HttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(MsgConstant.MSG_ERROR);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String string = response.body().string();
                    if (string == null) {
                        mHandler.sendEmptyMessage(MsgConstant.MSG_ERROR);
                        return;
                    }
                    Message message = Message.obtain();
                    message.what = MsgConstant.MSG_SUCCESS;
                    CourseBean courseBean = JSON.parseObject(string, CourseBean.class);
                    message.obj = courseBean;
                    mHandler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
