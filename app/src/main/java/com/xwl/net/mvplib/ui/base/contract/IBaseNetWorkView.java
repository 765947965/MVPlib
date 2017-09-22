package com.xwl.net.mvplib.ui.base.contract;

import com.xwl.net.mvplib.ui.base.reload.ReloadTips;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/14 14:18
 */

public interface IBaseNetWorkView extends IBaseMVPView {
    /**
     * <br> Description: 开启加载动画
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 14:01
     *
     * @param tips String
     */
    void startLoadingAnim(String tips);

    /**
     * <br> Description: 关闭加载动画
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 14:02
     */
    void stopLoadingAnim();

    /**
     * <br> Description: 显示错误情感图
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 14:02
     *
     * @param mReloadTips ReloadTips
     */
    void showLoadErrorUI(ReloadTips mReloadTips);

    /**
     * <br> Description: 关闭错误情感图
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 14:02
     */
    void dismissLoadErrorUI();
}
