package com.example.mvplogindemo.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvplogindemo.App;
import com.example.mvplogindemo.MainActivity;
import com.example.mvplogindemo.R;
import com.example.mvplogindemo.presenter.LoginPersenter;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

/**
 * Created by jianfang on 2016/5/15.
 */
public class LoginActivity extends Activity implements View.OnClickListener, ILoginView{

    private EditText mEditTextUserName, mEditTextPassword;
    private Button mButtonLogin;
    private ProgressDialog mProgressDialog;
    private LoginPersenter mPersenter;
    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        init();
    }

    private void init() {
        mEditTextUserName = (EditText) findViewById(R.id.et_username);
        mEditTextPassword = (EditText) findViewById(R.id.et_password);
        mButtonLogin = (Button) findViewById(R.id.bt_login);
        mButtonLogin.setOnClickListener(this);

        mProgressDialog = new ProgressDialog(LoginActivity.this);
        mProgressDialog.setTitle("提示");
        mProgressDialog.setMessage("正在登录...");

        app = new App();
        mPersenter = new LoginPersenter(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                mPersenter.login(getApplicationContext(),getUserName(), getPassword());
                break;
            default:
                break;
        }
    }

    @Override
    public String getUserName() {
        return mEditTextUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEditTextPassword.getText().toString();
    }

    @Override
    public void showDialog() {
        mProgressDialog.show();
    }

    @Override
    public void hideDialog() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pushMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
