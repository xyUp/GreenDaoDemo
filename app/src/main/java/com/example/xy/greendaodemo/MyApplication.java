package com.example.xy.greendaodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.xy.greendaodemo.db.DBHelper;
import com.example.xy.greendaodemo.gen.DaoMaster;
import com.example.xy.greendaodemo.gen.DaoSession;

/**
 * Created by xy on 2017/10/20.
 */

public class MyApplication extends Application {
    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static MyApplication getInstance(){
        if (instance == null){
            instance = new MyApplication();
        }
        return instance;
    }

    public DBHelper getDBHelper(){
       return DBHelper.getInstance();
    }
}
