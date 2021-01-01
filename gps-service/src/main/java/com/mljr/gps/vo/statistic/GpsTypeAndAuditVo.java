package com.mljr.gps.vo.statistic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @Date : 2018/12/24$ 15:23$
 * @Author : liht
 */
@Data
public class GpsTypeAndAuditVo {

    @ApiModelProperty(value = "gps设备类型")
    private String agencyType;
    @ApiModelProperty(value = "gps自动验证数据")
    private List<GpsAuditVo> gpsAuditVos;
    @ApiModelProperty(value = "gps验证失败次数基准的统计")
    private Set<GpsFailTimesOfDeviceVo> data;


}
