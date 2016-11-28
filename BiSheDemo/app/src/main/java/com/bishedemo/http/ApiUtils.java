package com.bishedemo.http;

import com.bishedemo.bean.Channel;
import com.bishedemo.bean.DetailNewsBean;
import com.bishedemo.interfaces.NewsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by fang on 2016/11/21.
 */

public class ApiUtils {

    public static final String URL_NEWS_BASE="http://v.juhe.cn/toutiao/";
    public static final String API_KEY = "68730f4dc5570ce9653bf25dc2dc853a";

    public static List<Channel> getChannelList() {
        List<Channel> list = new ArrayList<>();
        list.add(new Channel("头条", "toutiao"));
        list.add(new Channel("社会", "shehui"));
        list.add(new Channel("国内", "guonei"));
        list.add(new Channel("国际", "guoji"));
        list.add(new Channel("娱乐", "yule"));
        list.add(new Channel("体育", "tiyu"));
        list.add(new Channel("军事", "junshi"));
        list.add(new Channel("科技", "keji"));
        list.add(new Channel("财经", "caijing"));
        list.add(new Channel("时尚", "shishang"));

        return list;
    }

    public static void getChannelDetailList(String name, final GetListener<DetailNewsBean> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_NEWS_BASE)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        NewsApi newsApi = retrofit.create(NewsApi.class);
        Call<DetailNewsBean> call = newsApi.getDetailInfo(name, API_KEY);
        call.enqueue(new Callback<DetailNewsBean>() {
            @Override
            public void onResponse(Call<DetailNewsBean> call, retrofit2.Response<DetailNewsBean> response) {
                System.out.println(response.body().getError_code());
                listener.success(response.body());
            }
            @Override
            public void onFailure(Call<DetailNewsBean> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }




    public interface GetListener<T>{
        public void success(T t);
        public void error(String error);
    }

}
