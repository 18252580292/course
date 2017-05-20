package com.jskj.course.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jskj.course.MCApplication;
import com.jskj.course.R;
import com.jskj.course.adapter.HomeRecyclerAdapter;
import com.jskj.course.bean.HomeResultInfo;
import com.jskj.course.constant.HttpConstants;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HomeFragment extends Fragment {
    private RecyclerView mRecycleView;
    private TextView mTvMsg;
    private TextView mEtSearch;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HomeResultInfo resultInfo = (HomeResultInfo) msg.obj;
            mRecycleView.setLayoutManager(new LinearLayoutManager(MCApplication.mContext));
            mRecycleView.setAdapter(new HomeRecyclerAdapter(resultInfo));
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(container.getContext(), R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecycleView = (RecyclerView) view.findViewById(R.id.recycler_view_home_fm);
        mTvMsg = (TextView) view.findViewById(R.id.tv_msg_home_fm_top);
        mEtSearch = (TextView) view.findViewById(R.id.et_search_home_fm_top);
        requestData();
    }

    /**
     * get the data from server
     */
    private void requestData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(HttpConstants.HOME_URL).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                HomeResultInfo resultInfo = JSON.parseObject(response.body().string(),HomeResultInfo.class);
                Message msg = Message.obtain();
                msg.obj = resultInfo;
                msg.what = 1;
                mHandler.sendMessage(msg);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
