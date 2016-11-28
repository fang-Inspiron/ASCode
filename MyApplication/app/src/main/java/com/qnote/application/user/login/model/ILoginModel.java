package com.qnote.application.user.login.model;

import com.qnote.application.utils.CallBack;

public interface ILoginModel {
    int Login_ok = 1;
    int Password_error = 2;
    int System_error = 3;
    int Input_error = 4;

    void login(final String phoneNumber, final String password,
               final CallBack<LoginModel> callback);
}
