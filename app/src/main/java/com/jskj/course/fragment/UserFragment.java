package com.jskj.course.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jskj.course.R;
import com.jskj.course.constant.HttpConstants;
import com.jskj.course.ui.LoginActivity;
import com.jskj.course.ui.PlayActivity;
import com.jskj.course.util.TextUtils;
import com.jskj.course.util.ToastUtils;


public class UserFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout mLoginLayout;
    private TextView tvUsername;
    private TextView mTvCheckUpdate;
    private ImageView mIvHistory;
    private TextView mTvAboutUs;
    private TextView user_mobile_setting_tv;
    private TextView mTvSetting;
    private TextView mTvHistory;
    private SharedPreferences mPref;
    private String url;
    private String name;

    @Override
    protected View createView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_user, null);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mPref = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        mLoginLayout = view.findViewById(R.id.user_login_ll);
        tvUsername = view.findViewById(R.id.tv_username);
        mIvHistory = view.findViewById(R.id.user_img_history);
        mTvHistory = view.findViewById(R.id.tv_history);
        mTvCheckUpdate = view.findViewById(R.id.user_check_update_tv);
        mTvAboutUs = view.findViewById(R.id.user_about_us_tv);
        user_mobile_setting_tv = view.findViewById(R.id.user_mobile_setting_tv);
//        mTvSetting = view.findViewById(R.id.user_setting_tv);
    }

    @Override
    protected void initData() {
        super.initData();
        url = mPref.getString("url", "");
        name = mPref.getString("name", "");
        String img = mPref.getString("img", "");
        String username = mPref.getString("username", "");
        if (TextUtils.isEmpty(username)) {
            mLoginLayout.setClickable(true);
        } else {
            tvUsername.setText("欢迎您，亲爱的用户," + username);
            mLoginLayout.setClickable(false);
        }
        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(name)) {
            mIvHistory.setClickable(false);

        } else {
            mIvHistory.setClickable(true);
            Glide.with(getActivity()).load(HttpConstants.BASE_IMG_URL + img).into(mIvHistory);
            mTvHistory.setText(name);
        }

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mLoginLayout.setOnClickListener(this);
        mIvHistory.setOnClickListener(this);
        mTvCheckUpdate.setOnClickListener(this);
        mTvAboutUs.setOnClickListener(this);
        user_mobile_setting_tv.setOnClickListener(this);
//        mTvSetting.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_login_ll:
                click2Login();
                break;
            case R.id.user_img_history:
                Intent intent = new Intent(getActivity(), PlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                bundle.putString("name", name);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.user_check_update_tv:
                ToastUtils.showToast(getActivity(), "当前已经是最新版本！！！");
                break;
            case R.id.user_about_us_tv:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("关于我们");
                builder.setMessage("微课堂是提供给在校学生的一款在线学习app,版权归江科大所有");
                builder.create();
                builder.show();
                break;
            case R.id.user_mobile_setting_tv:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("省流量设置");
                builder1.setView(R.layout.setting_mobile);
                builder1.create();
                builder1.show();
                break;

//            case R.id.user_setting_tv:
//                break;
        }
    }

    private void click2Login() {
        // 跳转到登录界面
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivityForResult(intent, 200);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        tvUsername.setText("欢迎您，亲爱的用户," + data.getStringExtra("username"));
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString("username", data.getStringExtra("username"));
        editor.apply();
        mLoginLayout.setClickable(false);
    }
}
