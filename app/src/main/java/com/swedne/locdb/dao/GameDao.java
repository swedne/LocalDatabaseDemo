package com.swedne.locdb.dao;

import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.swedne.locdb.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2018/1/31.
 */

public class GameDao<Game> {
    //    protected Dao<Game, String> gameDao;
    private Dao gameDaoOpe;

    public GameDao(Game t) {
        try {
            gameDaoOpe = DatabaseHelper.getHelper().getDao(t.getClass());
        } catch (SQLException ex) {
            Log.e("GameDao", "实例化失败" + ex.toString());
        }
    }


    public List<Game> queryAll() {
        List<Game> gameList = null;
        try {
            QueryBuilder queryBuilder = gameDaoOpe.queryBuilder();
            gameList = queryBuilder.query();
        } catch (SQLException ex) {
            Log.e("GameDao", "获取所有数据" + ex.toString());
        }
        return gameList;
    }

    public void insert(Game game) {
        try {
            gameDaoOpe.create(game);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    /**
     * 清空数据
     *
     */
    public void clearAll() {
        try {
            DeleteBuilder deleteBuilder = gameDaoOpe.deleteBuilder();
            deleteBuilder.delete();
        } catch (SQLException ex) {
        }
    }
}
