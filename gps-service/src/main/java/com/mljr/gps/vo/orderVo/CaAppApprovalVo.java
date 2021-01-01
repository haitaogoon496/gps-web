package com.mljr.gps.vo.orderVo;

import com.mljr.gps.common.enums.CaAppApprovalEnum;
import com.mljr.gps.common.enums.CaAppInfoEnum;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class CaAppApprovalVo {
    private Integer carId;

    private String appCode;

    private Integer userId;

    private String userName;

    private Integer flowNo;

    private String flowName;

    private String status;

    private String type;

    private String riskCode;

    private String riskName;

    private String remarks;

    private Date approvalTime;

    private Date updateTime;
    
    private String statusStr;
    
    private String typeStr;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(Integer flowNo) {
        this.flowNo = flowNo;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName == null ? null : flowName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode == null ? null : riskCode.trim();
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName == null ? null : riskName.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    
    public String getStatusStr() {
        if(StringUtils.isNotEmpty(this.status)){
           if(this.status.equals(CaAppInfoEnum.StatusEnum.CAAPP20.getIndexStr())||
                   this.status.equals(CaAppInfoEnum.StatusEnum.CAAPP21.getIndexStr())||
                   this.status.equals(CaAppInfoEnum.StatusEnum.CAAPP22.getIndexStr())||
                   this.status.equals(CaAppInfoEnum.StatusEnum.CAAPP23.getIndexStr())||
                   this.status.equals(CaAppInfoEnum.StatusEnum.CAAPP24.getIndexStr())||
                   this.status.equals(CaAppInfoEnum.StatusEnum.CAAPP25.getIndexStr())||
                   this.status.equals(CaAppInfoEnum.StatusEnum.CAAPP26.getIndexStr())||
                   this.status.equals(CaAppInfoEnum.StatusEnum.CAAPP27.getIndexStr())||
                   this.status.equals(CaAppInfoEnum.StatusEnum.CAAPP28.getIndexStr())||
                   this.status.equals(CaAppInfoEnum.StatusEnum.CAAPP29.getIndexStr())||
                   this.status.equals(CaAppInfoEnum.StatusEnum.CAAPP30.getIndexStr()))
           return CaAppInfoEnum.StatusEnum.getNameByIndex(Integer.valueOf(this.status)); 
        }
        return "";
    }

    
    public String getTypeStr() {
        if(StringUtils.isNotEmpty(this.type)){
            return CaAppApprovalEnum.TypeEnum.getNameByIndex(Integer.valueOf(this.type));
        }
        return "";
    }

    
    
}