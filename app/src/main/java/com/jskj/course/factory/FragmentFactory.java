package com.jskj.course.factory;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.jskj.course.fragment.CourseFragment;
import com.jskj.course.fragment.HomeFragment;
import com.jskj.course.fragment.TypeFragment;
import com.jskj.course.fragment.UserFragment;

/**
 * fragment工厂类，避免Fragment多次创建
 */
public class FragmentFactory {
    private FragmentFactory() {
        throw new RuntimeException("don't permission create class");
    }

    private static SparseArray<Fragment> fms = new SparseArray<>(0);

    public static Fragment getFragment(int position) {
        Fragment fragment = fms.get(position);
        if (fragment != null) {
            return fragment;
        }

        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new TypeFragment();
                break;
            case 2:
                fragment = new CourseFragment();
                break;
            case 3:
                fragment = new UserFragment();
                break;
            default:
                throw new IllegalArgumentException("position illegal");
        }

        fms.put(position, fragment);
        return fragment;
    }
}
