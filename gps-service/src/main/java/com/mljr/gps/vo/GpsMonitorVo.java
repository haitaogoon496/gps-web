package com.mljr.gps.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * GPS 监控设置 vo
 * @author lingyu.shang
 */
@Data
@Builder
public class GpsMonitorVo {

    @ApiModelProperty(name="gpsAcceptUserCacheVoList", value="生产自动派单审核人员列表")
    private List<SyUserVo> gpsAcceptUserCacheVoList;

    @ApiModelProperty(name="testGpsAcceptUserCacheVoList", value="测试自动派单审核人员列表")
    private List<SyUserVo> testGpsAcceptUserCacheVoList;

    @ApiModelProperty(name="upperLimit", value="审核人员自动派单持有单量上限")
    private Integer upperLimit;

    @ApiModelProperty(name="gpsWebAcceptBillTestDealerCode", value="测试门店配置")
    private List<Integer> gpsWebAcceptBillTestDealerCode;



    @ApiModelProperty(name="gpsRemoveFlag", value="移除生产GPS审核领单池人员是否成功")
    private Boolean gpsRemoveFlag;

    @ApiModelProperty(name="failGpsRemoveDesc", value="移除生产GPS审核领单池人员失败原因")
    private String failGpsRemoveDesc;

    @ApiModelProperty(name="gpsRemoveTestFlag", value="移除测试GPS审核领单池人员是否成功")
    private Boolean gpsRemoveTestFlag;

    @ApiModelProperty(name="failGpsRemoveTestDesc", value="移除测试GPS审核领单池人员失败原因")
    private String failGpsRemoveTestDesc;

    @ApiModelProperty(name="upperLimitFlag", value="设置上限是否成功")
    private Boolean upperLimitFlag;

    @ApiModelProperty(name="failUpperLimitDesc", value="设置上限失败原因")
    private String failUpperLimitDesc;

    @ApiModelProperty(name="dealerCodeFlag", value="设置测试门店是否成功")
    private Boolean dealerCodeFlag;

    @ApiModelProperty(name="failDealerCode", value="设置失败的测试门店列表")
    private List<Integer> failDealerCode;

    @ApiModelProperty(name="failDealerCodeDesc", value="设置测试门店列表失败说明")
    private String failDealerCodeDesc;

}
