package com.bishedemo.function;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bishedemo.R;
import com.bishedemo.UniversalActivity;
import com.bishedemo.bean.IpBean;
import com.bishedemo.http.ApiUtils;
import com.bishedemo.lifehelper.BusActivity;
import com.bishedemo.lifehelper.JokeActivity;
import com.bishedemo.lifehelper.MenuActivity;
import com.bishedemo.lifehelper.ParcelActivity;
import com.bishedemo.lifehelper.Pm25Activity;
import com.bishedemo.lifehelper.PoemActivity;
import com.bishedemo.lifehelper.WeatherActivity;

/**
 * Created by fang on 2016/11/15.
 */

public class LifeHelperActivity extends UniversalActivity implements View.OnClickListener {

    private RelativeLayout layout_weather;
    private RelativeLayout layout_pm25;
    private RelativeLayout layout_bus;
    private RelativeLayout layout_parcel;
    private RelativeLayout layout_joke;
    private RelativeLayout layout_recipe;
    private RelativeLayout layout_poem;
    private TextView tv_weather;
    private TextView tv_pm25;
    private TextView tv_bus;
    private TextView tv_parcel;
    private TextView tv_joke;
    private TextView tv_recipe;
    private TextView tv_poem;
    private String city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.life_helper_main);

        getIp();
        initView();
    }

    private void getIp() {
        ApiUtils.getIpInfo(new ApiUtils.GetListener<IpBean>(){

            @Override
            public void success(IpBean ipBean) {
                city = ipBean.getShowapi_res_body().getCity();
                System.out.println("$$$$$$$$city:"+city);
            }

            @Override
            public void error(String error) {
                System.out.println(error);
            }
        });
    }

    private void initView() {
        tv_weather = (TextView) findViewById(R.id.tv_weather);
        tv_pm25 = (TextView) findViewById(R.id.tv_pm25);
        tv_bus = (TextView) findViewById(R.id.tv_bus);
        tv_recipe = (TextView) findViewById(R.id.tv_recipe);
        tv_joke = (TextView) findViewById(R.id.tv_joke);
        tv_parcel = (TextView) findViewById(R.id.tv_parcel);
        tv_poem = (TextView) findViewById(R.id.tv_poem);
        layout_weather = (RelativeLayout) findViewById(R.id.layout_weather);
        layout_pm25 = (RelativeLayout) findViewById(R.id.layout_pm25);
        layout_bus = (RelativeLayout) findViewById(R.id.layout_bus);
        layout_recipe = (RelativeLayout) findViewById(R.id.layout_recipe);
        layout_joke = (RelativeLayout) findViewById(R.id.layout_joke);
        layout_parcel = (RelativeLayout) findViewById(R.id.layout_parcel);
        layout_poem = (RelativeLayout) findViewById(R.id.layout_poem);
        layout_weather.setOnClickListener(this);
        layout_pm25.setOnClickListener(this);
        layout_bus.setOnClickListener(this);
        layout_recipe.setOnClickListener(this);
        layout_joke.setOnClickListener(this);
        layout_parcel.setOnClickListener(this);
        layout_poem.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.layout_weather:
                intent = new Intent(LifeHelperActivity.this, WeatherActivity.class);
                intent.putExtra("appTitle", tv_weather.getText());
                intent.putExtra("city", city);
                startActivity(intent);
                break;
            case R.id.layout_pm25:
                intent = new Intent(LifeHelperActivity.this, Pm25Activity.class);
                intent.putExtra("appTitle", tv_pm25.getText());
                intent.putExtra("city", city);
                startActivity(intent);
                break;
            case R.id.layout_bus:
                intent = new Intent(LifeHelperActivity.this, BusActivity.class);
                intent.putExtra("appTitle", tv_bus.getText());
                startActivity(intent);
                break;
            case R.id.layout_parcel:
                intent = new Intent(LifeHelperActivity.this, ParcelActivity.class);
                intent.putExtra("appTitle", tv_parcel.getText());
                startActivity(intent);
                break;
            case R.id.layout_joke:
                intent = new Intent(LifeHelperActivity.this, JokeActivity.class);
                intent.putExtra("appTitle", tv_joke.getText());
                startActivity(intent);
                break;
            case R.id.layout_recipe:
                intent = new Intent(LifeHelperActivity.this, MenuActivity.class);
                intent.putExtra("appTitle", tv_recipe.getText());
                startActivity(intent);
                break;
            case R.id.layout_poem:
                intent = new Intent(LifeHelperActivity.this, PoemActivity.class);
                intent.putExtra("appTitle", tv_poem.getText());
                startActivity(intent);
                break;
        }
    }
}
