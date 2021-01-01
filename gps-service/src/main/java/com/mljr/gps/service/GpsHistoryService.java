package com.mljr.gps.service;

import com.mljr.gps.entity.GpsHistory;
import com.mljr.gps.form.GpsHistoryForm;

import java.util.List;
import java.util.function.Consumer;

/**
 * @description: GPS审批历史记录Service
 * @Date : 2018/6/4 下午5:16
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface GpsHistoryService{
    /**
     * 查询未审批的历史记录
     * @param appCode
     * @return
     */
    GpsHistory queryRecordNoApprove(String appCode);
    /**
     * 申请
     * @param appCode
     * @param consumer
     * @return
     */
    Integer apply(String appCode, Consumer<GpsHistory> consumer);

    /**
     * 审批
     * @param appCode
     * @param consumer
     * @return
     */
    Integer approve(String appCode, Consumer<GpsHistory> consumer);
    /**
     *查询操作记录列表
     * @param form
     * @return
     */
    List<GpsHistory> queryList(GpsHistoryForm form);
    /**
     * 申请
     * @param appCode
     */
    void apply(String appCode);

    /**
     * 查询
     * @param historyId
     * @return
     */
    GpsHistory getById(Integer historyId);

    /**
     * 主键更新
     * @param gpsHistory
     * @return
     */
    int updateCarGpsById(GpsHistory gpsHistory);
}
