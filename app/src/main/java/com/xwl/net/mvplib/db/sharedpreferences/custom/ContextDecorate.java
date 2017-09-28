package com.xwl.net.mvplib.db.sharedpreferences.custom;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author: xiewenliang
 * @Filename: ContextDecorate
 * @Description: 密钥与密文组装实例
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/5/11 14:17
 */

public class ContextDecorate implements IContextDecorate {
    private static final String JSON_KEY = "$PS_DR_K#";
    private static final String JSON_VALUE = "$PS_DR_V#";

    @Override
    public String getDecorateContext(String key, String cipherText) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(JSON_KEY, key);
            jsonObject.put(JSON_VALUE, cipherText);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String[] getKeyAndCipherText(String decorateContext) {
        if (decorateContext.startsWith("{") && decorateContext.endsWith("}")) {
            try {
                JSONObject jsonObject = new JSONObject(decorateContext);
                String[] array = new String[2];
                array[0] = jsonObject.optString(JSON_KEY);
                array[1] = jsonObject.optString(JSON_VALUE);
                return array;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
