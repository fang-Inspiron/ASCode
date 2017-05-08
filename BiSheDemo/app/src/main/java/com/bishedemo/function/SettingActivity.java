package com.bishedemo.function;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bishedemo.R;
import com.bishedemo.UniversalActivity;
import com.bishedemo.setting.AboutActivity;
import com.bishedemo.setting.IntroduceActivity;
import com.bishedemo.utils.CleanMessageUtil;

/**
 * Created by fang on 2016/11/15.
 */

public class SettingActivity extends UniversalActivity implements View.OnClickListener{
    private RelativeLayout layout_introduce;
    private RelativeLayout layout_about;
    private RelativeLayout layout_clean;
    private TextView tv_cacheSize;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main);

        findId();
        try {
            tv_cacheSize.setText(CleanMessageUtil.getTotalCacheSize(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void findId() {
        layout_introduce = (RelativeLayout) findViewById(R.id.layout_introduce);
        layout_about = (RelativeLayout) findViewById(R.id.layout_about);
        layout_clean = (RelativeLayout) findViewById(R.id.layout_clean);
        tv_cacheSize = (TextView) findViewById(R.id.tv_cacheSize);

        layout_introduce.setOnClickListener(this);
        layout_about.setOnClickListener(this);
        layout_clean.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.layout_introduce:
                intent = new Intent(this, IntroduceActivity.class);
                intent.putExtra("settingTitle", "产品介绍");
                startActivity(intent);
                break;
            case R.id.layout_about:
                intent = new Intent(this, AboutActivity.class);
                intent.putExtra("settingTitle", "关于我们");
                startActivity(intent);
                break;
            case R.id.layout_clean:
                try {
                    CleanMessageUtil.clearAllCache(getApplicationContext());
                    tv_cacheSize.setText(CleanMessageUtil.getTotalCacheSize(getApplicationContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }
    }
}
