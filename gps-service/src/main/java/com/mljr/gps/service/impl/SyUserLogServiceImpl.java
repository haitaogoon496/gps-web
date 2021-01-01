package com.mljr.gps.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.gps.entity.SyUserLog;
import com.mljr.gps.form.SyUserLogForm;
import com.mljr.gps.mapper.SyUserLogMapper;
import com.mljr.gps.service.SyUserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 系统用户日志Service
 * @Date : 上午11:52 2018/3/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class SyUserLogServiceImpl implements SyUserLogService {
    @Autowired
    private SyUserLogMapper syUserLogMapper;
    @Override
    public List<SyUserLog> queryByPage(PageForm<SyUserLogForm> form) {
        return syUserLogMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<SyUserLogForm> form) {
        return syUserLogMapper.queryCount(form);
    }

    @Override
    public SyUserLog queryRecord(Long primaryKey) {
        return syUserLogMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(SyUserLog record) {
        syUserLogMapper.insert(record);
        return record.getId();
    }

    @Override
    public int updateRecord(SyUserLog record) {
        return syUserLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Long primaryKey) {
        return syUserLogMapper.deleteByPrimaryKey(primaryKey);
    }
}
