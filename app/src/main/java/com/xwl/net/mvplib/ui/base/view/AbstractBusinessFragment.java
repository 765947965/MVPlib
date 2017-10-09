package com.xwl.net.mvplib.ui.base.view;

import com.xwl.net.mvplib.ui.base.contract.BaseBusinessPresenter;
import com.xwl.net.mvplib.ui.base.contract.IBaseBusinessView;


/**
 * <br> ClassName:   AbstractBusinessFragment
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/19 10:59
 */

public abstract class AbstractBusinessFragment<V extends IBaseBusinessView, P extends BaseBusinessPresenter<V>>
        extends AbstractNetWorkFragment<V, P> implements IBaseBusinessView {

    @Override
    public void systemExit() {
        System.exit(0);
    }


    @Override
    public void onFinish() {
        willFinish();
        mActivity.finish();
    }

    /**
     * <br> Description: finish前的回调
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 9:41
     */
    protected void willFinish() {

    }
}
