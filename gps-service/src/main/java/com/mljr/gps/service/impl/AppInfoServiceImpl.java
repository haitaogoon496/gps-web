package com.mljr.gps.service.impl;

import com.mljr.gps.entity.AppInfo;
import com.mljr.gps.mapper.AppInfoMapper;
import com.mljr.gps.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 订单 Service接口
 * @Date : 2018/6/14 下午3:56
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Service
public class AppInfoServiceImpl implements AppInfoService {
    @Autowired
    private AppInfoMapper appInfoMapper;

    @Override
    public AppInfo queryAppInfo(String appCode) {
        return appInfoMapper.queryAppInfoByAppCode(appCode);
    }

    @Override
    public int updateForCarGps(AppInfo appInfo) {
        return appInfoMapper.updateForCarGps(appInfo);
    }

    @Override
    public List<AppInfo> queryAppInfoList(List<String> appCodeList) {
        return appInfoMapper.queryAppInfoByAppCodeList(appCodeList);
    }
}
