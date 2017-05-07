package com.bishedemo.note.ui.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bishedemo.R;
import com.bishedemo.note.module.Note;
import com.bishedemo.note.sqlite.DatabaseAccessFactory;
import com.bishedemo.note.ui.activity.NoteDetailActivity;
import com.bishedemo.note.utils.TimeUtils;

import java.util.ArrayList;

public class NoteAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Note> mNoteList;

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        super();
        mContext = context;
        mNoteList = notes;
    }

    @Override
    public int getCount() {
        return mNoteList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNoteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_note, null);
            convertView.setTag(new NoteViewHolder(convertView));

        }

        NoteViewHolder holder = (NoteViewHolder) convertView.getTag();
        holder.show(mNoteList.get(position));
        return convertView;
    }

    private class NoteViewHolder {

        private View mRootView;
        private TextView mTitleView;
        private TextView mTimeView;

        public NoteViewHolder(View rootView) {
            mRootView = rootView;
            mTitleView = (TextView) mRootView.findViewById(R.id.note_title);
            mTimeView = (TextView) mRootView.findViewById(R.id.time);
        }

        public void show(final Note note) {
            mTitleView.setText(note.title);
            mTimeView.setText(TimeUtils.getTime(note.modifyTime));
            mRootView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, NoteDetailActivity.class);
                    intent.putExtra("note_title", note.title);
                    //  TODO 还可以带其他参数
                    intent.putExtra("note_content", note.content);
                    mContext.startActivity(intent);
                }
            });
            mRootView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    mNoteList.remove(note);
                    notifyDataSetChanged();
                    DatabaseAccessFactory.getInstance(mContext).noteAccessor().delete(note);

//                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);  //先得到构造器
//                    builder.setTitle("提示");//设置标题
//                    builder.setMessage("是否确认删除?"); //设置内容
//                    builder.setPositiveButton("删除", new DialogInterface.OnClickListener() { //设置确定按钮
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            mNoteList.remove(note);
//                            notifyDataSetChanged();
//                            DatabaseAccessFactory.getInstance(mContext).noteAccessor().delete(note);
//                            dialog.dismiss(); //关闭dialog
//                            Toast.makeText(mContext, "确认" + which, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                            Toast.makeText(mContext, "取消" + which, Toast.LENGTH_SHORT).show();
//                        }
//                    });

                    return false;
                }
            });
        }
    }

}