package com.offlinecache.http;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.offlinecache.Bean.User;
import com.offlinecache.utils.HttpUtils;
import com.offlinecache.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fang on 2016/7/21.
 */
public class UploadData extends Thread{

    Context context;
    Handler handler;
    String url;

    public UploadData(Context context, Handler handler, String url) {
        this.context = context;
        this.handler = handler;
        this.url = url;
    }

    @Override
    public void run() {
        super.run();
        //先上传数据，再得到list.size()（用于命名文件名）
        HttpUtils httpUtils = HttpUtils.getInstance(context, UrlUtils.UPDATE);
        final List<String> list =  new ArrayList<>();
        boolean flag = false;
        for (String item: list) {
            if(item.equals(url))
                flag = true;    //如果list中已有该url
        }
        if (!flag){
            list.add(url);
        }
        User user = new User("fang", "123456", list);
        httpUtils.http(user, new HttpUtils.MyListener() {
            @Override
            public void onSuccess(String s) {
                System.out.println("UploadData--onSuccess:"+s);
                //发送消息
                Message msg = new Message();
                msg.arg1 = list.size();
                msg.what = 100;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(String s) {
                System.out.println("UploadData--onFailure:"+s);
                handler.sendEmptyMessage(101);
            }
        });
    }
}
