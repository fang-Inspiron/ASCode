package com.bishedemo.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.bishedemo.R;
import com.bishedemo.bean.DetailNewsBean;
import com.bishedemo.function.NewsDetailActivity;
import com.squareup.picasso.Picasso;

public class SampleAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<DetailNewsBean.ResultBean.DataBean> list;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private Context context;
    private String name;

    public List<DetailNewsBean.ResultBean.DataBean> getList() {
        return list;
    }

    public SampleAdapter(String name, Context context, List<DetailNewsBean.ResultBean.DataBean> list) {
        this.name = name;
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {

            ImageView imageView = ((ItemViewHolder) holder).imageView;
            if (list.get(position).getThumbnail_pic_s() != null) {
                Picasso.with(context).load(list.get(position).getThumbnail_pic_s()).resize(400, 400).centerCrop().into(imageView);
            }
            TextView news_title = ((ItemViewHolder) holder).news_title;
            news_title.setText(list.get(position).getTitle());
            TextView news_desc = ((ItemViewHolder) holder).news_digest;
            news_desc.setText(list.get(position).getAuthor_name());
            TextView news_time = ((ItemViewHolder) holder).news_time;
            news_time.setText(list.get(position).getDate());
            //点击进入webView
            news_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, NewsDetailActivity.class);
                    System.out.println(position);
                    intent.putExtra("url", list.get(position).getUrl());
                    intent.putExtra("appTitle", name);
                    context.startActivity(intent);
                }
            });

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item, parent, false);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        return new ItemViewHolder(view);
    }


    class ItemViewHolder extends ViewHolder{

        ImageView imageView;
        TextView news_title;
        TextView news_digest;
        TextView news_time;

        public ItemViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.news_image);
            news_title = (TextView) view.findViewById(R.id.news_title);
            news_digest = (TextView) view.findViewById(R.id.news_digest);
            news_time = (TextView) view.findViewById(R.id.news_time);
        }
    }

}
