package com.xwl.net.mvplib.net.callback;

import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.request.base.Request;
import com.xwl.net.mvplib.net.IRequestManage;
import com.xwl.net.mvplib.net.bean.CriterionBean;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * <br> ClassName:   AbstractNetWorkCallback
 * <br> Description:  自定义网络处理逻辑
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/15 14:23
 */

public abstract class AbstractNetWorkCallback<T> extends AbstractJsonCallback<T> {

    private IRequestManage mIRequestManage;
    private String mTips;
    private boolean mIsRetry;

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 14:29
     *
     * @param mIRequestManage IRequestManage
     */
    public AbstractNetWorkCallback(IRequestManage mIRequestManage) {
        this(mIRequestManage, null);
    }

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 14:29
     *
     * @param mIRequestManage IRequestManage
     * @param tips            加载提示
     */
    public AbstractNetWorkCallback(IRequestManage mIRequestManage, String tips) {
        this(mIRequestManage, tips, false);
    }

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 14:29
     *
     * @param mIRequestManage IRequestManage
     * @param tips            加载提示
     * @param isRetry         是否可以重新加载
     */
    public AbstractNetWorkCallback(IRequestManage mIRequestManage, String tips, boolean isRetry) {
        this.mIRequestManage = mIRequestManage;
        this.mTips = tips;
        this.mIsRetry = isRetry;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        mIRequestManage.startRequest(mTips);
        mIRequestManage.addCall(request, this);
        if (mIsRetry) {
            mIRequestManage.setRetryRequest(request, this);
        }
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        // 处理的都属于网络错误或者数据解析错误(不需要通知业务逻辑处理callback)
        Throwable e = response.getException();
        if (e != null) {
            if (e instanceof UnknownHostException || e instanceof ConnectException) {
                // 网络链接失败
                businessFailNotNet();
            } else if (e instanceof SocketTimeoutException) {
                // 网络链接超时
                businessFail(response.body(), e);
            } else if (e instanceof HttpException) {
                // 服务器响应400和500+
                businessFail(response.body(), e);
            } else if (e instanceof StorageException) {
                // sd卡不存在，无权限
                businessFail(response.body(), e);
            } else if (e instanceof JsonSyntaxException) {
                // 数据解析错误
                businessFail(response.body(), e);
            } else {
                // 业务错误
                businessFail(response.body(), e);
            }
        } else {
            businessFail(response.body(), new HttpException("未知错误"));
        }
    }


    /**
     * <br> Description: 业务处理逻辑成功
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 15:28
     *
     * @param t bean
     */
    protected final void businessSuccess(T t) {
        if (mIsRetry) {
            mIRequestManage.onSuccess(t);
        }
        abstractSuccess(t);
    }

    /**
     * <br> Description: 业务处理逻辑失败
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 15:28
     *
     * @param t bean
     * @param e 错误
     */
    protected final void businessFail(T t, Throwable e) {
        if (mIsRetry) {
            mIRequestManage.requestFail(t, e);
        }
        if (t instanceof CriterionBean) {
            mIRequestManage.disposeResultCodeAction((CriterionBean) t);
        }
        abstractFail(t, e);
    }

    /**
     * <br> Description: 业务处理逻辑失败(无网络)
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 15:28
     */
    protected final void businessFailNotNet() {
        if (mIsRetry) {
            mIRequestManage.requestNotNet();
        }
    }

    /**
     * <br> Description: 面向用户处理成功
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 15:28
     *
     * @param t bean
     */
    protected abstract void abstractSuccess(T t);

    /**
     * <br> Description: 面向用户处理失败
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 15:28
     *
     * @param t bean
     * @param e 错误
     */
    protected void abstractFail(T t, Throwable e) {

    }

    @Override
    public void onFinish() {
        super.onFinish();
        mIRequestManage.requestFinish();
        mIRequestManage.closeCall(this);
    }

}
