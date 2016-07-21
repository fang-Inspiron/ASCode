package com.xy.lifemanage.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xy.lifemanage.R;

/**
 * Created by nemo on 2016/5/10 0010.
 */
public class OrganizeFragment extends Fragment{
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.organize_main,container,false);
        return rootView;
    }
}
