package com.example.mvplogindemo.view;

/**
 * Created by jianfang on 2016/5/15.
 */
public interface ILoginView {
    //获取用户名
    public String getUserName();
    //获取密码
    public String getPassword();
    //显示Dialog
    public void showDialog();
    //隐藏Dialog
    public void hideDialog();
    //显示信息
    public void showMsg(String msg);
    //跳入Activity
    public void pushMainActivity();
}
