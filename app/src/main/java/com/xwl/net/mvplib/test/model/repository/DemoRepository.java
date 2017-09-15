package com.xwl.net.mvplib.test.model.repository;

import com.lzy.okgo.request.PostRequest;
import com.xwl.net.mvplib.net.HttpUtils;
import com.xwl.net.mvplib.net.IRequestManage;
import com.xwl.net.mvplib.net.bean.CriterionBean;
import com.xwl.net.mvplib.net.callback.AbstractJsonCallback;
import com.xwl.net.mvplib.net.callback.modelback.ICommonModelCallback;
import com.xwl.net.mvplib.test.model.bean.DemoBean;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/15 11:35
 */

public class DemoRepository {

    /**
     * <br> Description: 请求接口
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 14:34
     *
     * @param manage              manage
     * @param tips                tips
     * @param name                name
     * @param age                 age
     * @param commonModelCallback ICommonModelCallback
     */
    public static void loadData(IRequestManage manage, final String tips, String name, int age,
                                final ICommonModelCallback<CriterionBean<DemoBean>> commonModelCallback) {
        PostRequest<CriterionBean<DemoBean>> request = HttpUtils.post("https://mock.eolinker.com/ybv8Vyk8384111c1d8829a042560c865febccee911bfd0b?uri=demoActivity");
        request.params("name", name);
        request.params("age", age);
        request.execute(new AbstractJsonCallback<CriterionBean<DemoBean>>(manage, tips, true) {
            @Override
            protected void onSuccess(CriterionBean<DemoBean> demoBeanCriterionBean) {
                if (commonModelCallback != null) {
                    commonModelCallback.onSuccess(demoBeanCriterionBean);
                }
            }
        });
    }
}
