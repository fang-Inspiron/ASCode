package com.qnote.application.user.fogetpw.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.qnote.application.R;
import com.qnote.application.user.fogetpw.presenter.FogetpwPresenter;
import com.qnote.application.utils.CustomToast;


public class FogetpwActivity extends Activity implements IFogetpwView {

    Button enterBT;
    Button backBT;
    EditText userIdET;
    EditText mailET;
    String userId;
    String mailAddress;

    ProgressDialog progressDialog;
    FogetpwPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fogetpw);
        presenter = new FogetpwPresenter(this);
        initView();
    }

    private void initView() {
        enterBT = (Button) findViewById(R.id.enterBT);
        backBT = (Button) findViewById(R.id.back);
        userIdET = (EditText) findViewById(R.id.userIdET);
        mailET = (EditText) findViewById(R.id.mailET);
        initListener();
    }

    private void initListener() {
        enterBT.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                userId = userIdET.getText().toString().trim();
                mailAddress = mailET.getText().toString().trim();
                if (userId == null || userId.length() <= 0) {
                    showMsg("请输入账户名");
                } else if (mailAddress == null || mailAddress.equals("")) {
                    showMsg("请输入邮箱地址");
                } else {
                    presenter.resetPW(userId, mailAddress);
                }
            }
        });
        backBT.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void showMsg(String msg) {
        CustomToast.showToast(FogetpwActivity.this, msg, 2000);
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
    public void back() {
        finish();
    }
}
