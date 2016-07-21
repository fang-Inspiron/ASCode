package com.nemo.practice;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;


/**
 * Created by nemo on 2016/5/5 0005.
 */
public class Test extends Thread{

    @Override
    public void run() {
        Looper.prepare();
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.arg1==123){
                    System.out.println("copy that");
                }
                super.handleMessage(msg);
                System.out.println(Looper.myLooper()==Looper.getMainLooper());
            }
        };
        Handler handler1 = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.arg1==1234){
                    System.out.println("copy that1");
                }
                super.handleMessage(msg);
                System.out.println(Looper.myLooper()==Looper.getMainLooper());
            }
        };
        new Thread(new MyThread(handler,handler1)).start();
        Looper.loop();
    }
}
