package com.xwl.net.mvplib.ui.base.reload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xwl.net.mvplib.R;


/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/14 14:20
 */

public class ReloadTips {
    private String mTitle;
    private String mFirstContent;
    private String mSecondContent;
    private String mImageUrl;
    private int mImageResId;
    private View mView;

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 15:55
     *
     * @param title         title
     * @param firstContent  firstContent
     * @param secondContent secondContent
     * @param imageResId    imageResId
     */
    public ReloadTips(String title, String firstContent, String secondContent, int imageResId) {
        this.mTitle = title;
        this.mFirstContent = firstContent;
        this.mSecondContent = secondContent;
        this.mImageResId = imageResId;
    }

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 15:55
     *
     * @param title        title
     * @param firstContent firstContent
     * @param imageResId   imageResId
     */
    public ReloadTips(String title, String firstContent, int imageResId) {
        this.mTitle = title;
        this.mFirstContent = firstContent;
        this.mImageResId = imageResId;
    }

    public static ReloadTips getLoadErrorDefault() {
        return new ReloadTips("加载失败", "点击屏幕刷新重试", R.drawable.empty_investment_crash);
    }

    public static ReloadTips getLoadNotNetDefault() {
        return new ReloadTips("网络链接失败", "请检查网络链接", R.drawable.empty_investment_nonetwork);
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getFirstContent() {
        return mFirstContent;
    }

    public void setFirstContent(String firstContent) {
        this.mFirstContent = firstContent;
    }

    public String getSecondContent() {
        return mSecondContent;
    }

    public void setSecondContent(String secondContent) {
        this.mSecondContent = secondContent;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public int getImageResId() {
        return mImageResId;
    }

    public void setImageResId(int imageResId) {
        this.mImageResId = imageResId;
    }

    /**
     * <br> Description: 获取情感图View
     * <br> Author:      谢文良
     * <br> Date:        2017/9/22 15:56
     *
     * @param context context
     * @param root    root
     * @return View
     */
    public View getView(Context context, ViewGroup root) {
        if (mView == null) {
            mView = LayoutInflater.from(context).inflate(R.layout.reload_tips_layout, root, false);
            ((ImageView) mView.findViewById(R.id.iv_image)).setImageResource(mImageResId);
            ((TextView) mView.findViewById(R.id.tv_title)).setText(mTitle);
            ((TextView) mView.findViewById(R.id.tv_content1)).setText(mFirstContent);
            ((TextView) mView.findViewById(R.id.tv_content2)).setText(mSecondContent);
        }
        return mView;
    }
}
