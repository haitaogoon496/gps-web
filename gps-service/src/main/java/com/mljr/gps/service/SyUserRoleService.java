package com.mljr.gps.service;

import com.mljr.gps.base.service.BaseService;
import com.mljr.gps.entity.SyUserRoleKey;
import com.mljr.gps.form.SyUserRoleForm;

import java.util.List;

/**
 * @description: 系统用户角色Service
 * @Date : 2018/3/4 上午10:12
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface SyUserRoleService extends BaseService<SyUserRoleKey,SyUserRoleKey,SyUserRoleForm>{
    /**
     * 批量删除
     * @param userRoleKeys
     * @return
     */
    default int batchDelete(List<SyUserRoleKey> userRoleKeys){
        return 0;
    }
}
