package com.jskj.course.mvp.model;

import com.jskj.course.callback.HomeCallback;

public interface HomeModel {
    public void requestHomeDataFromServer(HomeCallback callback);
}
