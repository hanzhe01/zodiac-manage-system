package com.youlintech.zodiac.web.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

@ApiModel(
        description = "响应对象"
)
@AllArgsConstructor
//E 响应对象
public class ResponseEntitys<E> {
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
    public ResponseEntitys() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getCode() {
        return this.code;
    }

    public E getData() {
        return this.data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(E data) {
        this.data = data;
    }

    public String toString() {
        return "ResponseEntity(success=" + this.isSuccess() + ", msg=" + this.getMsg() + ", code=" + this.getCode() + ", data=" + this.getData();
    }
}

