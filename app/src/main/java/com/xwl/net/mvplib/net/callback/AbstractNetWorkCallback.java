package com.xwl.net.mvplib.net.callback;

import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.request.base.Request;
import com.xwl.net.mvplib.net.IRequestManage;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/15 14:23
 */

public abstract class AbstractNetWorkCallback<T> extends AbstractEncryptCallback<T> {

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
    public void onSuccess(com.lzy.okgo.model.Response<T> response) {
        mIRequestManage.onSuccess(response);
    }

    @Override
    public void onCacheSuccess(com.lzy.okgo.model.Response<T> response) {
        super.onCacheSuccess(response);
        mIRequestManage.onSuccess(response);
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        Throwable e = response.getException();
        if (e != null) {
            if (e instanceof UnknownHostException || e instanceof ConnectException) {
                // 网络链接失败
                mIRequestManage.requestNotNet();
            } else if (e instanceof SocketTimeoutException) {
                // 网络链接超时
                mIRequestManage.requestFail(response);
            } else if (e instanceof HttpException) {
                // 服务器响应400和500+
                mIRequestManage.requestFail(response);
            } else if (e instanceof StorageException) {
                // sd卡不存在，无权限
                mIRequestManage.requestFail(response);
            } else {
                // 业务无错误
                mIRequestManage.requestFail(response);
            }
        } else {
            mIRequestManage.requestFail(response);
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        mIRequestManage.requestFinish();
        mIRequestManage.closeCall(this);
    }

}
