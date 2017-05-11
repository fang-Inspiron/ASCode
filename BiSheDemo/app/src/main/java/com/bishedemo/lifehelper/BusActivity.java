package com.bishedemo.lifehelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bishedemo.R;
import com.bishedemo.bean.BusBean;
import com.bishedemo.http.ApiUtils;

/**
 * Created by fang on 2016/12/16.
 */

public class BusActivity extends AppCompatActivity {
    private EditText et_bus_city;
    private EditText et_bus_no;
    private String bus_city;
    private String bus_no;
    private TextView tv_bus_name;
    private TextView tv_bus_info;
    private TextView tv_bus_path;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_bus_main);
        setTitle(getIntent().getStringExtra("appTitle"));

        initView();

        //设置监听器
        et_bus_no.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || (keyEvent!=null && keyEvent.getKeyCode()==KeyEvent.KEYCODE_ENTER)) {
                    bus_city = et_bus_city.getText().toString();
                    bus_no = et_bus_no.getText().toString();
                    initData(bus_city, bus_no);
                    return true;
                }
                return false;
            }
        });
    }

    private void initData(String city, String busNo) {
        ApiUtils.getBusInfo(city, busNo, new ApiUtils.GetListener<BusBean>() {
            @Override
            public void success(BusBean busBean) {
                //设置信息
                if (busBean.getShowapi_res_body().getRetList()!= null && busBean.getShowapi_res_body().getRetList().size()>0) {
                    tv_bus_name.setText("公交线路名称："+busBean.getShowapi_res_body().getRetList().get(0).getName());
                    tv_bus_info.setText("相关信息："+busBean.getShowapi_res_body().getRetList().get(0).getInfo());
                    tv_bus_path.setText("途经站点："+busBean.getShowapi_res_body().getRetList().get(0).getStats().replace(";","--"));
                } else {
                    Toast.makeText(BusActivity.this, "抱歉，服务器异常！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void error(String error) {
                System.out.println(error);
            }
        });
    }

    private void initView() {
        et_bus_city = (EditText) findViewById(R.id.et_bus_city);
        et_bus_no = (EditText) findViewById(R.id.et_bus_no);
        tv_bus_name = (TextView) findViewById(R.id.tv_bus_name);
        tv_bus_info = (TextView) findViewById(R.id.tv_bus_info);
        tv_bus_path = (TextView) findViewById(R.id.tv_bus_path);
    }
}
