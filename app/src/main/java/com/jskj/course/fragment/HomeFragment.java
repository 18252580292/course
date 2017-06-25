package com.jskj.course.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.jskj.course.R;
import com.jskj.course.adapter.HomeAdapter;
import com.jskj.course.bean.HomeBean;
import com.jskj.course.callback.HttpCallback;
import com.jskj.course.constant.HttpConstants;
import com.jskj.course.util.OkhttpUtils;
import com.jskj.course.util.ToastUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

import static android.view.View.inflate;
import static com.jskj.course.constant.MsgConstant.MSG_ERROR;
import static com.jskj.course.constant.MsgConstant.MSG_SUCCESS;


public class HomeFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_ERROR:
                    ToastUtils.showToast(getActivity(), "出错了!!!");
                    break;
                case MSG_SUCCESS:
                    homeBean = JSON.parseObject((String) msg.obj, HomeBean.class);
                    loadRecyclerView(homeBean);
                    break;
            }

        }

    };
    private HomeBean homeBean;

    private void loadRecyclerView(HomeBean bean) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new HomeAdapter(getActivity(), homeBean));
    }

    @Override
    protected View createView() {
        return inflate(getActivity(), R.layout.fragment_home, null);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mRecyclerView = view.findViewById(R.id.main_recycle_view);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        OkhttpUtils.get(HttpConstants.HOME_URL, new HttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                mHandler.sendEmptyMessage(MSG_ERROR);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String string = response.body().string();
                    Message msg = Message.obtain();
                    msg.obj = string;
                    msg.what = MSG_SUCCESS;
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
