package com.qnote.application;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Environment;

import com.qnote.application.utils.VolleyUtil;

import java.io.File;

/**
 * Created by silei on 2016/9/6.
 */
public class Iapplication extends Application {
    public static final String userInfoSP="user_info";
    private static String userId;
    private static String token;


    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        Iapplication.userId = userId;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Iapplication.token = token;
    }


    /**
     * //初始化网络通信等模块
     */
    @Override
    public void onCreate() {
        super.onCreate();

        initData();
        VolleyUtil.initialize(this);


    }

    /**
     * 初始化数据
     */
    private void initData(){

        SharedPreferences sp=getSharedPreferences(userInfoSP,Application.MODE_PRIVATE);
        token=sp.getString("token","");
        userId=sp.getString("userId","");
    }
}
