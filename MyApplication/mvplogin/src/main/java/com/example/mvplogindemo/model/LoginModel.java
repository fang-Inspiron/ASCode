package com.example.mvplogindemo.model;

import android.content.Context;

import com.example.mvplogindemo.App;
import com.example.mvplogindemo.ICallBack;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by jianfang on 2016/5/15.
 */
public class LoginModel implements ILoginModel {

    private int status = ILoginModel.NETWORK_ERROR;
    private String msg = "";

    @Override
    public void login(Context context, String username, String password, final ICallBack callBack) {
        if (validate(username,password)) {
            setStatus(ILoginModel.LOGIN_ING);
            callBack.status(this);

            final BmobUser user = new BmobUser();
            user.setUsername(username);
            user.setPassword(password);
            user.login(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    //登录成功
                    setStatus(ILoginModel.LOGIN_SUCCESS);
                    setMsg("登录成功");
                    App.setUser(user);
                    callBack.status(LoginModel.this);
                }

                @Override
                public void onFailure(int i, String s) {
                    System.out.println(s);
                    //登录失败
                    setStatus(ILoginModel.LOGIN_FALUIRE);
                    setMsg(s);
                    callBack.status(LoginModel.this);
                }
            });

        } else {
            callBack.status(this);
        }
    }

    //验证输入信息
    private boolean validate(String username, String password) {
        if (username.length() == 0) {
            status = ILoginModel.CHECK_FALUIRE;
            msg = "用户名不能为空";
            return false;
        }
        if (password.length() == 0) {
            status = ILoginModel.CHECK_FALUIRE;
            msg = "密码不能为空";
            return false;
        }
        return true;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
