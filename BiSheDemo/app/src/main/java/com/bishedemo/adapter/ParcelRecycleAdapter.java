package com.bishedemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.bishedemo.R;
import com.bishedemo.bean.ParcelBean;

import java.util.List;

/**
 * Created by fang on 2016/12/14.
 */

public class ParcelRecycleAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<ParcelBean.ShowapiResBodyBean.DataBean> list;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private Context context;

    public List<ParcelBean.ShowapiResBodyBean.DataBean> getList() {
        return list;
    }

    public ParcelRecycleAdapter(Context context, List<ParcelBean.ShowapiResBodyBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parcel_recycle_item, parent, false);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            if (position == 0) {
                ((ItemViewHolder) holder).parcel_point.setImageResource(R.mipmap.parcel_green);
                ((ItemViewHolder) holder).parcel_line.setImageResource(R.mipmap.parcel_line_green);
                TextView parcel_content = ((ItemViewHolder) holder).parcel_content;
                parcel_content.setText(list.get(position).getContext());
                parcel_content.setTextColor(0xff22ad5a);
                TextView parcel_time = ((ItemViewHolder) holder).parcel_time;
                parcel_time.setText(list.get(position).getTime());
                parcel_time.setTextColor(0xff22ad5a);

            }
            ((ItemViewHolder) holder).parcel_content.setText(list.get(position).getContext());
            ((ItemViewHolder) holder).parcel_time.setText(list.get(position).getTime());
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
        ImageView parcel_point;
        ImageView parcel_line;
        TextView parcel_content;
        TextView parcel_time;

        public ItemViewHolder(View view) {
            super(view);
            parcel_point = (ImageView) view.findViewById(R.id.parcel_point);
            parcel_line = (ImageView) view.findViewById(R.id.parcel_line);
            parcel_content = (TextView) view.findViewById(R.id.parcel_context);
            parcel_time = (TextView) view.findViewById(R.id.parcel_time);
        }
    }
}
