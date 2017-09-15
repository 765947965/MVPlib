package com.xwl.net.mvplib.net.callback;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.Request;


/**
 * <br> ClassName:   EncryptCallback
 * <br> Description: 加解密
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/13 16:32
 */

abstract class AbstractEncryptCallback<T> extends AbsCallback<T> {
    @Override
    public void onStart(Request<T, ? extends Request> request) {
        if (request instanceof PostRequest) {
            // todo
        } else if (request instanceof GetRequest) {
            // todo
        }
    }

    /**
     * <br> Description: 解密数据
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 14:08
     *
     * @param nativeString 原始数据
     * @return 解密数据
     * @throws Throwable 解密异常
     */
    public String decryption(String nativeString) throws Throwable {
        // todo
        return nativeString;
    }
}
