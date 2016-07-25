package com.offlinecache.utils;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.offlinecache.Bean.User;

import java.security.Policy;

/**
 * Created by fang on 2016/7/21.
 */
public class HttpUtils {

    String url = "http://192.168.1.215:8080/UpdateServlet";
    MyListener myListener;
    RequestQueue requestQueue;

    public HttpUtils(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }

    public void http(final User user, final MyListener myListener) {
        StringRequest stringRequest = new StringRequest(url+"?username="+user.getUsername()+"&password="+user.getPassword()+"&links="+user.getLinks().toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        myListener.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                myListener.onFailure(error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

    public interface MyListener{
        void onSuccess(String s);
        void onFailure(String s);
    }
}
