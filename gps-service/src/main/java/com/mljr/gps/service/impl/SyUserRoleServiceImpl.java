package com.mljr.gps.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.gps.entity.SyUserRoleKey;
import com.mljr.gps.form.SyUserRoleForm;
import com.mljr.gps.mapper.SyUserRoleMapper;
import com.mljr.gps.service.SyUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 系统用户日志Service
 * @Date : 上午11:52 2018/3/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class SyUserRoleServiceImpl implements SyUserRoleService {
    @Autowired
    private SyUserRoleMapper syUserRoleMapper;
    @Override
    public List<SyUserRoleKey> queryByPage(PageForm<SyUserRoleForm> form) {
        return syUserRoleMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<SyUserRoleForm> form) {
        return syUserRoleMapper.queryCount(form);
    }

    @Override
    public SyUserRoleKey queryRecord(SyUserRoleKey primaryKey) {
        return syUserRoleMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(SyUserRoleKey record) {
        return syUserRoleMapper.insert(record);
    }

    @Override
    public int updateRecord(SyUserRoleKey record) {
        return syUserRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(SyUserRoleKey primaryKey) {
        return syUserRoleMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public int batchInsertIgnore(List<SyUserRoleKey> ignoreList) {
        return syUserRoleMapper.batchInsertIgnore(ignoreList);
    }

    @Override
    public int batchDelete(List<SyUserRoleKey> userRoleKeys) {
        return syUserRoleMapper.batchDelete(userRoleKeys);
    }
}
