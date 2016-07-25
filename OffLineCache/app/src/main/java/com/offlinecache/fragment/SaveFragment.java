package com.offlinecache.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.offlinecache.R;
import com.offlinecache.http.GetContentThread;
import com.offlinecache.http.UploadData;
import com.offlinecache.utils.AlertDialog;
import com.offlinecache.utils.CheckNetwork;

/**
 * Created by fang on 2016/7/19.
 */
public class SaveFragment extends Fragment {
    private View rootView;
    private EditText et_input;
    private Button btn_save;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //GetContentThread类中已缓存成功
            if (msg.what == 100) {
                int num = msg.arg1;
                System.out.println("num:"+num);
                new GetContentThread(handler, et_input.getText().toString(), num).start();
            } else if (msg.what == 200)
                Toast.makeText(getActivity(),"已缓存成功",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(),"缓存失败",Toast.LENGTH_SHORT).show();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragemnt_save, container, false);

        et_input = (EditText) rootView.findViewById(R.id.et_input);
        btn_save = (Button) rootView.findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckNetwork.checkNetworkConnection(getActivity()) == 1) {
                    //当前使用的是wifi
                    new UploadData(getContext(), handler, et_input.getText().toString()).start();
                } else if (CheckNetwork.checkNetworkConnection(getActivity()) == 2) {
                    //当前使用的是数据流量
                    new AlertDialog(getActivity()).builder().setTitle("温馨提示")
                            .setMsg("当前使用的是数据流量，确认要继续吗？")
                            .setPositiveButton("确认继续", new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    new UploadData(getContext(), handler, et_input.getText().toString()).start();
                                }
                            }).setNegativeButton("取消", new OnClickListener() {
                        @Override
                        public void onClick(View v) {}
                    }).show();
                } else {
                    new AlertDialog(getActivity()).builder().setMsg("网络无连接，请联网后继续！")
                            .setNegativeButton("确定", new OnClickListener() {
                                @Override
                                public void onClick(View v) {}
                            }).show();
                }
            }
        });

        return rootView;
    }

}
