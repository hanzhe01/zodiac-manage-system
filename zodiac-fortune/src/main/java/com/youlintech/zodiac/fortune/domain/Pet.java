package com.youlintech.zodiac.fortune.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youlintech.zodiac.common.annotation.Excel;
import com.youlintech.zodiac.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 白名单对象 tb_pet
 *
 * @author ouyagn
 * @date 2024-07-25
 */
@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@TableName("tb_pet")
@ApiModel(value = "Pet", description = "白名单实体")
public class Pet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId(type= IdType.AUTO)
    @ApiModelProperty("id")
    private Long id;

    /** 设备id */
    @Excel(name = "UUID")
    @ApiModelProperty("设备UUID")
    private String petId;

    @Excel(name = "网址+UUID")
    @ApiModelProperty("网址+UUID")
    private String urlUuid;

}
