package com.jskj.course.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by xuecheng.cui on 2017/6/20.
 */

public class NetworkUtils {

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        return activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean isMobileConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }
}
