package com.xwl.net.mvplib.db.sharedpreferences.custom;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * <br> ClassName:   CustomCombination
 * <br> Description: 对数据根据用户属性和版本属性的不同进行封装
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/27 15:35
 */

public class CustomCombination implements ICustomCombination {

    /*** 用户标识 ***/
    private String mUserFlag;
    /*** 版本标识 ***/
    private String mVersion;

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 16:09
     *
     * @param mUserFlag 用户标识
     * @param mVersion  版本标识
     */
    public CustomCombination(String mUserFlag, String mVersion) {
        if (TextUtils.isEmpty(mUserFlag) || TextUtils.isEmpty(mVersion)) {
            throw new IllegalStateException("mUserFlag or mVersion is null");
        }
        this.mUserFlag = mUserFlag;
        this.mVersion = mVersion;
    }

    @Override
    public String assembly(String cipher, String localArray) {
        try {
            cipher = TextUtils.isEmpty(cipher) ? "" : cipher;
            JSONObject array = null;
            if (!TextUtils.isEmpty(localArray)) {
                array = new JSONObject(localArray);
            }
            if (array == null) {
                array = new JSONObject();
            }
            JSONObject item = new JSONObject();
            item.put(mVersion, cipher);
            array.put(mUserFlag, item);
            return array.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String disassembly(String customCipher) {
        try {
            if (!TextUtils.isEmpty(customCipher)) {
                JSONObject array = new JSONObject(customCipher);
                JSONObject item = array.getJSONObject(mUserFlag);
                if (item != null) {
                    return item.getString(mVersion);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
