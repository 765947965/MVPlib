package com.xwl.net.mvplib.test.contract;

import com.xwl.net.mvplib.ui.base.contract.IBaseBusinessView;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/15 10:42
 */

public interface IDemoContract {
    interface IView extends IBaseBusinessView {
        void showContent(String context);
        void permissions();
    }

    interface IPresenter {
        void loadData();
    }
}
