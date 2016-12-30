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
import com.bishedemo.bean.MenuBean;

import java.util.List;

/**
 * Created by fang on 2016/12/14.
 */

public class MenuRecycleAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<MenuBean.ShowapiResBodyBean.NewslistBean> list;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private Context context;

    public List<MenuBean.ShowapiResBodyBean.NewslistBean> getList() {
        return list;
    }

    public MenuRecycleAdapter(Context context, List<MenuBean.ShowapiResBodyBean.NewslistBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_recycle_item, parent, false);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            String temp;
            ((ItemViewHolder) holder).menu_name.setText("菜谱名称："+list.get(position).getCp_name());

            temp = list.get(position).getType_name();
            if(!temp.equals("")) {
                ((ItemViewHolder) holder).menu_type.setText("菜谱类型：" + temp);
            }
            temp = list.get(position).getYuanliao();
            if(!temp.equals("")) {
                ((ItemViewHolder) holder).menu_yuanliao.setText("原料：" + temp);
            }
            temp = list.get(position).getTiaoliao();
            if(!temp.equals("")) {
                ((ItemViewHolder) holder).menu_tiaoliao.setText("调料：" + temp);
            }
            temp = list.get(position).getZuofa();
            if(!temp.equals("")) {
                ((ItemViewHolder) holder).menu_zuofa.setText("做法：" + temp);
            }
            temp = list.get(position).getTexing();
            if(!temp.equals("")) {
                ((ItemViewHolder) holder).menu_texing.setText("特性：" + temp);
            }
            temp = list.get(position).getTishi();
            if (!temp.equals("")) {
                ((ItemViewHolder) holder).menu_tishi.setText("提示：" + temp);
            }
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

        TextView menu_name;
        TextView menu_type;
        TextView menu_yuanliao;
        TextView menu_tiaoliao;
        TextView menu_zuofa;
        TextView menu_texing;
        TextView menu_tishi;

        public ItemViewHolder(View view) {
            super(view);
            menu_name = (TextView) view.findViewById(R.id.menu_name);
            menu_type = (TextView) view.findViewById(R.id.menu_type);
            menu_yuanliao = (TextView) view.findViewById(R.id.menu_yuanliao);
            menu_tiaoliao = (TextView) view.findViewById(R.id.menu_tiaoliao);
            menu_zuofa = (TextView) view.findViewById(R.id.menu_zuofa);
            menu_texing = (TextView) view.findViewById(R.id.menu_texing);
            menu_tishi = (TextView) view.findViewById(R.id.menu_tishi);
        }
    }
}
