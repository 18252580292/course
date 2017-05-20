package com.jskj.course;

import android.app.Application;
import android.content.Context;

public class MCApplication extends Application{
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
    }
}
