package com.xwl.net.mvplib.ui.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xwl.net.mvplib.R;
import com.xwl.net.mvplib.ui.base.contract.BaseBusinessPresenter;
import com.xwl.net.mvplib.ui.base.contract.IBaseBusinessView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <br> ClassName:   AbstractBusinessActivity
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/14 17:54
 */

public abstract class AbstractBusinessActivity<V extends IBaseBusinessView, P extends BaseBusinessPresenter<V>>
        extends AbstractNetWorkActivity<V, P> implements IBaseBusinessView {
    protected View mContentHeadView;
    private Unbinder mUnBinder;

    /**
     * <br> Description: onCreate
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 9:39
     *
     * @param savedInstanceState Bundle
     */
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abstract_business_activity);
        LinearLayout mLltMain = (LinearLayout) findViewById(R.id.lltMain);
        mContentHeadView = createHeadView(mLltMain);
        if (mContentHeadView != null) {
            mLltMain.addView(mContentHeadView);
        }
        View mContentView = createContentView(mLltMain);
        if (mContentView != null) {
            mLltMain.addView(mContentView);
        }
        mUnBinder = ButterKnife.bind(this);
    }

    /**
     * <br> Description: 创建头部bar
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 9:40
     *
     * @param root ViewGroup
     * @return View
     */
    protected View createHeadView(ViewGroup root) {
        View view = getLayoutInflater().inflate(R.layout.abstract_business_title, root, false);
        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                willFinish();
                finish();
            }
        });
        return view;
    }

    /**
     * <br> Description: 创建主布局
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 14:53
     *
     * @param root ViewGroup
     * @return 主布局
     */
    protected abstract View createContentView(ViewGroup root);

    /**
     * <br> Description: 设置标题
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 9:40
     *
     * @param title 标题
     */
    protected void setTitle(String title) {
        if (!TextUtils.isEmpty(title) && mContentHeadView != null
                && mContentHeadView.findViewById(R.id.tv_title) != null) {
            ((TextView) mContentHeadView.findViewById(R.id.tv_title)).setText(title);
        }
    }

    /**
     * <br> Description: 返回标题右侧控件
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 9:40
     *
     * @return 标题右侧控件
     */
    protected TextView getRightView() {
        if (mContentHeadView != null) {
            return (TextView) mContentHeadView.findViewById(R.id.tv_right);
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    @Override
    public void SystemExit() {
        System.exit(0);
    }


    @Override
    public void onFinish() {
        willFinish();
        finish();
    }

    /**
     * <br> Description: finish前的回调
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 9:41
     */
    protected void willFinish() {

    }
}
