package com.xy.lifemanage.view.itemview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.xy.lifemanage.R;
import com.xy.lifemanage.view.IUserView;
import com.xy.lifemanage.view.TopBar;

import java.util.List;

/**
 * Created by nemo on 2016/5/11 0011.
 */
public class ProItemView extends Activity implements IUserView{

    private TopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.or_item_main);
        initView();
    }

    private void initView() {
        topBar = (TopBar) findViewById(R.id.or_item_topBar);
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
