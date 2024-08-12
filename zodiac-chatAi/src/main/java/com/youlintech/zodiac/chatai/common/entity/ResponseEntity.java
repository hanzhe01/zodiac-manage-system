package com.youlintech.zodiac.chatai.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel(
        description = "响应对象"
)
@AllArgsConstructor
//E 响应对象
public class ResponseEntity<E> {
    @ApiModelProperty(
            value = "响应结果（true、false表示成功或失败）",
            name = "success",
            required = true
    )
    private boolean success;
    @ApiModelProperty(
            value = "用户可看的成功或失败的描述",
            name = "msg",
            required = true
    )
    private String msg;
    @ApiModelProperty(
            value = "响应码",
            name = "code",
            required = true
    )
    private String code;
    @ApiModelProperty(
            value = "响应的数据内容",
            name = "data",
            required = true
    )
    private E data;

}

