package com.mljr.gps.service;

import com.lyqc.base.enums.GpsFlowConstant.GpsFlowStepEnum;
import com.mljr.gps.entity.GpsOperateRecord;
import com.mljr.gps.form.GpsOperateRecordForm;

import java.util.List;
import java.util.function.Consumer;

/**
 * @description: GPS审批操作记录Service
 * @Date : 2018/6/4 下午5:17
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface GpsOperateRecordService {
    /**
     * 查询操作记录列表
     * @param form
     * @return
     */
    List<GpsOperateRecord> queryList(GpsOperateRecordForm form);
    /**
     * 创建记录，类型为：提交
     * @param appCode
     * @param consumer
     */
    void createSubmitRecord(String appCode,Integer userId, String userName, Consumer<GpsOperateRecord> consumer);

    /**
     * 根据流程步骤创建操作记录，目前该接口支持 开始、领单、结束 步骤，具体 审批步骤需要调用方处理，在consumer接口中回调设置
     * @param flowStepEnum
     * @param consumer
     */
    void insertRecord(GpsFlowStepEnum flowStepEnum, Consumer<GpsOperateRecord> consumer);

    /**
     * 查询订单操作记录
     * @param gpsForm
     */
    List<String> queryListByAppCodes(GpsOperateRecordForm gpsForm);

    /**
     * 查询订单最近的操作记录是否是退回复审操作
     * @param appCode
     */
    GpsOperateRecord queryRecordByAppCode(String appCode);
}
