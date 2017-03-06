package com.jskj.course.fragment;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

/**
 * Created by xuecheng.cui on 2017/2/3.
 */
public class FragmentFactory {
    private static SparseArray<Fragment> mFms = new SparseArray<>();

    public static Fragment getFragmentByPosition(int position) {
        Fragment fragment = mFms.get(position);
        if (fragment != null) {
            return fragment;
        }
        if (position == 0) {
            fragment = new HomeFragment();
        } else if (position == 1) {
            fragment = new TypeFragment();
        } else if (position == 2) {
            fragment = new MyCourseFragment();
        } else if (position == 3) {
            fragment = new UserFragment();
        } else {
            throw new IllegalArgumentException("the position may be in 0 ~ 3 !!!");
        }
        mFms.put(position, fragment);
        return fragment;
    }
}
