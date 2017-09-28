package com.xwl.net.mvplib.db.sharedpreferences.cryptographic;

/**
 * @author: xiewenliang
 * @Filename: 加解密（字节）
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/5/10 9:08
 */

public interface ICryptographic {
    /**
     * 加密字节
     *
     * @param context 待加密字节
     * @param key     密钥
     * @return 加密后的字节
     */
    byte[] encryptByte(byte[] context, String key);

    /**
     * 解密字节流
     *
     * @param context 待解密字节
     * @param key     密钥
     * @return 解密后的字节
     */
    byte[] decodeByte(byte[] context, String key);

    /**
     * <br> Description: 获取加密key
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 16:08
     *
     * @return 返回加密key
     */
    String getKey();
}
