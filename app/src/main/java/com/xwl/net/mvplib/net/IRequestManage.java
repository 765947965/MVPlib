package com.xwl.net.mvplib.net;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/14 9:56
 */

public interface IRequestManage {
    void startRequest(String tips);

    void addCall(Request request, Object tag);

    void closeCall(Object tag);

    void closeAllCall();

    <T> void onSuccess(T bean);

    <T> void requestFail(T bean, Throwable e);

    void requestNotNet();

    void requestFinish();

    void setRetryRequest(Request request, AbsCallback callback);

    void retryRequest();
}
