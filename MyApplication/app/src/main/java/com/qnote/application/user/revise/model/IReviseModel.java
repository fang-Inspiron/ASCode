package com.qnote.application.user.revise.model;


import com.qnote.application.utils.CallBack;

public interface IReviseModel {
	int TRUE = 1;
	int FALSE = 2;
	int NOTOKEN = 3;

	void changePW(final String oldPW, final String newPW,
				  final CallBack<ReviseModel> callBack);
}
