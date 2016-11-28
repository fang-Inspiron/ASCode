package com.qnote.application.user.revise.presenter;

import com.qnote.application.user.revise.model.IReviseModel;
import com.qnote.application.user.revise.model.ReviseModel;
import com.qnote.application.user.revise.view.ReviseActivity;
import com.qnote.application.utils.CallBack;

public class RevisePresenter {
    ReviseActivity view;
    IReviseModel model;

    public RevisePresenter(ReviseActivity view) {
        this.view = view;
        model = new ReviseModel();
    }

    public void changePW(String oldPW, String newPW) {
        view.showDialog();
        model.changePW(oldPW, newPW, new CallBack<ReviseModel>() {

            @Override
            public void getModel(ReviseModel model) {
                switch (model.state) {
                    case IReviseModel.TRUE:
                        view.dismissDialog();
                        view.showMsg(model.msg);
                        view.startLogin();
                        break;
                    case IReviseModel.FALSE:
                        view.dismissDialog();
                        view.showMsg(model.msg);
                        break;
                    case IReviseModel.NOTOKEN:
                        view.dismissDialog();
                        view.showMsg(model.msg);
                        view.startLogin();
                }
            }
        });

    }
}
