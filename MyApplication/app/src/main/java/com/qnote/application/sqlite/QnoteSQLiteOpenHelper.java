package com.qnote.application.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by silei on 2016/9/7.
 */
public class QnoteSQLiteOpenHelper extends SQLiteOpenHelper {
    //创建并管理数据库

    //用户信息表
    private static final String sql_userInfo = "create table if not exists Users"+
            "(id int primary key,user_name varchar,password varchar,token varchar);";
    private static final String notedb="create table notes"+
            "(notetime varchar(200),notetext varchar(20000),notetitle varchar(200));";
    //数据库版本
    private static final int VERSION = 1;

    public QnoteSQLiteOpenHelper(Context context, String name, CursorFactory factory,
                                 int version) {
        super(context, name, factory,
                version);
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
        sqLiteDatabase.execSQL(notedb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
