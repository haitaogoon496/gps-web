package com.mljr.gps.vo.orderVo;

import java.util.Date;

public class CaAppMessageVo {
    private Integer msgId;

    private String appCode;

    private Integer userId;

    private String userName;

    private Byte flowNo;

    private String flowName;

    private String message;

    private Date messageTime;

    private String status;

    private Integer yyId;

    private Integer pledgeType;

    private Integer usedNum;
    
    private String statusStr;

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
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

    public Byte getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(Byte flowNo) {
        this.flowNo = flowNo;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName == null ? null : flowName.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getYyId() {
        return yyId;
    }

    public void setYyId(Integer yyId) {
        this.yyId = yyId;
    }

    public Integer getPledgeType() {
        return pledgeType;
    }

    public void setPledgeType(Integer pledgeType) {
        this.pledgeType = pledgeType;
    }

    public Integer getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Integer usedNum) {
        this.usedNum = usedNum;
    }

    
    public String getStatusStr() {
        if(null!=this.status&&this.status.equals("1")){
            return "有效";
        }
        return "无效";
    }

    
    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
    
}