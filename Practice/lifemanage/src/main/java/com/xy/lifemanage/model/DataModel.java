package com.xy.lifemanage.model;

import android.content.Context;

import com.xy.lifemanage.bean.ORBean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by nemo on 2016/5/10 0010.
 */
public class DataModel implements IDataModel{
    @Override
    public void getOrganizeData(Context context,final IGetDataListener listener) {
        BmobQuery<ORBean> query = new BmobQuery<ORBean>();
        query.setLimit(50);
        query.findObjects(context, new FindListener<ORBean>() {
            @Override
            public void onSuccess(List<ORBean> object) {
                // TODO Auto-generated method stub
               listener.onSuccess(object);
            }
            @Override
            public void onError(int code, String msg) {
                listener.onError(msg);
            }
        });
    }

    @Override
    public void addOrganizeData() {

    }
}

interface IDataModel{
    void getOrganizeData(Context context,IGetDataListener listener);
    void addOrganizeData();
}