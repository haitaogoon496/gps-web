package com.mljr.gps.facade;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.AnnexTypeEnum;
import com.lyqc.base.enums.GpsFlowConstant;
import com.lyqc.base.enums.GpsHistoryConstant;
import com.lyqc.base.tuple.Tuple;
import com.lyqc.gpsprovider.enums.CarGpsConstant.*;
import com.lyqc.gpsprovider.enums.GpsDeviceAgencyType;
import com.lyqc.gpsprovider.re.GpsDeviceInfoRe;
import com.lyqc.gpsweb.dto.CarGpsDTO;
import com.lyqc.gpsweb.dto.GpsApproveDTO;
import com.lyqc.gpsweb.dto.GpsFlowDTO;
import com.lyqc.gpsweb.vo.GpsApplyFormVo;
import com.mljr.annotation.LogMonitor;
import com.mljr.annotation.OvalValidator;
import com.mljr.carrier.CarrierComponent;
import com.mljr.component.GpsDeviceInfoComponent;
import com.mljr.enums.LogTitleEnum;
import com.mljr.gps.common.consts.HeilCode;
import com.mljr.gps.common.enums.YesOrNoEnum;
import com.mljr.gps.common.exception.AlertException;
import com.mljr.gps.common.exception.BizException;
import com.mljr.gps.common.util.ValidateUtils;
import com.mljr.gps.component.SessionUserComponent;
import com.mljr.gps.entity.*;
import com.mljr.gps.form.CarGpsForm;
import com.mljr.gps.form.SyRoleForm;
import com.mljr.gps.service.*;
import com.mljr.gps.vo.GpsFlowVo;
import com.mljr.gps.vo.GpsInstallDetailVo;
import com.mljr.gps.voconvertor.GpsContractVoConvertor;
import com.mljr.gps.voconvertor.GpsFlowVoConvertor;
import com.mljr.redis.service.RedisUtil;
import com.mljr.util.PropertiesReader;
import com.mljr.util.StringTools;
import com.mljr.util.TimeTools;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @description: GPS审批退回
 * @author zhaoxin
 * @date 2018/7/6 下午3:41
 **/
