package com.xwl.net.mvplib.sharedpreferences.storage;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.xwl.net.mvplib.sharedpreferences.cryptographic.CryptographicString;
import com.xwl.net.mvplib.sharedpreferences.cryptographic.ICryptographicString;


/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/5/10 10:20
 */

public class StorageString implements IStorageString {

    /**
     * 字符串加解密工具
     */
    private ICryptographicString cryptographicString;
    /**
     * SharedPreferences类
     */
    private SharedPreferences sharedPreferences;

    public StorageString(SharedPreferences sharedPreferences) {
        cryptographicString = new CryptographicString();
        this.sharedPreferences = sharedPreferences;
    }


    @Override
    public void putString(String key, String value) {
        String encryptContext = cryptographicString.encryptString(value);
        encryptContext = TextUtils.isEmpty(encryptContext) ? "" : encryptContext;
        sharedPreferences.edit().putString(key, encryptContext).apply();
    }

    @Override
    public String getString(String key, String defValue) {
        String context = sharedPreferences.getString(key, "");
        return TextUtils.isEmpty(context) ? defValue : cryptographicString.decodeString(context);
    }
}
