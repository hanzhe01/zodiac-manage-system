package com.youlintech.zodiac.fortune.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ConstellationPairingVO {

    /** 素材配对id */
    @ApiModelProperty("素材配对id")
    private Long id;
    /** 星座被配对id */
    @ApiModelProperty("星座被配对id")
    private Long bePairedId;

    @ApiModelProperty("星座名称")
    private String bePairedName;

    /** 星座配对ID */
    @ApiModelProperty("星座配对ID")
    private Long pairingId;

    @ApiModelProperty("配对星座名称")
    private String pairedName;

    /** 星级 */
    @ApiModelProperty("星级")
    private BigDecimal starLevel;

    /** 建议 */
    @ApiModelProperty("建议")
    private String suggestion;

    /** 素材更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("素材更新时间")
    private Date dateUpdated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
