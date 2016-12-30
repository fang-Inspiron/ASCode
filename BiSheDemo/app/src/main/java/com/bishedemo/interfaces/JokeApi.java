package com.bishedemo.interfaces;

import com.bishedemo.bean.IpBean;
import com.bishedemo.bean.JokeBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fang on 2016/12/13.
 */

public interface JokeApi {
    @GET("107-32")
    Call<JokeBean> getJokeList(@Query("showapi_appid") String appid, @Query("showapi_sign") String secret);
}
