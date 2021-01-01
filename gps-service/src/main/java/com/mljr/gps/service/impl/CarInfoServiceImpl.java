package com.mljr.gps.service.impl;

import com.alibaba.fastjson.JSON;
import com.lyqc.gpsweb.dto.CarGpsDTO;
import com.lyqc.gpsweb.dto.GpsApproveDTO;
import com.mljr.gps.entity.CarInfo;
import com.mljr.gps.mapper.CarInfoMapper;
import com.mljr.gps.service.CarInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @description: 车辆信息Service
 * @Date : 2018/6/14 下午4:39
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Slf4j
@Service
public class CarInfoServiceImpl implements CarInfoService {
    final String delimiter = ",";
    @Autowired
    private CarInfoMapper carInfoMapper;
    @Override
    public CarInfo queryByCarId(Integer carId) {
        return carInfoMapper.selectByPrimaryKey(carId);
    }

    @Override
    public int updateGpsInfo(CarInfo carInfo) {
        return carInfoMapper.updateGpsInfo(carInfo);
    }

    @Override
    public void handleGpsInfo(Integer carId,GpsApproveDTO approveDTO) {
        Assert.notNull(carId,"carId不能为空");
        Assert.notNull(approveDTO,"approveDTO对象为空");
        log.info("处理车辆相关GPS信息,dto={}", JSON.toJSON(approveDTO));
        List<CarGpsDTO> gpsList = approveDTO.getGpsList();
        Assert.notEmpty(gpsList,"GPS设备信息不能为空");
        List<String> gpsNoList = new ArrayList<>();
        List<String> gpsDealerList = new ArrayList<>();
        List<String> gpsInstallWayList = new LinkedList<>();
        gpsList.forEach(gps -> {
            gpsNoList.add(gps.getGpsNo());
            gpsDealerList.add(gps.getDealerCode());
            gpsInstallWayList.add(gps.getGpsInstallWay().toString());
        });
        CarInfo carInfo = new CarInfo();
        carInfo.setCarId(carId);
        carInfo.setGpsNo(String.join(delimiter,gpsNoList));
        carInfo.setGpsDealer(String.join(delimiter,gpsDealerList));
        carInfo.setGpsCount(gpsList.size());
        carInfo.setGpsInstallWay(String.join(delimiter,gpsInstallWayList));
        carInfoMapper.updateGpsInfo(carInfo);
    }
}
