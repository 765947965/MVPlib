package com.xwl.net.mvplib.db.sharedpreferences;

import android.content.Context;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/5/10 10:44
 */

public class SharedConfig {
    private static final String DEFAULT_SHARE_FLAG = "Share";
    private static final String DEFAULT_SHARE_FILE_NAME = "DefaultFile";
    /**
     * 用户标识
     */
    private String mShareFlag;
    /**
     * 文件标识
     */
    private String mShareFileName;
    /**
     * 是否版本控制(是：每次版本变更，新版本数据初始为空)
     */
    private boolean mIsVersionControl;
    private int mShareMode;

    /**
     * <br> Description: 构造函数
     * <br> Author:      谢文良
     * <br> Date:        2017/9/28 11:05
     *
     * @param builder Builder清单
     */
    private SharedConfig(Builder builder) {
        this.mShareFlag = builder.mShareFlag;
        this.mShareFileName = builder.mShareFileName;
        this.mShareMode = builder.mShareMode;
        this.mIsVersionControl = builder.mIsVersionControl;
    }

    public String getShareFlag() {
        return mShareFlag;
    }

    public String getShareFileName() {
        return mShareFileName;
    }

    public int getShareMode() {
        return mShareMode;
    }

    public boolean isVersionControl() {
        return mIsVersionControl;
    }

    /**
     * <br> Description: 构建Builder
     * <br> Author:      谢文良
     * <br> Date:        2017/9/28 11:05
     *
     * @return Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * <br> ClassName:   Builder
     * <br> Description: Builder清单
     * <br>
     * <br> Author:      谢文良
     * <br> Date:        2017/9/28 11:06
     */
    public static class Builder {
        private String mShareFlag = DEFAULT_SHARE_FLAG;
        private String mShareFileName = DEFAULT_SHARE_FILE_NAME;
        private boolean mIsVersionControl = false;
        private int mShareMode = Context.MODE_PRIVATE;

        /**
         * <br> Description: 构建函数
         * <br> Author:      谢文良
         * <br> Date:        2017/9/28 11:07
         */
        private Builder() {
        }

        /**
         * <br> Description: 设置用户标识
         * <br> Author:      谢文良
         * <br> Date:        2017/9/28 11:07
         *
         * @param shareFlag 用户标识
         * @return Builder
         */
        public Builder setShareFlag(String shareFlag) {
            this.mShareFlag = shareFlag;
            return this;
        }

        /**
         * <br> Description: 设置SP文件名
         * <br> Author:      谢文良
         * <br> Date:        2017/9/28 11:07
         *
         * @param shareFileName SP文件名
         * @return Builder
         */
        public Builder setShareFileName(String shareFileName) {
            this.mShareFileName = shareFileName;
            return this;
        }

        /**
         * <br> Description: 设置SP文件权限模式
         * <br> Author:      谢文良
         * <br> Date:        2017/9/28 11:08
         *
         * @param shareMode 权限模式
         * @return Builder
         */
        public Builder setShareMode(int shareMode) {
            this.mShareMode = shareMode;
            return this;
        }

        /**
         * <br> Description: 设置是否需要版本控制
         * <br> Author:      谢文良
         * <br> Date:        2017/9/28 11:08
         *
         * @param versionControl 是否需要版本控制
         * @return Builder
         */
        public Builder setVersionControl(boolean versionControl) {
            mIsVersionControl = versionControl;
            return this;
        }

        /**
         * <br> Description: 构建SharedConfig
         * <br> Author:      谢文良
         * <br> Date:        2017/9/28 11:09
         *
         * @return SharedConfig
         */
        public SharedConfig build() {
            return new SharedConfig(this);
        }
    }
}
