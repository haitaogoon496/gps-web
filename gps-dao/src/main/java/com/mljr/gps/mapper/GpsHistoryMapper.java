package com.mljr.gps.mapper;

import com.mljr.gps.base.mapper.BaseMapper;
import com.mljr.gps.entity.GpsHistory;
import com.mljr.gps.form.GpsHistoryForm;

import java.util.List;

/**
 * @description: GPS审批历史记录Mapper
 * @Date : 2018/6/4 下午5:15
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface GpsHistoryMapper extends BaseMapper<GpsHistory,Integer,GpsHistoryForm> {
    /**
     * 查询未审批的历史记录
     * @param appCode
     * @return
     */
    GpsHistory queryRecordNoApprove(String appCode);

    List<GpsHistory> queryList(GpsHistoryForm form);
}