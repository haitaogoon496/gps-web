package com.mljr.gps.param;

import com.lyqc.base.common.validation.EnumValidation;
import com.lyqc.gpsprovider.enums.GpsDeviceAgencyType;
import com.mljr.gps.common.enums.GpsStatisticEnum;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.NotNull;

/**
 * @description:
 * @Date : 2018/10/23$ 14:50$
 * @Author : liht
 */
@Data
public class GpsDeviceParam {

    @ApiModelProperty(value = "gps设备号")
    private String deviceNo;
    @ApiModelProperty(value = "gps设备商")
    private String gpsDealer;
    @ApiModelProperty(value = "开始日期")
    @NotNull(message = "开始日期不能为空")
    private String startDate;
    @ApiModelProperty(value = "结束日期")
    @NotNull(message = "结束日期不能为空")
    private String endDate;
    @ApiModelProperty(value = "gps设备类型")
    private GpsDeviceAgencyType gpsDeviceAgencyType;
    @ApiModelProperty(value = "gps统计类别")
    private GpsStatisticEnum gpsStatisticEnum;
}
