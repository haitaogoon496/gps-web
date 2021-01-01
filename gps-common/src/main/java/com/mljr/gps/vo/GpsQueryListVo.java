package com.mljr.gps.vo;

import com.mljr.gps.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: GPS查询列表VO
 * @Date : 2018/6/20 上午10:55
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
public class GpsQueryListVo extends BaseEntity{
    /**
     * 订单号
     */
    @ApiModelProperty(name = "appCode",value = "订单号",dataType = "String")
    private String appCode;
    /**
     * 订单号后缀（用来页面显示）
     */
    @ApiModelProperty(name = "appCodeSuffix",value = "订单号后缀",dataType = "List<String>")
    private List<String> appCodeSuffix;
    /**
     * GPS审批状态
     */
    @ApiModelProperty(name = "isGps",value = "GPS审批状态",dataType = "Integer")
    private Integer isGps;

    /**
     * GPS审批状态描述
     */
    @ApiModelProperty(name = "gpsDesc",value = "GPS审批状态描述",dataType = "string")
    private String gpsDesc;
    /**
     * 流程序号
     */
    @ApiModelProperty(name = "flowSeq",value = "流程序号",dataType = "Integer")
    private Integer flowSeq;
    /**
     * 贷款年限
     */
    @ApiModelProperty(name = "loanPeriods",value = "贷款年限",dataType = "String")
    private String loanPeriods;
    /**
     * 省份
     */
    @ApiModelProperty(name = "province",value = "省份",dataType = "String")
    private String province;
    /**
     * 客户姓名
     */
    @ApiModelProperty(name = "customerName",value = "客户姓名",dataType = "String")
    private String customerName;
    /**
     * 客户是否在现场
     */
    @ApiModelProperty(name = "customerOnScene",value = "客户是否在现场",dataType = "String")
    private String isScene;
    /**
     * 是否自带征信
     */
    @ApiModelProperty(name = "isCredit",value = "是否自带征信",dataType = "String")
    private String isCredit;
    /**
     * 是否退回订单
     */
    @ApiModelProperty(name = "isBack",value = "是否退回订单",dataType = "String")
    private String isBack;
    /**
     * 是否快审订单
     */
    @ApiModelProperty(name = "isQuickAudit",value = "是否快审订单",dataType = "String")
    private String isQuickAudit;
    /**
     * 是否必须核查
     */
    @ApiModelProperty(name = "customerOnScene",value = "是否必须核查",dataType = "String")
    private String sendAppr;
    /**
     * 经销商单位
     */
    @ApiModelProperty(name = "companyName",value = "经销商单位",dataType = "String")
    private String companyName;
    /**
     * 经销商code
     */
    @ApiModelProperty(name = "dealerCode",value = "经销商code",dataType = "String")
    private String dealerCode;
    /**
     * 经销商门店
     */
    @ApiModelProperty(name = "dealerName",value = "经销商门店",dataType = "String")
    private String dealerName;
    /**
     * 车辆ID
     */
    @ApiModelProperty(name = "carId",value = "车辆ID",dataType = "Integer")
    private Integer carId;
    /**
     * 车架号
     */
    @ApiModelProperty(name = "carIdentify",value = "车架号",dataType = "String")
    private String carIdentify;
    /**
     * 车辆颜色
     */
    @ApiModelProperty(name = "carColor",value = "车辆颜色",dataType = "String")
    private String carColor;
    /**
     * 车类
     */
    @ApiModelProperty(name = "carType",value = "车类",dataType = "String")
    private String carType;
    /**
     * 车型
     */
    @ApiModelProperty(name = "carSeries",value = "车型",dataType = "String")
    private String carSeries;
    /**
     * 销售人
     */
    @ApiModelProperty(name = "saleName",value = "销售人",dataType = "String")
    private String saleName;
    /**
     * 工作流
     */
    @ApiModelProperty(name = "workflowQueue",value = "工作流",dataType = "String")
    private String workflowQueue;
    /**
     * 申请单状态
     */
    @ApiModelProperty(name = "appStatus",value = "申请单状态",dataType = "Integer")
    private Integer appStatus;
    /**
     * 放款资料状态
     */
    @ApiModelProperty(name = "loanFileStatus",value = "放款资料状态",dataType = "Integer")
    private Integer loanFileStatus;
    /**
     * 有线设备号
     */
    @ApiModelProperty(name = "wireDeviceNo",value = "有线设备号",dataType = "String")
    private String wireDeviceNo;
    /**
     * 有线安装方式
     */
    @ApiModelProperty(name = "wireInstallType",value = "有线安装方式",dataType = "String")
    private String wireInstallType;
    /**
     * 无线设备号
     */
    @ApiModelProperty(name = "wirelessDeviceNo",value = "无线设备号",dataType = "String")
    private String wirelessDeviceNo;
    /**
     * 无线安装方式
     */
    @ApiModelProperty(name = "wirelessInstallType",value = "无线安装方式",dataType = "String")
    private String wirelessInstallType;
    /**
     * 审核日期
     */
    @ApiModelProperty(name = "auditDate",value = "审核日期",dataType = "String")
    private String auditDate;
    /**
     * 审核意见
     */
    @ApiModelProperty(name = "auditIdea",value = "审核意见",dataType = "String")
    private String auditIdea;
    /**
     * 审核结果
     */
    @ApiModelProperty(name = "auditResult",value = "审核结果",dataType = "String")
    private String auditResult;
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
    @ApiModelProperty(name = "autoAuditResult",value = "自动验证结果",dataType = "String")
    private String autoAuditResult;
    /**
     * 补充内容
     */
    @ApiModelProperty(name = "auditSupplement",value = "补充内容",dataType = "String")
    private String auditSupplement;
    /**
     * 人工审批备注
     */
    @ApiModelProperty(name = "flowRemark",value = "人工审批备注",dataType = "String")
    private String flowRemark;
    /**
     * 流程阶段
     */
    @ApiModelProperty(name = "flowStepDesc",value = "流程阶段",dataType = "String")
    private String flowStepDesc;
    /**
     * 提交时间
     */
    @ApiModelProperty(name = "appTime",value = "提交时间",dataType = "String")
    private String appTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(name = "updateTime",value = "更新时间",dataType = "String")
    private String updateTime;
    /**
     * 产品名称
     */
    @ApiModelProperty(name = "productName",value = "产品名称",dataType = "String")
    private String productName;
    /**
     * GPS费用
     */
    @ApiModelProperty(name = "gpsFee",value = "GPS费用",dataType = "BigDecimal")
    private BigDecimal gpsFee;

