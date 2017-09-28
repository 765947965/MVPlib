package com.xwl.net.mvplib.db.sharedpreferences.storage;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.xwl.net.mvplib.db.sharedpreferences.cryptographic.CryptographicString;
import com.xwl.net.mvplib.db.sharedpreferences.cryptographic.ICryptographicString;
import com.xwl.net.mvplib.db.sharedpreferences.custom.CustomCombination;
import com.xwl.net.mvplib.db.sharedpreferences.custom.ICustomCombination;


/**
 * @author: xiewenliang
 * @Filename: StorageString
 * @Description: 字符串加解密存储封装
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/5/10 10:20
 */

public class StorageString implements IStorageString {

    /**
     * 字符串加解密工具
     */
    private ICryptographicString mCryptographicString;
    /**
     * 对加密后的字符串根据用户标识、版本号进行封装
     */
    private ICustomCombination mCustomCombination;
    /**
     * SharedPreferences类
     */
    private SharedPreferences mSharedPreferences;

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 16:13
     *
     * @param sharedPreferences SharedPreferences
     * @param mUserFlag         用户标识
     * @param mVersion          版本标识
     */
    public StorageString(SharedPreferences sharedPreferences, String mUserFlag, String mVersion) {
        mCryptographicString = new CryptographicString();
        mCustomCombination = new CustomCombination(mUserFlag, mVersion);
        this.mSharedPreferences = sharedPreferences;
    }


    @Override
    public void putString(String key, String value) {
        // 对字符串进行加密
        String encryptContext = mCryptographicString.encryptString(value);
        // 对字符串进行组装(包含到已有的数据组当中)
        String customContext = mCustomCombination.assembly(encryptContext, mSharedPreferences.getString(key, ""));
        // 对字符串进行空转换
        customContext = TextUtils.isEmpty(customContext) ? "" : customContext;
        // 存储
        mSharedPreferences.edit().putString(key, customContext).apply();
    }

    @Override
    public String getString(String key, String defValue) {
        // 获取组装后的字符串数组
        String customContext = mSharedPreferences.getString(key, "");
        // 对组装的字符串数组进行拆解
        String encryptContext = mCustomCombination.disassembly(customContext);
        // 解密拆解后的字符串
        return TextUtils.isEmpty(encryptContext) ? defValue : mCryptographicString.decodeString(encryptContext);
    }
}
