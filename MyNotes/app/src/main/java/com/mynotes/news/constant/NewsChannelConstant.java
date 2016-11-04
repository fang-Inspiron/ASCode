package com.mynotes.news.constant;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.mynotes.bean.NewsChannelTable;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by fang on 2016/11/4.
 */

public interface NewsChannelConstant {
    interface Model extends BaseModel {
        Observable<List<NewsChannelTable>> lodeMineNewsChannels();
        Observable<List<NewsChannelTable>> lodeMoreNewsChannels();
        Observable<String> swapDb(ArrayList<NewsChannelTable> newsChannelTableList,int fromPosition, int toPosition);
        Observable<String> updateDb(ArrayList<NewsChannelTable> mineChannelTableList, ArrayList<NewsChannelTable> moreChannelTableList);

    }

    interface View extends BaseView {
        void returnMineNewsChannels(List<NewsChannelTable> newsChannelsMine);
        void returnMoreNewsChannels(List<NewsChannelTable> newsChannelsMore);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void lodeChannelsRequest();
        public abstract void onItemSwap(ArrayList<NewsChannelTable> newsChannelTableList, int fromPosition, final int toPosition);
        public abstract void onItemAddOrRemove(ArrayList<NewsChannelTable> mineChannelTableList, ArrayList<NewsChannelTable> moreChannelTableList);
    }
}
