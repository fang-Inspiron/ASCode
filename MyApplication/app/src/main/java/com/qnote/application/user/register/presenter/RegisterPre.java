package com.qnote.application.user.register.presenter;

import com.qnote.application.user.register.mode.IRegisterMode;
import com.qnote.application.user.register.mode.RegisterMode;
import com.qnote.application.user.register.view.IRegister;

public class RegisterPre {
    private IRegister view;
    private IRegisterMode mode;

    public RegisterPre(IRegister view) {
        this.view = view;
        mode = new RegisterMode(this);
    }


    public void showMsg(String msg) {
        view.showMsg(msg);
    }

    public void register(String phoneNumber, String mailAddress, String password) {
        view.showDialog();
        mode.register(phoneNumber, mailAddress, password);
    }

    public void regist_fail() {
        view.dismissDialog();
    }

    public void regist_Success() {
        view.dismissDialog();
        view.back();
    }

}
