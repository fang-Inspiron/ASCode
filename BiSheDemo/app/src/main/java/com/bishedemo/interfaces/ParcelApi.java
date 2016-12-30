package com.bishedemo.interfaces;

import com.bishedemo.bean.ParcelBean;
import com.bishedemo.bean.WeatherBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fang on 2016/12/7.
 */

public interface ParcelApi {
    @GET("880-1")
    Call<ParcelBean> getParcelInfo(@Query("nu") String nu, @Query("showapi_appid") String appid, @Query("showapi_sign") String secret);
}
