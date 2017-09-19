package com.xwl.net.mvplib.ui.base.contract;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/18 14:36
 */

public class BaseBusinessPresenter<V extends IBaseBusinessView> extends BaseNetWorkPresenter<V> {

    public void onFinish() {
        if (mBaseIView != null) {
            mBaseIView.onFinish();
        }
    }

    public void SystemExit() {
        if (mBaseIView != null) {
            mBaseIView.SystemExit();
        }
    }
}
