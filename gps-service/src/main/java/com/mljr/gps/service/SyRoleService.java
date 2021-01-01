package com.mljr.gps.service;

import com.mljr.gps.base.service.BaseService;
import com.mljr.gps.entity.SyRole;
import com.mljr.gps.form.SyRoleForm;

import java.util.List;

/**
 * @description: 系统用户角色Service
 * @Date : 2018/3/4 上午10:12
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface SyRoleService extends BaseService<SyRole,Integer,SyRoleForm>{
    /**
     * 查询列表
     * @param form
     * @return
     */
    List<SyRole> queryList(SyRoleForm form);
}
