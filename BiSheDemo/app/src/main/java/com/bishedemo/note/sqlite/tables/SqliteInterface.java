package com.bishedemo.note.sqlite.tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * Sqlite基本操作
 * 
 * @author fang
 */
public interface SqliteInterface {

	boolean onCreate(SQLiteDatabase database);

	boolean onUpgrade(SQLiteDatabase database);
}
