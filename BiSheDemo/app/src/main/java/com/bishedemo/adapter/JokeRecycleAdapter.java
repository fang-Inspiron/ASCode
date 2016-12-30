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
import com.bishedemo.bean.JokeBean;

import java.util.List;

/**
 * Created by fang on 2016/12/14.
 */

public class JokeRecycleAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<JokeBean.ShowapiResBodyBean.ListBean> list;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private Context context;

    public List<JokeBean.ShowapiResBodyBean.ListBean> getList() {
        return list;
    }

    public JokeRecycleAdapter(Context context, List<JokeBean.ShowapiResBodyBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_recycle_item, parent, false);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).joke_title.setText(list.get(position).getTitle());

            String content = list.get(position).getContent();
            ((ItemViewHolder) holder).joke_content.setText(content.replace("<br/><br/>", "\n"));
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

        TextView joke_title;
        TextView joke_content;

        public ItemViewHolder(View view) {
            super(view);
            joke_title = (TextView) view.findViewById(R.id.joke_title);
            joke_content = (TextView) view.findViewById(R.id.joke_content);
        }
    }
}
