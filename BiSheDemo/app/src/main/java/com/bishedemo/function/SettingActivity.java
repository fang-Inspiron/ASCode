package com.bishedemo.function;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bishedemo.R;
import com.bishedemo.UniversalActivity;

/**
 * Created by fang on 2016/11/15.
 */

public class SettingActivity extends UniversalActivity{

    private RelativeLayout layout_introduce;
    private RelativeLayout layout_about;
    private RelativeLayout layout_clean;
    private RelativeLayout layout_night;
    private TextView tv_introduce;
    private TextView tv_about;
    private TextView tv_clean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main);

        findId();
    }

    private void findId() {
        layout_introduce = (RelativeLayout) findViewById(R.id.layout_introduce);
        layout_about = (RelativeLayout) findViewById(R.id.layout_about);
        layout_clean = (RelativeLayout) findViewById(R.id.layout_clean);
        layout_night = (RelativeLayout) findViewById(R.id.layout_night);
        tv_introduce = (TextView) findViewById(R.id.tv_introduce);
        tv_about = (TextView) findViewById(R.id.tv_about);
        tv_clean = (TextView) findViewById(R.id.tv_clean);
    }

}
