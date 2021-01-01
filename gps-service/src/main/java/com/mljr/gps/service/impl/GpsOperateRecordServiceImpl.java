package com.mljr.gps.service.impl;

import com.lyqc.base.enums.GpsFlowConstant;
import com.lyqc.base.enums.GpsFlowConstant.GpsFlowStatusEnum;
import com.lyqc.base.enums.GpsFlowConstant.GpsFlowStepEnum;
import com.mljr.gps.entity.GpsOperateRecord;
import com.mljr.gps.form.GpsOperateRecordForm;
import com.mljr.gps.mapper.GpsOperateRecordMapper;
import com.mljr.gps.service.GpsOperateRecordService;
import com.mljr.util.CollectionsTools;
import com.mljr.util.TimeTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @description: GPS审批操作记录Service
 * @Date : 2018/6/4 下午5:19
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Service
public class GpsOperateRecordServiceImpl implements GpsOperateRecordService {
    @Autowired
    GpsOperateRecordMapper gpsOperateRecordMapper;

    @Override
    public List<GpsOperateRecord> queryList(GpsOperateRecordForm form) {
        return gpsOperateRecordMapper.queryList(form);
    }

    @Override
    public void createSubmitRecord(String appCode, Integer userId, String userName,Consumer<GpsOperateRecord> consumer) {
        GpsOperateRecord record = new GpsOperateRecord();
        record.setAppCode(appCode);
        record.setFlowStep(GpsFlowConstant.GpsFlowStepEnum.START.getIndex());
        record.setFlowStatus(GpsFlowConstant.GpsFlowStatusEnum.NO_APPROVAL.getIndex());
        record.setCreateUserId(userId);
        record.setCreateUserName(userName);
        record.setCreateTime(TimeTools.createNowTime());
        consumer.accept(record);
        this.gpsOperateRecordMapper.insertRecord(record);
    }

    @Override
    public void insertRecord(GpsFlowStepEnum flowStepEnum, Consumer<GpsOperateRecord> consumer) {
        GpsOperateRecord record = new GpsOperateRecord();
        record.setFlowStep(flowStepEnum.getIndex());
        switch (flowStepEnum){
            case START:
                record.setFlowStatus(GpsFlowStatusEnum.NO_APPROVAL.getIndex());
                break;
            case PULL:
                record.setFlowStatus(GpsFlowStatusEnum.PROCESSING.getIndex());
                break;
            case END:
                record.setFlowStatus(GpsFlowStatusEnum.APPROVAL_SUCCESS.getIndex());
                break;
            default:
                break;
        }
        consumer.accept(record);
        this.gpsOperateRecordMapper.insertSelective(record);
    }

    @Override
    public List<String> queryListByAppCodes(GpsOperateRecordForm gpsForm) {
        return Optional.ofNullable(gpsOperateRecordMapper.queryListByAppCodes(gpsForm)).orElse(Collections.emptyList());
    }

    @Override
    public GpsOperateRecord queryRecordByAppCode(String appCode) {
        List<GpsOperateRecord> operateRecords = gpsOperateRecordMapper.queryListByAppCode(appCode);
        if (CollectionsTools.isNotEmpty(operateRecords)){
            return operateRecords.get(0);
        }
        return null;
    }
}
