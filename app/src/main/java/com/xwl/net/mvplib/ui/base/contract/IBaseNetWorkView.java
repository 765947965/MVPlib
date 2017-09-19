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
    void startLoadingAnim(String tips);

    void stopLoadingAnim();

    void showLoadErrorUI(ReloadTips mReloadTips);

    void dismissLoadErrorUI();
}
