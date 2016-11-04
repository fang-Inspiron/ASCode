package com.mynotes.news.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.mynotes.app.AppConstant;
import com.mynotes.bean.NewsChannelTable;
import com.mynotes.news.constant.NewsChannelConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fang on 2016/11/4.
 */

public class NewsChannelPresenter extends NewsChannelConstant.Presenter {
    @Override
    public void lodeChannelsRequest() {
        mRxManage.add(mModel.lodeMineNewsChannels().subscribe(new RxSubscriber<List<NewsChannelTable>>(mContext,false) {
            @Override
            protected void _onNext(List<NewsChannelTable> newsChannelTables) {
                mView.returnMineNewsChannels(newsChannelTables);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
        mRxManage.add(mModel.lodeMoreNewsChannels().subscribe(new RxSubscriber<List<NewsChannelTable>>(mContext,false) {
            @Override
            protected void _onNext(List<NewsChannelTable> newsChannelTables) {
                mView.returnMoreNewsChannels(newsChannelTables);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }

    @Override
    public void onItemSwap(final ArrayList<NewsChannelTable> newsChannelTableList, int fromPosition, int toPosition) {
        mRxManage.add(mModel.swapDb(newsChannelTableList, fromPosition, toPosition).subscribe(new RxSubscriber<String>(mContext,false) {
            @Override
            protected void _onNext(String s) {
                mRxManage.post(AppConstant.NEWS_CHANNEL_CHANGED, newsChannelTableList);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }

    @Override
    public void onItemAddOrRemove(final ArrayList<NewsChannelTable> mineChannelTableList, ArrayList<NewsChannelTable> moreChannelTableList) {
        mRxManage.add(mModel.updateDb(mineChannelTableList,moreChannelTableList).subscribe(new RxSubscriber<String>(mContext,false) {
            @Override
            protected void _onNext(String s) {
                mRxManage.post(AppConstant.NEWS_CHANNEL_CHANGED,mineChannelTableList);
            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }
}
