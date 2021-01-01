package com.mljr.gps.vo.orderVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author：rongss
 * @Description
 * @Date：Created in 下午9:17 2018/7/17
 */
@Data
public class CreditInfoVo {

    /**
     * 车辆贷款金额
     */
    @ApiModelProperty(name="carLoanAmount",value="车辆贷款金额",dataType="BigDecimal")
    private BigDecimal carLoanAmount;
    /**
     * 总贷款金额
     */
    @ApiModelProperty(name="loanAmount",value="总贷款金额",dataType="BigDecimal")
    private BigDecimal loanAmount;
    /**
     * 首付款
     */
    @ApiModelProperty(name="initPayment",value="首付款",dataType="BigDecimal")
    private BigDecimal initPayment;
    /**
     * 车辆销售价
     */
    @ApiModelProperty(name="salePrice",value="车辆销售价",dataType="BigDecimal")
    private BigDecimal salePrice;
    /**
     * 首付比例
     */
    @ApiModelProperty(name="initScale",value="首付比例",dataType="BigDecimal")
    private BigDecimal initScale;
    /**
     * 车类
     */
    @ApiModelProperty(name="isLcv",value="车类",dataType="String")
    private String isLcv;
    /**
     * 车类str
     */
    @ApiModelProperty(name="isLcvStr",value="车类str",dataType="String")
    private String isLcvStr;
    /**
     * 保险贷款金额
     */
    @ApiModelProperty(name="secureFee",value="保险贷款金额",dataType="BigDecimal")
    private BigDecimal secureFee;
    /**
     * 贷款期限
     */
    @ApiModelProperty(name="loanPeriods",value="贷款期限",dataType="String")
    private String loanPeriods;
    /**
     * 渠道验证结果
     */
    @ApiModelProperty(name="approvalValidateStr",value="渠道验证结果",dataType="Integer")
    private String approvalValidateStr;
    /**
     * gps费用
     */
    @ApiModelProperty(name="gpsFee",value="gps费用",dataType="BigDecimal")
    private BigDecimal gpsFee;
    /**
     * 审批流程
     */
    @ApiModelProperty(name="flowSeq",value="审批流程",dataType="Integer")
    private Integer flowSeq;
    /**
     * 审批流程
     */
    @ApiModelProperty(name="flowName",value="审批流程",dataType="String")
    private String flowName;
    /**
     * 人身险
     */
    @ApiModelProperty(name="lifeInsurance",value="人身险",dataType="BigDecimal")
    private BigDecimal lifeInsurance;
    /**
     * 贷款产品
     */
    @ApiModelProperty(name="productName",value="贷款产品",dataType="String")
    private String productName;
    /**
     * 续保押金
     */
    @ApiModelProperty(name="renewCommission",value="续保押金",dataType="BigDecimal")
    private BigDecimal renewCommission;
    /**
     * 商业险
     */
    @ApiModelProperty(name="businessInsurance",value="商业险",dataType="BigDecimal")
    private BigDecimal businessInsurance;
    /**
     * 购置税
     */
    @ApiModelProperty(name="trafficInsurance",value="购置税",dataType="BigDecimal")
    private BigDecimal trafficInsurance;
    /**
     * 交强险
     */
    @ApiModelProperty(name="taxPrice",value="交强险",dataType="BigDecimal")
    private BigDecimal taxPrice;

}
