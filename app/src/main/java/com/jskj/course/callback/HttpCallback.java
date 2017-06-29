package com.jskj.course.callback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by cui on 2017/6/18.
 */

public interface HttpCallback {
    void onFailure(Call call, IOException e);

    void onResponse(Call call, Response response);
}
