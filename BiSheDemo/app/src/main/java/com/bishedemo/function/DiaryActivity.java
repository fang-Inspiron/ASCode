package com.bishedemo.function;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.bishedemo.R;
import com.bishedemo.UniversalActivity;
import com.bishedemo.adapter.NoteListAdapter;
import com.bishedemo.bean.ListNoteBean;
import com.bishedemo.utils.SDHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fang on 2016/11/15.
 */

public class DiaryActivity extends UniversalActivity{

    private ListView listView;
    private ImageButton addButton;
    private NoteListAdapter adapter;
    private List<ListNoteBean> datas;
    public String newPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_main);

        initView();
        initDatas();
        adapter = new NoteListAdapter(getApplicationContext(), datas);
        listView.setAdapter(adapter);
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.notes_list);
        addButton = (ImageButton) findViewById(R.id.add_note_ib);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DiaryDetailActivity.class);
                intent.putExtra("fileName",datas.get(i).getName()+datas.get(i).getDate());
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DiaryDetailActivity.class));
            }
        });
    }

    private void initDatas() {
        datas = new ArrayList<>();
        List<String> listData = SDHelper.getFilesData();
        for (int i=0; i<listData.size(); i++) {
            datas.add(new ListNoteBean(listData.get(i).substring(0, 19),
                    listData.get(i).substring(19, listData.get(i).length()-5)));
        }
    }


}
