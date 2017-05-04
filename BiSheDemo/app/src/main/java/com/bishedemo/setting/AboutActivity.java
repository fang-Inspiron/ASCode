package com.bishedemo.setting;

import android.os.Bundle;

import com.bishedemo.R;
import com.bishedemo.UniversalActivity;

/**
 * Created by fang on 2017/5/3.
 */

public class AboutActivity extends UniversalActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_about);
        setTitle(getIntent().getStringExtra("settingTitle"));
    }
}
