package com.mljr.gps.entity;

import com.lyqc.settable.SubmitOrApprovalPropsSettable;
import com.mljr.gps.base.entity.BaseEntity;
import com.mljr.util.TimeTools;

import java.util.Date;

public class GpsHistory extends BaseEntity implements SubmitOrApprovalPropsSettable {
    private Integer id;

    private String appCode;

    private Date submitTime;

    private Integer submitUserId;

    private String submitUserName;

    private Date approvalTime;

    private Integer approvalUserId;

    private String approvalUserName;

    private String approvalIdea;

    private Integer installStatus;

    private Integer approvalStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getSubmitUserId() {
        return submitUserId;
    }

    public void setSubmitUserId(Integer submitUserId) {
        this.submitUserId = submitUserId;
    }

    public String getSubmitUserName() {
        return submitUserName;
    }

    public void setSubmitUserName(String submitUserName) {
        this.submitUserName = submitUserName == null ? null : submitUserName.trim();
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public Integer getApprovalUserId() {
        return approvalUserId;
    }

    public void setApprovalUserId(Integer approvalUserId) {
        this.approvalUserId = approvalUserId;
    }

    public String getApprovalUserName() {
        return approvalUserName;
    }

    public void setApprovalUserName(String approvalUserName) {
        this.approvalUserName = approvalUserName == null ? null : approvalUserName.trim();
    }

    public String getApprovalIdea() {
        return approvalIdea;
    }

    public void setApprovalIdea(String approvalIdea) {
        this.approvalIdea = approvalIdea == null ? null : approvalIdea.trim();
    }

    public Integer getInstallStatus() {
        return installStatus;
    }

    public void setInstallStatus(Integer installStatus) {
        this.installStatus = installStatus;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    @Override
    public void setSubmitProps(Integer submitUserId, String submitUserName, String submitTime) {
        this.submitUserId = submitUserId;
        this.submitUserName = submitUserName;
        this.submitTime = TimeTools.parseYYYY_MM_DD_HH_MI_SS(submitTime);
    }

    @Override
    public void setApprovalProps(Integer approvalUserId, String approvalUserName, String approvalTime) {
        this.approvalUserId = approvalUserId;
        this.approvalUserName = approvalUserName;
        this.approvalTime = TimeTools.parseYYYY_MM_DD_HH_MI_SS(approvalTime);
    }
}