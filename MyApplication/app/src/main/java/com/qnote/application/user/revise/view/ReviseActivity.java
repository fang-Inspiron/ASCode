package com.qnote.application.user.revise.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.qnote.application.R;
import com.qnote.application.user.login.view.LoginActivity;
import com.qnote.application.user.revise.presenter.RevisePresenter;
import com.qnote.application.utils.CustomToast;

public class ReviseActivity extends Activity {
	private Button backBT;
	private EditText oldPWET;
	private EditText newPW1ET;
	private EditText newPW2ET;
	private Button enterBT;
	private String oldPW;
	private String newPW1;
	private String newPW2;
	private ProgressDialog dialog;

	RevisePresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_revise_pw);
		presenter = new RevisePresenter(this);
		initView();
	}

	private void initView() {
		backBT = (Button) findViewById(R.id.back);
		oldPWET = (EditText) findViewById(R.id.oldPWET);
		newPW1ET = (EditText) findViewById(R.id.newPW1ET);
		newPW2ET = (EditText) findViewById(R.id.newPW2ET);
		enterBT = (Button) findViewById(R.id.enterBT);
		initListener();
	}

	private void initListener() {
		backBT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		enterBT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				oldPW = oldPWET.getText().toString().trim();
				newPW1 = newPW1ET.getText().toString().trim();
				newPW2 = newPW2ET.getText().toString().trim();
				if (oldPW.length() < 6) {
					showMsg("请输入正确密码");
				} else if (newPW1.length() < 6) {
					showMsg("请输入6-16位新密码");
				} else if (!newPW1.equals(newPW2)) {
					showMsg("两次密码输入不一致");
				} else {
					changePW(oldPW, newPW1);
				}
			}
		});

	}

	private void changePW(String oldPW, String newPW) {
		presenter.changePW(oldPW, newPW);
	}

	public void showMsg(String msg) {
		CustomToast.showToast(this, msg, 2000);
	}

	public void showDialog() {

		if (dialog == null) {
			dialog = new ProgressDialog(this);
		}
		dialog.show();
	}

	public void dismissDialog() {
		if (dialog != null) {
			dialog.cancel();
		}
	}

	public void startLogin() {
		startActivity(new Intent(this, LoginActivity.class));
		finish();
	}
}
