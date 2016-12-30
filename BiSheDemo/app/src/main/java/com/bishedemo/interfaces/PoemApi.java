package com.bishedemo.interfaces;

import com.bishedemo.bean.MenuBean;
import com.bishedemo.bean.PoemBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fang on 2016/12/13.
 */

public interface PoemApi {
    @GET("950-1")
    Call<PoemBean> getPoemInfo(@Query("key") String key, @Query("num") String num, @Query("showapi_appid") String appid, @Query("type") String type, @Query("yayuntype") String yayuntype, @Query("showapi_sign") String secret);
}
