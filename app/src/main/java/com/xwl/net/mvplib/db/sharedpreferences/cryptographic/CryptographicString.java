package com.xwl.net.mvplib.db.sharedpreferences.cryptographic;

import android.text.TextUtils;
import android.util.Base64;


import com.xwl.net.mvplib.db.sharedpreferences.custom.ContextDecorate;
import com.xwl.net.mvplib.db.sharedpreferences.custom.IContextDecorate;

import java.io.UnsupportedEncodingException;

/**
 * @author: xiewenliang
 * @Filename: CryptographicString
 * @Description: 字符串加密与解密
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/5/10 9:25
 */

public class CryptographicString extends Cryptographic implements ICryptographicString {
    /**
     * 密钥与密文组装工具
     */
    private IContextDecorate mContextDecorate;

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 16:08
     */
    public CryptographicString() {
        mContextDecorate = new ContextDecorate();
    }

    /**
     * 加密字符串
     *
     * @param context 待加密字符串
     * @return 加密后字符串
     */
    @Override
    public String encryptString(String context) {
        if (TextUtils.isEmpty(context)) {
            return null;
        }
        try {
            // 获取key
            String key = getKey();
            // 加密数据
            String cipherText = new String(Base64.encode(encryptByte(context.getBytes("UTF-8"), key),
                    Base64.DEFAULT), "UTF-8");
            // 按加密格式进行组装
            return mContextDecorate.getDecorateContext(key, cipherText);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 解密字符串
     *
     * @param context 待解密字符串
     * @return 已解密字符串
     */
    @Override
    public String decodeString(String context) {
        if (TextUtils.isEmpty(context)) {
            return null;
        }
        String[] array = mContextDecorate.getKeyAndCipherText(context);
        if (array == null || array.length != 2) {
            return null;
        }
        String key = array[0];
        String cipherText = array[1];
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(cipherText)) {
            return null;
        }
        try {
            // 对加密格式进行拆解
            return new String(decodeByte(Base64.decode(cipherText.getBytes("UTF-8"), Base64.DEFAULT), key), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
