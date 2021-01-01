package com.mljr.gps.entity;

import com.mljr.gps.base.entity.BaseEntity;

public class SyUserRoleKey extends BaseEntity{
    private Integer userId;

    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}