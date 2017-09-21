package com.xwl.net.mvplib.test.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xwl.net.mvplib.R;
import com.xwl.net.mvplib.test.contract.IDemoContract;
import com.xwl.net.mvplib.test.presenter.DemoPresenter;
import com.xwl.net.mvplib.ui.base.view.AbstractBusinessActivity;
import com.xwl.net.mvplib.util.jitter.RxJitter;


import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;


/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/15 10:40
 */

public class DemoActivity extends AbstractBusinessActivity<IDemoContract.IView, DemoPresenter>
        implements IDemoContract.IView, RxJitter.IEventObserver {

    @BindView(R.id.text)
    TextView mText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            setTitle("测试Demo" + getIntent().toString());
        } else {
            setTitle("测试Demo");
        }
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
        return getLayoutInflater().inflate(R.layout.demo_layout, root, false);
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
        mText.setText(context);
    }

    @OnClick({R.id.llt_demo, R.id.text})
    public void onViewClicked(View view) {
        RxJitter.bind(view).subscribe(this).bufferEvent(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxJitter.unBind();
    }

    @Override
    public void onEvent(Object o) {
        switch (((View) o).getId()) {
            case R.id.llt_demo:
                Log.i("click:", o.toString());
                break;
            case R.id.text:
                Log.i("click:", o.toString());
                startActivity(new Intent(this, getClass()));
                break;
        }
    }
}
