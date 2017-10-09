package com.xwl.net.mvplib.ui.base.contract;

import com.xwl.net.mvplib.net.bean.CriterionBean;

/**
 * <br> ClassName:   BaseBusinessPresenter
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

    @Override
    public void disposeResultCodeAction(CriterionBean mCriterionBean) {
        //TODO 对全局统一的网络请求错误code的处理（如停服等）
    }
}
