package com.bishedemo.http;

import com.bishedemo.bean.BusBean;
import com.bishedemo.bean.Channel;
import com.bishedemo.bean.DetailNewsBean;
import com.bishedemo.bean.IpBean;
import com.bishedemo.bean.JokeBean;
import com.bishedemo.bean.MenuBean;
import com.bishedemo.bean.ParcelBean;
import com.bishedemo.bean.Pm25Bean;
import com.bishedemo.bean.PoemBean;
import com.bishedemo.bean.WeatherBean;
import com.bishedemo.interfaces.BusApi;
import com.bishedemo.interfaces.IpApi;
import com.bishedemo.interfaces.JokeApi;
import com.bishedemo.interfaces.MenuApi;
import com.bishedemo.interfaces.NewsApi;
import com.bishedemo.interfaces.ParcelApi;
import com.bishedemo.interfaces.Pm25Api;
import com.bishedemo.interfaces.PoemApi;
import com.bishedemo.interfaces.WeatherApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by fang on 2016/11/21.
 */

public class ApiUtils {

    //新闻--使用的是聚合数据
    public static final String URL_NEWS_BASE="http://v.juhe.cn/toutiao/";
    public static final String API_KEY = "68730f4dc5570ce9653bf25dc2dc853a";
    //生活小助手--使用的是SHOWAPI
    public static final String URL_LIFE_HELPER_BASE="http://route.showapi.com/";
    public static final String APP_SECRET = "d16a31f9b32f43149d3866f9ace18aae";
    public static final String APP_ID = "23948";


    public static List<Channel> getChannelList() {
        List<Channel> list = new ArrayList<>();
        list.add(new Channel("头条", "toutiao"));
        list.add(new Channel("国内", "guonei"));
        list.add(new Channel("国际", "guoji"));
        list.add(new Channel("科技", "keji"));
        list.add(new Channel("娱乐", "yule"));
        list.add(new Channel("体育", "tiyu"));
        list.add(new Channel("社会", "shehui"));
        list.add(new Channel("军事", "junshi"));
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

    public static void getIpInfo(final GetListener<IpBean> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_LIFE_HELPER_BASE)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        IpApi ipApi = retrofit.create(IpApi.class);
        Call<IpBean> call = ipApi.getIpInfo(APP_ID,APP_SECRET);
        call.enqueue(new Callback<IpBean>() {
            @Override
            public void onResponse(Call<IpBean> call, Response<IpBean> response) {
                listener.success(response.body());
            }

            @Override
            public void onFailure(Call<IpBean> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }

    public static void getWeatherList(String area, final GetListener<WeatherBean> listener) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_LIFE_HELPER_BASE)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<WeatherBean> call = weatherApi.getWeatherInfo(area, APP_ID, APP_SECRET);
        call.enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                listener.success(response.body());
            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });

    }

    public static void getPm25Info(String city, final GetListener<Pm25Bean> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_LIFE_HELPER_BASE)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        Pm25Api pm25Api = retrofit.create(Pm25Api.class);
        Call<Pm25Bean> call = pm25Api.getPm25Info(city, APP_ID, APP_SECRET);
        call.enqueue(new Callback<Pm25Bean>() {
            @Override
            public void onResponse(Call<Pm25Bean> call, Response<Pm25Bean> response) {
                listener.success(response.body());
            }

            @Override
            public void onFailure(Call<Pm25Bean> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });

    }

    public static void getBusInfo(String city, String busNo, final GetListener<BusBean> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_LIFE_HELPER_BASE)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        BusApi busApi = retrofit.create(BusApi.class);
        Call<BusBean> call = busApi.getBusInfo(city,busNo, APP_ID, APP_SECRET);
        call.enqueue(new Callback<BusBean>() {
            @Override
            public void onResponse(Call<BusBean> call, Response<BusBean> response) {
                listener.success(response.body());
            }

            @Override
            public void onFailure(Call<BusBean> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }

    public static void getJokeList(final GetListener<JokeBean> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_LIFE_HELPER_BASE)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        JokeApi jokeApi = retrofit.create(JokeApi.class);
        Call<JokeBean> call = jokeApi.getJokeList(APP_ID,APP_SECRET);
        call.enqueue(new Callback<JokeBean>() {
            @Override
            public void onResponse(Call<JokeBean> call, Response<JokeBean> response) {
                listener.success(response.body());
            }

            @Override
            public void onFailure(Call<JokeBean> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }

    public static void getMenuList(String num, String word, final GetListener<MenuBean> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_LIFE_HELPER_BASE)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        MenuApi menuApi = retrofit.create(MenuApi.class);
        Call<MenuBean> call = menuApi.getMenuList(num,APP_ID,word,APP_SECRET);
        call.enqueue(new Callback<MenuBean>() {
            @Override
            public void onResponse(Call<MenuBean> call, Response<MenuBean> response) {
                listener.success(response.body());
            }

            @Override
            public void onFailure(Call<MenuBean> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });
    }

    public static void getParcelInfo(String nu, final GetListener<ParcelBean> listener) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_LIFE_HELPER_BASE)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        ParcelApi parcelApi = retrofit.create(ParcelApi.class);
        Call<ParcelBean> call = parcelApi.getParcelInfo(nu, APP_ID, APP_SECRET);
        call.enqueue(new Callback<ParcelBean>() {
            @Override
            public void onResponse(Call<ParcelBean> call, Response<ParcelBean> response) {
                listener.success(response.body());
            }

            @Override
            public void onFailure(Call<ParcelBean> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });

    }

    public static void getPoemInfo(String key, String num, String type, String yayuntype, final GetListener<PoemBean> listener) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_LIFE_HELPER_BASE)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        PoemApi poemApi = retrofit.create(PoemApi.class);
        Call<PoemBean> call = poemApi.getPoemInfo(key, num, APP_ID, type, yayuntype, APP_SECRET);
        call.enqueue(new Callback<PoemBean>() {
            @Override
            public void onResponse(Call<PoemBean> call, Response<PoemBean> response) {
                listener.success(response.body());
            }

            @Override
            public void onFailure(Call<PoemBean> call, Throwable t) {
                listener.error(t.getMessage());
            }
        });

    }

    public interface GetListener<T>{
        public void success(T t);
        public void error(String error);
    }

}
