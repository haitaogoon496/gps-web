package com.mljr.gps.facade;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.ConArgTypeEnum;
import com.lyqc.base.enums.GpsFlowConstant;
import com.lyqc.base.enums.GpsHistoryConstant;
import com.lyqc.gpsprovider.enums.CarGpsConstant.GpsInstallTypeEnum;
import com.lyqc.gpsweb.re.CarGpsRe;
import com.lyqc.gpsweb.vo.GpsHistoryRecordVo;
import com.lyqc.gpsweb.vo.GpsOperateRecordVo;
import com.mljr.annotation.LogMonitor;
import com.mljr.enums.LogTitleEnum;
import com.mljr.gps.common.util.StringUtils;
import com.mljr.gps.entity.CarGps;
import com.mljr.gps.entity.GpsHistory;
import com.mljr.gps.entity.GpsOperateRecord;
import com.mljr.gps.entity.SyArgControl;
import com.mljr.gps.form.CarGpsForm;
import com.mljr.gps.form.GpsHistoryForm;
import com.mljr.gps.form.GpsOperateRecordForm;
import com.mljr.gps.service.CarGpsService;
import com.mljr.gps.service.GpsHistoryService;
import com.mljr.gps.service.GpsOperateRecordService;
import com.mljr.gps.vo.CarGpsBackVo;
import com.mljr.util.CollectionsTools;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description: 车辆GPS安装单
 * @Date : 2018/6/20 上午10:28
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Component
public class CarGpsFacade{
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.GPS_INSTALL_INFO.getName();
    @Autowired
    private SyArgControlFacade syArgControlFacade;
    @Autowired
    private CarGpsService carGpsService;
    @Autowired
    private GpsOperateRecordService gpsOperateRecordService;
    @Autowired
    private GpsHistoryService gpsHistoryService;

    /**
     * 查询GPS安装单列表
     * @param appCode
     * @return
     */
    @LogMonitor("【查询GPS安装单列表】")
    public Result<List<CarGpsRe>> queryInstallList(String appCode){
        return queryInstallList(appCode,carGpsForm -> {});
    }

    /**
     * 查询GPS安装单列表
     * @param appCode
     * @param consumer
     * @return
     */
    @LogMonitor("【查询GPS安装单列表】")
    public Result<List<CarGpsRe>> queryInstallList(String appCode,Consumer<CarGpsForm> consumer){
        List<CarGpsRe> carGpsReList = new ArrayList<>(10);
        try {
            List<SyArgControl> argControlList = syArgControlFacade.queryList(ConArgTypeEnum.GPS_DEALER);
            argControlList.forEach(dict -> {
                CarGpsRe carGpsRe = new CarGpsRe();
                carGpsRe.setTid(dict.getrId().toString());
                carGpsRe.setDealerCode(dict.getConArgValue());
                carGpsRe.setDealerName(dict.getConArgName());
                carGpsRe.setGpsInstallWay(-1);
                carGpsRe.setGpsNo("");
                carGpsRe.setGpsPosition("");
                carGpsRe.setAutoAuditCode("");
                carGpsRe.setAutoAuditResult("");
                carGpsRe.setAuditSupplement("");
                carGpsRe.setRepeatGpsNoList(Collections.emptyList());
                carGpsRe.setHistoryId(0);
                carGpsRe.setIsBack(0);
                carGpsRe.setIsCurrent(0);
                carGpsReList.add(carGpsRe);
            });

            Map<String,CarGpsRe> argControlMap = carGpsReList.stream().collect(Collectors.toMap(CarGpsRe::getDealerCode, Function.identity()));
            CarGpsForm gpsForm = CarGpsForm.builder().appCode(appCode).build();
            consumer.accept(gpsForm);
            List<CarGps>  queryList = carGpsService.queryList(gpsForm);
            if(CollectionsTools.isNotEmpty(queryList)){
                queryList.forEach(gps -> {
                    String gpsDealer = gps.getGpsDealer();
                    CarGpsRe carGpsRe = argControlMap.get(gpsDealer);
                    if(null != carGpsRe){
                        carGpsRe.setCarGpsId(gps.getTid().toString());
                        carGpsRe.setGpsInstallWay(gps.getGpsInstallWay());
                        if (gps.getGpsInstallWay() != null) {
                            GpsInstallTypeEnum installTypeEnum = GpsInstallTypeEnum.getByIndex(gps.getGpsInstallWay());
                            carGpsRe.setGpsInstallWayDesc(installTypeEnum == null ? "" : installTypeEnum.getName());
                        }
                        carGpsRe.setGpsNo(gps.getGpsNo());
                        carGpsRe.setGpsPosition(gps.getGpsPosition());
                        carGpsRe.setAutoAuditCode(gps.getAutoAuditCode());
                        carGpsRe.setAutoAuditResult(gps.getAutoAuditResult());
                        carGpsRe.setAuditSupplement(gps.getAuditSupplement());
                        carGpsRe.setManualAuditCode(gps.getManualAuditCode());
                        carGpsRe.setManualAuditResult(gps.getManualAuditResult());
                        List<String> repeatQueryList = carGpsService.queryAppCodesByGpsNoForCaCarGps(gps.getGpsNo());;
                        Set<String> repeatList = repeatQueryList.stream().filter(each -> !each.equals(appCode)).collect(Collectors.toSet());
                        carGpsRe.setRepeatGpsNoList(new ArrayList<>(repeatList));
                    }
                });
            }
            LOGGER.info("gps安装单列表-appCode:{},carGpsList:{}", appCode, JSON.toJSON(carGpsReList));
        } catch (Exception e) {
            LOGGER.error("{}查询GPS安装单列表异常,appCode={}",LOG_TITLE,appCode,e);
            return Result.failInServer(carGpsReList);
        }
        return Result.suc(carGpsReList);
    }

