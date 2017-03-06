package com.jskj.course.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.jskj.course.R;
import com.jskj.course.adapter.HomeViewPagerAdapter;

public class HomeActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTab;
    private String[] mTabNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    /**
     * init the layout
     */
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager_home);
        mTab = (TabLayout) findViewById(R.id.tab_bottom_home);
    }

    private void initData() {
        mTabNames = getResources().getStringArray(R.array.home_bottom_tabs_name);
        mViewPager.setAdapter(new HomeViewPagerAdapter(getSupportFragmentManager(),mTabNames));
        mTab.setupWithViewPager(mViewPager);
        mTab.getTabAt(0).setIcon(R.drawable.home_home_selector);
        mTab.getTabAt(1).setIcon(R.drawable.home_type_selector);
        mTab.getTabAt(2).setIcon(R.drawable.home_my_course_selector);
        mTab.getTabAt(3).setIcon(R.drawable.home_user_selector);
    }

}
