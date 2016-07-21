package com.example.mvplogindemo.presenter;

import android.content.Context;

import com.example.mvplogindemo.ICallBack;
import com.example.mvplogindemo.model.ILoginModel;
import com.example.mvplogindemo.model.LoginModel;
import com.example.mvplogindemo.view.ILoginView;

/**
 * Created by jianfang on 2016/5/15.
 */
public class LoginPersenter {
    private ILoginView mLoginView;// 视图层
    private ILoginModel mLoginModel;// 逻辑层

    public LoginPersenter(ILoginView view) {
        mLoginView = view;
        mLoginModel = new LoginModel();
    }

    public void login(Context context,String username, String password) {
        mLoginModel.login(context, username, password, new ICallBack() {
            @Override
            public void status(LoginModel model) {
                switch (model.getStatus()) {
                    case ILoginModel.LOGIN_SUCCESS:
                        mLoginView.hideDialog();
                        mLoginView.pushMainActivity();
                        break;
                    case ILoginModel.LOGIN_FALUIRE:
                        mLoginView.hideDialog();
                        mLoginView.showMsg(model.getMsg());
                        break;
                    case ILoginModel.LOGIN_ING:
                        mLoginView.showDialog();
                        break;
                    case ILoginModel.CHECK_FALUIRE:
                        mLoginView.showMsg(model.getMsg());
                        break;
                }
            }
        });
    }

}
