package com.youlintech.zodiac.fortune.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import com.youlintech.zodiac.common.annotation.Excel;
import com.youlintech.zodiac.common.core.domain.BaseEntity;

/**
 * 星座管理对象 tb_constellation
 *
 * @author oyhz
 * @date 2024-07-17
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_constellation")
@ApiModel(value = "Constellation", description = "星座管理实体")
public class Constellation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 星座编号 */
    @Excel(name = "星座编号")
    @TableId(type= IdType.AUTO)
    @ApiModelProperty("星座编号")
    private Long id;

    /** 星座名称 */
    @Excel(name = "星座名称")
    @ApiModelProperty("星座名称")
    private String constellationName;

    /** 生日 */
    @Excel(name = "生日")
    @ApiModelProperty("生日")
    private String birthday;

    /** 更新日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("更新日期")
    private Date updataDate;

    /** 网址 */
    @Excel(name = "网址")
    @ApiModelProperty("网址")
    private String webUrl;

    /** 素材id */
    @Excel(name = "素材id")
    @ApiModelProperty("素材id")
    private Long meterialLibraryId;

    /** 周素材ID */
    @Excel(name = "周素材ID")
    @ApiModelProperty("周素材ID")
    private Long weekMeterialLibraryId;

}
