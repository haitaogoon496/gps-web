package com.mljr.gps.service;

import com.mljr.gps.entity.AppInfo;

import java.util.List;

/**
 * @description: 订单 Service接口
 * @Date : 2018/6/14 下午3:56
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface AppInfoService {
    /**
     * 查询申请单信息
     * @param appCode
     * @return
     */
    AppInfo queryAppInfo(String appCode);

    /**
     * 用于更新车辆GPS审批状态
     * @param appInfo
     * @return
     */
    int updateForCarGps(AppInfo appInfo);

    /**
     * 查询申请单信息
     * @param appCode
     * @return
     */
    List<AppInfo> queryAppInfoList(List<String> appCode);
}
