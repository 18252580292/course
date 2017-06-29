package com.jskj.course.mvp.model.impl;

import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.jskj.course.bean.HomeBean;
import com.jskj.course.callback.HomeCallback;
import com.jskj.course.callback.HttpCallback;
import com.jskj.course.constant.HttpConstants;
import com.jskj.course.mvp.model.HomeModel;
import com.jskj.course.utils.OkhttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class HomeModelImpl implements HomeModel {

    private Handler handler = new Handler();

    public HomeModelImpl() {

    }

    @Override
    public void requestHomeDataFromServer(final HomeCallback callback) {
        OkhttpUtils.get(HttpConstants.HOME_URL, new HttpCallback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                callback.onError(e.toString(), call);
            }

            @Override
            public void onResponse(final Call call, final Response response) {

                try {
                    String str = response.body().string();
                    final HomeBean homeBean = JSON.parseObject(str, HomeBean.class);
                    callback.onSuccess(homeBean, call);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }

}
