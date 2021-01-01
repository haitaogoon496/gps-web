package com.mljr.gps.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.gps.entity.UserOperateLog;
import com.mljr.gps.form.UserOperateLogForm;
import com.mljr.gps.mapper.UserOperateLogMapper;
import com.mljr.gps.service.UserOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 用户操作日志 Service
 * @Date : 下午6:28 2018/3/2
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class UserOperateLogServiceImpl implements UserOperateLogService {
    @Autowired
    private UserOperateLogMapper userOperateLogMapper;
    @Override
    public List<UserOperateLog> queryByPage(PageForm<UserOperateLogForm> form) {
        return null;
    }

    @Override
    public int queryCount(PageForm<UserOperateLogForm> form) {
        return userOperateLogMapper.queryCount(form);
    }

    @Override
    public UserOperateLog queryRecord(Integer primaryKey) {
        return userOperateLogMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(UserOperateLog record) {
        return userOperateLogMapper.insert(record);
    }

    @Override
    public int updateRecord(UserOperateLog record) {
        return userOperateLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return userOperateLogMapper.deleteByPrimaryKey(primaryKey);
    }
}
