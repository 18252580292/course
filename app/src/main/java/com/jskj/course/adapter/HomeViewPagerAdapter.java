package com.jskj.course.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jskj.course.fragment.FragmentFactory;

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    private String[] names;

    public HomeViewPagerAdapter(FragmentManager fm, String[] names) {
        super(fm);
        this.names = names;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getFragmentByPosition(position);
    }

    @Override
    public int getCount() {
        if(names != null && names.length > 0) {
            return names.length;
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.names[position];
    }
}
