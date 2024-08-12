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
 * 星座配对对象 tb_constellation_pairing
 *
 * @author ouyang
 * @date 2024-07-19
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_constellation_pairing")
@ApiModel(value = "ConstellationPairing", description = "星座配对实体")
public class ConstellationPairing extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 素材配对id */
    @TableId(type= IdType.AUTO)
    @ApiModelProperty("素材配对id")
    private Long id;

    /** 星座被配对id */
    @Excel(name = "星座名称", readConverterExp = "1=Aries, 2=Taurus, 3=Gemini, 4=Cancer, 5=Leo, 6=Virgo, 7=Libra, 8=Scorpio, 9=Sagittarius, 10=Capricorn, 11=Aquarius, 12=Pisces")
    @ApiModelProperty("星座被配对id")
    private Long bePairedId;

    /** 星座配对ID */
    @Excel(name = "配对星座", readConverterExp = "1=Aries, 2=Taurus, 3=Gemini, 4=Cancer, 5=Leo, 6=Virgo, 7=Libra, 8=Scorpio, 9=Sagittarius, 10=Capricorn, 11=Aquarius, 12=Pisces")
    @ApiModelProperty("星座配对ID")
    private Long pairingId;

    /** 星级 */
    @Excel(name = "星级",prompt = "0.0-5.0")
    @ApiModelProperty("星级")
    private BigDecimal starLevel;

    /** 建议 */
    @Excel(name = "建议")
    @ApiModelProperty("建议")
    private String suggestion;

    /** 素材更新时间 */
    @ApiModelProperty("素材更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateUpdated;

}
