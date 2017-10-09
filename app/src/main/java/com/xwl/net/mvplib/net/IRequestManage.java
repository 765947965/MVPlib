package com.xwl.net.mvplib.net;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;

/**
 * <br> ClassName:   IRequestManage
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/14 9:56
 */

public interface IRequestManage extends IResultCodeActionManage {
    /**
     * <br> Description: 开启开始请求提示语
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 16:01
     *
     * @param tips 提示语
     */
    void startRequest(String tips);

    /**
     * <br> Description: 加入请求管理
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 16:02
     *
     * @param request Request
     * @param tag     tag
     */
    void addCall(Request request, Object tag);

    /**
     * <br> Description: 取消指定请求
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 16:02
     *
     * @param tag tag
     */
    void closeCall(Object tag);

    /**
     * <br> Description: 取消本页面所有请求
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 16:03
     */
    void closeAllCall();

    /**
     * <br> Description: 请求成功
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 16:04
     *
     * @param <T>  T
     * @param bean bean
     */
    <T> void onSuccess(T bean);

    /**
     * <br> Description: 请求失败
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 16:05
     *
     * @param <T>  T
     * @param bean bean
     * @param e    Throwable
     */
    <T> void requestFail(T bean, Throwable e);

    /**
     * <br> Description: 无网络
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 16:05
     */
    void requestNotNet();

    /**
     * <br> Description: 请求结束
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 16:05
     */
    void requestFinish();

    /**
     * <br> Description: 设置重载
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 16:05
     *
     * @param request  Request
     * @param callback AbsCallback
     */
    void setRetryRequest(Request request, AbsCallback callback);

    /**
     * <br> Description: 重载
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 16:06
     */
    void retryRequest();
}
