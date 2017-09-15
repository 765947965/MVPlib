package com.xwl.net.mvplib.net.callback;


import com.google.gson.Gson;
import com.xwl.net.mvplib.net.IRequestManage;
import com.xwl.net.mvplib.net.bean.CriterionBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * <br> ClassName:   JsonCallback
 * <br> Description: 自定义业务数据处理逻辑
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/13 16:02
 */

public abstract class AbstractJsonCallback<T> extends AbstractNetWorkCallback<T> {
    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 14:29
     *
     * @param mIRequestManage IRequestManage
     */
    public AbstractJsonCallback(IRequestManage mIRequestManage) {
        super(mIRequestManage);
    }

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 14:29
     *
     * @param mIRequestManage IRequestManage
     * @param tips            加载提示
     */
    public AbstractJsonCallback(IRequestManage mIRequestManage, String tips) {
        super(mIRequestManage, tips);
    }

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 14:29
     *
     * @param mIRequestManage IRequestManage
     * @param tips            加载提示
     * @param isRetry         是否可以重新加载
     */
    public AbstractJsonCallback(IRequestManage mIRequestManage, String tips, boolean isRetry) {
        super(mIRequestManage, tips, isRetry);
    }

    /**
     * <br> Description: 请求成功回调
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 15:28
     *
     * @param t bean
     */
    protected abstract void onSuccess(T t);

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<T> response) {
        super.onSuccess(response);
        onSuccess(response.body());
    }

    @Override
    public void onCacheSuccess(com.lzy.okgo.model.Response<T> response) {
        super.onCacheSuccess(response);
        onSuccess(response.body());
    }


    @Override
    public T convertResponse(Response response) throws Throwable {
        // T = E<F>
        // T
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalArgumentException("The generic is empty");
        }
        // E
        Type rawType = ((ParameterizedType) type).getRawType();
        // F
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            return null;
        }
        String body = decryption(responseBody.string());
        responseBody.close();
        if (rawType == CriterionBean.class) {
            T mCriterionBean = new Gson().fromJson(body, type);
            if (((CriterionBean) mCriterionBean).getReturnCode() != 1) {
                throw new IllegalStateException(((CriterionBean) mCriterionBean).getReturnMessage());
            } else {
                return mCriterionBean;
            }
        } else {
            return new Gson().fromJson(body, type);
        }
    }
}
