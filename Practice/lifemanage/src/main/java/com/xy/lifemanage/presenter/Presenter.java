package com.xy.lifemanage.presenter;

import android.app.Activity;
import android.content.Context;

import com.xy.lifemanage.model.DataModel;
import com.xy.lifemanage.model.IGetDataListener;
import com.xy.lifemanage.model.UserModel;
import com.xy.lifemanage.view.IUserView;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by nemo on 2016/5/8 0008.
 */
public class Presenter{
    private UserModel userModel;
    private DataModel dataModel;
    private IUserView iUserView;
    private Context context;
    private Activity activity;
    public static Presenter presenter;
    public Presenter(Context context,IUserView activity){
        this.context = context;
        userModel = new UserModel();
        dataModel = new DataModel();
        iUserView = activity;
        presenter = this;
    }

    private Presenter(){

    }

    public String getText(int id){
        return iUserView.getEditText(id);
    }

    public static Presenter getInstance(){
        if(presenter==null){
            presenter = new Presenter();
        }
        return presenter;
    }

    public void getData(IGetDataListener listener){
        dataModel.getOrganizeData(context,listener);
    }

    public void login(String username, String password, SaveListener saveListener){
        userModel.login(context,username,password,saveListener);
    }
    public void register(String username, String password, String email,SaveListener saveListener){
        userModel.register(context,username,password,email,saveListener);
    }

    public void getCoding(String email,SaveListener listener){
        userModel.getCoding(context,email,listener);
    }
}
