package com.swedne.locdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/1/31.
 */


public class DBManager {

    private final int BUFFER_SIZE = 400000;
    public static final String DB_NAME = "test.db"; //保存的数据库文件名
    public static final String PACKAGE_NAME = "com.swedne.locdb/databases";
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME;  //在手机里存放数据库的位置(/data/data/com.cssystem.activity/cssystem.db)


    private SQLiteDatabase database;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    public void openDatabase() {
        System.out.println(DB_PATH + "/" + DB_NAME);
//        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }

    private SQLiteDatabase openDatabase(String dbfile) {

        try {
            if (!(new File(dbfile).exists())) {
                // 如 SQLite 数据库文件不存在，再检查一下 database 目录是否存在
                File f = new File(dbfile);
                // 如 database 目录不存在，新建该目录
                if (!f.exists()) {
                    f.mkdir();
                }
                //判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
                InputStream is = this.context.getResources().openRawResource(
                        R.raw.game); //欲导入的数据库
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }

            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
            return db;

        } catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
        return null;
    }

    public void closeDatabase() {
        this.database.close();

    }
}