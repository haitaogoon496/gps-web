package com.mljr.gps.mapper;

import com.mljr.gps.entity.AppInfo;

import java.util.List;

public interface AppInfoMapper {
    int deleteByPrimaryKey(Integer appNo);

    int insert(AppInfo record);

    int insertSelective(AppInfo record);

    AppInfo selectByPrimaryKey(Integer appNo);

    int updateByPrimaryKeySelective(AppInfo record);

    int updateByPrimaryKey(AppInfo record);

    /**
     * 查询申请单信息
     * @param appCode
     * @return
     */
    AppInfo queryAppInfoByAppCode(String appCode);

    /**
     * 用于更新车辆GPS审批状态
     * @param appInfo
     * @return
     */
    int updateForCarGps(AppInfo appInfo);

    List<AppInfo> queryAppInfoByAppCodeList(List<String> appCodeList);
}