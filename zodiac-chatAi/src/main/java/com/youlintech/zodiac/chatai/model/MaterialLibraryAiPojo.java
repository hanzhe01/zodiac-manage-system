package com.youlintech.zodiac.chatai.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MaterialLibraryAiPojo {
    /**
     * 星座id
     */
    private Long constellationId;

    /**
     * 总体运势
     */
    private String horoscopeFortune;

    /**
     * 总体运势星级
     */
    private BigDecimal horoscopeFortuneStar;

    /**
     * 幸运色
     */
    private String luckyColor;

    /**
     * 幸运数字
     */
    private Long luckyNumbers;

    /**
     * 幸运时间
     */
    private String luckyTime;

    /**
     * 幸运物品
     */
    private String luckyItems;

    /**
     * 购物链接
     */
    private String purchaseLink;

    /**
     * 运势标题一名称
     */
    private String firstTitleName;

    /**
     * 运势标题一运势内容
     */
    private String firstTitleContent;

    /**
     * 运势标题一运势内容星级
     */
    private BigDecimal firstTitleStar;

    /**
     * 运势标题二名称
     */
    private String secondTitleName;

    /**
     * 运势标题二内容
     */
    private String secondTitleContent;

    /**
     * 运势标题二星级
     */
    private BigDecimal secondTitleStar;

    /**
     * 运势标题三名称
     */
    private String thirdTitleName;

    /**
     * 运势标题三内容


     */
    private String thirdTitleContent;

    /**
     * 运势标题三星级
     */
    private BigDecimal thirdTitleStar;
}
