package com.mljr.gps.facade;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.GpsFlowConstant;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.gpsprovider.enums.CarGpsConstant;
import com.mljr.annotation.LogMonitor;
import com.mljr.enums.LogTitleEnum;
import com.mljr.gps.common.consts.GpsWebConstant;
import com.mljr.gps.component.GpsComponent;
import com.mljr.gps.component.SessionUserComponent;
import com.mljr.gps.entity.*;
import com.mljr.gps.form.CarGpsForm;
import com.mljr.gps.form.GpsCompositeQueryForm;
import com.mljr.gps.form.SyRoleForm;
import com.mljr.gps.service.*;
import com.mljr.gps.vo.SimpleGpsDeviceVo;
import com.mljr.util.CollectionsTools;
import com.mljr.util.StringTools;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @description: GPS综合查询
 * @Date : 2018/6/4 下午3:05
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Component
public class GpsCompositeQueryFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.GpsCompositeQuery.getName();
    private final String NULL_SYMBOL = "/";
    @Value(value = "${system_code}")
    private String systemCode;
    @Autowired
    private GpsCompositeQueryService gpsCompositeQueryService;
    @Autowired
    private CarGpsService carGpsService;
    @Autowired
    private SessionUserComponent sessionUserComponent;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private GpsFlowService gpsFlowService;
    @Autowired
    private GpsOperateRecordService gpsOperateRecordService;
    @Autowired
    private SyRoleService syRoleService;
    @Autowired
    private GpsComponent gpsComponent;

    /**
     * 查询总条数
     * @param form
     * @return
     */
    public int queryCount(PageForm<GpsCompositeQueryForm> form){
        initFormForGeneral(form);
        return gpsCompositeQueryService.queryCount(form);
    }

    /**
     * GPS综合查询
     * @param form
     * @return
     */
    public Result<PageVO<GpsCompositeQuery>> query(PageForm<GpsCompositeQueryForm> form){
        List<GpsCompositeQuery> voList = Collections.emptyList();
        int count = 0;
        try {
            initFormForGeneral(form);
            voList = gpsCompositeQueryService.queryByPage(form);
            handleList(voList);
            count = queryCount(form);
        } catch (Exception e) {
            LOGGER.error("{}query,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }


    /**
     * GPS综合查询条件
     * @param form
     * @throws Exception
     */
    private  void initFormForGeneral(PageForm<GpsCompositeQueryForm> form){
        if(StringTools.isNotEmpty(form.getForm().getBeginTime())){
            form.getForm().setBeginTime(form.getForm().getBeginTime() + " 00:00:00");
        }
        if(StringTools.isNotEmpty(form.getForm().getEndTime())){
            form.getForm().setEndTime(form.getForm().getEndTime() + " 23:59:59");
        }
        form.getForm().setIsGps(form.getForm().getAuditResult());
        form.getForm().setSystemCode(Optional.ofNullable(systemCode).orElse("100000"));
        LOGGER.info("{}queryByPage,form={}",LOG_TITLE, JSON.toJSON(form));
    }

    /**
     * 我的GPS审核领单数据导出
     * @param form
     * @return
     */
    public Result<PageVO<GpsCompositeQuery>> mineApprExportQuery(PageForm<GpsCompositeQueryForm> form){
        List<GpsCompositeQuery> voList = Collections.emptyList();
        int count = 0;
        try {
            initFormForMine(form);
            voList = gpsCompositeQueryService.queryMineApprExportByPage(form);
            handleList(voList);
            count = queryMineApprExportCount(form);
        } catch (Exception e) {
            LOGGER.error("{}query,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }


    /**
     * 查询总条数  我的GPS审核领单数据导出
     * @param form
     * @return
     */
    public int queryMineApprExportCount(PageForm<GpsCompositeQueryForm> form) throws Exception {
        initFormForMine(form);
        return gpsCompositeQueryService.queryMineApprExportCount(form);
    }


    /**
     * 处理数据
     * @param list
     * @return
     */
    public List<GpsCompositeQuery> handleList(List<GpsCompositeQuery> list){
        if(CollectionsTools.isNotEmpty(list)){
            List<String> appCodeScope = list.stream().map(record -> record.getAppCode()).collect(toList());
            CarGpsForm gpsForm = new CarGpsForm();
            gpsForm.setAppCodeScope(appCodeScope);
            List<CarGps> gpsList = carGpsService.queryList(gpsForm);
            if(CollectionsTools.isNotEmpty(gpsList)){
                //定义一个数据结构，<订单code，订单GPS设备VO对象>
                Map<String,SimpleGpsDeviceVo> gpsDeviceMap = new HashMap<>(30);
                // 1、组装数据结构模型
                gpsList.forEach(carGps -> {
                    SimpleGpsDeviceVo deviceVo = Optional.ofNullable(gpsDeviceMap.get(carGps.getAppCode())).orElse(new SimpleGpsDeviceVo());
                    String gpsDealer = carGps.getGpsDealer();
                    Integer gpsInstallWay = carGps.getGpsInstallWay();
                    //是否无线GPS设备
                    boolean isWireless = gpsDealer.endsWith("WX");
                    if(isWireless){
                        deviceVo.setWirelessDeviceDealer(gpsDealer);
                        deviceVo.setWirelessDeviceNo(carGps.getGpsNo());
                        deviceVo.setWirelessInstallType(null != gpsInstallWay ? CarGpsConstant.GpsInstallTypeEnum.getNameByIndex(gpsInstallWay) : "");
                        deviceVo.setWirelessGpsPosition(carGps.getGpsPosition());
                    }else{
                        deviceVo.setWireDeviceDealer(gpsDealer);
                        deviceVo.setWireDeviceNo(carGps.getGpsNo());
                        deviceVo.setWireInstallType(null != gpsInstallWay ? CarGpsConstant.GpsInstallTypeEnum.getNameByIndex(gpsInstallWay) : "");
                    }
                    gpsDeviceMap.put(carGps.getAppCode(),deviceVo);
                });
                // 2、循环结果集，组装GPS设备相关信息
                list.forEach(record -> {
                    SimpleGpsDeviceVo deviceVo = gpsDeviceMap.get(record.getAppCode());
                    CarGpsConstant.AppInfoIsGpsEnum isGpsEnum = CarGpsConstant.AppInfoIsGpsEnum.getByIndex(Optional.ofNullable(record.getIsGps()).orElse(-1));
                    String auditResult = CarGpsConstant.AppInfoIsGpsEnum.INSTALLED == isGpsEnum ? "审核成功" : "审批失败";
                    record.setAuditResult(auditResult);
                    if(null != deviceVo){
                        record.setWireDeviceNo(Optional.ofNullable(deviceVo.getWireDeviceNo()).orElse(NULL_SYMBOL));
                        record.setWireInstallType(Optional.ofNullable(deviceVo.getWireInstallType()).orElse(NULL_SYMBOL));
                        record.setWirelessDeviceNo(Optional.ofNullable(deviceVo.getWirelessDeviceNo()).orElse(NULL_SYMBOL));
                        record.setWirelessInstallType(Optional.ofNullable(deviceVo.getWirelessInstallType()).orElse(NULL_SYMBOL));
                        record.setWirelessGpsPosition(Optional.ofNullable(deviceVo.getWirelessGpsPosition()).orElse(NULL_SYMBOL));
                    }
                });
            }
        }
        return list;
    }
    /**
     * 我的gps领单导出 展示页面和下载时的共同参数校验
     * @param form
     * @throws Exception
     */
    private  void initFormForMine(PageForm<GpsCompositeQueryForm> form) throws Exception {
        form.getForm().setSystemCode(Optional.ofNullable(systemCode).orElse("100000"));
        if(form.getForm() == null){
            GpsCompositeQueryForm gpsCompositeQueryForm = new GpsCompositeQueryForm();
            gpsCompositeQueryForm.setApprovalUserId(sessionUserComponent.getLoginSessionUser().getUserId());
            form.setForm(gpsCompositeQueryForm);
        }else if(form.getForm().getApprovalUserId() == null){
            form.getForm().setApprovalUserId(sessionUserComponent.getLoginSessionUser().getUserId());
        }
        form.getForm().setExcludeStatusScope(GpsWebConstant.APPROVE_MINE_EXCLUDE_SCOPE);
        if(form.getForm().getApprovalUserId() == null){
            throw  new Exception("审批人未登陆。");
        }
        LOGGER.info("{}query,form={}",LOG_TITLE, JSON.toJSON(form));
    }

    /**
     * 重置gps审批人
     */
    @LogMonitor("【重置GPS审批人】")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> resetApprovalUser(String appCode) {
        Integer loginUserId = sessionUserComponent.getSessionUserId();
        String loginUserName = sessionUserComponent.getSessionTrueName();
        String submitTime = TimeTools.format4YYYYMMDDHHMISS(TimeTools.createNowTime());
        //判断订单是否存在
        AppInfo appInfo = appInfoService.queryAppInfo(appCode);
        if (appInfo != null) {
            //判断订单状态是否符合重置条件
            GpsFlow gpsFlow = gpsFlowService.queryByAppCode(appCode);
            boolean hasFlow = null != gpsFlow;
            if (hasFlow) {
                //判断当前操作人是否有权限,不是本人的操作 判断是否有GPS审核主管权限
                if (!loginUserId.equals(gpsFlow.getApprovalUserId())) {
                    // 查询用户所有角色
                    List<SyRole> userRoleList = syRoleService.queryList(new SyRoleForm(loginUserId));
                    if (CollectionUtils.isEmpty(userRoleList)) {
                        LOGGER.warn("{}-重置GPS审批人-当前用户没有角色-userId:{}", LOG_TITLE, loginUserId);
                        return Result.fail(RemoteEnum.FAILURE, "当前登陆账号与订单GPS审核人不一致，禁止操作");
                    }
                    List<String> list = userRoleList.stream().map(item -> item.getRoleName()).collect(Collectors.toList());
                    LOGGER.info("{}-重置GPS审批人- userId:{},所包含的role:{}", LOG_TITLE, loginUserId, JSON.toJSON(list));
                    boolean roleFlag = userRoleList.stream().filter(item -> item.getRoleName().contains("GPS审核主管") || item.getRoleName().contains("系统管理员")).count() > 0 ;
                    if (!roleFlag) {
                        LOGGER.warn("{}-当前用户没有角色-userId:{}", "", loginUserId);
                        return Result.fail(RemoteEnum.FAILURE, "当前登陆账号与订单GPS审核人不一致，禁止操作");
                    }
                }

                //修改信息
                gpsComponent.updateAppInfo(caAppInfo -> {
                    caAppInfo.setAppCode(appCode);
                    caAppInfo.setIsGps(String.valueOf(CarGpsConstant.AppInfoIsGpsEnum.UNINSTALL.getIndex()));
                });

                gpsFlow.setApprovalProps(null, null, null);
                gpsFlow.setFlowStep(GpsFlowConstant.GpsFlowStepEnum.START.getIndex());
                gpsFlow.setFlowStatus(GpsFlowConstant.GpsFlowStatusEnum.NO_APPROVAL.getIndex());
                gpsFlowService.updateRecordByAppCode(gpsFlow);

                this.gpsOperateRecordService.insertRecord(GpsFlowConstant.GpsFlowStepEnum.START, (record) -> {
                    record.setSubmitProps(loginUserId, loginUserName, submitTime);
                    record.setAppCode(appCode);
                    record.setRemark("重置GPS审批人");
                });
            }else {
                return Result.fail(RemoteEnum.FAILURE, "该订单无GPS审批记录，禁止操作");
            }
        }else {
                return Result.fail(RemoteEnum.FAILURE, "订单不存在");
            }
        return Result.suc();
        }
}
