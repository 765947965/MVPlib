package com.xwl.net.mvplib.test.view;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xwl.net.mvplib.test.contract.IDemoContract;
import com.xwl.net.mvplib.test.presenter.DemoPresenter;
import com.xwl.net.mvplib.ui.base.view.AbstractBusinessActivity;

import io.reactivex.functions.Consumer;


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
        permissions();
    }

    @Override
    public void permissions() {
        new RxPermissions(DemoActivity.this)
                .requestEach(Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE)
                .lastElement()
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            showToast("用户同意");
                            if (mPresenter != null) {
                                mPresenter.loadData();
                            }
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            showToast("用户拒绝，重新申请");
                            permissions();
                        } else {
                            showToast("用户拒绝");
                        }
                    }
                });
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
