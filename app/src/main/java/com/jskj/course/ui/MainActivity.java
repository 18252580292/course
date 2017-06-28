package com.jskj.course.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.jskj.course.R;
import com.jskj.course.factory.FragmentFactory;

/**
 * 主Activity主要是加载不同的fragment
 */
public class MainActivity extends AppCompatActivity {
    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        initBottomTab();
    }

    /**
     * init bottom item tab
     */
    private void initBottomTab() {
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        TabHost.TabSpec homeSpec = tabHost.newTabSpec("home").setIndicator(getBottomItemView("首页", R.drawable.home_home_selector));
        tabHost.addTab(homeSpec, FragmentFactory.getFragment(0).getClass(), null);
        TabHost.TabSpec typeSpec = tabHost.newTabSpec("type").setIndicator(getBottomItemView("分类", R.drawable.home_type_selector));
        tabHost.addTab(typeSpec, FragmentFactory.getFragment(1).getClass(), null);
        TabHost.TabSpec courseSpec = tabHost.newTabSpec("course").setIndicator(getBottomItemView("所有课程", R.drawable.home_my_course_selector));
        tabHost.addTab(courseSpec, FragmentFactory.getFragment(2).getClass(), null);
        TabHost.TabSpec userSpec = tabHost.newTabSpec("user").setIndicator(getBottomItemView("用户中心", R.drawable.home_user_selector));
        tabHost.addTab(userSpec, FragmentFactory.getFragment(3).getClass(), null);
    }


    private View getBottomItemView(String name, int imgId) {
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_item_view, null);
        TextView tv_name = view.findViewById(R.id.tv_bottom_name);
        ImageView iv_img = view.findViewById(R.id.iv_bottom_img);
        tv_name.setText(name);
        iv_img.setImageResource(imgId);
        return view;
    }
}
