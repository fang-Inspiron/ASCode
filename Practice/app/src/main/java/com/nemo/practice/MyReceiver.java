package com.nemo.practice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by nemo on 2016/5/1 0001.
 */
public class MyReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg=intent.getExtras().get("msg").toString();
        Toast.makeText(context,"intent.getAction()"+intent.getAction().toString(),
                Toast.LENGTH_LONG).show();
        System.out.println("msg:"+msg);
    }
}
