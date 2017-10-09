package com.xwl.net.mvplib.test.model.bean;


import java.io.Serializable;

/**
 * <br> ClassName:   DemoBean
 * <br> Description:
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/15 11:32
 */

public class DemoBean implements Serializable {
    private double SumMoney;
    private String HeadImage;
    private double AviMoney;
    private int Level;
    private String LevelDesc;
    private int MemberLevel;
    private String NickName;

    public double getSumMoney() {
        return SumMoney;
    }

    public void setSumMoney(double sumMoney) {
        SumMoney = sumMoney;
    }

    public String getHeadImage() {
        return HeadImage;
    }

    public void setHeadImage(String headImage) {
        HeadImage = headImage;
    }

    public double getAviMoney() {
        return AviMoney;
    }

    public void setAviMoney(double aviMoney) {
        AviMoney = aviMoney;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public String getLevelDesc() {
        return LevelDesc;
    }

    public void setLevelDesc(String levelDesc) {
        LevelDesc = levelDesc;
    }

    public int getMemberLevel() {
        return MemberLevel;
    }

    public void setMemberLevel(int memberLevel) {
        MemberLevel = memberLevel;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }
}
