package com.mljr.gps.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @description:
 * @Date : 2018/4/12$ 18:12$
 * @Author : liht
 */
public class ConfigParamsVo {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "参数key")
    private String paramKey;
    @ApiModelProperty(value = "参数值")
    private String paramValue;
    @ApiModelProperty(value = "参数描述")
    private String paramDesc;
    @ApiModelProperty(value = "状态(1:启用 0:暂停)")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
