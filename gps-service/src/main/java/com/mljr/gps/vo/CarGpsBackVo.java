package com.mljr.gps.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: GPS退回原因VO
 * @author: zhaoxin
 * @date: 2018/10/17 下午5:24
 **/
@Data
public class CarGpsBackVo {

    @ApiModelProperty(name = "gpsNo",value = "设备ID",dataType = "String")
    private String gpsNo;

    @ApiModelProperty(name = "manualAuditResult",value = "人工审核结果",dataType = "String")
    private String manualAuditResult;

    @ApiModelProperty(name = "auditSupplement",value = "补充内容",dataType = "String")
    private String auditSupplement;
}
