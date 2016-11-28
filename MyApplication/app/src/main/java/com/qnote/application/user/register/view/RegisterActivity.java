package com.qnote.application.user.register.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.qnote.application.R;
import com.qnote.application.user.register.presenter.RegisterPre;
import com.qnote.application.utils.CustomToast;


public class RegisterActivity extends Activity implements IRegister {

    private Button backBT;
    private EditText userIdET;
    private EditText passwordET;
    private EditText mailET;
    private Button registerBT;
    private String userId;
    private String passWord;
    private String mailAddress;
    private RegisterPre presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        presenter = new RegisterPre(this);
        initViews();
    }

    private void initViews() {
        backBT = (Button) findViewById(R.id.back);
        userIdET = (EditText) findViewById(R.id.userIdET);
        passwordET = (EditText) findViewById(R.id.userPasswordET);
        mailET = (EditText) findViewById(R.id.mailET);
        registerBT = (Button) findViewById(R.id.registerBT);
        initListener();
    }

    private void initListener() {
        backBT.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                back();
            }
        });

        registerBT.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                userId = userIdET.getText().toString();
                mailAddress = mailET.getText().toString();
                passWord = passwordET.getText().toString();
                if (userId == null || userId.length() ==0) {
                    showMsg("请输入账户名");
                } else if (mailAddress == null || mailAddress.length() <= 0) {
                    showMsg("请输入邮箱地址");
                } else if (passWord == null || passWord.length() < 6) {
                    showMsg("请输入6位以上密码");
                } else {
                    register();
                }
            }
        });

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
    public void register() {
        presenter.register(userId, mailAddress, passWord);
    }

    @Override
    public void back() {
        finish();
    }


}