    /**
     * 查询GPS操作记录列表
     * @param appCode
     * @return
     */
    @LogMonitor("【查询GPS操作记录】")
    public Result<List<GpsOperateRecordVo>> queryOperateRecordList(String appCode){
        List<GpsOperateRecordVo> resultList = new ArrayList<>();
        try {
            List<GpsOperateRecord> queryList = gpsOperateRecordService.queryList(GpsOperateRecordForm.builder().appCode(appCode).build());
            queryList.forEach(record -> {
                GpsOperateRecordVo recordVo = new GpsOperateRecordVo();
                BeanUtils.copyProperties(record,recordVo);
                recordVo.setFlowStepDesc(GpsFlowConstant.GpsFlowStepEnum.getNameByIndex(recordVo.getFlowStep()));
                recordVo.setFlowStatusDesc(GpsFlowConstant.GpsFlowStatusEnum.getNameByIndex(recordVo.getFlowStatus()));
                recordVo.setCreateTimeStr(TimeTools.format4YYYYMMDDHHMISS(record.getCreateTime()));
                resultList.add(recordVo);
            });
        } catch (Exception e) {
            LOGGER.error("查询GPS操作记录异常,appCode={}",LOG_TITLE,appCode,e);
            return Result.failInServer(resultList);
        }
        return Result.suc(resultList);
    }
    /**
     * 查询GPS历史记录列表
     * @param appCode
     * @return
     */
    @LogMonitor("【查询GPS历史记录】")
    public Result<List<GpsHistoryRecordVo>> queryHistoryList(String appCode){
        List<GpsHistoryRecordVo> resultList = new ArrayList<>();
        try {
            List<GpsHistory> queryList = gpsHistoryService.queryList(GpsHistoryForm.builder().appCode(appCode).build());
            queryList.forEach(record -> {
                GpsHistoryRecordVo recordVo = new GpsHistoryRecordVo();
                BeanUtils.copyProperties(record,recordVo);
                recordVo.setApprovalStatusDesc(GpsFlowConstant.GpsFlowStatusEnum.getNameByIndex(record.getApprovalStatus()));
                recordVo.setInstallStatusDesc(GpsHistoryConstant.GpsInstallStatusEnum.getNameByIndex(record.getInstallStatus()));
                recordVo.setSubmitTimeStr(TimeTools.format4YYYYMMDDHHMISS(record.getSubmitTime()));
                recordVo.setApprovalTimeStr(Optional.ofNullable(TimeTools.format4YYYYMMDDHHMISS(record.getApprovalTime())).orElse(""));
                resultList.add(recordVo);
            });
        } catch (Exception e) {
            LOGGER.error("查询GPS历史记录异常,appCode={}",LOG_TITLE,appCode,e);
            return Result.failInServer(resultList);
        }
        return Result.suc(resultList);
    }

    public Result<List<CarGpsBackVo>> queryGPSBackList(String appCode,Integer historyId) {
        List<CarGpsBackVo> resultList = new ArrayList<>();
        try {
            List<CarGps> backList;
            if(null == historyId || historyId == 0){
                backList  = carGpsService.queryListForCurrentBack(appCode);
            }else{
                backList  = carGpsService.queryListForBackReasonView(appCode,historyId);
            }
            if (CollectionsTools.isEmpty(backList)){
                return Result.suc(CollectionsTools.emptyList());
            }
            backList.forEach(record -> {
                CarGpsBackVo carGpsBackVo = new CarGpsBackVo();
                carGpsBackVo.setGpsNo(StringUtils.killNull(record.getGpsNo()));
                carGpsBackVo.setManualAuditResult(StringUtils.killNull(record.getManualAuditResult()));
                carGpsBackVo.setAuditSupplement(StringUtils.killNull(record.getAuditSupplement()));
                resultList.add(carGpsBackVo);
            });
        }catch (Exception e){
            LOGGER.error("查询GPS退回原因异常,appCode={}",LOG_TITLE,appCode,e);
            return Result.failInServer(resultList);
        }
        return Result.suc(resultList);
    }
}
