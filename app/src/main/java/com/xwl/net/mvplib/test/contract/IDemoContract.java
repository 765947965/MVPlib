package com.xwl.net.mvplib.test.contract;

import com.xwl.net.mvplib.ui.base.contract.IBaseView;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/15 10:42
 */

public interface IDemoContract {
    interface IView extends IBaseView {
        void showContent(String context);
    }

    interface IPresenter {
        void loadData();
    }
}
