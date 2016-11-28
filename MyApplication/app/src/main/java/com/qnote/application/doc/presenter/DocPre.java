package com.qnote.application.doc.presenter;

import com.qnote.application.doc.model.DocModel;
import com.qnote.application.doc.model.IdocModel;
import com.qnote.application.doc.view.IdocView;
import com.qnote.application.utils.CallBack;

/**
 * Created by silei on 2016/9/10.
 */
public class DocPre {
    IdocView view;
    IdocModel<DocModel> model;
    public DocPre(IdocView idocView){
        view=idocView;
        model=new DocModel();
    }


    public void syncList(long updatetime){
        view.startSyncAnim();
        model.syncList(updatetime, new CallBack<DocModel>() {
            @Override
            public void getModel(DocModel model) {
                view.stopSyncAnim();
                view.showMsg(model.getMsg());
                switch (model.getState()){
                    case IdocModel.SYNC_OK:
                        view.setDate(model.getRequest());
                        break;
                    case IdocModel.SYNC_FAIL:
                        break;
                    case IdocModel.NOTOKEN:
                        view.startLogin();
                        break;
                }
            }
        });
    }

}
