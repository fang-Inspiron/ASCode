package com.bishedemo.lifehelper;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bishedemo.R;
import com.bishedemo.adapter.MenuRecycleAdapter;
import com.bishedemo.bean.MenuBean;
import com.bishedemo.http.ApiUtils;
import com.bishedemo.utils.DividerItemDecoration;

/**
 * Created by fang on 2016/12/13.
 */

public class MenuActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MenuRecycleAdapter adapter;
    private LinearLayoutManager layoutManager;
    private EditText menu_word;
    private EditText menu_num;
    private String word;
    private String num;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_menu_main);
        setTitle(getIntent().getStringExtra("appTitle"));
        recyclerView = (RecyclerView) findViewById(R.id.menu_recycleView);
        menu_word = (EditText) findViewById(R.id.menu_word);
        menu_num = (EditText) findViewById(R.id.menu_num);

        //设置监听器
        menu_num.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || (keyEvent!=null && keyEvent.getKeyCode()==KeyEvent.KEYCODE_ENTER)) {
                    word = menu_word.getText().toString();
                    num = menu_num.getText().toString();
                    initData(word, num);
                    return true;
                }
                return false;
            }
        });


    }

    private void initData(String word, String num) {
        ApiUtils.getMenuList(num, word, new ApiUtils.GetListener<MenuBean>() {
            @Override
            public void success(MenuBean menuBean) {

                if (menuBean.getShowapi_res_body().getNewslist()!=null) {
                    adapter = new MenuRecycleAdapter(getApplicationContext(), menuBean.getShowapi_res_body().getNewslist());
                    initView();
                } else {
                    Toast.makeText(MenuActivity.this, "服务器异常", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void error(String error) {
                System.out.println(error);
            }
        });
    }

    @Override
    public void onRefresh() {
        ApiUtils.getMenuList(num, word, new ApiUtils.GetListener<MenuBean>() {
            @Override
            public void success(MenuBean menuBean) {
                adapter = new MenuRecycleAdapter(getApplicationContext(), menuBean.getShowapi_res_body().getNewslist());
                initView();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void error(String error) {
                System.out.println(error);
            }
        });
    }

    private void initView() {
        layoutManager = new LinearLayoutManager(getApplicationContext());

        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置Adapter
        recyclerView.setAdapter(adapter);
        //设置分隔线
        recyclerView.addItemDecoration( new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL_LIST));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        /*
         * 下拉刷新
         */
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.menu_swipe);
        //设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setEnabled(true);



        recyclerView.setHasFixedSize(true);
    }

}
