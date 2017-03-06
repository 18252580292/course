package com.jskj.course;

import android.app.Application;
import android.content.Context;

/**
 * Created by xuecheng.cui on 2017/2/3.
 */
public class MCApplication extends Application{
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
    }
}
