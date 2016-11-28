package com.qnote.application.doc.view;

import com.qnote.application.doc.bean.NoteListBean;

/**
 * Created by silei on 2016/9/10.
 */
public interface IdocView {
    void startSyncAnim();
    void stopSyncAnim();
    void setDate(NoteListBean data);
    void showMsg(String msg);
    void startLogin();
}
