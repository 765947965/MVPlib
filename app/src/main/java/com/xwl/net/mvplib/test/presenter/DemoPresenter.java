package com.xwl.net.mvplib.test.presenter;

import com.google.gson.Gson;
import com.xwl.net.mvplib.net.bean.CriterionBean;
import com.xwl.net.mvplib.net.callback.modelback.CommonModelCallback;
import com.xwl.net.mvplib.test.contract.IDemoContract;
import com.xwl.net.mvplib.test.model.bean.DemoBean;
import com.xwl.net.mvplib.test.model.repository.DemoRepository;
import com.xwl.net.mvplib.ui.base.contract.BaseBusinessPresenter;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/15 10:45
 */

public class DemoPresenter extends BaseBusinessPresenter<IDemoContract.IView> implements IDemoContract.IPresenter {
    @Override
    public void loadData() {
        DemoRepository.loadData(this, "加载中...", "1", 99, new CommonModelCallback<CriterionBean<DemoBean>>() {
            @Override
            public void onSuccess(CriterionBean<DemoBean> demoBeanCriterionBean) {
                super.onSuccess(demoBeanCriterionBean);
                if (mBaseIView != null) {
                    mBaseIView.showContent(new Gson().toJson(demoBeanCriterionBean.getData()));
                }
            }
        });
    }
}
