package com.qnote.application.user.login.view;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qnote.application.Iapplication;
import com.qnote.application.R;
import com.qnote.application.user.fogetpw.view.FogetpwActivity;
import com.qnote.application.user.login.presenter.LoginPre;
import com.qnote.application.user.register.view.RegisterActivity;
import com.qnote.application.utils.CustomToast;

public class LoginActivity extends Activity implements ILoginView {

    Button loginBT;
    View fogetPWV;
    View registerV;
    EditText userIdET;
    EditText passwordET;
    String userId;
    String password;
    ProgressDialog progressDialog;

    LoginPre presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPre(this);
        initView();

    }

    private void initView() {

        fogetPWV = findViewById(R.id.forgetPWTV);
        registerV = findViewById(R.id.registerTV);
        userIdET = (EditText) findViewById(R.id.userIdET);
        passwordET = (EditText) findViewById(R.id.userPasswordET);
        loginBT = (Button) findViewById(R.id.loginBT);
        initListener();
    }

    private void initListener() {

        loginBT.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                userId = userIdET.getText().toString();
                password = passwordET.getText().toString();
                if (userId == null || userId.length() == 0) {
                    showMsg("请输入账户名");
                } else if (password == null || password.length() < 6) {
                    showMsg("请输入6位以上密码");
                } else {
                    login(userId, password);
                }

            }
        });
        fogetPWV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,
                        FogetpwActivity.class));
            }
        });
        registerV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,
                        RegisterActivity.class));
            }
        });
    }

    @Override
    public void login(String phoneNumber, String password) {
        presenter.login(phoneNumber, password);
    }

    @Override
    public void showMsg(String msg) {
        CustomToast.showToast(this, msg, 2000);
    }

    @Override
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.show();
    }

    @Override
    public void dismissDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void back(boolean loginState, String token) {
        if (loginState) {
            Iapplication.setUserId(userId);
            Iapplication.setToken(token);
            finish();
        }


    }

    @Override
    public void saveUserInfo(String userId, String password, String token) {
        SharedPreferences sp=getSharedPreferences(Iapplication.userInfoSP, Application.MODE_PRIVATE);
        SharedPreferences.Editor ed=sp.edit();
        ed.putString("userId",userId);
        ed.putString("password",password);
        ed.putString("token",token);
        ed.apply();
    }
}
