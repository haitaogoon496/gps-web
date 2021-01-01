package com.mljr.gps.vo.statistic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @Date : 2018/12/24$ 15:23$
 * @Author : liht
 */
@Data
public class GpsStatisticDataVo {
    @ApiModelProperty(value = "日期")
    private String date;
    @ApiModelProperty(value = "gps类型和认证数据")
    private List<GpsTypeAndAuditVo> gpsTypeAndAuditVos;

}
