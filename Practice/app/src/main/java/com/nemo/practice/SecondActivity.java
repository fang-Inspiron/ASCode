package com.nemo.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by nemo on 2016/5/5 0005.
 */
public class SecondActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_second);
        Intent intent = new Intent();
        intent.putExtra("msg","hello world");
        setResult(Activity.RESULT_OK,intent);
    }
}
