package com.offlinecache.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.offlinecache.MainActivity;
import com.offlinecache.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fang on 2016/7/19.
 */
public class LocalityFragment extends Fragment {
    private View rootView;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> group;
    private List<List<String>> children;
    private List<String> childFirst;
    private List<String> childSecond;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragemnt_locality, container, false);
        group = new ArrayList<>();
        group.add("未缓存");
        group.add("已缓存");

        childFirst = new ArrayList<>();

        childFirst.add("http://www.sina.com.cn/");

        childSecond = new ArrayList<>();
        childSecond.add("https://www.baidu.com/");
        childSecond.add("http://www.hao123.com/");

        children = new ArrayList<>();
        children.add(childFirst);
        children.add(childSecond);

        //实例化expandableListView
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);
        //设置adapter
        expandableListView.setAdapter(new MyExpandableListAdapter(getActivity()));

        //childSecond表示已缓存，点击可查看，添加监听器
        //此处需要创建SQLite数据库来标识num，不然每次都新启动App，num都从0开始
//        Intent intent = new Intent();
//        intent.putExtra("num", MainActivity.num);
//        intent.setClass(getContext(), WebActivity.class);
//        startActivity(intent);

        return rootView;
    }



    class MyExpandableListAdapter extends BaseExpandableListAdapter {

        private Context context;
        public MyExpandableListAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return group.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return children.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return group.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return children.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            //自己设计的一个简单类，用来存储控件的基相关信息
            GroupHolder groupHolder = null;
            if (convertView == null) {
                // 这里的convertView其实是一个起缓冲作用的工具，因为当一个item从屏幕中滚出
                // 我们把它放到convertView里,这样再滑回来的时候可以直接去取，不用重新创建
                convertView = (View) getActivity().getLayoutInflater().from(context).inflate(
                        R.layout.group_layout, null);     //把界面放到缓冲区
                groupHolder = new GroupHolder();          //实例化我们创建的这个类
                //实例化类里的TextView
                groupHolder.txt = (TextView) convertView.findViewById(R.id.notice_item_date);
                //给view对象一个标签，告诉计算机我们已经在缓冲区里放了一个view，下回直接来拿就行了
                convertView.setTag(groupHolder);
            } else {
                groupHolder = (GroupHolder) convertView.getTag();     //然后他就直接来拿
            }
            groupHolder.txt.setText(group.get(groupPosition));//最后在相应的group里设置他相应的Text
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ItemHolder itemHolder = null;
            if (convertView == null) {
                convertView = (View) getActivity().getLayoutInflater().from(context).inflate(
                        R.layout.child_layout, null);
                itemHolder = new ItemHolder();
                itemHolder.txt = (TextView) convertView.findViewById(R.id.child_item);
                convertView.setTag(itemHolder);
            } else {
                itemHolder = (ItemHolder) convertView.getTag();
            }
            itemHolder.txt.setText(children.get(groupPosition).get(childPosition));
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    class GroupHolder {
        public TextView txt;
    }

    class ItemHolder {
        public TextView txt;
    }
}
