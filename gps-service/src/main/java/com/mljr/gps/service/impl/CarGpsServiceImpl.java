package com.mljr.gps.service.impl;

import com.mljr.gps.entity.CarGps;
import com.mljr.gps.form.CarGpsForm;
import com.mljr.gps.mapper.CarGpsMapper;
import com.mljr.gps.service.CarGpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 车辆GPS信息Service
 * @Date : 2018/6/4 下午5:10
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Service
public class CarGpsServiceImpl implements CarGpsService {
    @Autowired
    CarGpsMapper carGpsMapper;

    @Override
    public int insertRecord(CarGps carGps) {
        return carGpsMapper.insert(carGps);
    }

    @Override
    public int deleteByLogical(String appCode) {
        return carGpsMapper.deleteByLogical(appCode);
    }

    @Override
    public int updateIsBack0(String appCode) {
        return carGpsMapper.updateIsBack0(appCode);
    }

    @Override
    public int updateIsCurrent0(String appCode) {
        return carGpsMapper.updateIsCurrent0(appCode);
    }

    @Override
    public List<CarGps> queryList(CarGpsForm form) {
        return carGpsMapper.queryList(form);
    }

    @Override
    public List<String> queryAppCodesByGpsNoForCaCarGps(String gpsNo) {
        return carGpsMapper.queryAppCodesByGpsNoForCaCarGps(gpsNo);
    }

    @Override
    public int updateGpsAuditStatus(CarGpsForm form) {
        return carGpsMapper.updateStatusByAppCode(form);
    }

    @Override
    public int updateSelective(CarGps carGps) {
        return carGpsMapper.updateByPrimaryKeySelective(carGps);
    }

    @Override
    public List<CarGps> queryListForBackReasonView(String appCode,Integer historyId) {
        return carGpsMapper.queryListForBackReasonView(appCode,historyId);
    }

    @Override
    public List<CarGps> queryListForCurrentBack(String appCode) {
        return carGpsMapper.queryListForCurrentBack(appCode);
    }

}
