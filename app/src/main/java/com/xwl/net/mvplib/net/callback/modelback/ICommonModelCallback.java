package com.xwl.net.mvplib.net.callback.modelback;

import com.lzy.okgo.model.Response;

/**
 * <br> ClassName:   ICommonRequestCallback
 * <br> Description: 网络请求统一P层接口回掉
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/8/8 9:43
 */

public interface ICommonModelCallback<T> {
    /**
     * <br> Description: 请求成功
     * <br> Author:      谢文良
     * <br> Date:        2017/8/8 9:47
     *
     * @param t T
     */
    void onSuccess(T t);

    /**
     * <br> Description: 请求成功
     * <br> Author:      谢文良
     * <br> Date:        2017/8/8 9:47
     *
     * @param t    T
     * @param list 参数
     */
    void onSuccess(T t, Object... list);

    /**
     * <br> Description: 请求失败
     * <br> Author:      谢文良
     * <br> Date:        2017/8/8 9:47
     *
     * @param response Response
     */
    void onFailure(Response<T> response);
}
