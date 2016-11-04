package com.mynotes.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.mynotes.R;
import com.mynotes.app.AppApplication;
import com.mynotes.app.AppConstant;
import com.mynotes.bean.NewsChannelTable;
import com.mynotes.contract.NewsMainContract;
import com.mynotes.model.NewsMainModel;
import com.mynotes.news.activity.NewsChannelActivity;
import com.mynotes.presenter.NewsMainPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by fang on 2016/11/4.
 */

public class NewsMainFragment extends BaseFragment<NewsMainPresenter, NewsMainModel> implements NewsMainContract.View {

    @Bind(R.id.tab_layout)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.add_channel_iv)
    ImageView addChannelIv;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private BaseFragmentAdapter fragmentAdapter;


    @Override
    protected int getLayoutResource() {
        return R.layout.app_bar_news;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        mPresenter.lodeMineChannelsRequest();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRxManager.post(AppConstant.NEWS_LIST_TO_TOP, "");
            }
        });
    }

    @OnClick(R.id.add_channel_iv)
    public void clickAdd() {
        NewsChannelActivity.startAction(getContext());
    }

    //十一月四号晚上22:15

    @Override
    public void returnMainNewsChannels(List<NewsChannelTable> newsChannelsMine) {

    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }
}
