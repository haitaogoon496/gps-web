package com.mljr.gps.vo;

import com.mljr.gps.base.vo.BaseVo;

import java.util.Date;

/**
 * @description: 用户角色Vo
 * @Date : 上午10:10 2018/3/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class SyUserRoleVo extends BaseVo {
    private static final long serialVersionUID = 3702763134688023608L;
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
