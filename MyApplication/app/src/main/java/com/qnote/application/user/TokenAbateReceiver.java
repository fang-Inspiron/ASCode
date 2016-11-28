package com.qnote.application.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.qnote.application.user.login.view.LoginActivity;

public class TokenAbateReceiver extends BroadcastReceiver {
    /**
     * 当用户token失效时，唤起登录界面
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i=new Intent(context, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
