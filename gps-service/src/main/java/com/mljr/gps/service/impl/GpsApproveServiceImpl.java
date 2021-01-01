package com.mljr.gps.service.impl;

import com.lyqc.base.enums.GpsFlowConstant;
import com.lyqc.base.enums.GpsHistoryConstant;
import com.lyqc.base.tuple.Tuple;
import com.lyqc.gpsprovider.enums.CarGpsConstant.GpsApproveStatusEnum;
import com.lyqc.gpsweb.enums.GpsApprovalBackEnum;
import com.mljr.gps.component.PushComponent;
import com.mljr.gps.entity.AppInfo;
import com.mljr.gps.entity.GpsOperateRecord;
import com.mljr.gps.entity.SyUser;
import com.mljr.gps.form.CarGpsForm;
import com.mljr.gps.service.AppInfoService;
import com.mljr.gps.service.CarGpsService;
import com.mljr.gps.service.GpsApproveService;
import com.mljr.gps.service.GpsFlowService;
import com.mljr.gps.service.GpsHistoryService;
import com.mljr.gps.service.GpsOperateRecordService;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.function.Predicate;

/**
 * @description:
 * @Date : 下午5:57 2017/11/28
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class GpsApproveServiceImpl implements GpsApproveService {
    protected static final Logger LOGGER = LoggerFactory.getLogger(GpsApproveServiceImpl.class);
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private GpsFlowService gpsFlowService;
    @Autowired
    private CarGpsService carGpsService;
    @Autowired
    private GpsHistoryService gpsHistoryService;
    @Autowired
    private GpsOperateRecordService gpsOperateRecordService;
    @Autowired
    private PushComponent pushComponent;

    @Override
    public Tuple<Integer> insertRecord(String appCode, SyUser sessionUser) {
        Integer submitUserId = sessionUser.getUserId();
        String submitUserName = sessionUser.getTrueName();
        String submitTime = TimeTools.format4YYYYMMDDHHMISS(TimeTools.createNowTime());

        this.gpsFlowService.start(appCode,(flow) -> {
            flow.setSubmitProps(submitUserId,submitUserName,submitTime);
        });

        Integer historyId = gpsHistoryService.apply(appCode,(history) -> history.setSubmitProps(submitUserId,submitUserName,submitTime));

        gpsOperateRecordService.insertRecord(GpsFlowConstant.GpsFlowStepEnum.START,(record)->{
            record.setSubmitProps(submitUserId,submitUserName,submitTime);
            record.setAppCode(appCode);
            record.setRemark(GpsFlowConstant.OperateRecordRemarkEnum.GPS_INSTALL_ATTACH_UPDATE_FOR_APP.getName());
        });

        return Tuple.newInstance(historyId);
    }

    @Override
    public Tuple<Integer> updateRecord(String appCode, SyUser sessionUser, boolean isApproveSuccess) {
        Integer approvalUserId = sessionUser.getUserId();
        String approvalUserName = StringUtils.isEmpty(sessionUser.getTrueName()) ? sessionUser.getUserName() : sessionUser.getTrueName();
        String approvalTime = TimeTools.format4YYYYMMDDHHMISS(TimeTools.createNowTime());
        gpsFlowService.approve(appCode,(flow) -> {
            flow.setApprovalProps(approvalUserId,approvalUserName,approvalTime);
            //审核成功
            if(isApproveSuccess){
                flow.setFlowStep(GpsFlowConstant.GpsFlowStepEnum.END.getIndex());
                flow.setFlowStatus(GpsFlowConstant.GpsFlowStatusEnum.APPROVAL_SUCCESS.getIndex());
            }else{
                flow.setFlowStep(GpsFlowConstant.GpsFlowStepEnum.APPROVAL.getIndex());
                flow.setFlowStatus(GpsFlowConstant.GpsFlowStatusEnum.REJECTED.getIndex());
            }
        });

        Integer historyId = gpsHistoryService.approve(appCode,(history) -> {
            history.setApprovalProps(approvalUserId,approvalUserName,approvalTime);
            history.setInstallStatus(GpsHistoryConstant.GpsInstallStatusEnum.INSTALLED.getIndex());
            //审核成功
            if(isApproveSuccess){
                history.setApprovalStatus(GpsHistoryConstant.GpsApprovalStatusEnum.APPROVED.getIndex());
            }else{
                history.setApprovalStatus(GpsHistoryConstant.GpsApprovalStatusEnum.REJECTED.getIndex());
            }
        });

        gpsOperateRecordService.insertRecord(GpsFlowConstant.GpsFlowStepEnum.APPROVAL,(record)->{
            record.setSubmitProps(approvalUserId,approvalUserName,approvalTime);
            record.setAppCode(appCode);
            //审核成功
            if(isApproveSuccess){
                record.setFlowStep(GpsFlowConstant.GpsFlowStepEnum.END.getIndex());
                record.setFlowStatus(GpsFlowConstant.GpsFlowStatusEnum.APPROVAL_SUCCESS.getIndex());
                record.setRemark(GpsFlowConstant.OperateRecordRemarkEnum.GPS_APPROVE_SUCCESS.getName());
            }else{
                record.setFlowStatus(GpsFlowConstant.GpsFlowStatusEnum.REJECTED.getIndex());
                record.setRemark(GpsFlowConstant.OperateRecordRemarkEnum.GPS_APPROVE_REJECT.getName());
            }
        });
        AppInfo appInfo = appInfoService.queryAppInfo(appCode);
        // 推送Push
        pushComponent.pushMessage(appCode,pushDTO -> {
            String statusDesc = isApproveSuccess ? "已审批通过" : "已驳回";
            pushDTO.setStaffId(Integer.valueOf(appInfo.getBelongSale()));
            pushDTO.setTitle("GPS审批管理");
            pushDTO.setContent(MessageFormat.format("亲！订单号：{0}，GPS审批主管【{1}】，请查收。",appCode,statusDesc));
        });
        return Tuple.newInstance(historyId);
    }

    @Override
    public void backApprove(String appCode, Predicate<String> predicate) {
        if(predicate.test(appCode)){
            this.gpsFlowService.start(appCode);
            //把gps的人工审核状态如果为1置为2,否则gps重新编辑后无法保存 //
            CarGpsForm gpsForm = new CarGpsForm();
            gpsForm.setAppCode(appCode);
            gpsForm.setStatus(GpsApproveStatusEnum.SUCCESS.getIndex());
            gpsForm.setNewStatus(GpsApproveStatusEnum.PROCESS.getIndex());
            carGpsService.updateGpsAuditStatus(gpsForm);
        }
    }

    @Override
    public String canOperateGps(String appCode) {
        GpsOperateRecord caGpsOperateRecord = gpsOperateRecordService.queryRecordByAppCode(appCode);
        String message = "";
        if (caGpsOperateRecord != null){
            for(GpsApprovalBackEnum e : GpsApprovalBackEnum.values()){
                if(e.getName().equals(caGpsOperateRecord.getRemark())){
                    message = caGpsOperateRecord.getRemark();
                    return "申请单号:" + appCode + message +"，禁止操作";
                }
            }
        }
        return message;
    }
}
