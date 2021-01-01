package com.mljr.gps.vo.orderVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author：rongss
 * @Description
 * @Date：Created in 下午6:23 2018/8/8
 */
@Data
@NoArgsConstructor
public class ReplyLoanFeeVO {

    /**
     * 批复车辆贷款金额
     */
    @ApiModelProperty(name="carLoanAmount",value="批复车辆贷款金额",dataType="BigDecimal")
    private BigDecimal carLoanAmount;
    /**
     * 批复总贷款金额
     */
    @ApiModelProperty(name="loanAmount",value="批复总贷款金额",dataType="BigDecimal")
    private BigDecimal loanAmount;
    /**
     * 批复申请首付款
     */
    @ApiModelProperty(name="initPayment",value="批复首付款",dataType="BigDecimal")
    private BigDecimal initPayment;
    /**
     * 批复申请首付款比例
     */
    @ApiModelProperty(name="initScale",value="批复申请首付款比例",dataType="BigDecimal")
    private BigDecimal initScale;
    /**
     * 批复申请还款期限
     */
    @ApiModelProperty(name="loanPeriods",value="批复申请还款期限",dataType="Integer")
    private Integer loanPeriods;
    /**
     * 批复申请GPS费用
     */
    @ApiModelProperty(name="gpsFee",value="批复申请GPS费用",dataType="BigDecimal")
    private BigDecimal gpsFee;
    /**
     * 批复人身险
     */
    @ApiModelProperty(name="lifeInsurance",value="批复人身险",dataType="BigDecimal")
    private BigDecimal lifeInsurance;
    /**
     * 批复续保押金
     */
    @ApiModelProperty(name="renewCommission",value="批复续保押金",dataType="BigDecimal")
    private BigDecimal renewCommission;
    /**
     * 批复账户管理费
     */
    @ApiModelProperty(name="accountFee",value="批复账户管理费",dataType="BigDecimal")
    private BigDecimal accountFee;
    /**
     * 批复平台费
     */
    @ApiModelProperty(name="comFee",value="批复平台费",dataType="BigDecimal")
    private BigDecimal comFee;
    /**
     * 是否打印批复信息
     */
    @ApiModelProperty(name="isPrintStr",value="是否打印配偶信息",dataType="String")
    private String isPrintStr;

}
