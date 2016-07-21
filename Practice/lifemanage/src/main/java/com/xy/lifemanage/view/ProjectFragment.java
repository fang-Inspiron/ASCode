package com.xy.lifemanage.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xy.lifemanage.R;
import com.xy.lifemanage.bean.ORBean;
import com.xy.lifemanage.model.IGetDataListener;
import com.xy.lifemanage.presenter.Presenter;
import com.xy.lifemanage.utils.ToastUtils;
import com.xy.lifemanage.utils.Utils;
import com.xy.lifemanage.view.itemview.ProItemView;
import com.xy.lifemanage.view.myview.CommonAdapter;
import com.xy.lifemanage.view.myview.PopMenu;
import com.xy.lifemanage.view.myview.UserMenu;
import com.xy.lifemanage.view.myview.ViewHolder;
import com.xy.lifemanage.view.myview.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nemo on 2016/5/10 0010.
 */
public class ProjectFragment extends Fragment implements XListView.IXListViewListener{
    private View rootView;
    private XListView xListView;
    private CommonAdapter<ORBean> mAdapter;
    private Handler mHandler;
    private List<ORBean> items;
    private Presenter presenter;
    private UserMenu mMenu;
    private TopBar topBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.project_main,container,false);
        presenter = Presenter.getInstance();
        initData();
        initMenu();
        topBar = (TopBar) rootView.findViewById(R.id.topBarPro);
        topBar.setTopBarOnclickListener(new TopBar.topBarOnclickListener() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {
                mMenu.showAsDropDown(topBar.rightButton);
            }
        });
        return rootView;
    }
    private void initMenu() {
        mMenu = new UserMenu(getContext());
        mMenu.addItem("加入小组", 0);
        mMenu.addItem("创建小组", 1);
        mMenu.setOnItemSelectedListener(new PopMenu.OnItemSelectedListener() {
            @Override
            public void selected(View view, PopMenu.Item item, int position) {
                switch (item.id) {
                    case 0:
                        Dialog dialog = new Dialog(getContext());
                        Utils.showDateTimePicker(getContext(),dialog);
                        break;
                    case 1:
                        ToastUtils.toast(getContext(),"创建小组");
                        break;
                }
            }
        });
    }

    private void initData() {
        items = new ArrayList<ORBean>();
        presenter.getData(new IGetDataListener() {
            @Override
            public void onSuccess(List var1) {
                items = var1;
                initView();
            }
            @Override
            public void onError(String var2) {
                ToastUtils.toast(getContext(),var2);
            }
        });
    }

    private void initView() {
        xListView = (XListView) rootView.findViewById(R.id.or_list);
        xListView.setPullLoadEnable(true);
        mAdapter = new CommonAdapter<ORBean>(getContext(), items,
                R.layout.project_item) {
            @Override
            public void convert(final ViewHolder holder, ORBean t) {
                holder.setText(R.id.or_name, t.getOr_name());
                holder.setText(R.id.or_post, t.getOr_post());
                String url = t.getOr_image().getUrl();
                System.out.println(url);
                Picasso.with(getContext()).load(url).into((ImageView) holder.getView(R.id.or_image));
            }
        };
        xListView.setAdapter(mAdapter);
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ProItemView.class);
                intent.putExtra("id",i);
                startActivity(intent);
            }
        });
        xListView.setPullLoadEnable(false);//设置是否可以上拉加载
        xListView.setPullRefreshEnable(false);//设置是否可以下拉刷新
        xListView.setXListViewListener(this);
        mHandler = new Handler();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
