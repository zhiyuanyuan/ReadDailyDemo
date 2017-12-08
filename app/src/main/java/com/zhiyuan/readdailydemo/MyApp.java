package com.zhiyuan.readdailydemo;

import android.app.Application;
import android.content.Context;

import com.zhiyuan.util.DBOpenHelper;

/**
 * Created by admin on 2017/12/8.
 */

public class MyApp extends Application {
    public static Context mContext;
    public static final  String DB_NAME = "daily.db";
    public static final  String SP_NAME = "daily_sp";
    private static DBOpenHelper mDbOpenHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        mDbOpenHelper = new DBOpenHelper(mContext,DB_NAME,null,1);
    }
}
