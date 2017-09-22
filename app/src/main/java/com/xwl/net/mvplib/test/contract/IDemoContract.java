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
    /**
     * <br> ClassName:   IView
     * <br> Description: IView
     * <br>
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 15:59
     */
    interface IView extends IBaseBusinessView {
        /**
         * <br> Description: 显示正文
         * <br> Author:      谢文良
         * <br> Date:        2017/9/22 15:59
         *
         * @param context String
         */
        void showContent(String context);

        /**
         * <br> Description: 请求权限
         * <br> Author:      谢文良
         * <br> Date:        2017/9/22 15:59
         */
        void permissions();
    }

    /**
     * <br> ClassName:   IPresenter
     * <br> Description: IPresenter
     * <br>
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 15:59
     */
    interface IPresenter {
        /**
         * <br> Description: 加载数据
         * <br> Author:      谢文良
         * <br> Date:        2017/9/22 16:00
         */
        void loadData();
    }
}
