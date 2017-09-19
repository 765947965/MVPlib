package com.xwl.net.mvplib.ui.base.contract;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/14 14:18
 */

public interface IBaseBusinessView extends IBaseNetWorkView {

    void onFinish();

    void SystemExit();
}
