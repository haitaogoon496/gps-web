package com.mljr.gps.entity;

import com.mljr.gps.base.entity.BaseEntity;
import net.sf.oval.constraint.NotNull;

import java.sql.Timestamp;
import java.util.Date;

public class SyArgControl extends BaseEntity{
    private Integer rId;

    @NotNull(message = "参数名称不能为空")
    private String conArgName;
    @NotNull(message = "参数code不能为空")
    private String conArgCode;
    @NotNull(message = "状态不能为空")
    private Integer conArgStatus;
    @NotNull(message = "参数值不能为空")
    private String conArgValue;
    @NotNull(message = "参数类型不能为空")
    private Integer conArgType;

    private Timestamp conArgStartdate;

    private Timestamp conArgStopdate;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getConArgName() {
        return conArgName;
    }

    public void setConArgName(String conArgName) {
        this.conArgName = conArgName == null ? null : conArgName.trim();
    }

    public String getConArgCode() {
        return conArgCode;
    }

    public void setConArgCode(String conArgCode) {
        this.conArgCode = conArgCode == null ? null : conArgCode.trim();
    }

    public Integer getConArgStatus() {
        return conArgStatus;
    }

    public void setConArgStatus(Integer conArgStatus) {
        this.conArgStatus = conArgStatus;
    }

    public String getConArgValue() {
        return conArgValue;
    }

    public void setConArgValue(String conArgValue) {
        this.conArgValue = conArgValue == null ? null : conArgValue.trim();
    }

    public Integer getConArgType() {
        return conArgType;
    }

    public void setConArgType(Integer conArgType) {
        this.conArgType = conArgType;
    }

    public Date getConArgStartdate() {
        return conArgStartdate;
    }

    public void setConArgStartdate(Timestamp conArgStartdate) {
        this.conArgStartdate = conArgStartdate;
    }

    public Date getConArgStopdate() {
        return conArgStopdate;
    }

    public void setConArgStopdate(Timestamp conArgStopdate) {
        this.conArgStopdate = conArgStopdate;
    }

    @Override
    public boolean isInsert() {
        return this.rId == null || this.rId == 0;
    }

}