package com.mynotes.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.mynotes.app.AppConstant;
import com.mynotes.bean.NewsChannelTable;
import com.mynotes.contract.NewsMainContract;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by fang on 2016/11/4.
 */

public class NewsMainPresenter extends NewsMainContract.Presenter {
    @Override
    public void lodeMineChannelsRequest() {
        mRxManage.add(mModel.lodeMineNewsChannels().subscribe(new RxSubscriber<List<NewsChannelTable>>(mContext, false) {
            @Override
            protected void _onNext(List<NewsChannelTable> newsChannelTables) {
                mView.returnMainNewsChannels(newsChannelTables);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }

    @Override
    public void onStart() {
        super.onStart();
        //监听新闻频道变化刷新
        mRxManage.on(AppConstant.NEWS_CHANNEL_CHANGED, new Action1<List<NewsChannelTable>>() {
            @Override
            public void call(List<NewsChannelTable> newsChannelTables) {
                if(newsChannelTables!=null) {
                    mView.returnMainNewsChannels(newsChannelTables);
                }
            }
        });
    }
}
