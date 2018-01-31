package com.swedne.locdb;

import android.app.Application;

/**
 * Created by Administrator on 2018/1/31.
 */

public class MyApplication extends Application {
    private static MyApplication mContext;

    public MyApplication() {
        mContext = this;
    }

    public static MyApplication getInstance() {
        return mContext;
    }
}
