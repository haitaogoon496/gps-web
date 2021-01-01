package com.mljr.gps.service;

import com.lyqc.gpsweb.dto.GpsApproveDTO;
import com.mljr.gps.entity.CarInfo;

/**
 * @description: 车辆信息Service
 * @Date : 2018/6/14 下午4:39
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface CarInfoService {
    /**
     * 根据CarId查询车辆信息
     * @param carId
     * @return
     */
    CarInfo queryByCarId(Integer carId);
    /**
     * 根据车辆id更新GPS相关信息
     * 包含：gpsInstallWay,gpsDealer,gpsNo
     * @param carInfo
     * @return
     */
    int updateGpsInfo(CarInfo carInfo);
    /**
     * 处理车辆GPS相关信息（gpsCount、gpsNo、GpsDealer、gpsInstallWay）
     * 业务场景：GPS安装单已驳回、我的GPS领单中-审批
     * @param carId
     * @param approveDTO
     */
    void handleGpsInfo(Integer carId,GpsApproveDTO approveDTO);
}
