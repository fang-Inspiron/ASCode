package com.bishedemo.lifehelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.bishedemo.R;
import com.bishedemo.adapter.JokeRecycleAdapter;
import com.bishedemo.bean.JokeBean;
import com.bishedemo.http.ApiUtils;
import com.bishedemo.utils.DividerItemDecoration;

/**
 * Created by fang on 2016/12/13.
 */

public class JokeActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private JokeRecycleAdapter adapter;
    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_joke_main);
        setTitle(getIntent().getStringExtra("appTitle"));
        recyclerView = (RecyclerView) findViewById(R.id.joke_recycleView);


        initData();
    }

    private void initData() {
        ApiUtils.getJokeList(new ApiUtils.GetListener<JokeBean>() {
            @Override
            public void success(JokeBean jokeBean) {
                adapter = new JokeRecycleAdapter(getApplicationContext(), jokeBean.getShowapi_res_body().getList());
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
        ApiUtils.getJokeList(new ApiUtils.GetListener<JokeBean>() {
            @Override
            public void success(JokeBean jokeBean) {
                adapter = new JokeRecycleAdapter(getApplicationContext(), jokeBean.getShowapi_res_body().getList());
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
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.joke_swipe);
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
