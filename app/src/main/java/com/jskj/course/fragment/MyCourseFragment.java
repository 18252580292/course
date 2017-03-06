package com.jskj.course.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jskj.course.MCApplication;
import com.jskj.course.R;

/**
 * Created by xuecheng.cui on 2017/2/3.
 */
public class MyCourseFragment extends Fragment implements View.OnClickListener {
    private Button mBtnLogin;
    private Button mBtnRegist;

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
        }
    }

    private void login() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(MCApplication.mContext,R.layout.login,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void regist() {

    }
}
