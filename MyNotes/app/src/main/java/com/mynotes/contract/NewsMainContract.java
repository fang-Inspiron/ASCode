package com.mynotes.contract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.mynotes.bean.NewsChannelTable;

import java.util.List;

import rx.Observable;

/**
 * Created by fang on 2016/11/4.
 */

public interface NewsMainContract {

    interface Model extends BaseModel {
        Observable< List<NewsChannelTable> > lodeMineNewsChannels();
    }

    interface View extends BaseView {
        void returnMainNewsChannels(List<NewsChannelTable> newsChannelsMine);
    }

    abstract static class Presenter extends BasePresenter<View,Model> {
        public abstract void lodeMineChannelsRequest();
    }

}
