package com.qnote.application.user.login.view;

public interface ILoginView {
	void login(String phoneNumber, String password);

	void showMsg(String msg);

	void showDialog();

	void dismissDialog();

	void back(boolean loginState,String token);

	void saveUserInfo(String phoneNumber, String password, String token);
}
