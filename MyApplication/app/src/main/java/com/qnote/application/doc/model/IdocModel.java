package com.qnote.application.doc.model;

import com.qnote.application.utils.CallBack;

/**
 * Created by silei on 2016/9/10.
 */
public interface IdocModel<T> {
    int SYNC_OK=1;
    int SYNC_FAIL=2;
    int NOTOKEN = 3;
     void syncList( final long updatetime,
               final CallBack<T> callback);
}
