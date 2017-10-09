package com.xwl.net.mvplib;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.xwl.net.mvplib.net.HttpUtils;

/**
 * <br> ClassName:   MyApplication
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/15 9:50
 */

public class MyApplication extends Application {
    /*** 全局Context ***/
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        HttpUtils.init(this);
    }
}
