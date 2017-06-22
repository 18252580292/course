package com.jskj.course.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.jskj.course.R;
import com.jskj.course.fragment.FragmentFactory;
import com.jskj.course.util.FragmentUtils;

public class MainActivity extends AppCompatActivity {
    private RadioGroup mHomeRg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView() {
        mHomeRg = (RadioGroup) findViewById(R.id.main_rg);
    }

    private void initEvent() {
        mHomeRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                FragmentUtils.switchFragment(MainActivity.this, R.id.container_fl, FragmentFactory.getFragment(i));
            }
        });
        mHomeRg.check(R.id.home_rb);
    }
}
