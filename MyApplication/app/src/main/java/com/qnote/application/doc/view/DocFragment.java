package com.qnote.application.doc.view;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.qnote.application.R;
import com.qnote.application.doc.bean.ListNoteBean;
import com.qnote.application.doc.bean.NoteListBean;
import com.qnote.application.doc.presenter.DocPre;
import com.qnote.application.note.NoteActivity;
import com.qnote.application.sqlite.QnoteSQLiteOpenHelper;
import com.qnote.application.utils.CustomToast;
import com.qnote.application.utils.Urls;

import java.util.ArrayList;
import java.util.List;

public class DocFragment extends Fragment implements IdocView {
    private ListView docListView;
    private View view;
    private View syncV;
    private NoteListBean.ValueBean listData;
    private NoteListAdapter adapter;
    private List<ListNoteBean> datas=new ArrayList<>();
    private ImageButton add_noteIB;

    private DocPre presenter;

    public DocFragment() {
        presenter = new DocPre(this);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_doc, container, false);
        initView();

        adapter = new NoteListAdapter(getContext(), datas);
        docListView.setAdapter(adapter);
        initDatas();
        return view;
    }

    private void syncDatas() {
        //// TODO: 2016/9/1 同步数据
        try {
            presenter.syncList(listData.getUpdatetime());
        } catch (Exception ex) {
            presenter.syncList(0);
        }
    }


    private void initDatas() {
        //// TODO: 2016/9/1 加载数据，本地缓存加载

        SQLiteDatabase db=new QnoteSQLiteOpenHelper(getContext(),"note.db").getReadableDatabase();
        Cursor cursor = db.query("notes", null, null, null, null, null, null);
        ListNoteBean bean;
        while (cursor.moveToNext()) {
            bean=new ListNoteBean();
            bean.setName(cursor.getString(cursor.getColumnIndex("notetitle")));
            bean.setUpdatetime(Long.valueOf(cursor.getString(cursor.getColumnIndex("notetime"))));
            bean.setText(cursor.getString(cursor.getColumnIndex("notetext")));
            datas.add(bean);
        }
        adapter.notifyDataSetChanged();


    }

    private void initView() {
        docListView = (ListView) view.findViewById(R.id.docLV);
        docListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(),NoteActivity.class);
                intent.putExtra("title",datas.get(position).getName());
                intent.putExtra("text",datas.get(position).getText());
                startActivity(intent);
            }
        });
        add_noteIB= (ImageButton) view.findViewById(R.id.add_noteIB);
        add_noteIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NoteActivity.class));
            }
        });
        syncV = view.findViewById(R.id.syncIV);
        syncV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                syncDatas();
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

    @Override
    public void startSyncAnim() {
        Animation an = AnimationUtils.loadAnimation(getContext(), R.anim.sync_rotate);
        syncV.startAnimation(an);
    }

    @Override
    public void stopSyncAnim() {
        syncV.clearAnimation();
    }

    @Override
    public void setDate(NoteListBean data) {
        listData = data.getValue();
        datas = listData.getDate();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMsg(String msg) {
        CustomToast.showToast(this.getContext(), msg, 2000);
    }

    @Override
    public void startLogin() {
        Intent intent = new Intent();
        intent.setAction(Urls.LOGINACTION);
        getActivity().sendBroadcast(intent);
    }
}
