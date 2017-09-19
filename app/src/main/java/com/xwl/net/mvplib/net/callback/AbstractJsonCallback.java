package com.xwl.net.mvplib.net.callback;


import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * <br> ClassName:   JsonCallback
 * <br> Description: 自定义业务数据处理逻辑
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/13 16:02
 */

public abstract class AbstractJsonCallback<T> extends AbstractEncryptCallback<T> {

    @Override
    public T convertResponse(Response response) throws Throwable {
        // T = E<F>
        // T
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalArgumentException("The generic is empty");
        }
        // E
        Type rawType = ((ParameterizedType) type).getRawType();
        // F
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            return null;
        }
        String body = decryption(responseBody.string());
        responseBody.close();
        return new Gson().fromJson(body, type);
    }
}
