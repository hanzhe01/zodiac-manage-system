package com.youlintech.zodiac.chatai.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName tb_material_library
 */
@TableName(value ="tb_material_library")
@Data
public class MaterialLibrary implements Serializable {
    /**
     * 素材id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

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

    /**
     * 是否是本周运势0-是 1-否 
     */
    private Long isWeekFortune;

    /**
     * 生成日期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date generationDate;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}