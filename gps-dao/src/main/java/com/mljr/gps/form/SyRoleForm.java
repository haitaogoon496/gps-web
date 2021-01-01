package com.mljr.gps.form;

import com.lyqc.base.common.BaseForm;
import lombok.AllArgsConstructor;

/**
 * @description: 系统角色Form类
 * @Date : 下午4:17 2018/3/12
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@AllArgsConstructor
public class SyRoleForm extends BaseForm {
    private static final long serialVersionUID = -4280623769995487087L;

    private Integer userId;

    private String roleName;

    public SyRoleForm() {
    }

    public SyRoleForm(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
