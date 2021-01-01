package com.mljr.gps.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: GPS审核结果（包括自动审核 和 人工审核）
 * @Date : 2018/6/20 下午1:27
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
public class GpsAuditResultBo implements Serializable{
    /**
     * 人工审核结果
     */
    @ApiModelProperty(name = "manualAuditResult",value = "人工审核结果",dataType = "String")
    private String manualAuditResult;
    /**
     * 人工审核备注
     */
    @ApiModelProperty(name = "manualAuditRemark",value = "人工审核备注",dataType = "String")
    private String manualAuditRemark;
    /**
     * 自动验证结果
     */
    @ApiModelProperty(name = "autoAuditResult",value = "自动验证状态",dataType = "String")
    private String autoAuditResult;
    /**
     * 人工审批备注
     */
    @ApiModelProperty(name = "auditSupplement",value = "人工审批备注",dataType = "String")
    private String auditSupplement;
}
