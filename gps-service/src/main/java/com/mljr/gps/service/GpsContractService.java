package com.mljr.gps.service;

import com.mljr.gps.base.service.BaseService;
import com.mljr.gps.entity.GpsContract;
import com.mljr.gps.form.GpsContractForm;

/**
 * @description:
 * @Date : 2018/8/21 14:52
 * @Author : lihaitao
 */
public interface GpsContractService extends BaseService<GpsContract,Integer,GpsContractForm>{
    GpsContract queryRecordByAppCode(String appCode);
}
