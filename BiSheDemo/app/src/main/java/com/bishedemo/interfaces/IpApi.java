package com.bishedemo.interfaces;

import com.bishedemo.bean.IpBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fang on 2016/12/13.
 */

public interface IpApi {
    @GET("632-1")
    Call<IpBean> getIpInfo(@Query("showapi_appid") String appid, @Query("showapi_sign") String secret);
}
