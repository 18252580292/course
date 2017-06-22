package com.jskj.course.util;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.jskj.course.fragment.BaseFragment;

/**
 * Created by cui on 2017/6/17.
 */

public class FragmentUtils {
    public static void switchFragment(AppCompatActivity activity, @IdRes int containerId, BaseFragment fragment) {
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.commit();
    }
}
