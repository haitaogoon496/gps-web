package com.mljr.gps.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @description:
 * @Date : 2018/12/17$ 15:33$
 * @Author : liht
 */
@Data
public class ErrorGpsDeviceVo {

    @ApiModelProperty(value = "横轴-日期")
    private String date;
    @ApiModelProperty(value = "设备号+对应的失败次数")
    private Map<String, Object> deviceMap;
}
