package com.xwl.net.mvplib.ui.base.contract;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
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
    private String title;
    private String firstContent;
    private String secondContent;
    private String imageUrl;
    private int imageResId;
    private View view;

    public ReloadTips(String title, String firstContent, String secondContent, int imageResId) {
        this.title = title;
        this.firstContent = firstContent;
        this.secondContent = secondContent;
        this.imageResId = imageResId;
    }

    public ReloadTips(String title, String firstContent, int imageResId) {
        this.title = title;
        this.firstContent = firstContent;
        this.imageResId = imageResId;
    }

    public static ReloadTips getLoadErrorDefault() {
        return new ReloadTips("加载失败", "点击屏幕刷新重试", R.drawable.empty_investment_crash);
    }

    public static ReloadTips getLoadNotNetDefault() {
        return new ReloadTips("网络链接失败", "请检查网络链接", R.drawable.empty_investment_nonetwork);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstContent() {
        return firstContent;
    }

    public void setFirstContent(String firstContent) {
        this.firstContent = firstContent;
    }

    public String getSecondContent() {
        return secondContent;
    }

    public void setSecondContent(String secondContent) {
        this.secondContent = secondContent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public View getView(Context context, ViewGroup root) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.reload_tips_layout, root, false);
            ((ImageView) view.findViewById(R.id.iv_image)).setImageResource(imageResId);
            ((TextView) view.findViewById(R.id.tv_title)).setText(title);
            ((TextView) view.findViewById(R.id.tv_content1)).setText(firstContent);
            ((TextView) view.findViewById(R.id.tv_content2)).setText(secondContent);
        }
        return view;
    }
}
