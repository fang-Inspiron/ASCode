package com.mynotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.jaydenxiao.common.base.BaseActivity;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;

    private String[] mTitles = {"首页", "日记", "记事本", "笑话"};
    private int[] mIconUnselectIds = {R.mipmap.rb_home_normal,
            R.mipmap.rb_dairy_normal, R.mipmap.rb_clock_normal, R.mipmap.rb_joke_normal};
    private int[] mIconSelectIds = {R.mipmap.rb_home_pressed,
            R.mipmap.rb_dairy_pressed, R.mipmap.rb_clock_pressed, R.mipmap.rb_joke_pressed};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }
}
