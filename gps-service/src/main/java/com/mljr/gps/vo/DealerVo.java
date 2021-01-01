package com.mljr.gps.vo;

import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * @description: 费用规则门店管理配置VO类
 * @Date : 2018/2/8$ 15:58$
 * @Author : liht
 */
public class DealerVo implements Serializable{

    private static final long serialVersionUID = 4706079380557281660L;
    @ApiModelProperty(value = "自增主键")
    private String id;

    /**
     * 关联规则主键id
     */
    @ApiModelProperty(name="ruleSeq",value="关联规则主键id",required = true,dataType="String")
    @NotNull(message = "[规则id]不能为空")
    private String ruleSeq;
    /**
     * 门店code
     */
    @ApiModelProperty(name="dealerCode",value="门店code",required = true,dataType="String")
    @NotNull(message = "[门店code]不能为空")
    private String dealerCode;
    /**
     * 门店名称
     */
    @ApiModelProperty(name="dealerName",value="门店名称",required = false,dataType="String")
    private String dealerName;
    /**
     * 供应商名称
     */
    @ApiModelProperty(name="companyName",value="供应商名称",required = false,dataType="String")
    private String companyName;
    /**
     * 状态
     */
    @ApiModelProperty(name="companyNstatusame",value="状态",required = false,dataType="String")
    private  String status;
    /**
     * 标示是否关联门店，适用于VIEW视图显示添加还是删除
     */
    @ApiModelProperty(name="linked",value="标示是否关联门店",required = false,dataType="String")
    private String linked;

    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRuleSeq() {
        return ruleSeq;
    }

    public void setRuleSeq(String ruleSeq) {
        this.ruleSeq = ruleSeq;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLinked() {
        return linked;
    }

    public void setLinked(String linked) {
        this.linked = linked;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
