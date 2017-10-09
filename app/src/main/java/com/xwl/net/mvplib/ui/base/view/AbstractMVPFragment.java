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
 * <br> ClassName:   AbstractMVPFragment
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/19 10:38
 */

public abstract class AbstractMVPFragment<V extends IBaseMVPView, P extends BaseMVPPresenter<V>> extends Fragment
        implements IBaseMVPView, FragmentUserVisibleController.IUserVisibleCallback {

    protected Activity mActivity;

    protected FrameLayout mRootViewGroup;

    protected P mPresenter;

    protected V mBaseView;

    private Unbinder mUnBinder;

    private FragmentUserVisibleController mUserVisibleController;

    private boolean mIsLoadUI;

    private Bundle mSavedInstanceState;

    /**
     * <br> Description: AbstractMVPFragment
     * <br> Author:      谢文良
     * <br> Date:        2017/9/21 11:43
     */
    public AbstractMVPFragment() {
        mUserVisibleController = new FragmentUserVisibleController(this, this);
    }

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
     * <br> Description: 返回主布局Id
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 14:53
     *
     * @return 主布局Id
     */
    protected abstract int createContentViewId();

    /**
     * <br> Description: 创建主布局(相关View的初始化都在这里进行，这里已经可以使用ButterKnife绑定的View变量了)
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 14:53
     *
     * @param view               主布局
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void onCreateContent(View view, Bundle savedInstanceState);

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        mSavedInstanceState = savedInstanceState;
        mRootViewGroup = new FrameLayout(getActivity());
        mRootViewGroup.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        mPresenter = createPresenter();
        mBaseView = createBaseView();
        if (mPresenter != null && mBaseView != null) {
            mPresenter.attachView(mBaseView);
        }
        return mRootViewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUserVisibleController.activityCreated();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    @Override
    public final void setWaitingShowToUser(boolean waitingShowToUser) {
        mUserVisibleController.setWaitingShowToUser(waitingShowToUser);
    }

    @Override
    public final boolean isWaitingShowToUser() {
        return mUserVisibleController.isWaitingShowToUser();
    }

    @Override
    public final boolean isVisibleToUser() {
        return mUserVisibleController.isVisibleToUser();
    }

    @Override
    public final void callSuperSetUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }


    @Override
    public final void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {
        if (!mIsLoadUI) {
            int resId = createContentViewId();
            if (resId != 0) {
                mIsLoadUI = true;
                View view = mActivity.getLayoutInflater().inflate(resId, mRootViewGroup, false);
                mRootViewGroup.addView(view);
                mUnBinder = ButterKnife.bind(this, mRootViewGroup);
                onCreateContent(view, mSavedInstanceState);
            }
        } else {
            onVisibleLazyToUserChanged(isVisibleToUser, invokeInResumeOrPause);
        }
    }

    /**
     * <br> Description: 当Fragment对用户可见或不可见的就会回调此方法，可以在这个方法里记录页面显示日志或初始化页面
     * <br> 解决回退到Fragment，不调用setUserVisibleHint的情况
     * <br> Author:      谢文良
     * <br> Date:        2017/9/21 11:59
     *
     * @param isVisibleToUser       isVisibleToUser
     * @param invokeInResumeOrPause invokeInResumeOrPause
     */
    public void onVisibleLazyToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {

    }

    @Override
    public final void setUserVisibleHint(boolean isVisibleToUser) {
        mUserVisibleController.setUserVisibleHint(isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public final void onResume() {
        super.onResume();
        mUserVisibleController.resume();
        if (mIsLoadUI) {
            onLazyResume();
        }
    }

    /**
     * <br> Description: onResume
     * <br> Author:      谢文良
     * <br> Date:        2017/7/21 9:36
     */
    protected void onLazyResume() {

    }

    @Override
    public final void onPause() {
        super.onPause();
        mUserVisibleController.pause();
        if (mIsLoadUI) {
            onLazyPause();
        }
    }

    /**
     * <br> Description: onPause
     * <br> Author:      谢文良
     * <br> Date:        2017/7/21 9:36
     */
    protected void onLazyPause() {

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
