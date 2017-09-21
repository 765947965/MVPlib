package com.xwl.net.mvplib.ui.base.view;

/**
 * @author: liaoshengjian
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/7/13 14:41
 */

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Fragment的mUserVisibleHint属性控制器，用于准确的监听Fragment是否对用户可见
 * <br>
 * <br>mUserVisibleHint属性有什么用？
 * <br>* 使用ViewPager时我们可以通过Fragment的getUserVisibleHint()&&isResume()方法来判断用户是否能够看见某个Fragment
 * <br>* 利用这个特性我们可以更精确的统计页面的显示事件和标准化页面初始化流程（真正对用户可见的时候才去请求数据）
 * <br>
 * <br>解决BUG
 * <br>* FragmentUserVisibleController还专门解决了在Fragment或ViewPager嵌套ViewPager时子Fragment的mUserVisibleHint属性与父Fragment的mUserVisibleHint属性不同步的问题
 * <br>* 例如外面的Fragment的mUserVisibleHint属性变化时，其包含的ViewPager中的Fragment的mUserVisibleHint属性并不会随着改变，这是ViewPager的BUG
 * <br>
 * <br>使用方式（假设你的基类Fragment是MyFragment）：
 * <br>1. 在你的MyFragment的构造函数中New一个FragmentUserVisibleController（一定要在构造函数中new）
 * <br>2. 重写Fragment的onActivityCreated()、onResume()、onPause()、setUserVisibleHint(boolean)方法，分别调用FragmentUserVisibleController的activityCreated()、resume()、pause()、setUserVisibleHint(boolean)方法
 * <br>3. 实现FragmentUserVisibleController.UserVisibleCallback接口并实现以下方法
 * <br>&nbsp&nbsp&nbsp&nbsp* void setWaitingShowToUser(boolean)：直接调用FragmentUserVisibleController的setWaitingShowToUser(boolean)即可
 * <br>&nbsp&nbsp&nbsp&nbsp* void isWaitingShowToUser()：直接调用FragmentUserVisibleController的isWaitingShowToUser()即可
 * <br>&nbsp&nbsp&nbsp&nbsp* void callSuperSetUserVisibleHint(boolean)：调用父Fragment的setUserVisibleHint(boolean)方法即可
 * <br>&nbsp&nbsp&nbsp&nbsp* void onVisibleToUserChanged(boolean, boolean)：当Fragment对用户可见或不可见的就会回调此方法，你可以在这个方法里记录页面显示日志或初始化页面
 * <br>&nbsp&nbsp&nbsp&nbsp* boolean isVisibleToUser()：判断当前Fragment是否对用户可见，直接调用FragmentUserVisibleController的isVisibleToUser()即可
 */
@SuppressWarnings("RestrictedApi")
@SuppressLint("LongLogTag")
public class FragmentUserVisibleController {

    @SuppressWarnings("FieldCanBeLocal")
    private boolean mWaitingShowToUser;
    private Fragment mFragment;
    private IUserVisibleCallback mUserVisibleCallback;

    /**
     * <br> Description: FragmentUserVisibleController
     * <br> Author:      谢文良
     * <br> Date:        2017/9/21 11:36
     *
     * @param fragment            Fragment
     * @param userVisibleCallback UserVisibleCallback
     */
    public FragmentUserVisibleController(Fragment fragment, IUserVisibleCallback userVisibleCallback) {
        this.mFragment = fragment;
        this.mUserVisibleCallback = userVisibleCallback;
    }

    /**
     * <br> Description: activityCreated
     * <br> Author:      谢文良
     * <br> Date:        2017/9/21 11:36
     */
    public void activityCreated() {

        // 如果自己是显示状态，但父Fragment却是隐藏状态，就把自己也改为隐藏状态，并且设置一个等待显示的标记
        if (mFragment.getUserVisibleHint()) {
            Fragment parentFragment = mFragment.getParentFragment();
            if (parentFragment != null && !parentFragment.getUserVisibleHint()) {
                mUserVisibleCallback.setWaitingShowToUser(true);
                mUserVisibleCallback.callSuperSetUserVisibleHint(false);
            }
        }
    }

