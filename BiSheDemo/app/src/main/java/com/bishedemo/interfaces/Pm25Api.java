package com.bishedemo.interfaces;

import com.bishedemo.bean.Pm25Bean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fang on 2016/12/13.
 */

public interface Pm25Api {
    @GET("104-29")
    Call<Pm25Bean> getPm25Info(@Query("city") String city, @Query("showapi_appid") String appid, @Query("showapi_sign") String secret);
}
