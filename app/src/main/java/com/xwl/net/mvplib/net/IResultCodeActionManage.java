package com.xwl.net.mvplib.net;

import com.xwl.net.mvplib.net.bean.CriterionBean;

/**
 * <br> ClassName:   IResultCodeActionManage
 * <br> Description: 对于业务Code的相关处理
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/10/9 17:10
 */

public interface IResultCodeActionManage {
    /**
     * <br> Description: 统一处理返回值的相关行为
     * <br> Author:      谢文良
     * <br> Date:        2017/10/9 17:14
     *
     * @param mCriterionBean CriterionBean
     */
    void disposeResultCodeAction(CriterionBean mCriterionBean);
}
