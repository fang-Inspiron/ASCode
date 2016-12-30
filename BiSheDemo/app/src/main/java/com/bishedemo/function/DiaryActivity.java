package com.bishedemo.function;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.bishedemo.R;
import com.bishedemo.UniversalActivity;
import com.bishedemo.adapter.NoteListAdapter;
import com.bishedemo.bean.ListNoteBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fang on 2016/11/15.
 */

public class DiaryActivity extends UniversalActivity{

    private ListView listView;
    private ImageButton imageButton;
    private NoteListAdapter adapter;
    private List<ListNoteBean> datas;

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
        imageButton = (ImageButton) findViewById(R.id.add_note_ib);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DiaryDetailActivity.class);
              //  intent.putExtra("text",datas.get(i).getText());
                startActivity(intent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DiaryDetailActivity.class));
            }
        });
    }

    private void initDatas() {
        datas = new ArrayList<>();
        ListNoteBean bean = new ListNoteBean("1", "Demo", "2016/11/20 17:13", "ASADDGHAGDH");
        ListNoteBean bean1 = new ListNoteBean("2", "Demo2", "2016/11/28 17:13", "ASADDGHAGDH");
        datas.add(bean);
        datas.add(bean1);
    }
}
