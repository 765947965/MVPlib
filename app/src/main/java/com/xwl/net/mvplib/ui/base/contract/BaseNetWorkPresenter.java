package com.xwl.net.mvplib.ui.base.contract;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;
import com.xwl.net.mvplib.net.IRequestManage;
import com.xwl.net.mvplib.ui.base.reload.ReloadTips;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/14 14:14
 */

public class BaseNetWorkPresenter<V extends IBaseNetWorkView> extends BaseMVPPresenter<V> implements IRequestManage {

    private BlockingQueue<Object> mRequestList = new LinkedBlockingDeque<>();

    private Request mRequest;

    private AbsCallback mCallback;

    @Override
    public void detachView() {
        super.detachView();
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
    public <T> void onSuccess(T bean) {
        if (mBaseIView != null) {
            mBaseIView.dismissLoadErrorUI();
        }
    }

    @Override
    public <T> void requestFail(T bean, Throwable e) {
        if (mBaseIView != null) {
            mBaseIView.showLoadErrorUI(ReloadTips.getLoadErrorDefault());
            if (e != null) {
                mBaseIView.showToast(e.getMessage());
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
        this.mRequest = request;
        this.mCallback = callback;
    }

    @Override
    public void retryRequest() {
        if (mRequest != null && mCallback != null) {
            mRequest.execute(mCallback);
        }
    }
}
