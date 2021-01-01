package com.mljr.gps.entity;

import com.mljr.gps.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: GPS综合查询实体对象
 * @Date : 2018/6/3 下午5:40
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
public class GpsCompositeQuery extends BaseEntity{
    /**
     * 订单号
     */
    @ApiModelProperty(name = "appCode",value = "订单号",dataType = "String")
    private String appCode;
    /**
     * GPS审批状态
     */
    @ApiModelProperty(name = "isGps",value = "GPS审批状态",dataType = "Integer")
    private Integer isGps;
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
     * 无线安装位置
     */
    @ApiModelProperty(name = "wirelessGpsPosition",value = "无线安装位置",dataType = "String")
    private String wirelessGpsPosition;
}
