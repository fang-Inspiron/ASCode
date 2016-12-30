package com.bishedemo.function;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.bishedemo.R;
import com.bishedemo.UniversalActivity;

/**
 * Created by fang on 2016/11/15.
 */


//由于使用一些成员变量时权限问题，才放到该包下
public class ClockActivity extends UniversalActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_main);

    }


}
