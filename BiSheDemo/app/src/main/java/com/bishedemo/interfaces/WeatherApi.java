package com.bishedemo.interfaces;

import com.bishedemo.bean.WeatherBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fang on 2016/12/7.
 */

public interface WeatherApi {
    @GET("9-9")
    Call<WeatherBean> getWeatherInfo(@Query("area") String area, @Query("showapi_appid") String appid, @Query("showapi_sign") String secret);
}
