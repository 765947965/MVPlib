package com.xwl.net.mvplib.ui.base.contract;

/**
 * <br> ClassName:   IBaseBusinessView
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/14 14:18
 */

public interface IBaseBusinessView extends IBaseNetWorkView {
    /**
     * <br> Description: 结束
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 11:55
     */
    void onFinish();

    /**
     * <br> Description: 退出进程
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 11:55
     */
    void systemExit();
}
