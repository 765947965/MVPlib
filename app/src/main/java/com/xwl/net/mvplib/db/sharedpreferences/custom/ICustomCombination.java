package com.xwl.net.mvplib.db.sharedpreferences.custom;

/**
 * <br> ClassName:   ICustomCombination
 * <br> Description: 对数据根据用户属性和版本属性的不同进行封装
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/9/27 15:17
 */

public interface ICustomCombination {

    /**
     * <br> Description: 对加密后的数据进行组装
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 15:31
     *
     * @param cipher    加密后的密文值(要加入的)
     * @param localArray    本地已有的值组
     * @return 组装后的json
     */
    String assembly(String cipher, String localArray);

    /**
     * <br> Description: 对组装后的数据进行拆解
     * <br> Author:      谢文良
     * <br> Date:        2017/9/27 15:34
     *
     * @param customCipher 组装后的json
     * @return 拆解后的真实值
     */
    String disassembly(String customCipher);
}
