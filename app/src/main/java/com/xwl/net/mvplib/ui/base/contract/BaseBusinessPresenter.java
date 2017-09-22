package com.xwl.net.mvplib.ui.base.contract;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/18 14:36
 */

public class BaseBusinessPresenter<V extends IBaseBusinessView> extends BaseNetWorkPresenter<V> {

    /**
     * <br> Description: 结束
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 11:55
     */
    public void onFinish() {
        if (mBaseIView != null) {
            mBaseIView.onFinish();
        }
    }

    /**
     * <br> Description: 退出进程
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 11:55
     */
    public void systemExit() {
        if (mBaseIView != null) {
            mBaseIView.systemExit();
        }
    }
}
