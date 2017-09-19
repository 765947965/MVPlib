package com.xwl.net.mvplib.ui.base.contract;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/18 14:33
 */

public class BaseMVPPresenter<V extends IBaseMVPView> {
    protected V mBaseIView;

    public void attachView(V mBaseIView) {
        this.mBaseIView = mBaseIView;
    }

    public void detachView() {
        mBaseIView = null;
    }
}
