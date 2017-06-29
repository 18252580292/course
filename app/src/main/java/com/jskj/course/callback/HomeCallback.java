package com.jskj.course.callback;

import com.jskj.course.bean.HomeBean;

import okhttp3.Call;


public interface HomeCallback {
    public void onSuccess(HomeBean homeBean, Call call);

    public void onError(String msg, Call call);
}
