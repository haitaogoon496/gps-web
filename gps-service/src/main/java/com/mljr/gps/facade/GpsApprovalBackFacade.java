package com.mljr.gps.facade;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.AppInfoStatusEnum;
import com.lyqc.base.enums.GpsHistoryConstant;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.gpsprovider.enums.CarGpsConstant;
import com.lyqc.gpsweb.enums.GpsApprovalBackEnum;
import com.mljr.annotation.LogMonitor;
import com.mljr.enums.LogTitleEnum;
import com.mljr.gps.component.GpsComponent;
import com.mljr.gps.entity.AppInfo;
import com.mljr.gps.entity.GpsHistory;
import com.mljr.gps.form.GpsApprovalBackForm;
import com.mljr.gps.service.AppInfoService;
import com.mljr.gps.service.GpsApproveService;
import com.mljr.gps.service.GpsHistoryService;
import com.mljr.gps.service.GpsOperateRecordService;
import com.mljr.util.CollectionsTools;
import com.mljr.util.StringTools;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @description: GPS审批退回
 * @author zhaoxin
 * @date 2018/7/6 下午3:41
 **/
@Component
public class GpsApprovalBackFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.GPS_APPROVAL_BACK.getName();

    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private GpsOperateRecordService gpsOperateRecordService;
    @Autowired
    private GpsApproveService gpsApproveService;
    @Autowired
    private GpsComponent gpsComponent;
    @Autowired
    private GpsHistoryService gpsHistoryService;

    @LogMonitor("GPS审批退回操作")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> updateGpsInfo(String appCode) {
        if (StringTools.isEmpty(appCode)){
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM);
        }
        AppInfo appInfo = appInfoService.queryAppInfo(appCode);
        if(null == appInfo){
            LOGGER.info("updateGpsInfo appCode={}信息不存在",appCode);
            return Result.fail(RemoteEnum.FAILURE,"申请单不存在");
        }
        //判断订单状态，不是终审审批通过的订单不进行GPS审批信息修改操作
        AppInfoStatusEnum appInfoStatusEnum = AppInfoStatusEnum.getByIndex(Integer.parseInt(appInfo.getStatus()));
        final boolean predicate =  AppInfoStatusEnum._17 == appInfoStatusEnum;
        if (!predicate){
            LOGGER.info("GPS审核退回, appCode={},status={} 不支持修改GPS安装状态", appCode, appInfo.getStatus());
            return Result.suc("当前订单状态不允许修改GPS审批信息");
        }
        //判断gps是否安装
        if(null != appInfo.getIsGps()){
            CarGpsConstant.AppInfoIsGpsEnum isGpsEnum = CarGpsConstant.AppInfoIsGpsEnum.getByIndex(Integer.valueOf(appInfo.getIsGps()));
            if(isGpsEnum != CarGpsConstant.AppInfoIsGpsEnum.UNINSTALL){
                //修改信息
                gpsComponent.updateAppInfo(caAppInfo -> {
                    caAppInfo.setAppCode(appCode);
                    caAppInfo.setIsGps(String.valueOf(CarGpsConstant.AppInfoIsGpsEnum.UNINSTALL.getIndex()));
                });
            }
        }
        this.gpsApproveService.backApprove(appInfo.getAppCode(),(x)-> predicate);
        return Result.suc("GPS审核退回操作成功");
    }

    @LogMonitor("GPS审批退回添加操作记录")
    public Result<String> insertOperateRecord(GpsApprovalBackForm form) {
        if (form == null){
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM);
        }
        String approvalName = GpsApprovalBackEnum.getNameByIndex(form.getApprovalType());
        try {
            Integer submitUserId = Optional.ofNullable(form.getUserId()).orElse(-1);
            String submitUserName = Optional.ofNullable(form.getUserName()).orElse("系统");
            gpsOperateRecordService.createSubmitRecord(form.getAppCode(), submitUserId, submitUserName,(record) -> record.setRemark(approvalName));
            LOGGER.info("{}添加GPS审批操作记录成功,appCode={},submitDTO={}",approvalName,form.getAppCode(), JSON.toJSON(form));
            GpsHistory gpsHistory = gpsHistoryService.queryRecordNoApprove(form.getAppCode());
            LOGGER.info("{}查看GPS历史记录表中是否有未审批记录,appCode={},gpsHistory={}",approvalName,form.getAppCode(), JSON.toJSON(gpsHistory));
            if (gpsHistory != null){
                LOGGER.info("{}GPS历史记录表中有未审批记录,appCode={},gpsHistory={}",approvalName,form.getAppCode(), JSON.toJSON(gpsHistory));
                gpsHistory.setApprovalStatus(GpsHistoryConstant.GpsApprovalStatusEnum.REJECTED.getIndex());
                gpsHistory.setApprovalIdea(approvalName);
                gpsHistory.setApprovalProps(1,"系统",TimeTools.format4YYYYMMDDHHMISS(TimeTools.createNowTime()));
                gpsHistory.setInstallStatus(GpsHistoryConstant.GpsInstallStatusEnum.INSTALLED.getIndex());
                gpsHistoryService.updateCarGpsById(gpsHistory);
            }
        }catch (Exception e){
            LOGGER.error("{}添加GPS审批操作记录异常,appCode={},submitDTO={}",approvalName,form.getAppCode(), JSON.toJSON(form),e);
            return Result.fail(RemoteEnum.ERROR_IN_BUSINESS,"添加GPS审批操作记录失败");
        }
        return Result.suc();
    }

    @LogMonitor("直接审批经理跑批处理")
    public Result<String> bactchUpdateGpsApprovalInfo(GpsApprovalBackForm form) {
        if (form == null){
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM);
        }
        List<String> appCodes = Optional.ofNullable(form.getAppCodeList()).orElse(CollectionsTools.emptyList());
        appCodes.forEach(appCode -> updateGpsInfo(appCode));
        return  Result.suc();
    }
}


