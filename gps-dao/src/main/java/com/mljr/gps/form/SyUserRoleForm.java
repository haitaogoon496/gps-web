package com.mljr.gps.form;

import com.lyqc.base.common.BaseForm;
import com.mljr.gps.entity.SyUser;

/**
 * @description: 系统用户角色
 * @Date : 上午10:08 2018/3/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class SyUserRoleForm extends BaseForm {
    private static final long serialVersionUID = 1227570672679917728L;

    private Integer userId;

    private Integer roleId;

    public SyUserRoleForm() {

    }

    public SyUserRoleForm(Integer userId,Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

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
