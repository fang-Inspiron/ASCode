package com.example.funnymath;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by jianfang on 2016/4/5.
 */
public class AboutUsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
    }
}
