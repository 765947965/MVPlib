package com.xwl.net.mvplib.ui.base.contract;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/14 14:18
 */

public interface IBaseView {
    void startLoadingAnim(String tips);

    void stopLoadingAnim();

    void showLoadErrorUI(ReloadTips mReloadTips);

    void dismissLoadErrorUI();

    void showToast(String info);

    void onFinish();
}
