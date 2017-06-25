package com.jskj.course.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.jskj.course.R;
import com.jskj.course.callback.HttpCallback;
import com.jskj.course.constant.HttpConstants;
import com.jskj.course.constant.MsgConstant;
import com.jskj.course.util.OkhttpUtils;
import com.jskj.course.util.TextUtils;
import com.jskj.course.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private ImageButton mBackBtn;
    private Button mRegisterBtn;
    private Button mBtnLogin;
    private TextInputEditText usernameEt;
    private TextInputEditText pwdEt;
    private TextInputEditText pwdTwoEt;
    private TextInputEditText inputUsername;
    private TextInputEditText inputPwd;

    private Button btnSubmit;
    private Button btnCancel;
    private AlertDialog dialog;
    private AlertDialog.Builder builder;

    private LoginHandler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mHandler = new LoginHandler(this);
        initView();
        initEvent();
    }

    private void initView() {
        mBackBtn = (ImageButton) findViewById(R.id.back_img_btn);
        mRegisterBtn = (Button) findViewById(R.id.register_btn);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        inputUsername = (TextInputEditText) findViewById(R.id.input_user_et);
        inputPwd = (TextInputEditText) findViewById(R.id.input_pwd_et);
    }

    private void initEvent() {
        mBackBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img_btn:
                finish();
                break;
            case R.id.register_btn:
                showRegisterDialog();
                break;
            case R.id.btn_login:
                new Thread() {
                    @Override
                    public void run() {
                        login();
                    }
                }.start();
                break;
            case R.id.btn_submit:
                register();
                break;
            case R.id.btn_cancel:
                dialog.dismiss();
                break;
        }
    }

    private void login() {
        String username = inputUsername.getText().toString();
        String password = inputPwd.getText().toString();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    ToastUtils.showToast(LoginActivity.this, "用户名或者密码不能为空!!!");
                }
            });
            return;
        }
        try {
            String json = OkhttpUtils.get(HttpConstants.LOGIN_URL + "?username=" + username + "&password=" + password);
            Log.e(TAG, "login: " + json);
            final JSONObject obj = new JSONObject(json);
            if (obj.getInt("code") == 200) {
                //login success
                Intent intent = new Intent();
                intent.putExtra("username", username);
                setResult(300, intent);
                finish();
            } else {
                //login failed
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ToastUtils.showToast(LoginActivity.this, obj.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void register() {
        String username = usernameEt.getText().toString();
        String password = pwdEt.getText().toString();
        String passwordTwo = pwdTwoEt.getText().toString();
        if (TextUtils.isEmpty(username)) {
            usernameEt.setError("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            pwdEt.setError("密码不能为空");
            return;
        }

        if (!password.equals(passwordTwo)) {
            ToastUtils.showToast(this, "两次输入的密码不一致！！！");
            return;
        }
        // 请求数据库，判断数据库中是否存在此用户名
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        OkhttpUtils.post(HttpConstants.REGISTER_URL, map, new HttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                mHandler.sendEmptyMessage(MsgConstant.MSG_ERROR);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String string = response.body().string();
                    Message msg = Message.obtain();
                    msg.what = MsgConstant.MSG_SUCCESS;
                    msg.obj = string;
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    /**
     * toast register dialog
     */
    private void showRegisterDialog() {
        builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.register_dialog, null);
        builder.setView(view);
        initDialogView(view);
        builder.create();
        dialog = builder.show();
    }

    private void initDialogView(View view) {
        usernameEt = view.findViewById(R.id.username_et);
        pwdEt = view.findViewById(R.id.pwd_et);
        pwdTwoEt = view.findViewById(R.id.pwd_two_et);
        btnSubmit = view.findViewById(R.id.btn_submit);
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }


    class LoginHandler extends Handler {
        private WeakReference<Activity> wrAty;

        public LoginHandler(Activity activity) {
            wrAty = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MsgConstant.MSG_ERROR:
                    ToastUtils.showToast(wrAty.get(), "出错了!!!");
                    break;
                case MsgConstant.MSG_SUCCESS:
                    try {
                        String str = (String) msg.obj;
                        JSONObject obj = new JSONObject(str);
                        ToastUtils.showToast(wrAty.get(), obj.getString("msg"));
                        if (obj.getInt("code") == 500) {
                        } else {
                            dialog.dismiss();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
