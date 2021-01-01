package com.mljr.gps.vo.orderVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @Date : 2018/9/29$ 14:40$
 * @Author : liht
 */
@Data
public class OrderAllInfoVo implements Serializable {
    @ApiModelProperty(value = "订单详情")
    private OrderInfoVo orderInfoVo;
    @ApiModelProperty(value = "批复贷款信息")
    private ReplyLoanFeeVO replyLoanFeeVO;
    @ApiModelProperty(value = "车辆信息")
    private CaCarInfoVo caCarInfoVo;
    @ApiModelProperty(value = "申请人信息")
    private CaProppserInfoVo caProppserInfoVo;
    @ApiModelProperty(value = "配偶信息")
    private CaRelativesInfoVO caRelativesInfoVO;
    @ApiModelProperty(value = "联系人信息")
    private EmContactsVo emContactsVo;
    @ApiModelProperty(value = "贷款信息")
    private CreditInfoVo creditInfoVo;
    @ApiModelProperty(value = "商户信息")
    private DealerInfoVo dealerInfoVo;
    @ApiModelProperty(value = "经销商显示")
    private List<OrderTipsVo> orderTipsVoList;
    @ApiModelProperty(value = "附件信息")
    private List<CaAppAnnexVo> caAppAnnexVoList;
    @ApiModelProperty(value = "审批记录信息")
    private List<CaAppApprovalVo> caAppApprovalVoList;
    @ApiModelProperty(value = "留言信息")
    private List<CaAppMessageVo> caAppMessageVoList;
    @ApiModelProperty(value = "担保人信息")
    private CaGuaranteeInfoVo caGuaranteeInfoVo;
    @ApiModelProperty(value = "审批信息")
    private ApprovalInfoVo approvalInfoVo;
    @ApiModelProperty(value = "风控信息")
    private RiskInfoVo riskInfoVo;
    @ApiModelProperty(value = "GPS信息")
    private GPSInfoVo gpsInfoVo;
}
