package com.mljr.gps.vo;

import com.lyqc.gpsweb.re.CarGpsRe;
import com.lyqc.gpsweb.vo.GpsApplyFormVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @Date : 2018/8/28$ 16:46$
 * @Author : liht
 */
@Data
public class GpsInstallDetailVo {

    @ApiModelProperty(value = "gps审批申请单")
    private GpsApplyFormVo gpsApplyFormVo;
    @ApiModelProperty(value = "gps流程")
    private GpsFlowVo gpsFlowVo;
    @ApiModelProperty(value = "gps设备信息")
    private List<CarGpsRe> carGpsReList;
    @ApiModelProperty(value = "gps联系人")
    private GpsContractVo gpsContractVo;
    @ApiModelProperty(value = "退回原因")
    private List<CarGpsBackVo> carGpsBackList;
}
