package com.bishedemo.note.ui.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import com.bishedemo.UniversalActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * activity基类
 * 
 * @author renhui
 */
public class BaseActivity extends UniversalActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 集成测试模式开启
		MobclickAgent.setDebugMode(true);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

}
