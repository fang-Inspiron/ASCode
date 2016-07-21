package com.xy.lifemanage.model;

import android.content.Context;

import com.xy.lifemanage.bean.UserBean;

import cn.bmob.v3.listener.EmailVerifyListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by nemo on 2016/5/8 0008.
 */
public class UserModel implements IUserModel{

    @Override
    public void login(Context context, String username, String password, final SaveListener listener) {
        UserBean ub = new UserBean();
        ub.setUsername(username);
        ub.setPassword(password);
        ub.login(context, new SaveListener(){
            @Override
            public void onSuccess() {
                listener.onSuccess();
                //通过BmobUser.getCurrentUser(context)方法获取登录成功后的本地用户信息
            }
            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                listener.onFailure(code,msg);
            }
        });
    }

    @Override
    public void register(Context context, String username, String password, String email, final SaveListener listener) {
        UserBean ub = new UserBean();
        ub.setUsername(username);
        ub.setPassword(password);
        ub.signUp(context, new SaveListener() {
            @Override
            public void onSuccess() {
                listener.onSuccess();
                //通过BmobUser.getCurrentUser(context)方法获取登录成功后的本地用户信息
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                listener.onFailure(code, msg);
            }
        });
    }

    @Override
    public void getCoding(Context context, final String email, final SaveListener saveListener) {
        UserBean.requestEmailVerify(context, email, new EmailVerifyListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                saveListener.onSuccess();
            }
            @Override
            public void onFailure(int code, String e) {
                // TODO Auto-generated method stub
                saveListener.onFailure(code,e);
            }
        });
    }
}
interface IUserModel{
    public void login(Context context,String username,String password,SaveListener saveListener);
    public void register(Context context,String username,String password,String email,SaveListener saveListener);
    public void getCoding(Context context,String email,SaveListener saveListener);
}