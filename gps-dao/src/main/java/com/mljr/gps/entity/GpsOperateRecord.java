package com.mljr.gps.entity;

import com.lyqc.settable.SubmitOrApprovalPropsSettable;
import com.mljr.gps.base.entity.BaseEntity;
import com.mljr.util.TimeTools;

import java.io.Serializable;
import java.util.Date;
/**
 * @description: GPS审批操作记录
 * @Date : 2018/6/4 下午5:18
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class GpsOperateRecord extends BaseEntity implements SubmitOrApprovalPropsSettable {

    private Integer id;

    private String appCode;

    private Integer flowStep;

    private Integer flowStatus;

    private Date createTime;

    private Integer createUserId;

    private String createUserName;

    private String remark;

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

    public Integer getFlowStep() {
        return flowStep;
    }

    public void setFlowStep(Integer flowStep) {
        this.flowStep = flowStep;
    }

    public Integer getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(Integer flowStatus) {
        this.flowStatus = flowStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public void setSubmitProps(Integer submitUserId, String submitUserName, String submitTime) {
        this.createUserId = submitUserId;
        this.createUserName = submitUserName;
        this.createTime = TimeTools.parseYYYY_MM_DD_HH_MI_SS(submitTime);
    }

    @Override
    public void setApprovalProps(Integer approvalUserId, String approvalUserName, String approvalTime) {

    }
}