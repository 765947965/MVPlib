package com.xwl.net.mvplib.ui.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
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
    private Unbinder mUnBinder;

    /*** 标题右侧控件 ***/
    protected ImageView mIvLeft;
    /*** 标题正文 ***/
    protected TextView mTvTitle;
    /*** 标题右侧控件 ***/
    protected TextView mTvRight;

    /**
     * <br> Description: onCreate
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 9:39
     *
     * @param savedInstanceState Bundle
     */
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abstract_business_activity);
        LinearLayout mLltMain = (LinearLayout) findViewById(R.id.lltMain);
        int headViewId = createHeadViewId();
        View mContentHeadView = null;
        if (headViewId != 0) {
            mContentHeadView = getLayoutInflater().inflate(headViewId, mLltMain, false);
            mLltMain.addView(mContentHeadView);
        }
        int contentViewId = createContentViewId();
        View mContentView = null;
        if (contentViewId != 0) {
            mContentView = getLayoutInflater().inflate(contentViewId, mLltMain, false);
            mLltMain.addView(mContentView);
        }
        mUnBinder = ButterKnife.bind(this);
        initHeadView(mContentHeadView);
        onCreateContent(mContentView, savedInstanceState);
    }

    /**
     * <br> Description: 创建头部bar
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 9:40
     *
     * @return View
     */
    protected int createHeadViewId() {
        return R.layout.abstract_business_title;
    }

    /**
     * <br> Description: 初始化头部
     * <br> Author:      谢文良
     * <br> Date:        2017/9/21 14:28
     *
     * @param mContentHeadView mContentHeadView
     */
    protected void initHeadView(View mContentHeadView) {
        if (mContentHeadView != null) {
            if (mContentHeadView.findViewById(R.id.iv_close) instanceof ImageView) {
                mIvLeft = (ImageView) mContentHeadView.findViewById(R.id.iv_close);
            }
            if (mContentHeadView.findViewById(R.id.tv_title) instanceof TextView) {
                mTvTitle = (TextView) mContentHeadView.findViewById(R.id.tv_title);
            }
            if (mContentHeadView.findViewById(R.id.tv_right) instanceof TextView) {
                mTvRight = (TextView) mContentHeadView.findViewById(R.id.tv_right);
            }
            if (mIvLeft != null) {
                mIvLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        willFinish();
                        finish();
                    }
                });
            }
        }
    }

    /**
     * <br> Description: 创建主布局
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 14:53
     *
     * @return 主布局
     */
    protected abstract int createContentViewId();

    /**
     * <br> Description: 创建初始化(相关View的初始化都在这里进行，这里已经可以使用ButterKnife绑定的View变量了)
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 14:53
     *
     * @param view               主布局
     * @param savedInstanceState Bundle
     */
    protected abstract void onCreateContent(View view, Bundle savedInstanceState);

    /**
     * <br> Description: 设置标题
     * <br> Author:      谢文良
     * <br> Date:        2017/9/15 9:40
     *
     * @param title 标题
     */
    protected void setTitle(String title) {
        if (!TextUtils.isEmpty(title) && mTvTitle != null) {
            mTvTitle.setText(title);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    @Override
    public void systemExit() {
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