@Component
public class GpsApproveFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.GPS_APPROVE.getName();
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private CarGpsService carGpsService;
    @Autowired
    private CarInfoService carInfoService;
    @Autowired
    private GpsFlowService gpsFlowService;
    @Autowired
    private GpsApproveService gpsApproveService;
    @Autowired
    private GpsDeviceInfoComponent gpsDeviceInfoComponent;
    @Autowired
    private SessionUserComponent sessionUserComponent;
    @Autowired
    private CarGpsFacade carGpsFacade;
    @Autowired
    private GpsContractService gpsContractService;
    @Autowired
    private SyRoleService syRoleService;
    @Autowired
    private GpsHistoryService gpsHistoryService;
    @Autowired
    private GpsOperateRecordService gpsOperateRecordService;
    @Autowired
    private CarrierComponent carrierComponent;
    @Autowired
    private AppAnnexService appAnnexService;
    @Autowired
    AsyncTaskExecutor asyncTaskExecutor;
    @Autowired
    private RedisUtil redisUtil;
    private final Integer MAX_FILE_SIZE = 10485760;
    private static final String SYSTEM_CODE = PropertiesReader.getProperties("config").getProperty("system_code");

    private List<String> fileTypes = Arrays.asList("pdf");
    /**
     * 查询GPS申请单
     * @param appCode
     * @return
     */
    @LogMonitor("查询GPS申请单")
    public Result<GpsApplyFormVo> queryApplyForm(String appCode){
        GpsApplyFormVo gpsApplyFormVo = null;
        try {
            gpsApplyFormVo = gpsFlowService.queryApplyForm(appCode);
        } catch (Exception e) {
            LOGGER.error("GPS审批查询GPS申请单异常,appCode={}",LOG_TITLE,appCode,e);
            return Result.failInServer(gpsApplyFormVo);
        }
        return Result.suc(gpsApplyFormVo);
    }

    /**
     * 保存GPS设备信息
     * 业务说明：车贷申请管理==》待上传GPS安装单
     * @param dto
     * @return
     */
    @LogMonitor("保存GPS设备信息")
    @OvalValidator("保存GPS设备信息")
    public Result<String> apply(GpsApproveDTO dto) {
        String appCode = dto.getAppCode();
        try {
            //1、逻辑删除GPS安装信息
            carGpsService.deleteByLogical(appCode);
            SyUser syUser = sessionUserComponent.getSessionUser();
            //2、处理GPS审批相关
            Tuple<Integer> tuple = gpsApproveService.insertRecord(appCode,syUser);
            Integer historyId = tuple.getFirst();
            //3、获取GPS自动认证状态，并新增GPS安装信息
            saveCarGpsInfos(historyId,dto,getAutoApproveResult(dto).get());
        } catch (Exception e) {
            LOGGER.error("{}异常,appCode={}",LOG_TITLE,appCode,e);
            return Result.failInServer("G保存失败");
        }
        return Result.suc("保存成功");
    }

    @LogMonitor("gps安装详情数据")
    public Result<GpsInstallDetailVo> getDetail(String appCode,Integer historyId) {
        LOGGER.info("gps安装单详情-appCode:{};historyId:{}", appCode, historyId);
        GpsInstallDetailVo detailVo = new GpsInstallDetailVo();
        detailVo.setGpsApplyFormVo(queryApplyForm(appCode).getData());
        detailVo.setCarGpsReList(carGpsFacade.queryInstallList(appCode,gpsForm -> gpsForm.setHistoryId(historyId)).getData());
        GpsFlow gpsFlow = gpsFlowService.queryByAppCode(appCode);
        if (gpsFlow != null) {
            GpsFlowVo gpsFlowVo = new GpsFlowVoConvertor().convert(gpsFlow);
            detailVo.setGpsFlowVo(gpsFlowVo);
        }
        GpsContract gpsContract = gpsContractService.queryRecordByAppCode(appCode);
        if (gpsContract != null) {
            detailVo.setGpsContractVo(new GpsContractVoConvertor().convert(gpsContract));
        }
        detailVo.setCarGpsBackList(carGpsFacade.queryGPSBackList(appCode,historyId).getData());
        return Result.suc(detailVo);
    }


    /**
     * 文件上传
     * @param file
     * @param appCode
     * @param annexType
     * @param annexName
     * @return
     */
    public Result<String> uploadGpsFile(MultipartFile file, String appCode, AnnexTypeEnum annexType, String annexName) {
        ValidateUtils.notEmpty(appCode, HeilCode.E_500, "appCode不能为空");
        AppInfo appInfo = appInfoService.queryAppInfo(appCode);
        ValidateUtils.notNull(appInfo, HeilCode.E_500, "appCode有误，不存在当前记录，请核实后操作");
        ValidateUtils.notNull(annexType, HeilCode.E_500, "文件类型不能为空");
        ValidateUtils.notNull(file, HeilCode.E_500, "请选择上传的文件");
        ValidateUtils.isFalse(file.isEmpty(), HeilCode.E_500, "请选择上传的文件");
        LOGGER.info("上传gps附件信息-appCode:{},annexType:{},annexName:{},fileName:{}", appCode, annexType.getName(), annexName, file.getOriginalFilename());
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        ValidateUtils.notEmpty(suffix, HeilCode.E_500, "请填写文件后缀");
        ValidateUtils.isFalse(fileTypes.contains(suffix.toLowerCase()), HeilCode.E_500, "支持【pdf】格式附件上传");

        try {
            LOGGER.info("文件上传-appCode:{}-文件大小:{}{}",appCode,file.getBytes().length/1024/1024,"MB");
            ValidateUtils.isFalse(file.getBytes().length > MAX_FILE_SIZE, HeilCode.E_500, "文件大小不能超过10M");
            String fileKey = carrierComponent.uploadStream(file.getBytes(), file.getOriginalFilename());
            if (StringUtils.isEmpty(fileKey)) {
                LOGGER.error("上传gps附件失败-appCode：{},调用影像中心失败，返回key值为空");
                return Result.fail(HeilCode.E_500, "上传文件失败,请稍后重试");
            }

            AppAnnex appAnnex = new AppAnnex();
            appAnnex.setAnnexAddress(fileKey);
            appAnnex.setAnnexName(annexName);
            appAnnex.setAppCode(appCode);
            appAnnex.setCreateTime(TimeTools.createNowTime());
            SyUser syUser = sessionUserComponent.getSessionUser();
            appAnnex.setUserId(syUser.getUserId());
            appAnnex.setUserName(syUser.getTrueName());
            appAnnex.setStatus(1);
            appAnnex.setAnnexType(annexType.getIndex());
            appAnnex.setImgKey(fileKey);
            appAnnexService.insert(appAnnex);
        } catch (AlertException e) {
            throw new AlertException(HeilCode.E_500, e.getMessage());
        } catch (Exception e) {
            throw new AlertException(HeilCode.E_500, "文件上传失败，请稍后重试");
        }
        return Result.suc("上传成功");
    }

    @LogMonitor("gps安装详情数据")
    public Result<GpsInstallDetailVo> getDetail(String appCode) {
        return getDetail(appCode,null);
    }

    public Result<String> doApprove(GpsApproveDTO dto){
        CompletableFuture<Result<List<GpsDeviceInfoRe>>> completableFuture = getAutoApproveResult(dto);
        return ((GpsApproveFacade)AopContext.currentProxy()).approve(dto,completableFuture);
    }

    private CompletableFuture<Result<List<GpsDeviceInfoRe>>> getAutoApproveResult(GpsApproveDTO dto){
        List<CarGpsDTO> gpsList = dto.getGpsList();
        Tuple<GpsDeviceAgencyType> gpsDeviceAgencyTypeTuple = Tuple.newInstance(GpsDeviceAgencyType.segGPS);
        gpsList.stream().map(each -> each.getDealerCode()).findAny().ifPresent(each -> gpsDeviceAgencyTypeTuple.setFirst(GpsDeviceAgencyType.getByGpsDealer(each)));
        List<String> gpsDeviceList = gpsList.stream().filter(gps->!StringUtils.isEmpty(gps.getGpsNo())).map(gps -> gps.getGpsNo()).collect(Collectors.toList());
        return CompletableFuture.supplyAsync(()-> gpsDeviceInfoComponent.getGpsInfos(gpsDeviceList,gpsDeviceAgencyTypeTuple.getFirst()),asyncTaskExecutor);
    }

    /**
     * GPS人工审核
     * @param dto
     * @return
     */
    @LogMonitor("GPS人工审批")
    @OvalValidator("GPS人工审批")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<String> approve(GpsApproveDTO dto,CompletableFuture<Result<List<GpsDeviceInfoRe>>> completableFuture) {
        String appCode = dto.getAppCode();
        try {
            AppInfo appInfo = appInfoService.queryAppInfo(appCode);
            if(null == appInfo){
                return Result.fail(HeilCode.E_400, "申请单号不存在");
            }
            //判断订单是否被退回至复审审批
            String message = gpsApproveService.canOperateGps(appCode);
            if (StringTools.isNotEmpty(message)){
                return Result.fail(HeilCode.E_400, message);
            }
            String lockKey = getLockKey("vinCodeLock") + ":" + appCode;
            boolean bool = redisUtil.setNx(lockKey, "lock", 60);
            if(!bool){
                return Result.fail(HeilCode.E_500, "车架号已修改，本次审核无效");
            }

            GpsFlowDTO gpsFlowDTO = dto.getGpsFlowDTO();
            final String flowRemark = gpsFlowDTO.getFlowRemark();
            //是否审批成功
            final boolean isApproveSuccess = dto.getGpsFlowDTO().isApproveSuccess();
            //1、逻辑删除GPS安装信息
            carGpsService.deleteByLogical(appCode);
            //2、相关操作
            carGpsService.updateIsCurrent0(appCode);
            //更新审批备注
            GpsFlow gpsFlow = new GpsFlow();
            gpsFlow.setAppCode(appCode);
            gpsFlow.setRemark(Optional.ofNullable(flowRemark).orElse(""));
            Integer violationSituation = gpsFlowDTO.getViolationSituation();
            if(ViolationSituationEnum.NO.getIndex() == violationSituation){
                gpsFlow.setViolationType("");
            }else{
                gpsFlow.setViolationType(gpsFlowDTO.getViolationType());
            }
            gpsFlow.setViolationSituation(violationSituation);
            gpsFlowService.updateRecord(gpsFlow);
            SyUser syUser = sessionUserComponent.getSessionUser();
            //3、处理GPS审批相关
            Tuple<Integer> tuple = gpsApproveService.updateRecord(appCode,syUser,isApproveSuccess);
            Integer historyId = tuple.getFirst();
            //4、获取GPS自动认证状态，并新增GPS安装信息
            saveCarGpsInfos(historyId,dto,completableFuture.get());
            //5、更新app_info 的isGps、gpsConfirmDate
            String isGps = String.valueOf(isApproveSuccess ? AppInfoIsGpsEnum.INSTALLED.getIndex() : AppInfoIsGpsEnum.UNINSTALL.getIndex());
            AppInfo appinfo = new AppInfo();
            appinfo.setAppCode(appCode);
            appinfo.setIsGps(isGps);
            appinfo.setGpsConfirmDate(TimeTools.createNowTime());
            appInfoService.updateForCarGps(appinfo);
            //6、更新车辆的相关GPS信息
            carInfoService.handleGpsInfo(appInfo.getCarId(),dto);
        } catch (Exception e) {
            LOGGER.error("{}异常,dto={}",LOG_TITLE,JSON.toJSON(dto),e);
            throw new BizException(HeilCode.E_400,"gps审核失败，请稍后重试");
        }finally {
            redisUtil.del(getLockKey("vinCodeLock") + ":" + appCode);
        }
        return Result.suc("GPS审核成功");
    }

    /**
     * 保存GPS设备信息
     * @param historyId
     * @param dto
     */
    private void saveCarGpsInfos(Integer historyId,GpsApproveDTO dto,Result<List<GpsDeviceInfoRe>> gpsDeviceInfoResult){
        //是否审批成功
        final boolean isApproveSuccess = dto.getGpsFlowDTO().isApproveSuccess();
        //是否标示ca_car_gps 的is_back属性值为1
        final boolean isBack = !isApproveSuccess ;
        String appCode = dto.getAppCode();
        AppInfo appInfo = appInfoService.queryAppInfo(appCode);
        List<CarGpsDTO> gpsList = dto.getGpsList();
       /* Tuple<GpsDeviceAgencyType> gpsDeviceAgencyTypeTuple = Tuple.newInstance(GpsDeviceAgencyType.segGPS);
        gpsList.stream().map(each -> each.getDealerCode()).findAny().ifPresent(each -> gpsDeviceAgencyTypeTuple.setFirst(GpsDeviceAgencyType.getByGpsDealer(each)));
        List<String> gpsDeviceList = gpsList.stream().filter(gps->!StringUtils.isEmpty(gps.getGpsNo())).map(gps -> gps.getGpsNo()).collect(Collectors.toList());
        Result<List<GpsDeviceInfoRe>> gpsDeviceInfoResult = gpsDeviceInfoComponent.getGpsInfos(gpsDeviceList,gpsDeviceAgencyTypeTuple.getFirst());*/
        if(!gpsDeviceInfoResult.isSuccess()){
            LOGGER.warn("{}GPS自动认证状态失败,appCode={},gpsDeviceInfoResult={}",LOG_TITLE,appCode, JSON.toJSON(gpsDeviceInfoResult));
            throw new BizException("GPS自动认证状态失败");
        }
        List<GpsDeviceInfoRe> gpsDeviceInfoReList = gpsDeviceInfoResult.getData();
        Map<String,String> autoAuditCodeMap = new HashMap<>(gpsDeviceInfoReList.size());
        Map<String,String> autoAuditResultMap = new HashMap<>(gpsDeviceInfoReList.size());
        gpsDeviceInfoReList.stream().forEach(gpsDeviceInfoRe -> {
            autoAuditCodeMap.put(gpsDeviceInfoRe.getGpsId(),Arrays.toString(gpsDeviceInfoRe.getStatusCode()));
            autoAuditResultMap.put(gpsDeviceInfoRe.getGpsId(),Arrays.toString(gpsDeviceInfoRe.getStatusDesc()));
        });
        gpsList.forEach(gpsDTO -> {
            if (!StringUtils.isEmpty(gpsDTO.getGpsNo())) {
                CarGps carGps = buildCarGps(gpsDTO);
                carGps.setAppCode(appCode);
                carGps.setAutoAuditCode(autoAuditCodeMap.get(gpsDTO.getGpsNo()));
                carGps.setAutoAuditResult(autoAuditResultMap.get(gpsDTO.getGpsNo()));
                carGps.setCarId(appInfo.getCarId());
                carGps.setHistoryId(historyId);
                carGps.setIsBack(isBack ? CarGpsIsBackEnum.YES.getIndex() : CarGpsIsBackEnum.NO.getIndex());
                carGps.setIsCurrent(isBack ? CarGpsIsCurrentEnum.YES.getIndex() : CarGpsIsCurrentEnum.NO.getIndex());
                carGps.setStatus(isApproveSuccess ? CarGpsStatusEnum.SUCCESS.getIndex() : CarGpsStatusEnum.FAILURE.getIndex());
                carGpsService.insertRecord(carGps);
            }
        });
    }

    private CarGps buildCarGps(CarGpsDTO gpsDTO){
        CarGps carGps = new CarGps();
        BeanUtils.copyProperties(gpsDTO,carGps);
        carGps.setStatus(AppInfoIsGpsEnum.UPLOADED.getIndex());
        if (StringUtils.isEmpty(gpsDTO.getDealerCode())) {
            throw new RuntimeException("dealerCode不能为空,gpsNo:"+gpsDTO.getGpsNo());
        }

        carGps.setGpsDealer(gpsDTO.getDealerCode());
        carGps.setUpdateTime(TimeTools.format4YYYYMMDDHHMISS(TimeTools.createNowTime()));
        carGps.setDelStatus(YesOrNoEnum.NO.getIndex());
        return carGps;
    }


    @LogMonitor("勾回审批完成的单子")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> hookGps(String appCode) {
        LOGGER.info("勾回gps单子----appCode:{}", appCode);
        AppInfo appInfo = appInfoService.queryAppInfo(appCode);
        ValidateUtils.notNull(appInfo, HeilCode.E_500, "订单不存在,请核实后操作");
        ValidateUtils.isTrue(String.valueOf(AppInfoIsGpsEnum.INSTALLED.getIndex()).equals(appInfo.getIsGps()), HeilCode.E_500, "勾回失败,只能勾回已确认安装状态的订单");
        List<String> statusList = Arrays.asList("32");
        ValidateUtils.isFalse(statusList.contains(appInfo.getStatus()), HeilCode.E_500, "当前订单状态不支持勾回操作");
        SyUser syUser = sessionUserComponent.getSessionUser();
        List<SyRole> syRoleList = syRoleService.queryList(new SyRoleForm(syUser.getUserId()));
        ValidateUtils.notEmpty(syRoleList, HeilCode.E_500, "您没有角色,请联系管理员");
        GpsFlow gpsFlow = gpsFlowService.queryByAppCode(appCode);
        ValidateUtils.notNull(gpsFlow, HeilCode.E_500, "数据错误,没有审批记录");
        ValidateUtils.notNull(gpsFlow.getApprovalUserId(), HeilCode.E_500, "数据错误,当前数据没有保存审批人员");
        List<String> roleNames = syRoleList.stream().map(record->record.getRoleName()).collect(Collectors.toList());
        LOGGER.info("当前用户角色:{}", JSON.toJSON(roleNames));
        boolean isCan = roleNames.contains("GPS审核主管") || roleNames.contains("系统管理员") || gpsFlow.getApprovalUserId() == syUser.getUserId();
        ValidateUtils.isTrue(isCan, HeilCode.E_500, "您只能勾回自己的单子");

        // 重置数据
        appInfo.setIsGps(String.valueOf(AppInfoIsGpsEnum.UPLOADED.getIndex()));
        appInfoService.updateForCarGps(appInfo);
        // 审批中
        gpsFlow.setFlowStatus(GpsFlowConstant.GpsFlowStatusEnum.PROCESSING.getIndex());
        // 领单
        gpsFlow.setFlowStep(GpsFlowConstant.GpsFlowStepEnum.PULL.getIndex());
        gpsFlowService.updateRecord(gpsFlow);

        // carGps
        CarGpsForm carGpsForm = new CarGpsForm();
        carGpsForm.setAppCode(appCode);
        List<CarGps> carGpsList = carGpsService.queryList(carGpsForm);
        ValidateUtils.notEmpty(carGpsList, HeilCode.E_500, "数据错误,当前订单没有carGps数据");
        LOGGER.info("勾回gps订单-appCode:{},查询carGpsList数据:{}", appCode, JSON.toJSON(carGpsList));
        GpsHistory gpsHistory = gpsHistoryService.getById(carGpsList.get(0).getHistoryId());
        ValidateUtils.notNull(gpsHistory, HeilCode.E_500, "数据错误,没有历史记录");
        gpsHistory.setInstallStatus(GpsHistoryConstant.GpsInstallStatusEnum.INSTALLED.getIndex());
        gpsHistory.setApprovalStatus(GpsHistoryConstant.GpsApprovalStatusEnum.UN_APPROVE.getIndex());
        gpsHistoryService.updateCarGpsById(gpsHistory);
        String approvalTime = TimeTools.format4YYYYMMDDHHMISS(TimeTools.createNowTime());
        gpsOperateRecordService.insertRecord(GpsFlowConstant.GpsFlowStepEnum.APPROVAL,(record)->{
            record.setSubmitProps(syUser.getUserId(),syUser.getTrueName(),approvalTime);
            record.setAppCode(appCode);
            //审核成功
            record.setFlowStep(GpsFlowConstant.GpsFlowStepEnum.PULL.getIndex());
            record.setFlowStatus(GpsFlowConstant.GpsFlowStatusEnum.PROCESSING.getIndex());
            record.setRemark(GpsFlowConstant.OperateRecordRemarkEnum.GPS_HOOK.getName());

        });
        return Result.suc("操作成功");
    }

    private String getLockKey(String key) {
        String systemType = "100000".equals(SYSTEM_CODE) ? "zy" : "qd";
        return new StringBuffer(systemType).append(":").append(key).toString();
    }
}
