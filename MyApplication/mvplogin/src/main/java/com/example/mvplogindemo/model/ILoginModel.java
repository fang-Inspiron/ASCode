package com.example.mvplogindemo.model;

import android.content.Context;

import com.example.mvplogindemo.ICallBack;

/**
 * Created by jianfang on 2016/5/15.
 */
public interface ILoginModel {
    public static final int NETWORK_ERROR = 1;
    public static final int LOGIN_SUCCESS = 2;
    public static final int LOGIN_FALUIRE = 3;
    public static final int LOGIN_ING = 4;
    public static final int CHECK_FALUIRE = 5;

    public void login(Context context, String username, String password, ICallBack callBack);
}
