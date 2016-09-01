package com.offlinecache.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.offlinecache.Bean.User;

import org.json.JSONObject;
import org.jsoup.helper.HttpConnection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by fang on 2016/7/21.
 */
public class HttpUtils {

    static HttpUtils httpUtils;
    Context context;
    static String url;
    MyListener myListener;
    RequestQueue requestQueue;

    private HttpUtils(Context context, String url){
        this.context = context;
        this.url = url;
        requestQueue = Volley.newRequestQueue(context);
    }

    public static HttpUtils getInstance(Context context, String url) {
        if (httpUtils==null) {
            httpUtils = new HttpUtils(context, url);
        }
        httpUtils.setUrl(url);
        return httpUtils;
    }

    public void setUrl(String url) {
        this.url = url;
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
                System.out.println("VolleyError:"+error.toString());
                myListener.onFailure(error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

    public void http(final String username, final MyListener myListener) {
        StringRequest stringRequest = new StringRequest(url+"?username="+username,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("HttpUtils==="+response.toString());
                        myListener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                myListener.onFailure("error:"+error.toString());
            }
        });
        requestQueue.add(stringRequest);

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        System.out.println("@@@@@JSONRequest:"+response.toString());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                System.out.println("@@@@@@error:"+error);
//            }
//        });
    }

    public interface MyListener{
        void onSuccess(String s);
        void onFailure(String s);
    }

    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?username=" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
