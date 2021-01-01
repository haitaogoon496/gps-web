package com.mljr.gps.service.impl;

import com.lyqc.base.enums.GpsFlowConstant;
import com.lyqc.gpsweb.vo.GpsApplyFormVo;
import com.mljr.gps.entity.GpsAuditor;
import com.mljr.gps.entity.GpsFlow;
import com.mljr.gps.mapper.GpsFlowMapper;
import com.mljr.gps.service.GpsFlowService;
import com.mljr.util.TimeTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @description: GPS审批流程 Service
 * @Date : 2018/6/4 下午5:14
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Service
public class GpsFlowServiceImpl implements GpsFlowService {
    @Autowired
    GpsFlowMapper gpsFlowMapper;

    @Override
    public int getHasPullCountByApproveUserId(Integer userId) {
        return gpsFlowMapper.getHasPullCountByApproveUserId(userId);
    }

    @Override
    public GpsFlow queryByAppCode(String appCode) {
        return gpsFlowMapper.queryByAppCode(appCode);
    }

    @Override
    public int insertRecord(GpsFlow gpsFlow) {
        return gpsFlowMapper.insert(gpsFlow);
    }

    @Override
    public void updateRecord(GpsFlow gpsFlow) {
        gpsFlowMapper.updateRecord(gpsFlow);
    }

    @Override
    public GpsApplyFormVo queryApplyForm(String appCode) {
        return gpsFlowMapper.queryApplyForm(appCode);
    }

    @Override
    public void start(String appCode, Consumer<GpsFlow> consumer) {
        GpsFlow caGpsFlow = gpsFlowMapper.queryByAppCode(appCode);
        //重新发起申请|第一次发起申请
        caGpsFlow = Optional.ofNullable(caGpsFlow).orElse(new GpsFlow());
        caGpsFlow.setAppCode(appCode);
        caGpsFlow.setFlowStep(GpsFlowConstant.GpsFlowStepEnum.START.getIndex());
        caGpsFlow.setFlowStatus(GpsFlowConstant.GpsFlowStatusEnum.NO_APPROVAL.getIndex());
        consumer.accept(caGpsFlow);
        gpsFlowMapper.updateRecord(caGpsFlow);
    }

    @Override
    public void approve(String appCode, Consumer<GpsFlow> consumer) {
        GpsFlow caGpsFlow = gpsFlowMapper.queryByAppCode(appCode);
        if(null != caGpsFlow){
            consumer.accept(caGpsFlow);
            gpsFlowMapper.updateRecord(caGpsFlow);
        }else{//兼容线上流程中，处于准备待审批操作的单据，统一按照此逻辑处理
            caGpsFlow = new GpsFlow();
            String submitTime = TimeTools.format4YYYYMMDDHHMISS(TimeTools.createNowTime());
            caGpsFlow.setAppCode(appCode);
            caGpsFlow.setSubmitProps(-1,"APP客户端",submitTime);
            caGpsFlow.setApprovalIdea("线上流程待审批单据特殊处理");
            consumer.accept(caGpsFlow);
            gpsFlowMapper.insertSelective(caGpsFlow);
        }
    }

    @Override
    public void start(String appCode) {
        GpsFlow gpsFlow = gpsFlowMapper.queryByAppCode(appCode);
        if(null == gpsFlow){
            return;
        }
        gpsFlow.setAppCode(appCode);
        gpsFlow.setFlowStep(GpsFlowConstant.GpsFlowStepEnum.START.getIndex());
        gpsFlow.setFlowStatus(GpsFlowConstant.GpsFlowStatusEnum.NO_APPROVAL.getIndex());
        gpsFlowMapper.updateRecord(gpsFlow);
    }

    @Override
    public List<GpsAuditor> getMinGpsApprUsers(List<Integer> list) {
        return gpsFlowMapper.getMinGpsApprUsers(list);
    }

    @Override
    public void updateRecordByAppCode(GpsFlow gpsFlow) {
        gpsFlowMapper.updateRecordByAppCode(gpsFlow);
    }
}
