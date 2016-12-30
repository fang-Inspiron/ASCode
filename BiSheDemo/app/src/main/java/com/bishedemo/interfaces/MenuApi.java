package com.bishedemo.interfaces;

import com.bishedemo.bean.JokeBean;
import com.bishedemo.bean.MenuBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fang on 2016/12/13.
 */

public interface MenuApi {
    @GET("930-1")
    Call<MenuBean> getMenuList(@Query("num") String num, @Query("showapi_appid") String appid, @Query("word") String word, @Query("showapi_sign") String secret);
}
