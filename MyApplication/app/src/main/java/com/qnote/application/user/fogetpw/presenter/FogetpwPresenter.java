package com.qnote.application.user.fogetpw.presenter;


import com.qnote.application.user.fogetpw.model.FogetpwModel;
import com.qnote.application.user.fogetpw.model.IFogetpwModel;
import com.qnote.application.user.fogetpw.view.IFogetpwView;
import com.qnote.application.utils.CallBack;

public class FogetpwPresenter {
	IFogetpwView view;
	IFogetpwModel model;

	public FogetpwPresenter(IFogetpwView view) {
		this.view = view;
		model = new FogetpwModel();
	}


	public void resetPW(String userId, String mailAddress) {
		view.showDialog();
		model.resetPW(userId, mailAddress, new CallBack<FogetpwModel>() {

			@Override
			public void getModel(FogetpwModel model) {
				view.dismissDialog();
				switch (model.state) {
				case IFogetpwModel.Operate_ok:
					view.showMsg(model.msg);
					view.back();
					break;
				case IFogetpwModel.Input_error:
					view.showMsg(model.msg);
					break;
				case IFogetpwModel.System_error:
					view.showMsg(model.msg);
					break;
				default:
					view.showMsg(model.msg);
					break;
				}
			}
		});

	}
}
