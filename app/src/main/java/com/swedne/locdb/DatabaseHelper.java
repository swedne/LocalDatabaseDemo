package com.swedne.locdb;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.swedne.locdb.bean.Game;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/31.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "test.db";
    private static final int DB_VERSION = 61;
    private static DatabaseHelper instance;
    private Map<String, Dao> daoMap = new HashMap<String, Dao>();

    public DatabaseHelper() {
        super(MyApplication.getInstance(), DB_NAME, null, DB_VERSION);
    }

    public static synchronized DatabaseHelper getHelper() {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null) {
                    instance = new DatabaseHelper();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        createTables(connectionSource);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(database, connectionSource);
    }

    /**
     * 创建表
     */
    public static void createTables(ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Game.class);
//            TableUtils.createTable(connectionSource, Immediate.class);
//            TableUtils.createTable(connectionSource, MatchResult.class);
//            TableUtils.createTable(connectionSource, MatchSchedule.class);
//            TableUtils.createTable(connectionSource, MatchNumber.class);
//            TableUtils.createTable(connectionSource, NumberOdds.class);
//            TableUtils.createTable(connectionSource, User.class);
//            TableUtils.createTable(connectionSource, Msg.class);
//            TableUtils.createTable(connectionSource, GambleHallMatch.class);
//            TableUtils.createTable(connectionSource, Cache.class);
//            TableUtils.createTable(connectionSource, BannerBean.class);
//            TableUtils.createTable(connectionSource, Notice.class);
//            TableUtils.createTable(connectionSource, Category.class);
//            TableUtils.createTable(connectionSource, Product.class);
        } catch (SQLException ex) {
            Log.e("DatabaseHelper", "创建数据库表失败" + ex);
        }
    }

    /**
     * 删除表
     */
    public static void removeTables(ConnectionSource connectionSource) {
//        try {
//            TableUtils.dropTable(connectionSource, GqMatch.class, true);
//            TableUtils.dropTable(connectionSource, Immediate.class, true);
//            TableUtils.dropTable(connectionSource, MatchResult.class, true);
//            TableUtils.dropTable(connectionSource, MatchSchedule.class, true);
//            TableUtils.dropTable(connectionSource, MatchNumber.class, true);
//            TableUtils.dropTable(connectionSource, NumberOdds.class, true);
//            TableUtils.dropTable(connectionSource, User.class, true);
//            TableUtils.dropTable(connectionSource, Msg.class, true);
//            TableUtils.dropTable(connectionSource, GambleHallMatch.class, true);
//            TableUtils.dropTable(connectionSource, Cache.class, true);
//        } catch (SQLException ex) {
//            LogUtil.e("删除数据库表失败", ex);
//        }
    }


    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daoMap.containsKey(className)) {
            dao = daoMap.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daoMap.put(className, dao);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        daoMap.clear();

    }

    /**
     * 清空数据库
     */
    public void removeAllData() {
        for (String key : daoMap.keySet()) {
            try {
                daoMap.get(key).deleteBuilder().delete();
            } catch (SQLException e) {

            }
        }
    }

}
