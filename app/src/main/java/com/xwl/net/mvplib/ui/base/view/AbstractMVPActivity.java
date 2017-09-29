package com.xwl.net.mvplib.ui.base.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.xwl.net.mvplib.ui.base.contract.BaseMVPPresenter;
import com.xwl.net.mvplib.ui.base.contract.IBaseMVPView;


/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/14 11:08
 */

public abstract class AbstractMVPActivity<V extends IBaseMVPView, P extends BaseMVPPresenter<V>>
        extends AppCompatActivity implements IBaseMVPView {

    protected FrameLayout mRootViewGroup;

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

    @Deprecated
    @Override
    public final void setContentView(@LayoutRes int layoutResID) {
        throw new IllegalStateException("this method is deprecated, please use mRootViewGroup replace");
    }

    @Deprecated
    @Override
    public final void setContentView(View view) {
        throw new IllegalStateException("this method is deprecated, please use mRootViewGroup replace");
    }

    @Deprecated
    @Override
    public final void setContentView(View view, ViewGroup.LayoutParams params) {
        throw new IllegalStateException("this method is deprecated, please use mRootViewGroup replace");
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
}
