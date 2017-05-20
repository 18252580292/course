package com.jskj.course.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jskj.course.MCApplication;
import com.jskj.course.R;


public class MyCourseFragment extends Fragment implements View.OnClickListener {
    private Button mBtnLogin;
    private Button mBtnRegist;
    private Button mBtnSubmitDialog;
    private Button mBtnCancelDialog;
    private TextInputLayout mEtUsername;
    private TextInputLayout mEtPwd;
    private AlertDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_course, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mBtnLogin = (Button) view.findViewById(R.id.btn_login);
        mBtnRegist = (Button) view.findViewById(R.id.btn_regist);
        mBtnLogin.setOnClickListener(this);
        mBtnRegist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_regist:
                regist();
                break;
            case R.id.btn_submit:
                realLogin();
                break;
            case R.id.btn_cancel:
                dialog.cancel();
                break;
        }
    }

    /**
     * 向服务器发送数据请求登录
     */
    private void realLogin() {

    }

    private void login() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(MCApplication.mContext,R.layout.login,null);
        initDialogView(view);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();
    }

    private void initDialogView(View view) {
        mBtnSubmitDialog = (Button) view.findViewById(R.id.btn_submit);
        mBtnCancelDialog = (Button) view.findViewById(R.id.btn_cancel);
        mEtPwd = (TextInputLayout) view.findViewById(R.id.et_pwd);
        mEtUsername = (TextInputLayout) view.findViewById(R.id.et_username);
        initEvent();
    }

    private void initEvent() {
        mBtnSubmitDialog.setOnClickListener(this);
        mBtnCancelDialog.setOnClickListener(this);
    }

    private void regist() {

    }
}
