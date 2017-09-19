package com.xwl.net.mvplib.ui.base.view;

import android.app.ProgressDialog;
import android.view.View;

import com.xwl.net.mvplib.ui.base.contract.BaseNetWorkPresenter;
import com.xwl.net.mvplib.ui.base.contract.IBaseNetWorkView;
import com.xwl.net.mvplib.ui.base.reload.ReloadTips;


/**
 * <br> ClassName:  AbstractNetWorkActivity
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/14 11:07
 */
public abstract class AbstractNetWorkActivity<V extends IBaseNetWorkView, P extends BaseNetWorkPresenter<V>>
        extends AbstractMVPActivity<V, P> implements IBaseNetWorkView {
    private View mReloadTipsView;
    private ProgressDialog mProgressDialog;


    @Override
    public void startLoadingAnim(String tips) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }
        mProgressDialog.setMessage(tips);
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void stopLoadingAnim() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showLoadErrorUI(ReloadTips mReloadTips) {
        if (mReloadTips.getView(this, mRootViewGroup) != mReloadTipsView) {
            dismissLoadErrorUI();
            mReloadTipsView = mReloadTips.getView(this, mRootViewGroup);
            mRootViewGroup.addView(mReloadTipsView);
            mReloadTipsView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPresenter != null) {
                        mPresenter.retryRequest();
                    }
                }
            });
        }
    }

    @Override
    public void dismissLoadErrorUI() {
        if (mReloadTipsView != null) {
            mRootViewGroup.removeView(mReloadTipsView);
        }
    }
}
