package com.nemo.practice;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.Cache;
import com.android.volley.CacheDispatcher;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.net.CacheRequest;
import java.net.CacheResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> list;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1 == 123){

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();
//        initData();
//        initListView();
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivityForResult(intent,300);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Activity.RESULT_OK){
            System.out.println(data.getStringExtra("msg"));
        }
    }

    private void initListView() {
        listView.setAdapter(new MyBaseAdapter(this,list));
    }

    private void initData() {
        list = new ArrayList<String>();
        for (int i=1;i<20;i++){
            list.add("这是第"+i+"个Item");
        }
    }

    private void initView() {
       // listView = (ListView) findViewById(R.id.listView);
    }
    class MyBaseAdapter extends BaseAdapter{
        private ArrayList<String> list;
        private Context context;
        private float downX;
        private float upX;

        public MyBaseAdapter(Context context,ArrayList<String> list){
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
                holder = new Holder();
                holder.tv = (ImageView) convertView.findViewById(R.id.text);
                holder.bt1 = (Button) convertView.findViewById(R.id.leftButton);
                holder.bt2 = (Button) convertView.findViewById(R.id.rightButton);
                convertView.setTag(holder);
            }else{
                holder = (Holder) convertView.getTag();
                holder.bt1.setVisibility(View.GONE);
                holder.bt2.setVisibility(View.GONE);
            }
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            convertView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final Holder h = (Holder) v.getTag();
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            downX = event.getX();
                            break;
                        case MotionEvent.ACTION_UP:
                            upX = event.getX();
                            break;
                    }
                    if (h.bt1 != null) {
                        if (Math.abs(downX - upX) > 35) {  //2次坐标的绝对值如果大于35，就认为是左右滑动
                            h.bt1.setVisibility(View.VISIBLE);  //显示删除button
                            h.bt2.setVisibility(View.VISIBLE);
                            ObjectAnimator.ofInt(h.tv, "translationX", -200).setDuration(500).start();
//                            bt1 = h.bt1;  //赋值给全局button，一会儿用
//                            view=v; //得到itemview，在上面加动画
                            return true; //终止事件
                        }else{
                            h.bt1.setVisibility(View.GONE);  //显示删除button
                            h.bt2.setVisibility(View.GONE);
                        }
                        return false;  //释放事件，使onitemClick可以执行
                    }
                    return false;
                }
            });

            return convertView;
        }
        class Holder{
            private ImageView tv;
            private Button bt1;
            private Button bt2;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
