package com.xwl.net.mvplib.ui.base.contract;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xwl.net.mvplib.net.IRequestManage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/14 14:14
 */

public class BasePresenter<V extends IBaseView> implements IRequestManage {

    protected V mBaseIView;

    private BlockingQueue<Object> mRequestList = new LinkedBlockingDeque<>();

    private Request request;

    private AbsCallback callback;

    public void attachView(V mBaseIView) {
        this.mBaseIView = mBaseIView;
    }

    public void detachView() {
        mBaseIView = null;
        closeAllCall();
    }

    @Override
    public void startRequest(String tips) {
        if (mBaseIView != null) {
            mBaseIView.startLoadingAnim(tips);
        }
    }

    @Override
    public void addCall(Request request, Object tag) {
        mRequestList.add(tag);
        request.tag(tag);
    }

    @Override
    public void closeCall(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }

    @Override
    public void closeAllCall() {
        for (Object tag : mRequestList) {
            OkGo.getInstance().cancelTag(tag);
        }
        mRequestList.clear();
    }

    @Override
    public <B> void onSuccess(Response<B> response) {
        if (mBaseIView != null) {
            mBaseIView.dismissLoadErrorUI();
        }
    }

    @Override
    public <B> void requestFail(Response<B> response) {
        if (mBaseIView != null) {
            mBaseIView.showLoadErrorUI(ReloadTips.getLoadErrorDefault());
            if (response.getException() != null) {
                mBaseIView.showToast(response.getException().getMessage());
            }
        }
    }

    @Override
    public void requestNotNet() {
        if (mBaseIView != null) {
            mBaseIView.showLoadErrorUI(ReloadTips.getLoadNotNetDefault());
        }
    }

    @Override
    public void requestFinish() {
        if (mBaseIView != null) {
            mBaseIView.stopLoadingAnim();
        }
    }

    @Override
    public void setRetryRequest(Request request, AbsCallback callback) {
        this.request = request;
        this.callback = callback;
    }

    @Override
    public void retryRequest() {
        if (request != null && callback != null) {
            request.execute(callback);
        }
    }
}
