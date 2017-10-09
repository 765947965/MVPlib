package com.xwl.net.mvplib.net.callback;

import com.lzy.okgo.request.base.Request;
import com.xwl.net.mvplib.net.IRequestManage;
import com.xwl.net.mvplib.net.bean.CriterionBean;

/**
 * <br> ClassName:   AbstractBusinessCallback
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/19 8:51
 */

public abstract class AbstractBusinessCallback<T> extends AbstractNetWorkCallback<T> {

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/10/9 17:21
     *
     * @param mIRequestManage IRequestManage
     */
    public AbstractBusinessCallback(IRequestManage mIRequestManage) {
        this(mIRequestManage, null);
    }

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/10/9 17:21
     *
     * @param mIRequestManage IRequestManage
     * @param tips            tips
     */
    public AbstractBusinessCallback(IRequestManage mIRequestManage, String tips) {
        this(mIRequestManage, tips, false);
    }

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/10/9 17:21
     *
     * @param mIRequestManage IRequestManage
     * @param tips            tips
     * @param isRetry         isRetry
     */
    public AbstractBusinessCallback(IRequestManage mIRequestManage, String tips, boolean isRetry) {
        super(mIRequestManage, tips, isRetry);
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        request.headers("testHeader", "value1").params("testParam", "value2");
    }

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<T> response) {
        T t = response.body();
        if (t == null) {
            Throwable e = new IllegalStateException("服务器数据异常");
            businessFail(null, e);
        } else if (t instanceof CriterionBean) {
            // 主要处理服务器业务逻辑错误
            CriterionBean mCriterionBean = (CriterionBean) t;
            if (mCriterionBean.getReturnCode() != 1) {
                Throwable e = new IllegalStateException(mCriterionBean.getReturnMessage());
                businessFail(t, e);
            } else {
                businessSuccess(t);
            }
        } else {
            businessSuccess(t);
        }
    }

    @Override
    public void onCacheSuccess(com.lzy.okgo.model.Response<T> response) {
        super.onCacheSuccess(response);
        onSuccess(response);
    }
}
