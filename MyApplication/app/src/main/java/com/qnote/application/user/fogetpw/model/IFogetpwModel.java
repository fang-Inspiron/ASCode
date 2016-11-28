package com.qnote.application.user.fogetpw.model;


import com.qnote.application.utils.CallBack;

public interface IFogetpwModel {
	int Operate_ok = 1;
	int System_error = 3;
	int Input_error = 4;


	void resetPW(String phoneNumber, String password,
				 CallBack<FogetpwModel> callBack);
}
