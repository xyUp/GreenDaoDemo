package com.example.xy.greendaodemo.db;

import android.content.Context;

import com.example.xy.greendaodemo.gen.DaoMaster;
import com.example.xy.greendaodemo.gen.DaoSession;

/**
 * Created by xy on 2017/10/20.
 */

public class DBHelper {
    private static DBHelper instance;
    private static Context sContext;
    private static DaoMaster sDaoMaster;
    private static DaoSession sDaoSession;

    public DBHelper(){}

    public static DBHelper getInstance(){
        if (instance == null){
            instance = new DBHelper();
        }
        return instance;
    }

    public static DaoMaster getDaoMaster(Context context){
        synchronized(DBHelper.class) {
            if (sDaoMaster == null) {
                DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "notes.db", null);
                sDaoMaster = new DaoMaster(helper.getWritableDb());
            }
        }
        return sDaoMaster;
    }

    public static DaoSession getDaoSession(Context context){
        if (sDaoSession == null){
            if (sDaoMaster == null){
                sDaoMaster = getDaoMaster(context);
            }
            sDaoSession = sDaoMaster.newSession();
        }
        return sDaoSession;
    }

    public static void init(Context context){
        sContext = context;
        instance = new DBHelper();

        sDaoSession = getDaoSession(sContext);
    }



}
