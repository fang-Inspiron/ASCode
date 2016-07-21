package com.xy.lifemanage.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xy.lifemanage.R;
import com.xy.lifemanage.presenter.Presenter;
import com.xy.lifemanage.utils.ToastUtils;
import com.xy.lifemanage.view.proview.CreateTaskView;

import java.util.List;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by nemo on 2016/5/9 0009.
 */
public class LoginView extends Activity implements IUserView{

    private Presenter presenter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        presenter = new Presenter(getApplicationContext(),this);
        initView();
    }

    private void initView() {
        button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String username = presenter.getText(R.id.username);
//                String password = presenter.getText(R.id.password);
//                presenter.login(username, password, new SaveListener() {
//                    @Override
//                    public void onSuccess() {
//                        ToastUtils.toast(LoginView.this,"登录成功!");
                        Intent intent = new Intent(LoginView.this,MainView.class);
                        startActivity(intent);
                        finish();
//                    }
//                    @Override
//                    public void onFailure(int i, String s) {
//                        ToastUtils.toast(LoginView.this,s);
//                    }
//                });
            }
        });
        TopBar topBar = (TopBar) findViewById(R.id.topBarLogin);
        topBar.setTopBarOnclickListener(new TopBar.topBarOnclickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
    }

    @Override
    public String getEditText(int id) {
        return ((TextView)findViewById(id)).getText().toString();
    }

    @Override
    public Bitmap getImageView(int id) {
        return ((ImageView)findViewById(id)).getDrawingCache();
    }

    @Override
    public void setEditText(int id, String str) {
        ((TextView)findViewById(id)).setText(str);
    }

    @Override
    public void setImageView(int id, Bitmap bitmap) {
        ((ImageView)findViewById(id)).setImageBitmap(bitmap);
    }

    @Override
    public void setListView(int id, List items) {

    }
}
