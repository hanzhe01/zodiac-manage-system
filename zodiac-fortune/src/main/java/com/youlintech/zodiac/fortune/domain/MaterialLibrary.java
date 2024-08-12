package com.youlintech.zodiac.fortune.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.youlintech.zodiac.common.annotation.Excel;
import com.youlintech.zodiac.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 素材管理对象 tb_material_library
 *
 * @author ruoyi
 * @date 2024-07-17
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_material_library")
@ApiModel(value = "MaterialLibrary", description = "素材管理实体")
public class MaterialLibrary extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 素材id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("素材id")
    private Long id;

    /**
     * 星座id
     */
    @ApiModelProperty("星座id")
    @Excel(name = "星座名称", readConverterExp = "1=Aries, 2=Taurus, 3=Gemini, 4=Cancer, 5=Leo, 6=Virgo, 7=Libra, 8=Scorpio, 9=Sagittarius, 10=Capricorn, 11=Aquarius, 12=Pisces",
            prompt = "1=Aries, 2=Taurus, 3=Gemini, 4=Cancer, 5=Leo, 6=Virgo, 7=Libra, 8=Scorpio, 9=Sagittarius, 10=Capricorn, 11=Aquarius, 12=Pisces")
    private Long constellationId;

    /**
     * 总体运势
     */
    @ApiModelProperty("总体运势")
    @Excel(name = "总体运势")
    private String horoscopeFortune;

    /**
     * 总体运势星级
     */
    @Excel(name = "总体运势星级", prompt = "0.0-5.0")
    @ApiModelProperty("总体运势星级")
    private BigDecimal horoscopeFortuneStar;

    /**
     * 幸运色
     */
    @Excel(name = "幸运色")
    @ApiModelProperty("幸运色")
    private String luckyColor;

    /**
     * 幸运数字
     */
    @Excel(name = "幸运数字")
    @ApiModelProperty("幸运数字")
    private Long luckyNumbers;

    /**
     * 幸运时间
     */
    @Excel(name = "幸运时间", prompt = "hh:mm-hh:mm")
    @ApiModelProperty("幸运时间")
    private String luckyTime;

    /**
     * 幸运物品
     */
    @Excel(name = "幸运物品")
    @ApiModelProperty("幸运物品")
    private String luckyItems;

    /**
     * 购物链接
     */
    @Excel(name = "购物链接")
    @ApiModelProperty("购物链接")
    private String purchaseLink;

    /**
     * 运势标题一名称
     */
    @Excel(name = "运势一名称")
    @ApiModelProperty("标题一名称")
    private String firstTitleName;

    /**
     * 运势标题一运势内容
     */
    @Excel(name = "运势一运内容")
    @ApiModelProperty("运势一内容")
    private String firstTitleContent;

    /**
     * 运势标题一运势内容星级
     */
    @Excel(name = "运势一星级")
    @ApiModelProperty("运势一星级(0.0-5.0)")
    private BigDecimal firstTitleStar;

    /**
     * 运势标题二名称
     */
    @Excel(name = "运势二名称")
    @ApiModelProperty("运势二名称")
    private String secondTitleName;

    /**
     * 运势标题二内容
     */
    @Excel(name = "运势二内容")
    @ApiModelProperty("运势二内容")
    private String secondTitleContent;

    /**
     * 运势标题二星级
     */
    @Excel(name = "运势二星级")
    @ApiModelProperty("运势二星级(0.0-5.0)")
    private BigDecimal secondTitleStar;

    /**
     * 运势标题三名称
     */
    @Excel(name = "运势三名称")
    @ApiModelProperty("运势三名称")
    private String thirdTitleName;

    /**
     * 运势标题三内容
     */
    @Excel(name = "运势三内容 ")
    @ApiModelProperty("运势三内容 ")
    private String thirdTitleContent;

    /**
     * 运势标题三星级
     */
    @Excel(name = "标题三星级")
    @ApiModelProperty("运势三星级(0.0-5.0)")
    private BigDecimal thirdTitleStar;

    /**
     * 是否是本周运势0-是 1-否
     */
    @Excel(name = "是否是本周运势", readConverterExp = "0=是,1=否", prompt = "0-是 1-否")
    @ApiModelProperty("是否是本周运势0-是 1-否 ")
    private Long isWeekFortune;

    /**
     * 生成日期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("生成日期时间")
    private Date generationDate;

}
