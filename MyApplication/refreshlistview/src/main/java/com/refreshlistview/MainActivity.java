package com.refreshlistview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.refreshlistview.adapter.MyAdapter;
import com.refreshlistview.bean.ApkEntity;
import com.refreshlistview.widget.RefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RefreshListView.onRefreshListener{

    private List<ApkEntity> apk_list = null;
    private MyAdapter adapter = null;
    private RefreshListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setData();
        showList();
        listView.setRefreshListener(this);
    }

    private void setData() {
        apk_list = new ArrayList<ApkEntity>();
        for (int i = 0; i < 10; i++) {
            ApkEntity entity = new ApkEntity();
            entity.setName("默认数据");
            entity.setDes("这是一个神奇的应用");
            entity.setInfo("50w用户");
            apk_list.add(entity);
        }
    }

    private void generateData() {
        for (int i = 0; i < 1; i++) {
            ApkEntity entity = new ApkEntity();
            entity.setName("1");
            entity.setDes("1");
            entity.setInfo("1");
            apk_list.add(0, entity);
        }
    }

    private void showList() {
        if (adapter == null) {
            listView = (RefreshListView) findViewById(R.id.listView);
            adapter = new MyAdapter(MainActivity.this, apk_list);
            listView.setAdapter(adapter);
        } else {
            adapter.onDataChange(apk_list);
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 刷新数据
                generateData();
                // 通知界面显示这些数据
                showList();
                // 通知ListView刷新完毕
                listView.refreshComplete();
            }
        }, 2000);
    }
}
