package com.mljr.gps.vo;

import com.mljr.gps.base.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 简单GPS设备VO类
 * @Date : 2018/6/4 下午7:33
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
public class SimpleGpsDeviceVo extends BaseVo {
    /**
     * 订单号
     */
    @ApiModelProperty(name="appCode",value="订单号",dataType="String")
    private String appCode;
    /**
     * 有线供应商
     */
    @ApiModelProperty(name="wireDeviceDealer",value="有线GPS供应商",dataType="String")
    private String wireDeviceDealer;
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
     * 无线供应商
     */
    @ApiModelProperty(name="wirelessDeviceDealer",value="无线GPS供应商",dataType="String")
    private String wirelessDeviceDealer;
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
     * 无线安装位置
     */
    @ApiModelProperty(name = "wirelessGpsPosition",value = "无线安装位置",dataType = "String")
    private String wirelessGpsPosition;
}
