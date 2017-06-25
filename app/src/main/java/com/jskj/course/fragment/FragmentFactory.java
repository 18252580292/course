package com.jskj.course.fragment;

import android.util.SparseArray;

import com.jskj.course.R;

/**
 * fragment 工厂类
 */
public class FragmentFactory {
    private static SparseArray<BaseFragment> fms = new SparseArray<>();

    /**
     * 根据不同的位置得到不同的fragment
     * @param position
     * @return
     */
    public static BaseFragment getFragment(int position) {
        BaseFragment fragment = fms.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {
            case R.id.home_rb:
                fragment = new HomeFragment();
                break;
//            case R.id.type_rb:
//                fragment = new TypeFragment();
//                break;
            case R.id.course_rb:
                fragment = new CourseFragment();
                break;
            case R.id.user_rb:
                fragment = new UserFragment();
                break;
        }

        if (fragment != null) {
            fms.put(position, fragment);
        }
        return fragment;
    }
}
