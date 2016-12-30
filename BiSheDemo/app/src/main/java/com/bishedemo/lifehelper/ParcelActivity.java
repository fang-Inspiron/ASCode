package com.bishedemo.lifehelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.bishedemo.R;
import com.bishedemo.adapter.JokeRecycleAdapter;
import com.bishedemo.adapter.ParcelRecycleAdapter;
import com.bishedemo.bean.JokeBean;
import com.bishedemo.bean.ParcelBean;
import com.bishedemo.http.ApiUtils;
import com.bishedemo.utils.DividerItemDecoration;

/**
 * Created by fang on 2016/12/16.
 */

public class ParcelActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ParcelRecycleAdapter adapter;
    private LinearLayoutManager layoutManager;
    private EditText parcel_nu;
    private String nu;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_parcel_main);
        setTitle(getIntent().getStringExtra("appTitle"));
        recyclerView = (RecyclerView) findViewById(R.id.parcel_recycleView);
        parcel_nu = (EditText) findViewById(R.id.parcel_nu);

        //设置监听器
        parcel_nu.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || (keyEvent!=null && keyEvent.getKeyCode()==KeyEvent.KEYCODE_ENTER)) {
                    nu = parcel_nu.getText().toString();
                    initData(nu);
                    return true;
                }
                return false;
            }
        });
    }

    private void initData(String nu) {
        ApiUtils.getParcelInfo(nu, new ApiUtils.GetListener<ParcelBean>() {
            @Override
            public void success(ParcelBean parcelBean) {
                adapter = new ParcelRecycleAdapter(getApplicationContext(), parcelBean.getShowapi_res_body().getData());
                initView();
            }

            @Override
            public void error(String error) {
                System.out.println(error);
            }
        });
    }

    @Override
    public void onRefresh() {
        ApiUtils.getParcelInfo(nu, new ApiUtils.GetListener<ParcelBean>() {
            @Override
            public void success(ParcelBean parcelBean) {
                adapter = new ParcelRecycleAdapter(getApplicationContext(), parcelBean.getShowapi_res_body().getData());
                initView();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void error(String error) {
                System.out.println(error);
            }
        });
    }

    private void initView() {
        layoutManager = new LinearLayoutManager(getApplicationContext());

        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置Adapter
        recyclerView.setAdapter(adapter);
        //设置分隔线
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL_LIST));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        /*
         * 下拉刷新
         */
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.parcel_swipe);
        //设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setEnabled(true);



        recyclerView.setHasFixedSize(true);
    }

}
