package com.xwl.net.mvplib.net.bean;

import java.io.Serializable;

/**
 * <br> ClassName:   ICriterionBean
 * <br> Description: 用于标记网络请求统一Bean
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/13 16:11
 */

public class CriterionBean<T> implements Serializable {
    private int ReturnCode;
    private String ReturnMessage;
    private T Data;

    public int getReturnCode() {
        return ReturnCode;
    }

    public void setReturnCode(int returnCode) {
        ReturnCode = returnCode;
    }

    public String getReturnMessage() {
        return ReturnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        ReturnMessage = returnMessage;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
