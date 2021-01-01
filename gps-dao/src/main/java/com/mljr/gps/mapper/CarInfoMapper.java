package com.mljr.gps.mapper;

import com.mljr.gps.entity.CarInfo;

/**
 * @description: 车辆信息Mapper
 * @Date : 2018/6/14 下午4:38
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface CarInfoMapper {
    /**
     * 根据CarId查询车辆信息
     * @param carId
     * @return
     */
    CarInfo selectByPrimaryKey(Integer carId);
    /**
     * 根据车辆id更新GPS相关信息
     * 包含：gpsInstallWay,gpsDealer,gpsNo
     * @param carInfo
     * @return
     */
    int updateGpsInfo(CarInfo carInfo);
}