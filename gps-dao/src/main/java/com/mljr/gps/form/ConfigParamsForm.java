package com.mljr.gps.form;

import com.lyqc.base.common.BaseForm;
import io.swagger.annotations.ApiModelProperty;

/**
 * @description:
 * @Date : 2018/4/12$ 18:10$
 * @Author : liht
 */
public class ConfigParamsForm extends BaseForm {

    @ApiModelProperty(value = "参数key")
    private String paramKey;
    @ApiModelProperty(value = "参数值")
    private String paramValue;
    @ApiModelProperty(value = "状态(1:启用 0:暂停)")
    private Byte status;

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
