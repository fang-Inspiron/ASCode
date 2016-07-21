package com.nemo.practice;

import android.os.Handler;
import android.os.Message;

/**
 * Created by nemo on 2016/5/5 0005.
 */
public class MyThread implements Runnable{

    private Handler handler;
    private Handler handler1;

    public MyThread(Handler handler,Handler handler1){
        this.handler = handler;
        this.handler1 = handler1;
    }

    @Override
    public void run() {
        Message m = new Message();
        m.arg1 = 123;
        handler.sendMessage(m);
        Message m1 = new Message();
        m1.arg1 = 1234;
        handler1.sendMessage(m1);
    }
}
