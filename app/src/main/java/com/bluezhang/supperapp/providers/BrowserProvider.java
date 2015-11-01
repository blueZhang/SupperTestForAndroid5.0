package com.bluezhang.supperapp.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * 浏览器相关的数据内容提供者。
 */
public class BrowserProvider extends ContentProvider {

    private DBhelper dBhelper;

    private static final UriMatcher match;
    public static final int CODE_HISTORY = 1;

    static {
        match = new UriMatcher(UriMatcher.NO_MATCH);
        //添加匹配规则
        /**
         * 参数1 代表要匹配的 authority  "*" 代表所有的
         * 可以理解为服务器地址，如果有多个
         * authority支持，那么只能够匹配制定的那个；
         * 参数2 是表的名称
         *
         */
        match.addURI("*", "history", CODE_HISTORY);//comtent://authority//history
    }

    private SQLiteDatabase db;
    private SQLiteDatabase rdb;

    public BrowserProvider() {
    }

    /**
     * 不能执行长时间的操作<br/>
     * 创建DBHelper
     *
     * @return true 代表加载成功 false代表加载失败
     */
    @Override
    public boolean onCreate() {
        dBhelper = new DBhelper(getContext());
        rdb = dBhelper.getReadableDatabase();
        db = dBhelper.getWritableDatabase();


        return true;
    }

    /**
     * 添加数据 ，根据URI添加不同的数；
     * ContentV啊卢瑟、包含数据内容；
     *
     * @param uri
     * @param values
     * @return uri 代表新添加的一条的记录的uri  也就是说可以用这个进行删除这条数据
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //TODO 进行插入的实现
        Uri ret = null;
        if (uri != null) {
            if (values == null) {
                values = new ContentValues();
            }
            //TODO 判断匹配URI到底是处理哪一个表
            int code = BrowserProvider.match.match(uri);;
            switch (code) {
                case CODE_HISTORY://代表添加历史纪录
                    long rid = db.insert(
                            DataContract.TABLE_HISTORY,
                            "url",
                            values
                    );
                    if (rid > -1) {
                        //TODO 代表记录处插入成功
                        //定义content://xxxx/history/id 代表新添加的记录
                        //ret = Uri.withAppendedPath(uri,Long.toString(rid));
                        ret = ContentUris.withAppendedId(uri, rid);
                    }
                    break;
            }
        }
        return ret;
    }

    /**
     * 查询
     *
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor ret = null;
        if (uri != null) {
            int code = BrowserProvider.match.match(uri);
            switch (code) {
                case CODE_HISTORY:
                    ret = rdb.query(
                            DataContract.TABLE_HISTORY,
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            sortOrder
                    );
                    break;
            }
        }

        return ret;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int ret = 0;
        if (uri != null) {
            if (values == null) {
                values = new ContentValues();
            }
            //TODO 判断匹配URI到底是处理哪一个表
            int code = BrowserProvider.match.match(uri);
            switch (code) {
                case CODE_HISTORY://代表添加历史纪录
                    //更新行
                    ret = db.update(
                            DataContract.TABLE_HISTORY,
                            values,
                            selection,
                            selectionArgs
                    );
                    break;
            }
        }
        return ret;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int ret = 0;
        if (uri != null) {
            ret = db.delete(
                    DataContract.TABLE_HISTORY,
                    selection,
                    selectionArgs
            );

        }

        return ret ;

    }

    @Override
    public String getType(Uri uri) {

        //TODO 对URI进行判断，判断是那种类型的URI 并返回时属于哪一表的URI
        String ret = null;
        int code = BrowserProvider.match.match(uri);
        switch (code){
            case CODE_HISTORY:
                break;
            //TODO 如果有其他的表的话那么就要进行比较
        }


        return ret;
    }


}