    /**
     * <br> Description: resume
     * <br> Author:      谢文良
     * <br> Date:        2017/9/21 11:37
     */
    public void resume() {
        if (mFragment.getUserVisibleHint()) {
            mUserVisibleCallback.onVisibleToUserChanged(true, true);
        }
    }

    /**
     * <br> Description: pause
     * <br> Author:      谢文良
     * <br> Date:        2017/9/21 11:38
     */
    public void pause() {
        if (mFragment.getUserVisibleHint()) {
            mUserVisibleCallback.onVisibleToUserChanged(false, true);
        }
    }

    /**
     * <br> Description: setUserVisibleHint
     * <br> Author:      谢文良
     * <br> Date:        2017/9/21 11:38
     *
     * @param isVisibleToUser isVisibleToUser
     */
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (mFragment.isResumed()) {
            mUserVisibleCallback.onVisibleToUserChanged(isVisibleToUser, false);
        }

        if (mFragment.getActivity() != null) {
            List<Fragment> childFragmentList = mFragment.getChildFragmentManager().getFragments();
            if (isVisibleToUser) {
                // 将所有正等待显示的子Fragment设置为显示状态，并取消等待显示标记
                if (childFragmentList != null && childFragmentList.size() > 0) {
                    for (Fragment childFragment : childFragmentList) {
                        if (childFragment instanceof IUserVisibleCallback) {
                            IUserVisibleCallback userVisibleCallback = (IUserVisibleCallback) childFragment;
                            if (userVisibleCallback.isWaitingShowToUser()) {
                                userVisibleCallback.setWaitingShowToUser(false);
                                childFragment.setUserVisibleHint(true);
                            }
                        }
                    }
                }
            } else {
                // 将所有正在显示的子Fragment设置为隐藏状态，并设置一个等待显示标记
                if (childFragmentList != null && childFragmentList.size() > 0) {
                    for (Fragment childFragment : childFragmentList) {
                        if (childFragment instanceof IUserVisibleCallback) {
                            IUserVisibleCallback userVisibleCallback = (IUserVisibleCallback) childFragment;
                            if (childFragment.getUserVisibleHint()) {
                                userVisibleCallback.setWaitingShowToUser(true);
                                childFragment.setUserVisibleHint(false);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 当前Fragment是否对用户可见
     *
     * @return boolean
     */
    @SuppressWarnings("unused")
    public boolean isVisibleToUser() {
        return mFragment.isResumed() && mFragment.getUserVisibleHint();
    }

    public boolean isWaitingShowToUser() {
        return mWaitingShowToUser;
    }

    public void setWaitingShowToUser(boolean waitingShowToUser) {
        this.mWaitingShowToUser = waitingShowToUser;
    }

    /**
     * <br> ClassName:   UserVisibleCallback
     * <br> Description: UserVisibleCallback
     * <br>
     * <br> Author:      谢文良
     * <br> Date:        2017/9/21 11:38
     */
    public interface IUserVisibleCallback {
        /**
         * <br> Description: setWaitingShowToUser
         * <br> Author:      谢文良
         * <br> Date:        2017/9/21 11:39
         *
         * @param waitingShowToUser waitingShowToUser
         */
        void setWaitingShowToUser(boolean waitingShowToUser);

        /**
         * <br> Description: isWaitingShowToUser
         * <br> Author:      谢文良
         * <br> Date:        2017/9/21 11:39
         *
         * @return boolean
         */
        boolean isWaitingShowToUser();

        /**
         * <br> Description: isVisibleToUser
         * <br> Author:      谢文良
         * <br> Date:        2017/9/21 11:39
         *
         * @return boolean
         */
        boolean isVisibleToUser();

        /**
         * <br> Description: callSuperSetUserVisibleHint
         * <br> Author:      谢文良
         * <br> Date:        2017/9/21 11:39
         *
         * @param isVisibleToUser isVisibleToUser
         */
        void callSuperSetUserVisibleHint(boolean isVisibleToUser);

        /**
         * <br> Description: onVisibleToUserChanged
         * <br> Author:      谢文良
         * <br> Date:        2017/9/21 11:40
         *
         * @param isVisibleToUser       isVisibleToUser
         * @param invokeInResumeOrPause invokeInResumeOrPause
         */
        void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause);
    }
}
