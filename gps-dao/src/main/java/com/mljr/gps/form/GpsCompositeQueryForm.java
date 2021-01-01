package com.mljr.gps.form;

import com.lyqc.base.common.BaseForm;
import com.lyqc.base.enums.CarGpsConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: GPS综合查询Form
 * @Date : 2018/6/3 下午5:50
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
public class GpsCompositeQueryForm extends BaseForm{
    /**
     * 客户姓名
     */
    @ApiModelProperty(name = "customerName",value = "客户姓名",dataType = "String")
    private String customerName;
    /**
     * 审核人姓名
     */
    @ApiModelProperty(name = "approvalUserName",value = "审核人姓名",dataType = "String")
    private String approvalUserName;
    /**
     * 审核人id
     */
    @ApiModelProperty(name = "approvalUserId",value = "审核人id",dataType = "String")
    private Integer approvalUserId;
    /**
     * 审批结果
     */
    @ApiModelProperty(name = "auditResult",value = "审批结果",dataType = "String")
    private String auditResult;
    /**
     * GPS审批状态
     * {@link CarGpsConstant.AppInfoIsGpsEnum#getIndex()}
     */
    @ApiModelProperty(name = "isGps",value = "GPS审批状态",dataType = "String")
    private String isGps;
    /**
     * systemCode
     */
    @ApiModelProperty(name = "systemCode",value = "systemCode",dataType = "String")
    private String systemCode;
    /**
     * 要过滤的订单状态集合
     */
    @ApiModelProperty(name = "excludeStatusScope",value = "要过滤的订单状态集合",dataType = "List<String>")
    private List<String> excludeStatusScope;

}
