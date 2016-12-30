package com.bishedemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.bishedemo.R;
import com.bishedemo.bean.MenuBean;
import com.bishedemo.bean.PoemBean;

import java.util.List;

/**
 * Created by fang on 2016/12/14.
 */

public class PoemRecycleAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<String> list;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private Context context;

    public List<String> getList() {
        return list;
    }

    public PoemRecycleAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poem_recycle_item, parent, false);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).poem_text.setText(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;

    }

    class ItemViewHolder extends ViewHolder {
        TextView poem_text;

        public ItemViewHolder(View view) {
            super(view);
            poem_text = (TextView) view.findViewById(R.id.poem_text);
        }
    }
}
