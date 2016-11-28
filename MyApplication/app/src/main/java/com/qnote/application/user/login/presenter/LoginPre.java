package com.qnote.application.user.login.presenter;

import com.qnote.application.user.login.model.ILoginModel;
import com.qnote.application.user.login.model.LoginModel;
import com.qnote.application.user.login.view.ILoginView;
import com.qnote.application.utils.CallBack;
import com.qnote.application.utils.MD5Util;


public class LoginPre {
    ILoginView view;
    ILoginModel model;

    public LoginPre(ILoginView view) {
        this.view = view;
        model = new LoginModel();
    }

    public void login(final String phoneNumber, final String password) {
        view.showDialog();
        model.login(phoneNumber, MD5Util.MD5(password),
                new CallBack<LoginModel>() {

                    @Override
                    public void getModel(LoginModel model) {
                        view.showMsg(model.msg);
                        switch (model.state) {
                            case ILoginModel.Login_ok:
                                saveUserInfo(phoneNumber, password, model.getToken());

                                view.dismissDialog();
                                view.back(true, model.getToken());
                                break;
                            default:
                                view.dismissDialog();
                                break;
                        }
                    }
                });
    }

    private void saveUserInfo(String userId, String password, String token) {
        //// TODO: 2016/9/6 存储到SharedPreferences
       view.saveUserInfo(userId,MD5Util.MD5(password),token);
    }
}
