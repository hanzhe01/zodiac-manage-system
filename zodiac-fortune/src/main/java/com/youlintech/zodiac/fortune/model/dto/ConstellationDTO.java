package com.youlintech.zodiac.fortune.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ConstellationDTO {

    @ApiModelProperty("星座名称")
    private String constellationName;

    /** 生日 */
    @ApiModelProperty("生日")
    private String birthday;

    /** 更新日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("更新日期")
    private Date updataDate;

    /** 网址 */
    @ApiModelProperty("网址")
    private String webUrl;
}
