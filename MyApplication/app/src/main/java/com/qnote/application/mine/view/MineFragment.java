package com.qnote.application.mine.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.qnote.application.R;
import com.qnote.application.user.login.view.LoginActivity;
import com.qnote.application.user.register.view.RegisterActivity;
import com.qnote.application.user.revise.view.ReviseActivity;

public class MineFragment extends Fragment {

    private Button loginBT;
    private Button regeistBT;
    private View reviseV;
    private View view;

    public MineFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        initView();
        return view;
    }

    void initView(){
        loginBT= (Button) view.findViewById(R.id.m_loginBT);
        regeistBT= (Button) view.findViewById(R.id.registerBT);
        reviseV=view.findViewById(R.id.reviseRL);
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        regeistBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RegisterActivity.class));
            }
        });
        reviseV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ReviseActivity.class));
            }
        });

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
