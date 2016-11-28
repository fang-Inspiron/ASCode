package com.qnote.application.doc.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qnote.application.R;
import com.qnote.application.doc.bean.ListNoteBean;

import java.util.List;

/**
 * Created by silei on 2016/9/1.
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
            viewHolder.typeIV= (ImageView) view.findViewById(R.id.typeIV);
            viewHolder.titleTV= (TextView) view.findViewById(R.id.titleTV);
            viewHolder.dateTV= (TextView) view.findViewById(R.id.dateTV);
            viewHolder.sizeTV= (TextView) view.findViewById(R.id.sizeTV);
            view.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.setData(getItem(i));
        return view;
    }

    private static class ViewHolder {
        ImageView typeIV;
        TextView titleTV;
        TextView dateTV;
        TextView sizeTV;

        public void setData(ListNoteBean data) {
            //// TODO: 2016/9/1 填充字段
            titleTV.setText(data.getName());
            dateTV.setText(data.getDate());

        }
    }
}
