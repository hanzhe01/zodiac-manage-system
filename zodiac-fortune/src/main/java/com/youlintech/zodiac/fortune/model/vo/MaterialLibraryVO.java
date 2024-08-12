package com.youlintech.zodiac.fortune.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youlintech.zodiac.fortune.domain.MaterialLibrary;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MaterialLibraryVO extends MaterialLibrary {
    @ApiModelProperty("星座名称")
    private String constellationName;

    /** 生日 */
    @ApiModelProperty("生日")
    private String birthday;

    /** 网址 */
    @ApiModelProperty("网址")
    private String webUrl;
}
