package com.flj.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;



/**
 * Created by cguyu on 2018/6/3.
 */

public class DatabaseManager {
    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DatabaseManager() {
    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCE;
    }
     //fast_ec.db 数据库名称();
    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao() {
        return mDao;
    }
}