package com.mljr.gps.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.gps.entity.GpsContract;
import com.mljr.gps.form.GpsContractForm;
import com.mljr.gps.mapper.GpsContractMapper;
import com.mljr.gps.service.GpsContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @description:
 * @Date : 2018/8/21 15:16
 * @Author : lihaitao
 */
@Service
public class GpsContractServiceImpl implements GpsContractService {
    @Autowired
    GpsContractMapper contractMapper;


    @Override
    public GpsContract queryRecordByAppCode(String appCode) {
        return contractMapper.queryRecordByAppCode(appCode);
    }

    @Override
    public List<GpsContract> queryByPage(PageForm<GpsContractForm> form) {
        return contractMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<GpsContractForm> form) {
        return contractMapper.queryCount(form);
    }

    @Override
    public GpsContract queryRecord(Integer primaryKey) {
        return contractMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(GpsContract record) {
        return contractMapper.insertSelective(record);
    }

    @Override
    public int updateRecord(GpsContract record) {
        return contractMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return contractMapper.deleteByPrimaryKey(primaryKey);
    }
}
