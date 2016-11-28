package com.bishedemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bishedemo.R;
import com.bishedemo.adapter.SampleAdapter;
import com.bishedemo.bean.Channel;
import com.bishedemo.bean.DetailNewsBean;
import com.bishedemo.http.ApiUtils;
import com.bishedemo.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fang on 2016/11/15.
 */

public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private View rootView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SampleAdapter sampleAdapter;
    private View footerView;
    private int lastVisibleItem;
    private LinearLayoutManager layoutManager;
    private  String name;
    private String appTitle;

    @Override
    public void setArguments(Bundle args) {
        name = args.getString("name");
        appTitle = args.getString("appTitle");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main,null);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycleView);
        initData(name);

        return rootView;
    }


    private void initData(final String name) {
        ApiUtils.getChannelDetailList(name,new ApiUtils.GetListener<DetailNewsBean>() {
            @Override
            public void success(DetailNewsBean channel) {
                sampleAdapter = new SampleAdapter(appTitle, getContext(), channel.getResult().getData());
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
        ApiUtils.getChannelDetailList(name, new ApiUtils.GetListener<DetailNewsBean>() {
            @Override
            public void success(DetailNewsBean channel) {
                sampleAdapter = new SampleAdapter(appTitle, getContext(), channel.getResult().getData());
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

        layoutManager = new LinearLayoutManager(getContext());

        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置Adapter
        recyclerView.setAdapter(sampleAdapter);
        //设置分隔线
        recyclerView.addItemDecoration( new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        /*
         * 下拉刷新
         */
        swipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe);
        //设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setEnabled(true);


        /*
         * 上拉加载监听
         */
//        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == sampleAdapter.getItemCount()) {
//                    //滑到最后一个，加载更多
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
//            }
//
//        });
        recyclerView.setHasFixedSize(true);
    }


}
