package com.bishedemo.lifehelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bishedemo.R;
import com.bishedemo.adapter.PoemRecycleAdapter;
import com.bishedemo.bean.PoemBean;
import com.bishedemo.http.ApiUtils;
import com.bishedemo.utils.DividerItemDecoration;

/**
 * Created by fang on 2016/12/13.
 */

public class PoemActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private PoemRecycleAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Spinner spinner_num;
    private Spinner spinner_type;
    private Spinner spinner_yayuntype;
    private EditText poem_key;
    private String[] arr_num = {"五言", "七言"};
    private String[] arr_type = {"藏头", "藏尾", "藏中", "递增", "递减"};
    private String[] arr_yayuntype = {"双句一压", "双句押韵", "一三四押"};
    private String num;
    private String type;
    private String yayuntype;
    private String key;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_poem_main);
        setTitle(getIntent().getStringExtra("appTitle"));


        findId();
        spinnerSetAdapter();

        //设置监听器
        poem_key.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || (keyEvent!=null && keyEvent.getKeyCode()==KeyEvent.KEYCODE_ENTER)) {
                    key = poem_key.getText().toString();
                    initData(num, type, yayuntype, key);
                    return true;
                }
                return false;
            }
        });


    }



    private void initData(String num, String type, String yayuntype, String key) {
        ApiUtils.getPoemInfo(key, num, type, yayuntype, new ApiUtils.GetListener<PoemBean>() {
            @Override
            public void success(PoemBean poemBean) {
                if (poemBean.getShowapi_res_body().getList()!=null) {
                    adapter = new PoemRecycleAdapter(getApplicationContext(), poemBean.getShowapi_res_body().getList());
                    initView();
                } else {
                    Toast.makeText(PoemActivity.this, "服务器异常", Toast.LENGTH_SHORT).show();
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
        ApiUtils.getPoemInfo(key, num, type, yayuntype, new ApiUtils.GetListener<PoemBean>() {
            @Override
            public void success(PoemBean poemBean) {
                adapter = new PoemRecycleAdapter(getApplicationContext(), poemBean.getShowapi_res_body().getList());
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
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.poem_swipe);
        //设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setEnabled(true);



        recyclerView.setHasFixedSize(true);
    }

    private void spinnerSetAdapter() {
        ArrayAdapter<String> adapter_num = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_num);
        adapter_num.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_num.setAdapter(adapter_num);
        spinner_num.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (arr_num[i]) {
                    case "五言":
                        num = "5";
                        System.out.println("num:"+num);
                        break;
                    case "七言":
                        num = "7";
                        System.out.println("num:"+num);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter adapter_type = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, arr_type);
        spinner_type.setAdapter(adapter_type);
        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (arr_type[i]) {
                    case "藏头":
                        type = "1";
                        break;
                    case "藏尾":
                        type = "2";
                        break;
                    case "藏中":
                        type = "3";
                        break;
                    case "递增":
                        type = "4";
                        break;
                    case "递减":
                        type = "5";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter adapter_yayuntype = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, arr_yayuntype);
        spinner_yayuntype.setAdapter(adapter_yayuntype);
        spinner_yayuntype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (arr_yayuntype[i]) {
                    case "双句一压":
                        yayuntype = "1";
                        System.out.println("yayuntype:"+yayuntype);
                        break;
                    case "双句押韵":
                        yayuntype = "2";
                        System.out.println("yayuntype:"+yayuntype);
                        break;
                    case "一三四押":
                        yayuntype = "3";
                        System.out.println("yayuntype:"+yayuntype);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    private void findId() {
        recyclerView = (RecyclerView) findViewById(R.id.poem_recycleView);
        spinner_num = (Spinner) findViewById(R.id.poem_num);
        spinner_type = (Spinner) findViewById(R.id.poem_type);
        spinner_yayuntype = (Spinner) findViewById(R.id.poem_yayuntype);
        poem_key = (EditText) findViewById(R.id.poem_key);
    }
}
