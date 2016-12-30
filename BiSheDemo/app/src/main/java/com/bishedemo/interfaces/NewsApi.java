package com.bishedemo.interfaces;

import com.bishedemo.bean.DetailNewsBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fang on 2016/11/21.
 */

public interface NewsApi {

    @GET("index")
    Call<DetailNewsBean> getDetailInfo(@Query("type") String type, @Query("key") String key);

}
