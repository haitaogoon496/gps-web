package com.mljr.gps.mapper;

import com.mljr.gps.base.mapper.BaseMapper;
import com.mljr.gps.entity.SyUserRoleKey;
import com.mljr.gps.form.SyUserRoleForm;

import java.util.List;

public interface SyUserRoleMapper extends BaseMapper<SyUserRoleKey,SyUserRoleKey,SyUserRoleForm>{
    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    int batchDelete(List<SyUserRoleKey> list);

    @Override
    int batchInsertIgnore(List<SyUserRoleKey> list);
}