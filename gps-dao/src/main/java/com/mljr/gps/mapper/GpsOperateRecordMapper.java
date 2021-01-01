package com.mljr.gps.mapper;

import com.mljr.gps.base.mapper.BaseMapper;
import com.mljr.gps.entity.GpsOperateRecord;
import com.mljr.gps.form.GpsOperateRecordForm;

import java.util.List;

/**
 * @description: GPS审批操作记录Mapper
 * @Date : 2018/6/4 下午5:18
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface GpsOperateRecordMapper extends BaseMapper<GpsOperateRecord,Integer,GpsOperateRecordForm>{
    /**
     * 新增GPS审批结果
     * @param record
     * @return
     */
    int insertRecord(GpsOperateRecord record);

    /**
     * 查询订单是否被退回过
     * @param form
     * @return
     */
    List<String> queryListByAppCodes(GpsOperateRecordForm form);

    /**
     * 查询订单是否被退回过
     * @param appCode
     * @returng
     */
    List<GpsOperateRecord> queryListByAppCode(String appCode);
}