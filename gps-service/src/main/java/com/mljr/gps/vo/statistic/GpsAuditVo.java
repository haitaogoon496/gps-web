package com.mljr.gps.vo.statistic;

import com.mljr.gps.common.util.ValidateUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @Date : 2018/12/24$ 15:22$
 * @Author : liht
 */
@Data
public class GpsAuditVo implements Serializable{

    @ApiModelProperty(value = "gps设备号")
    private String gpsNo;
    @ApiModelProperty(value = "gps自动验证失败次数")
    private Integer times;
}
