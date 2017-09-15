package com.xwl.net.mvplib.ui.base.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.xwl.net.mvplib.ui.base.contract.BasePresenter;
import com.xwl.net.mvplib.ui.base.contract.IBaseView;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/14 11:08
 */

public abstract class AbstractMVPActivity<V extends IBaseView, P extends BasePresenter<V>> extends AppCompatActivity
        implements IBaseView {

    protected FrameLayout mRootViewGroup;

    private View mRootContentView;

    protected P mPresenter;

    protected V mBaseView;

    /**
     * <br> Description: 创建Presenter
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 9:43
     *
     * @return Presenter
     */
    protected abstract P createPresenter();

    /**
     * <br> Description: 创建BaseView
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 9:43
     *
     * @return BaseView
     */
    protected abstract V createBaseView();

    /**
     * <br> Description: onCreate
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 9:39
     *
     * @param savedInstanceState Bundle
     */
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootViewGroup = new FrameLayout(this);
        mRootViewGroup.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        super.setContentView(mRootViewGroup);
        mPresenter = createPresenter();
        mBaseView = createBaseView();
        if (mPresenter != null && mBaseView != null) {
            mPresenter.attachView(mBaseView);
        }
    }

    @Override
    public final void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        setContentView(view);
    }

    @Override
    public final void setContentView(View view) {
        setContentView(view, null);
    }

    @Override
    public final void setContentView(View view, ViewGroup.LayoutParams params) {
        if (view != null) {
            if (mRootContentView != null) {
                mRootViewGroup.removeView(mRootContentView);
            }
            mRootContentView = view;
            if (params != null) {
                mRootViewGroup.addView(mRootContentView, params);
            } else {
                mRootViewGroup.addView(mRootContentView);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mBaseView = null;
        }
    }

    @Override
    public void showToast(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinish() {
        willFinish();
        finish();
    }

    /**
     * <br> Description: finish前的回调
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 9:41
     */
    protected void willFinish() {

    }
}
