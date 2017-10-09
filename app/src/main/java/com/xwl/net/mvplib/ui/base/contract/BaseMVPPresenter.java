package com.xwl.net.mvplib.ui.base.contract;

/**
 * <br> ClassName:   BaseMVPPresenter
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/18 14:33
 */

public class BaseMVPPresenter<V extends IBaseMVPView> {
    protected V mBaseIView;

    /**
     * <br> Description: 绑定BaseIView
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 11:56
     *
     * @param mBaseIView BaseIView
     */
    public void attachView(V mBaseIView) {
        this.mBaseIView = mBaseIView;
    }

    /**
     * <br> Description: 解绑BaseIView
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 11:56
     */
    public void detachView() {
        mBaseIView = null;
    }
}
