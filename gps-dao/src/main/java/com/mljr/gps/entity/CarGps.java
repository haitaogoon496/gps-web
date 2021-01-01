package com.mljr.gps.entity;

import com.lyqc.gpsprovider.enums.CarGpsConstant;
import com.mljr.gps.base.entity.BaseEntity;
/**
 * @description: 车辆GPS信息表
 * @String : 2018/6/4 下午5:04
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public class CarGps extends BaseEntity{
    /**
     * 主键id
     */
    private Integer tid;
    /**
     * 单号
     */
    private String appCode;
    /**
     * 车辆id
     */
    private Integer carId;
    /**
     * GPS供应商
     */
    private String gpsDealer;
    /**
     * GPS设备号
     */
    private String gpsNo;
    /**
     * GPS安装位置
     */
    private String gpsPosition;
    /**
     * GPS安装方式
     */
    private Integer gpsInstallWay;
    /**
     * 人工审核code
     */
    private String manualAuditCode;
    /**
     * 人工审核结果
     */
    private String manualAuditResult;
    /**
     * 人工审核补充内容
     */
    private String auditSupplement;
    /**
     * 自动审批代码
     */
    private String autoAuditCode;
    /**
     * 自动审批结果
     */
    private String autoAuditResult;
    /**
     * GPS审核状态
     * {@link CarGpsConstant.AppInfoIsGpsEnum#getIndex()}
     */
    private Integer status;
    /**
     * 是否删除
     */
    private Integer delStatus;
    /**
     * 历史记录id
     */
    private Integer historyId;
    /**
     * 是否GPS审批退回
     */
    private Integer isBack;
    /**
     * 是否GPS当前审批记录
     */
    private Integer isCurrent;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = (appCode == null ? null : appCode.trim());
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getGpsDealer() {
        return gpsDealer;
    }

    public void setGpsDealer(String gpsDealer) {
        this.gpsDealer = gpsDealer == null ? null : gpsDealer.trim();
    }

    public String getGpsNo() {
        return gpsNo;
    }

    public void setGpsNo(String gpsNo) {
        this.gpsNo = gpsNo == null ? null : gpsNo.trim();
    }

    public String getGpsPosition() {
        return gpsPosition;
    }

    public void setGpsPosition(String gpsPosition) {
        this.gpsPosition = gpsPosition;
    }

    public Integer getGpsInstallWay() {
        return gpsInstallWay;
    }

    public void setGpsInstallWay(Integer gpsInstallWay) {
        this.gpsInstallWay = gpsInstallWay;
    }

    public String getManualAuditCode() {
        return manualAuditCode;
    }

    public void setManualAuditCode(String manualAuditCode) {
        this.manualAuditCode = manualAuditCode == null ? null : manualAuditCode.trim();
    }

    public String getManualAuditResult() {
        return manualAuditResult;
    }

    public void setManualAuditResult(String manualAuditResult) {
        this.manualAuditResult = manualAuditResult == null ? null : manualAuditResult.trim();
    }

    public String getAuditSupplement() {
        return auditSupplement;
    }

    public void setAuditSupplement(String auditSupplement) {
        this.auditSupplement = auditSupplement == null ? null : auditSupplement.trim();
    }

    public String getAutoAuditCode() {
        return autoAuditCode;
    }

    public void setAutoAuditCode(String autoAuditCode) {
        this.autoAuditCode = autoAuditCode == null ? null : autoAuditCode.trim();
    }

    public String getAutoAuditResult() {
        return autoAuditResult;
    }

    public void setAutoAuditResult(String autoAuditResult) {
        this.autoAuditResult = autoAuditResult == null ? null : autoAuditResult.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Integer getIsBack() {
        return isBack;
    }

    public void setIsBack(Integer isBack) {
        this.isBack = isBack;
    }

    public Integer getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Integer isCurrent) {
        this.isCurrent = isCurrent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}