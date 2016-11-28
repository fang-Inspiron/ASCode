package com.bishedemo.function;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bishedemo.R;
import com.bishedemo.UniversalActivity;

/**
 * Created by fang on 2016/11/15.
 */

public class ClockActivity extends UniversalActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_main);
    }

}