    /**
     * GPS设备号
     */
    @ApiModelProperty(name = "gpsDeviceNo",value = "gps设备号",dataType = "String")
    private String gpsDeviceNo;

    /**
     * GPS安装方式
     */
    @ApiModelProperty(name = "gpsInstallType",value = "安装方式",dataType = "String")
    private String gpsInstallType;

    /**
     * GPS流程步骤
     */
    @ApiModelProperty(name = "gpsFlowStep",value = "GPS流程步骤",dataType = "Integer")
    private Integer gpsFlowStep;

    /**
     * GPS流程步骤
     */
    @ApiModelProperty(name = "gpsFlowStepDesc",value = "GPS流程步骤",dataType = "String")
    private String gpsFlowStepDesc;

    /**
     * GPS审批状态 (0:未审批 1:审批通过 2:审批失败)
     */
    @ApiModelProperty(name = "gpsFlowStatus",value = "GPS审批状态",dataType = "Integer")
    private Integer gpsFlowStatus;

    /**
     * GPS审批状态 (0:未审批 1:审批通过 2:审批失败)
     */
    @ApiModelProperty(name = "gpsFlowStatusDesc",value = "GPS审批状态",dataType = "String")
    private String gpsFlowStatusDesc;

    /**
     * GPS数量
     */
    @ApiModelProperty(name = "gpsCount",value = "GPS数量",dataType = "String")
    private String gpsCount;

    /**
     * 主贷人id
     */
    @ApiModelProperty(name = "proppserId",value = "主贷人id",dataType = "Integer")
    private Integer proppserId;
}
