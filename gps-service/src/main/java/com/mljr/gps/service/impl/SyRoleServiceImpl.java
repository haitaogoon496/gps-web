package com.mljr.gps.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.gps.entity.SyRole;
import com.mljr.gps.form.SyRoleForm;
import com.mljr.gps.mapper.SyRoleMapper;
import com.mljr.gps.service.SyRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 系统用户角色Service
 * @Date : 下午4:20 2018/3/12
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class SyRoleServiceImpl implements SyRoleService {
    @Autowired
    private SyRoleMapper syRoleMapper;
    @Override
    public List<SyRole> queryList(SyRoleForm form) {
        return syRoleMapper.queryList(form);
    }

    @Override
    public List<SyRole> queryByPage(PageForm<SyRoleForm> form) {
        return syRoleMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<SyRoleForm> form) {
        return syRoleMapper.queryCount(form);
    }

    @Override
    public SyRole queryRecord(Integer primaryKey) {
        return syRoleMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(SyRole record) {
        syRoleMapper.insert(record);
        return record.getRoleId();
    }

    @Override
    public int updateRecord(SyRole record) {
        return syRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return syRoleMapper.deleteByPrimaryKey(primaryKey);
    }
}
