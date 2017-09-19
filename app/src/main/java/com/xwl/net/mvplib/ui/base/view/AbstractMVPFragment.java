package com.xwl.net.mvplib.ui.base.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.xwl.net.mvplib.ui.base.contract.BaseMVPPresenter;
import com.xwl.net.mvplib.ui.base.contract.IBaseMVPView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/19 10:38
 */

public abstract class AbstractMVPFragment<V extends IBaseMVPView, P extends BaseMVPPresenter<V>> extends Fragment
        implements IBaseMVPView {

    protected Activity mActivity;

    protected FrameLayout mRootViewGroup;

    protected P mPresenter;

    protected V mBaseView;

    private Unbinder mUnBinder;


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
     * <br> Description: 创建主布局
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 14:53
     *
     * @param root ViewGroup
     * @return 主布局
     */
    protected abstract View createContentView(ViewGroup root);

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        mRootViewGroup = new FrameLayout(getActivity());
        mRootViewGroup.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        View contentView = createContentView(mRootViewGroup);
        if (contentView != null) {
            mRootViewGroup.addView(contentView);
        }
        mPresenter = createPresenter();
        mBaseView = createBaseView();
        if (mPresenter != null && mBaseView != null) {
            mPresenter.attachView(mBaseView);
        }
        mUnBinder = ButterKnife.bind(mRootViewGroup);
        return mRootViewGroup;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
            mBaseView = null;
        }
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    @Override
    public void showToast(String info) {
        Toast.makeText(mActivity, info, Toast.LENGTH_SHORT).show();
    }
}
