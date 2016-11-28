package com.bishedemo.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fang on 2016/11/20.
 */

public class QnoteSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String notedb="create table notes"+
            "(notetime varchar(200),notetitle varchar(200)),notetext varchar(20000);";
    //数据库版本
    private static final int VERSION = 1;

    public QnoteSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public QnoteSQLiteOpenHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    //未指定版本号则默认为1
    public QnoteSQLiteOpenHelper(Context context, String name) {
        this(context, name, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
