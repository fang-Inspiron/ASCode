package com.offlinecache.http;

import com.android.volley.RequestQueue;

/**
 * Created by fang on 2016/7/25.
 */
public class GetListNum extends Thread {

    String url = "http://192.168.1.215:8080/FindServlet";
    RequestQueue requestQueue;

    @Override
    public void run() {
        super.run();


    }
}
