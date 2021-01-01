package com.mljr.gps.form;

import com.lyqc.base.common.BaseForm;
import com.lyqc.gpsweb.enums.GpsManualAuditCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: GPS综合查询Form
 * @Date : 2018/6/3 下午5:50
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
public class GpsQueryListForm extends BaseForm{
    /**
     * 申请单编号
     */
    @ApiModelProperty(name = "appCode",value = "申请单编号",dataType = "String")
    private String appCode;

    /**
     * 申请单编号
     */
    @ApiModelProperty(name = "appCodeList",value = "申请单编号集合",dataType = "List<String>")
    private List<String> appCodeList;

    /**
     * 客户ID
     */
    @ApiModelProperty(name = "proppserIds",value = "申请人ID集合",dataType = "List<Integer>")
    List<Integer> proppserIds;
    /**
     * 客户姓名
     */
    @ApiModelProperty(name = "customerName",value = "客户姓名",dataType = "String")
    private String customerName;
    /**
     * 经销商范围（主要用于按权限查询，不同用户所属不同管辖门店）
     */
    @ApiModelProperty(name = "dealerCodeScope",value = "经销商范围",dataType = "List<Integer>")
    private List<Integer> dealerCodeScope;
    /**
     * 经销商名称
     */
    @ApiModelProperty(name = "companyName",value = "经销商名称",dataType = "String")
    private String companyName;
    /**
     * 经销商门店
     */
    @ApiModelProperty(name = "dealerName",value = "经销商门店",dataType = "String")
    private String dealerName;
    /**
     * 提交时间
     */
    @ApiModelProperty(name = "appTime",value = "提交时间",dataType = "String")
    private String appTime;
    /**
     * 人工审核结果代码
     * {@link GpsManualAuditCodeEnum#getName()}
     */
    @ApiModelProperty(name = "manualAuditCode",value = "人工审核状态",dataType = "String")
    private String manualAuditCode;
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
     * {@link com.lyqc.gpsprovider.enums.CarGpsConstant.AppInfoIsGpsEnum#getIndex()}
     */
    @ApiModelProperty(name = "isGps",value = "GPS审批状态",dataType = "String")
    private String isGps;
    /**
     * GPS审批状态范围
     * {@link com.lyqc.gpsprovider.enums.CarGpsConstant.AppInfoIsGpsEnum#getIndex()}
     */
    @ApiModelProperty(name = "isGpsScope",value = "GPS审批状态范围",dataType = "String")
    private List<Integer> isGpsScope;
    /**
     * 要过滤的订单状态集合
     */
    @ApiModelProperty(name = "excludeStatusScope",value = "要过滤的订单状态集合",dataType = "List<String>")
    private List<String> excludeStatusScope;
    /**
     * 业务标示
     */
    @ApiModelProperty(name = "buzMark",value = "业务标示",dataType = "String")
    private String buzMark;
    @ApiModelProperty(value = "提交时间开始")
    private String appTimeStart;
    @ApiModelProperty(value = "提交时间结束")
    private String appTimeEnd;
    @ApiModelProperty(value = "是否二手车")
    private String isOld;
    @ApiModelProperty(value = "是否Lcv")
    private String isLcv;
    @ApiModelProperty(value = "利率档位")
    private String rateLevel;
    @ApiModelProperty(value = "是否vip经销商")
    private String isVip;
    @ApiModelProperty(value = "登记人姓名")
    private String userName;
    @ApiModelProperty(value = "身份证号码")
    private String idNo;
    @ApiModelProperty(value = "车牌类型")
    private String isCompanyLicense;
    @ApiModelProperty(value = "状态")
    private String status;

    /**
     * 所属销售
     */
    @ApiModelProperty(name = "saleName",value = "所属销售",dataType = "String")
    private String saleName;
    /**
     * 所属运营
     */
    @ApiModelProperty(name = "operationName",value = "所属运营",dataType = "String")
    private String operationName;

    /**
     * systemCode
     */
    @ApiModelProperty(name = "systemCode",value = "systemCode",dataType = "String")
    private String systemCode;

}
