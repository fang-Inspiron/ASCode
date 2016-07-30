package com.picassodemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by fang on 2016/7/29.
 */
public class ImageAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> urls;

    public ImageAdapter(Context context, ArrayList<String> urls) {
        this.context = context;
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        //设置placeholder和error image
        Picasso.with(context).load(urls.get(position)).placeholder(R.mipmap.image_placeholder).error(R.mipmap.ic_launcher).into(imageView);

        //设置网格中显示imageView的属性
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(280,280));
        return imageView;
    }
}
