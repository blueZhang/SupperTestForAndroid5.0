package com.bluezhang.supperapp.providers;

/**
 * Author: blueZhang
 * DATE:2015/10/31
 * Time: 10:02
 * email:bluezhang521@163.com
 */

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 数据库以及ContentProvider中约定的表，表明，字段名，以及类的定义。
 */
public final class DataContract {
    private DataContract(){}

    /**
     * 历史表名称
     */
    public static final String TABLE_HISTORY = "histories";
    /**
     *使用静态类的形式来代表一张表以及表的字段，uri等数据的信息；
     * BaseColumns实现他的目的是使用  _id  的字段
     *  public interface BaseColumns
         {
             public static final String _ID = "_id";
             public static final String _COUNT = "_count";
         }
     */
    public static class History implements BaseColumns{

        public static final String AUTHORITY = "com.bluezhang.superapp.provier.BrowserProvider";
        /**
         * 定义特定的字段名称
         */
        public static final String URL = "url";
        /**
         * history 这个表的CONTENT_URI
         */
        public static final Uri CONTENT_URI =
                Uri.parse("content://"+AUTHORITY)
                .buildUpon()
                        .appendPath("history")
                        .build();


        /**
         * Constructs a new instance of {@code Object}.
         */
        public History() {
            super();
        }
    }
}
