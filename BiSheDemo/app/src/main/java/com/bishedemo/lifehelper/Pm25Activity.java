package com.bishedemo.lifehelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bishedemo.R;
import com.bishedemo.bean.Pm25Bean;
import com.bishedemo.http.ApiUtils;

/**
 * Created by fang on 2016/12/13.
 */

public class Pm25Activity extends AppCompatActivity {
    private TextView tv_city;
    private TextView tv_pm25;
    private TextView tv_primary_pollutant;
    private TextView tv_quality;
    private TextView tv_pm10;
    private TextView tv_so2;
    private TextView tv_o3;
    private TextView tv_co;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_pm25_main);
        setTitle(getIntent().getStringExtra("appTitle"));
        String city = getIntent().getStringExtra("city");

        initView();
        tv_city.setText(city);
        ApiUtils.getPm25Info(city, new ApiUtils.GetListener<Pm25Bean>() {
            @Override
            public void success(Pm25Bean pm25Bean) {
                tv_pm25.setText("PM2.5："+pm25Bean.getShowapi_res_body().getPm().getPm2_5()+"ug/m3");
                tv_primary_pollutant.setText("基本污染物："+pm25Bean.getShowapi_res_body().getPm().getPrimary_pollutant());
                tv_quality.setText("空气质量："+pm25Bean.getShowapi_res_body().getPm().getQuality());
                tv_pm10.setText("pm10："+pm25Bean.getShowapi_res_body().getPm().getPm10());
                tv_so2.setText("SO2："+pm25Bean.getShowapi_res_body().getPm().getSo2());
                tv_o3.setText("O3："+pm25Bean.getShowapi_res_body().getPm().getO3());
                tv_co.setText("CO："+String.valueOf(pm25Bean.getShowapi_res_body().getPm().getCo()));
            }

            @Override
            public void error(String error) {
                System.out.println(error);
            }
        });
    }

    private void initView() {
        tv_city = (TextView) findViewById(R.id.city);
        tv_pm25 = (TextView) findViewById(R.id.tv_pm25);
        tv_primary_pollutant = (TextView) findViewById(R.id.tv_primary_pollutant);
        tv_quality = (TextView) findViewById(R.id.tv_quality);
        tv_pm10 = (TextView) findViewById(R.id.tv_pm10);
        tv_so2 = (TextView) findViewById(R.id.tv_so2);
        tv_o3 = (TextView) findViewById(R.id.tv_o3);
        tv_co = (TextView) findViewById(R.id.tv_co);
    }
}
