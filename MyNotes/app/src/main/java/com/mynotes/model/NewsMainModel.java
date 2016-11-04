package com.mynotes.model;

import android.app.Application;

import com.jaydenxiao.common.baserx.RxSchedulers;
import com.jaydenxiao.common.commonutils.ACache;
import com.mynotes.app.AppApplication;
import com.mynotes.app.AppConstant;
import com.mynotes.bean.NewsChannelTable;
import com.mynotes.contract.NewsMainContract;
import com.mynotes.db.NewsChannelTableManager;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by fang on 2016/11/4.
 */

public class NewsMainModel implements NewsMainContract.Model {

    @Override
    public Observable<List<NewsChannelTable>> lodeMineNewsChannels() {
        return Observable.create(new Observable.OnSubscribe<List<NewsChannelTable>>() {
            public void call(Subscriber<? super List<NewsChannelTable>> subscriber) {
                ArrayList<NewsChannelTable> newsChannelTableList = (ArrayList<NewsChannelTable>) ACache.get(AppApplication.getAppContext()).getAsObject(AppConstant.CHANNEL_MINE);
                if (newsChannelTableList == null) {
                    newsChannelTableList = (ArrayList<NewsChannelTable>) NewsChannelTableManager.loadNewsChannelsStatic();
                    ACache.get(AppApplication.getAppContext()).put(AppConstant.CHANNEL_MINE, newsChannelTableList);
                }
                subscriber.onNext(newsChannelTableList);
                subscriber.onCompleted();
            }
        }).compose(RxSchedulers.<List<NewsChannelTable>>io_main());
    }
}
