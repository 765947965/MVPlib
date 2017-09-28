package com.xwl.net.mvplib.db.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


import com.xwl.net.mvplib.db.sharedpreferences.storage.IStorageBaseType;
import com.xwl.net.mvplib.db.sharedpreferences.storage.StorageBaseType;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description: 本地数据存储(SharedPreferences)管理类
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/5/10 11:45
 */

public class SharedManager {

    /**
     * SharedManager存储集合
     */
    private static Map<String, WeakReference<SharedManager>> sharedMap = new HashMap<>();

    /**
     * SharedPreferences类
     */
    private SharedPreferences mSharedPreferences;
    /**
     * 类型存储
     */
    private IStorageBaseType mStorageBaseType;

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 16:14
     *
     * @param context      上下文
     * @param sharedConfig 基础配置
     */
    private SharedManager(Context context, SharedConfig sharedConfig) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot with a null Context");
        } else {
            context = context.getApplicationContext() == null ? context : context.getApplicationContext();
            mSharedPreferences = context.getSharedPreferences(sharedConfig.getShareFileName(),
                    sharedConfig.getShareMode());
            mStorageBaseType = new StorageBaseType(mSharedPreferences, sharedConfig.getShareFlag(),
                    getVersion(context, sharedConfig.isVersionControl()));
        }
    }


    /**
     * 获取本地存储管理类
     *
     * @param context      Context
     * @param sharedConfig 配置信息
     * @return 返回管理类
     */
    public static SharedManager get(Context context, SharedConfig sharedConfig) {
        String mapKey = sharedConfig.getShareFlag() + sharedConfig.getShareFileName();
        if (!sharedMap.containsKey(mapKey) || sharedMap.get(mapKey).get() == null) {
            synchronized (SharedManager.class) {
                if (!sharedMap.containsKey(mapKey) || sharedMap.get(mapKey).get() == null) {
                    SharedManager sharedManager = new SharedManager(context, sharedConfig);
                    sharedMap.put(mapKey, new WeakReference<>(sharedManager));
                }
            }
        }
        return sharedMap.get(mapKey).get();
    }

    /**
     * 清除存储的数据
     */
    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }

    /**
     * 保存字符串
     *
     * @param key   键
     * @param value 值
     */
    public void putString(String key, String value) {
        mStorageBaseType.putString(key, value);
    }

    /**
     * 获取字符串
     *
     * @param key      键
     * @param defValue 默认值
     * @return 返回字符串
     */
    public String getString(String key, String defValue) {
        return mStorageBaseType.getString(key, defValue);
    }

    /**
     * 保存int
     *
     * @param key   键
     * @param value 值
     */
    public void putInt(String key, int value) {
        mStorageBaseType.putInt(key, value);
    }

    /**
     * 获取int
     *
     * @param key      键
     * @param defValue 默认值
     * @return 返回int
     */
    public int getInt(String key, int defValue) {
        return mStorageBaseType.getInt(key, defValue);
    }

    /**
     * 保存long
     *
     * @param key   键
     * @param value 值
     */
    public void putLong(String key, long value) {
        mStorageBaseType.putLong(key, value);
    }

    /**
     * 获取long
     *
     * @param key      键
     * @param defValue 默认值
     * @return 返回long
     */
    public long getLong(String key, long defValue) {
        return mStorageBaseType.getLong(key, defValue);
    }

    /**
     * 保存float
     *
     * @param key   键
     * @param value 值
     */
    public void putFloat(String key, float value) {
        mStorageBaseType.putFloat(key, value);
    }

    /**
     * 获取float
     *
     * @param key      键
     * @param defValue 默认值
     * @return 返回float
     */
    public float getFloat(String key, float defValue) {
        return mStorageBaseType.getFloat(key, defValue);
    }

    /**
     * 保存double
     *
     * @param key   键
     * @param value 值
     */
    public void putDouble(String key, double value) {
        mStorageBaseType.putDouble(key, value);
    }

    /**
     * 获取double
     *
     * @param key      键
     * @param defValue 默认值
     * @return 返回float
     */
    public double getDouble(String key, double defValue) {
        return mStorageBaseType.getDouble(key, defValue);
    }

    /**
     * 保存boolean
     *
     * @param key   键
     * @param value 值
     */
    public void putBoolean(String key, boolean value) {
        mStorageBaseType.putBoolean(key, value);
    }

    /**
     * 获取boolean
     *
     * @param key      键
     * @param defValue 默认值
     * @return 返回boolean
     */
    public boolean getBoolean(String key, boolean defValue) {
        return mStorageBaseType.getBoolean(key, defValue);
    }

    /**
     * 本地存储中是否包涵该key的值
     *
     * @param key 键
     * @return 是否包涵
     */
    public boolean contains(String key) {
        return mSharedPreferences.contains(key);
    }

    /**
     * 返回版本号
     *
     * @param context          Context
     * @param isVersionControl 是否需要版本控制
     * @return 版本号
     */
    private String getVersion(Context context, boolean isVersionControl) {
        if (isVersionControl) {
            try {
                PackageManager pm = context.getPackageManager();
                PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
                return String.valueOf(pi.versionCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "#0.00";
    }
}
