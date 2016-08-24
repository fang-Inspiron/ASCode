package com.listviewbetter;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);

        list = new ArrayList<String>();
        for (int i=0; i<30; i++) {
            list.add("条目"+i);
        }
        listView.setAdapter(new MyAdapter());
    }


    class MyAdapter extends BaseAdapter {

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
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder holder;

            // 判断convertView的状态，来达到复用效果
            if(null == convertView) {
                //如果convertView为空，则表示第一次显示该条目，需要创建一个view
                view = View.inflate(MainActivity.this, R.layout.listview_item, null);
                //新建一个viewholder对象
                holder = new ViewHolder();
                //将findviewbyID的结果赋值给holder对应的成员变量
                holder.tvHolder = (TextView) view.findViewById(R.id.tv);
                // 将holder与view进行绑定
                view.setTag(holder);
            } else {
                //否则表示可以复用convertView
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            // 直接操作holder中的成员变量即可，不需要每次都findViewById
            holder.tvHolder.setText(list.get(position));
            return view;
        }

    }
}
