package com.xwl.net.mvplib.db.sharedpreferences.storage;

/**
 * @author: xiewenliang
 * @Filename: IStorageBaseType
 * @Description: 基本数据类型存储封装
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/5/10 15:27
 */

public interface IStorageBaseType extends IStorageString {

    /**
     * <br> Description: 存储int
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 16:10
     *
     * @param key   key
     * @param value value
     */
    void putInt(String key, int value);

    /**
     * <br> Description: 获取存储的值
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 16:10
     *
     * @param key      key
     * @param defValue 默认值
     * @return 返回存储的值
     */
    int getInt(String key, int defValue);

    /**
     * <br> Description: 存储long
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 16:10
     *
     * @param key   key
     * @param value value
     */
    void putLong(String key, long value);

    /**
     * <br> Description: 获取存储的值
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 16:10
     *
     * @param key      key
     * @param defValue 默认值
     * @return 返回存储的值
     */
    long getLong(String key, long defValue);

    /**
     * <br> Description: 存储float
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 16:10
     *
     * @param key   key
     * @param value value
     */
    void putFloat(String key, float value);

    /**
     * <br> Description: 获取存储的值
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 16:10
     *
     * @param key      key
     * @param defValue 默认值
     * @return 返回存储的值
     */
    float getFloat(String key, float defValue);

    /**
     * <br> Description: 存储boolean
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 16:10
     *
     * @param key   key
     * @param value value
     */
    void putBoolean(String key, boolean value);

    /**
     * <br> Description: 获取存储的值
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 16:10
     *
     * @param key      key
     * @param defValue 默认值
     * @return 返回存储的值
     */
    boolean getBoolean(String key, boolean defValue);

    /**
     * <br> Description: 存储double
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 16:10
     *
     * @param key   key
     * @param value value
     */
    void putDouble(String key, double value);

    /**
     * <br> Description: 获取存储的值
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 16:10
     *
     * @param key      key
     * @param defValue 默认值
     * @return 返回存储的值
     */
    double getDouble(String key, double defValue);
}
