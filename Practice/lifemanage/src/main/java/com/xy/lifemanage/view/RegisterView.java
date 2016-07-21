package com.xy.lifemanage.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xy.lifemanage.R;
import com.xy.lifemanage.bean.UserBean;
import com.xy.lifemanage.presenter.Presenter;
import com.xy.lifemanage.utils.ToastUtils;
import com.xy.lifemanage.utils.Utils;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by nemo on 2016/5/9 0009.
 */
public class RegisterView extends Activity implements IUserView{
    private Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);
        presenter = new Presenter(getApplicationContext(),this);
        initView();
    }

    private void initView() {
        TopBar topBar = (TopBar) findViewById(R.id.regist_topBar);
        topBar.setTopBarOnclickListener(new TopBar.topBarOnclickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        Button register_ok = (Button) findViewById(R.id.register_ok);
        Button getCode = (Button) findViewById(R.id.get_code);


//        getCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String email = presenter.getText(R.id.email);
//                final String psw = presenter.getText(R.id.password_first);
//                final String psw2 = presenter.getText(R.id.password_second);
//             //   if(Utils.isEmail(email)){
//                    presenter.getCoding(email, new SaveListener() {
//                        @Override
//                        public void onSuccess() {
//                            ToastUtils.toast(RegisterView.this,"发送成功!请前往邮箱验证!");
//                        }
//                        @Override
//                        public void onFailure(int i, String s) {
//                            ToastUtils.toast(RegisterView.this,s);
//                        }
//                    });
////                }else{
////                    ToastUtils.toast(RegisterView.this,"邮箱格式不正确，请重新输入!");
////                }
//            }
//        });
        register_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = presenter.getText(R.id.email);
                final String psw = presenter.getText(R.id.password_first);
                final String psw2 = presenter.getText(R.id.password_second);

                if((!psw.equals(psw2)) || psw.equals("") || psw2.equals("")){
                    ToastUtils.toast(RegisterView.this,"两次密码不一致，请重新输入!");
                }else{
                    presenter.register(email, psw, email, new SaveListener() {
                        @Override
                        public void onSuccess() {

//                            UserBean bmobUser = BmobUser.getCurrentUser(RegisterView.this,UserBean.class);
//                            if(bmobUser != null){
//                                if((Boolean) BmobUser.getObjectByKey(RegisterView.this, "emailVerified")){
                                    ToastUtils.dialog(RegisterView.this,"注册成功，前往登录？");
//                                }
//                                else{
//                                    ToastUtils.toast(RegisterView.this,"请先验证邮箱!");
//                                }
//                            }else{
//                                System.out.println("注册失败");
//                            }
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            ToastUtils.toast(RegisterView.this,s);
                        }
                    });
                }

            }
        });
    }


    @Override
    public String getEditText(int id) {
        return null;
    }

    @Override
    public Bitmap getImageView(int id) {
        return null;
    }

    @Override
    public void setEditText(int id, String str) {

    }

    @Override
    public void setImageView(int id, Bitmap bitmap) {

    }

    @Override
    public void setListView(int id, List items) {

    }
}
