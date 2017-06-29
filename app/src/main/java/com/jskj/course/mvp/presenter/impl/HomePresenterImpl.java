package com.jskj.course.mvp.presenter.impl;

import android.os.Handler;

import com.jskj.course.bean.HomeBean;
import com.jskj.course.callback.HomeCallback;
import com.jskj.course.mvp.model.HomeModel;
import com.jskj.course.mvp.model.impl.HomeModelImpl;
import com.jskj.course.mvp.presenter.HomePresenter;
import com.jskj.course.mvp.view.HomeFragmentView;

import okhttp3.Call;


public class HomePresenterImpl implements HomePresenter, HomeCallback {
    private HomeModel homeModel;
    private HomeFragmentView mHomeView;
    private Handler mHandler = new Handler();

    public HomePresenterImpl() {
        homeModel = new HomeModelImpl();
    }

    @Override
    public void loadHomeData(HomeFragmentView view) {
        this.mHomeView = view;
        homeModel.requestHomeDataFromServer(this);
    }

    @Override
    public void onSuccess(final HomeBean homeBean, Call call) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mHomeView.setAdapter(homeBean);
            }
        });
    }

    @Override
    public void onError(final String msg, Call call) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mHomeView.showErrorMessage(msg);
            }
        });

    }
}
