package com.xwl.net.mvplib.ui.base.view;

import android.app.ProgressDialog;
import android.view.View;

import com.xwl.net.mvplib.ui.base.contract.BaseNetWorkPresenter;
import com.xwl.net.mvplib.ui.base.contract.IBaseNetWorkView;
import com.xwl.net.mvplib.ui.base.reload.ReloadTips;

/**
 * <br> ClassName:   AbstractNetWorkFragment
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/19 10:55
 */

public abstract class AbstractNetWorkFragment<V extends IBaseNetWorkView, P extends BaseNetWorkPresenter<V>>
        extends AbstractMVPFragment<V, P> implements IBaseNetWorkView {
    private View mReloadTipsView;
    private ProgressDialog mProgressDialog;

    @Override
    public void startLoadingAnim(String tips) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mActivity);
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
        if (mReloadTips.getView(mActivity, mRootViewGroup) != mReloadTipsView) {
            dismissLoadErrorUI();
            mReloadTipsView = mReloadTips.getView(mActivity, mRootViewGroup);
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
