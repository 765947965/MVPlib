package com.xwl.net.mvplib.test.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.xwl.net.mvplib.test.contract.IDemoContract;
import com.xwl.net.mvplib.test.presenter.DemoPresenter;
import com.xwl.net.mvplib.ui.base.view.AbstractBusinessActivity;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/15 10:40
 */

public class DemoActivity extends AbstractBusinessActivity<IDemoContract.IView, DemoPresenter>
        implements IDemoContract.IView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("测试Demo");
        if (mPresenter != null) {
            mPresenter.loadData();
        }
    }

    @Override
    protected View createContentView(ViewGroup root) {
        return null;
    }

    @Override
    protected DemoPresenter createPresenter() {
        return new DemoPresenter();
    }

    @Override
    protected IDemoContract.IView createBaseView() {
        return this;
    }

    @Override
    public void showContent(String context) {
        showToast(context);
    }
}
