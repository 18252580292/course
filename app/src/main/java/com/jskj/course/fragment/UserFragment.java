package com.jskj.course.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.jskj.course.R;
import com.jskj.course.ui.LoginActivity;

/**
 * Created by cui on 2017/6/17.
 */

public class UserFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout mLoginLayout;
    @Override
    protected View createView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_user, null);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mLoginLayout = view.findViewById(R.id.user_login_ll);

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mLoginLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_login_ll:
                click2Login();
                break;
        }
    }

    private void click2Login() {
        // 跳转到登录界面
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}
