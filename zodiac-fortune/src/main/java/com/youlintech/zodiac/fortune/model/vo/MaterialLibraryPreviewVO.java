package com.youlintech.zodiac.fortune.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(description = "星座预览效果VO")
public class MaterialLibraryPreviewVO {
    @ApiModelProperty(value = "星座素材ID")
    private Long id;

    @ApiModelProperty(value = "星座名称")
    private String constellationName;

    @ApiModelProperty(value = "生日范围")
    private String birthday;

    @ApiModelProperty(value = "星座配对ID")
    private Long meterialLibraryId;

    @ApiModelProperty(value = "星座运势描述")
    private String horoscopeFortune;

    @ApiModelProperty(value = "星座总体运势星级评价")
    private BigDecimal horoscopeFortuneStar;

    @ApiModelProperty(value = "幸运颜色")
    private String luckyColor;

    @ApiModelProperty(value = "幸运数字")
    private Long luckyNumbers;

    @ApiModelProperty(value = "幸运时间")
    private String luckyTime;

    @ApiModelProperty(value = "幸运物品")
    private String luckyItems;

    @ApiModelProperty(value = "购物链接")
    private String purchaseLink;

    @ApiModelProperty(value = "第一运势标题")
    private String firstTitleName;

    @ApiModelProperty(value = "第一运势内容")
    private String firstTitleContent;

    @ApiModelProperty(value = "第一运势星级评价")
    private BigDecimal firstTitleStar;

    @ApiModelProperty(value = "第二运势标题")
    private String secondTitleName;

    @ApiModelProperty(value = "第二运势内容")
    private String secondTitleContent;

    @ApiModelProperty(value = "第二运势星级评价")
    private BigDecimal secondTitleStar;

    @ApiModelProperty(value = "第三运势标题")
    private String thirdTitleName;

    @ApiModelProperty(value = "第三运势内容")
    private String thirdTitleContent;

    @ApiModelProperty(value = "第三运势星级评价")
    private BigDecimal thirdTitleStar;

    @ApiModelProperty(value = "配对星座ID")
    private Long pairingId;

    @ApiModelProperty(value = "星级水平")
    private BigDecimal starLevel;

    @ApiModelProperty(value = "建议信息")
    private String suggestion;

    @ApiModelProperty(value = "配对星座名称")
    private String pairingConstellationName;

    @ApiModelProperty(value = "配对星座的生日范围")
    private String pairingBirthday;
}
