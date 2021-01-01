package com.mljr.gps.service.impl;

import com.lyqc.base.enums.GpsHistoryConstant;
import com.lyqc.gpsprovider.enums.CarGpsConstant;
import com.mljr.gps.entity.GpsHistory;
import com.mljr.gps.form.GpsHistoryForm;
import com.mljr.gps.mapper.GpsHistoryMapper;
import com.mljr.gps.service.GpsHistoryService;
import com.mljr.util.CollectionsTools;
import com.mljr.util.TimeTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

/**
 * @description: GPS审批历史记录Service
 * @Date : 2018/6/4 下午5:17
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Service
public class GpsHistoryServiceImpl implements GpsHistoryService {
    @Autowired
    GpsHistoryMapper gpsHistoryMapper;

    @Override
    public GpsHistory queryRecordNoApprove(String appCode) {
        return gpsHistoryMapper.queryRecordNoApprove(appCode);
    }

    @Override
    public Integer apply(String appCode, Consumer<GpsHistory> consumer) {
        boolean hasHistory = true;
        Integer historyId = 0;
        GpsHistory gpsHistory = queryRecordNoApprove(appCode);
        // APP上传的时候，已经会生成历史记录，因此 【车贷申请管理》未上传GPS安装单】上传GPS安装单的时候，无需再生成历史记录。
        if(null == gpsHistory){
            hasHistory = false;
            gpsHistory = new GpsHistory();
            gpsHistory.setAppCode(appCode);
            gpsHistory.setInstallStatus(GpsHistoryConstant.GpsInstallStatusEnum.INSTALLED.getIndex());
            gpsHistory.setApprovalStatus(GpsHistoryConstant.GpsApprovalStatusEnum.UN_APPROVE.getIndex());
            consumer.accept(gpsHistory);
        }else{
            historyId = gpsHistory.getId();
        }
        if(hasHistory){
            gpsHistoryMapper.updateByPrimaryKeySelective(gpsHistory);
        }else{
            historyId = gpsHistoryMapper.insert(gpsHistory);
        }
        return historyId;
    }

    @Override
    public Integer approve(String appCode, Consumer<GpsHistory> consumer) {
        GpsHistory gpsHistory = queryRecordNoApprove(appCode);
        Integer historyId = 0;
        if(null != gpsHistory){
            historyId = gpsHistory.getId();
            consumer.accept(gpsHistory);
            gpsHistoryMapper.updateByPrimaryKeySelective(gpsHistory);
        }else{//兼容线上流程中，处于准备待审批操作的单据，统一按照此逻辑处理
            gpsHistory = new GpsHistory();
            gpsHistory.setAppCode(appCode);
            gpsHistory.setSubmitUserId(-1);
            gpsHistory.setSubmitUserName("APP客户端");
            gpsHistory.setSubmitTime(TimeTools.createNowTime());
            gpsHistory.setApprovalIdea("线上流程待审批单据特殊处理");
            consumer.accept(gpsHistory);
            gpsHistoryMapper.insert(gpsHistory);
            historyId = gpsHistory.getId();
        }
        return historyId;
    }

    @Override
    public List<GpsHistory> queryList(GpsHistoryForm form) {
        return gpsHistoryMapper.queryList(form);
    }

    @Override
    public void apply(String appCode) {
        GpsHistoryForm gpsHistoryForm = GpsHistoryForm.builder().appCode(appCode)
                .approvalStatus(GpsHistoryConstant.GpsApprovalStatusEnum.UN_APPROVE.getIndex()).build();
        List<GpsHistory> gpsHistoryList = gpsHistoryMapper.queryList(gpsHistoryForm);
        GpsHistory gpsHistory = null;
        if (CollectionsTools.isNotEmpty(gpsHistoryList)){
        // APP上传的时候，已经会生成历史记录，已有记录更新安装状态及审批状态。
            gpsHistory = gpsHistoryList.get(0);
            gpsHistory.setInstallStatus(CarGpsConstant.AppInfoIsGpsEnum.INSTALLED.getIndex());
            gpsHistory.setApprovalStatus(GpsHistoryConstant.GpsApprovalStatusEnum.UN_APPROVE.getIndex());
        }
        if(gpsHistory != null){
            this.gpsHistoryMapper.updateByPrimaryKeySelective(gpsHistory);
        }
    }

    @Override
    public GpsHistory getById(Integer historyId) {
        return gpsHistoryMapper.selectByPrimaryKey(historyId);
    }

    @Override
    public int updateCarGpsById(GpsHistory gpsHistory) {
        return gpsHistoryMapper.updateByPrimaryKeySelective(gpsHistory);
    }
}
