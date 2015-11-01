package com.bluezhang.supperapp.providers;

/**
 * Author: blueZhang
 * DATE:2015/10/31
 * Time: 09:57
 * email:bluezhang521@163.com
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库工具
 */
public class DBhelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "app.db";
    private  static final int DB_VER = 1;
    private static final String CREATE_TABLE_HISTORY =
            "create table "
                    +DataContract.TABLE_HISTORY+"("
                    +DataContract.History._ID+
                    " integer primary key autoincrement, "
                     +DataContract.History.URL +
                    " text not null "+")";

    /**
     * 第三个参数是SqliteFactory
     * @param context
     */
    public DBhelper(Context context) {
        super(context,DB_NAME,null,DB_VER);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_HISTORY);

    }

    /**

     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //TODO　将旧的数据进行删除，添加新的数据库

    }
}
