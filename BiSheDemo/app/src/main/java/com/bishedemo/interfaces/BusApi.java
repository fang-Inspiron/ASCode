package com.bishedemo.interfaces;

import com.bishedemo.bean.BusBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fang on 2017/5/10.
 */

public interface BusApi {
    @GET("844-2")
    Call<BusBean> getBusInfo(@Query("city") String city, @Query("busNo") String busNo, @Query("showapi_appid") String appid, @Query("showapi_sign") String secret);
}
