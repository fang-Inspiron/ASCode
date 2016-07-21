package com.example.mvplogindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.bmob.v3.BmobUser;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewUserName, mTextViewEmail;
    private BmobUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = App.getUser();

        mTextViewUserName = (TextView) findViewById(R.id.tv_username);
        mTextViewEmail = (TextView) findViewById(R.id.tv_email);

        mTextViewUserName.setText("用户名：" + user.getUsername());
        mTextViewEmail.setText("Email： " + user.getEmail());
    }
}
