package com.bishedemo.lifehelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bishedemo.R;
import com.bishedemo.bean.WeatherBean;
import com.bishedemo.http.ApiUtils;
import com.bishedemo.utils.CommonAdapter;
import com.bishedemo.utils.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fang on 2016/11/29.
 */

public class WeatherActivity extends AppCompatActivity {

    private ListView mListView;
    private List<WeatherBean.ShowapiResBodyBean.DayListBean> mDatas;
    private TextView tv_city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_weather_main);
        setTitle(getIntent().getStringExtra("appTitle"));
        String area = getIntent().getStringExtra("city");

        tv_city = (TextView) findViewById(R.id.city);
        tv_city.setText(area);

        ApiUtils.getWeatherList(area,new ApiUtils.GetListener<WeatherBean>() {
            @Override
            public void success(WeatherBean bean) {
                mDatas=bean.getShowapi_res_body().getDayList();
                initView();
            }
            @Override
            public void error(String error) {
                System.out.println(error);
            }
        });
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new CommonAdapter<WeatherBean.ShowapiResBodyBean.DayListBean>(getApplicationContext(),mDatas,R.layout.weather_layout_item) {
            @Override
            public void convert(ViewHolder holder, WeatherBean.ShowapiResBodyBean.DayListBean dayListBean) {
                String time = dayListBean.getDaytime();
                holder.setText(R.id.tv_date, time.substring(4,6)+"/"+time.substring(6,8));
                ImageView iv_day = holder.getView(R.id.day_img);
                Picasso.with(getApplicationContext()).load(dayListBean.getDay_weather_pic()).into(iv_day);
                ImageView iv_night = holder.getView(R.id.night_img);
                Picasso.with(getApplicationContext()).load(dayListBean.getNight_weather_pic()).into(iv_night);
                holder.setText(R.id.day_weather, "白天天气："+dayListBean.getDay_weather());
                holder.setText(R.id.night_weather, "夜间天气："+dayListBean.getNight_weather());
                holder.setText(R.id.day_temperature, "白天温度："+dayListBean.getDay_air_temperature()+"℃");
                holder.setText(R.id.night_temperature, "夜间温度："+dayListBean.getNight_air_temperature()+"℃");
                holder.setText(R.id.day_wind_power, "白天风力："+dayListBean.getDay_wind_power());
                //holder.setText(R.id.night_wind_power, "夜间风力："+dayListBean.getNight_wind_power());
                holder.setText(R.id.night_wind_power, "夜间风力：微风");
                holder.setText(R.id.day_wind_direction, "白天风向："+dayListBean.getDay_wind_direction());
                holder.setText(R.id.night_wind_direction, "夜间风向："+dayListBean.getNight_wind_direction());
            }
        });
    }
}
