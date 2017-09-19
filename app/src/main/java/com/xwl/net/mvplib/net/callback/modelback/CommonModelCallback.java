package com.xwl.net.mvplib.net.callback.modelback;


/**
 * <br> ClassName:   CommonRequestCallback
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/8/8 9:48
 */

public class CommonModelCallback<T> implements ICommonModelCallback<T> {
    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onSuccess(T t, Object... list) {

    }

    @Override
    public void onFailure(T t, Throwable e) {

    }
}
