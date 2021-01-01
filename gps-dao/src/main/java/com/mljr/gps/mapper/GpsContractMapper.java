package com.mljr.gps.mapper;

import com.mljr.gps.base.mapper.BaseMapper;
import com.mljr.gps.entity.GpsContract;
import com.mljr.gps.form.GpsContractForm;

public interface GpsContractMapper extends BaseMapper<GpsContract,Integer,GpsContractForm>{
    GpsContract queryRecordByAppCode(String appCode);
}