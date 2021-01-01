package com.mljr.gps.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: GPS退回审批修改Form
 * @author zhaoxin
 * @date 2018/7/9 下午2:09
 **/
@Data
public class GpsApprovalBackForm {
    /**
     * 订单号
     */
    @ApiModelProperty(name = "appCode",value = "订单号",dataType = "String")
    private String appCode;
    /**
     * 退回审批类型
     */
    @ApiModelProperty(name = "approvalType",value = "退回审批类型",dataType = "Integer")
    private Integer approvalType;

    /**
     * 审批人
     */
    @ApiModelProperty(name = "userId",value = "审批人",dataType = "Integer")
    private Integer userId;

    /**
     * 审批人姓名
     */
    @ApiModelProperty(name = "userName",value = "审批人姓名",dataType = "String")
    private String userName;

    /**
     * 订单号
     */
    @ApiModelProperty(name = "appCodeList",value = "订单号",dataType = "List")
    private List<String> appCodeList;
}
