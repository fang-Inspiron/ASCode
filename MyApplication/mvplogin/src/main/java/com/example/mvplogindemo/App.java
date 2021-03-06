package com.example.mvplogindemo;

import android.app.Application;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

/**
 * Created by jianfang on 2016/5/16.
 */
public class App extends Application {

    //应用密匙
    private static final String APP_KEY = "8722e67d30922a677bf0eaaea23ba794";
    private static BmobUser user;

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this,APP_KEY);
    }

    public static BmobUser getUser() {
        return user;
    }

    public static void setUser(BmobUser user) {
        App.user = user;
    }
}
