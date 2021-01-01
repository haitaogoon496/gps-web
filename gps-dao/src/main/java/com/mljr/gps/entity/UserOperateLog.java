package com.mljr.gps.entity;

import com.mljr.gps.base.entity.BaseEntity;

import java.util.Date;
/**
 * @description: 用户操作日志Entity
 * @Date : 2018/3/2 下午6:26
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class UserOperateLog extends BaseEntity{
    private static final long serialVersionUID = 6206971177620180353L;
    /**
     * 主键id
     */
    private Long flowId;
    /**
     * 操作人
     */
    private String userName;
    /**
     * 操作人
     */
    private Integer userId;
    /**
     * 业务类型
     */
    private Integer authModel;
    /**
     * 操作日期
     */
    private String authDate;
    /**
     * 操作类型
     */
    private String authType;
    /**
     * 日志详细
     */
    private String authDetail;

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAuthModel() {
        return authModel;
    }

    public void setAuthModel(Integer authModel) {
        this.authModel = authModel;
    }

    public String getAuthDate() {
        return authDate;
    }

    public void setAuthDate(String authDate) {
        this.authDate = authDate;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType == null ? null : authType.trim();
    }

    public String getAuthDetail() {
        return authDetail;
    }

    public void setAuthDetail(String authDetail) {
        this.authDetail = authDetail == null ? null : authDetail.trim();
    }
}