package com.offlinecache.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.offlinecache.utils.HttpUtils;
import com.offlinecache.utils.UrlUtils;

/**
 * Created by fang on 2016/7/25.
 */
public class GetListNum extends Thread {

    Context context;
    Handler handler;
    String url;

    public GetListNum(Context context, Handler handler, String url) {
        this.context = context;
        this.handler = handler;
        this.url = url;
    }

    @Override
    public void run() {
        HttpUtils httpUtils = HttpUtils.getInstance(context, UrlUtils.FIND);
        httpUtils.http("fang", new HttpUtils.MyListener(){

            @Override
            public void onSuccess(String s) {
                System.out.println("成功s!!!!!"+s);
                Message msg = new Message();
            }

            @Override
            public void onFailure(String s) {
                System.out.println("失败s:"+s);
            }
        });
    }
}
