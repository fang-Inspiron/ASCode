package com.bishedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bishedemo.R;
import com.bishedemo.bean.ListNoteBean;

import java.util.List;

/**
 * Created by fang on 2016/11/20.
 */
public class NoteListAdapter extends BaseAdapter {
    private LayoutInflater mInflater = null;
    private List<ListNoteBean> mList;

    public NoteListAdapter(Context context, List<ListNoteBean> datas) {
        this.mInflater = LayoutInflater.from(context);
        this.mList = datas;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public ListNoteBean getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null) {
            viewHolder=new ViewHolder();
            view=mInflater.inflate(R.layout.item_doc,null);
            viewHolder.titleTV= (TextView) view.findViewById(R.id.title_tv);
            viewHolder.dateTV= (TextView) view.findViewById(R.id.time_tv);
            view.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.setData(getItem(i));
        return view;
    }

    private static class ViewHolder {
        TextView titleTV;
        TextView dateTV;

        public void setData(ListNoteBean data) {
            // 填充字段
            titleTV.setText(data.getName());
            dateTV.setText(data.getDate());
        }
    }
}
