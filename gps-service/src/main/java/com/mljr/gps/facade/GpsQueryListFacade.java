package com.mljr.gps.facade;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Const;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.AppInfoStatusEnum;
import com.lyqc.base.enums.CarLoanConstant.IsCreditEnum;
import com.lyqc.base.enums.GpsFlowConstant;
import com.lyqc.base.enums.LoanFileStatusEnum;
import com.lyqc.base.enums.RuleConditionConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.gpsprovider.enums.CarGpsConstant;
import com.lyqc.gpsprovider.enums.CarGpsConstant.AppInfoIsGpsEnum;
import com.lyqc.gpsweb.dto.GpsQueryListDTO;
import com.lyqc.gpsweb.vo.GpsInstalledListVo;
import com.mljr.annotation.CacheParam;
import com.mljr.gps.common.enums.DictionaryConstant;
import com.mljr.gps.common.enums.QueryBuzMarkEnum;
import com.mljr.gps.component.GpsComponent;
import com.mljr.gps.component.SessionUserComponent;
import com.mljr.gps.entity.CarGps;
import com.mljr.gps.form.CarGpsForm;
import com.mljr.gps.form.GpsOperateRecordForm;
import com.mljr.gps.form.GpsQueryListForm;
import com.mljr.gps.service.*;
import com.mljr.gps.vo.GpsQueryListVo;
import com.mljr.util.CollectionsTools;
import com.mljr.util.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @description: GPS查询列表Facade
 * @Date : 2018/6/20 上午10:28
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Component
public class GpsQueryListFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Value(value = "${system_code}")
    private String systemCode;
    @Autowired
    private GpsQueryListService gpsQueryListService;
    @Autowired
    private CarGpsService carGpsService;
    @Autowired
    private GpsOperateRecordService gpsOperateRecordService;
    @Autowired
    private SessionUserComponent sessionUserComponent;
    @Autowired
    private SyUserService syUserService;
    @Autowired
    private GpsComponent gpsComponent;
    @Autowired
    private ProppserInfoService proppserInfoService;
    /**
     * 车贷申请管理-待上传GPS安装单
     * @param form
     * @return
     */
    public Result<PageVO<GpsQueryListVo>> queryListForTobeUploadOnApply(PageForm<GpsQueryListForm> form){
        List<GpsQueryListVo> carGpsReList = new ArrayList<>();
        int totalCount = 0;
        String LOG_TITLE = "车贷申请管理-待上传GPS安装单";
        try {
            if(StringTools.isNotEmpty(form.getForm().getAppTime())){
                form.getForm().setAppTime(form.getForm().getAppTime() + " 00:00:00");
            }
            form.getForm().setBuzMark(QueryBuzMarkEnum.TobeUploadOnApply.getName());
            form.getForm().setIsGpsScope(Arrays.asList(AppInfoIsGpsEnum.UNINSTALL.getIndex(),AppInfoIsGpsEnum.UPLOADED.getIndex()));
            LOGGER.info("{},form={}",LOG_TITLE, JSON.toJSON(form));
            carGpsReList = gpsQueryListService.queryListForTobeUploadOnApply(form);
            totalCount = gpsQueryListService.queryCountForTobeUploadOnApply(form);
            if(!carGpsReList.isEmpty()){
                //1、设置相关属性
                carGpsReList.forEach(record -> {
                    String isCredit = Optional.ofNullable(record.getIsCredit()).orElse(String.valueOf(IsCreditEnum.FO.getIndex()));
                    record.setIsCredit(IsCreditEnum.getNameByIndex(Integer.valueOf(isCredit)));
                    record.setIsScene("1".equals(record.getIsScene()) ? Const.YES : Const.NON);
                    record.setSendAppr("6".equals(record.getSendAppr()) ? Const.YES : Const.NON);
                    record.setFlowStepDesc(getFlowStep(record));
                    record.setCarType(RuleConditionConstant.IsLcvEnum.getNameByIndex(Integer.valueOf(record.getCarType())));
                    record.setAppCodeSuffix(getAppCodeSuffix(record));
                });
            }
        } catch (Exception e) {
            LOGGER.error("{}异常,form={}",LOG_TITLE,JSON.toJSON(form),e);
            return Result.failInServer(PageVO.newInstance(totalCount,carGpsReList));
        }
        return Result.suc(PageVO.newInstance(totalCount,carGpsReList));
    }
    /**
     * 车贷申请管理-GPS安装单列表
     * @param form
     * @return
     */
    @CacheParam(prefix = "queryListForInstalledOnApply", expire = 7*24*60*60)
    public Result<PageVO<GpsQueryListVo>> queryListForInstalledOnApply(PageForm<GpsQueryListForm> form){
        List<GpsQueryListVo> carGpsReList = new ArrayList<>();
        int totalCount = 0;
        String LOG_TITLE = "车贷申请管理-GPS安装单列表";
        Boolean emptyFlag = StringUtils.isEmpty(form.getForm().getCustomerName()) ? Boolean.TRUE : Boolean.FALSE;
        try {
            if(StringTools.isNotEmpty(form.getForm().getBeginTime())){
                form.getForm().setBeginTime(form.getForm().getBeginTime() + " 00:00:00");
            }
            if(StringTools.isNotEmpty(form.getForm().getEndTime())){
                form.getForm().setEndTime(form.getForm().getEndTime() + " 23:59:59");
            }
            form.getForm().setBuzMark(QueryBuzMarkEnum.InstalledOnApply.getName());
            if (CollectionsTools.isEmpty(form.getForm().getDealerCodeScope())){
                Integer userId = sessionUserComponent.getLoginSessionUser().getUserId();
                List<Integer> dealerCodeScope = syUserService.getDealerCodeScopeByUserId(userId);
                form.getForm().setDealerCodeScope(dealerCodeScope);
            }
            LOGGER.info("{},form={}",LOG_TITLE, JSON.toJSON(form));
            List<GpsQueryListVo>  proppserInfo;
            Map<String,String>  customerNameMap = null;
            if (StringTools.isNotEmpty(form.getForm().getCustomerName())){
                //根据查询条件中的客户姓名进行查找
                proppserInfo = proppserInfoService.listByForm(form.getForm());
                if (CollectionUtils.isEmpty(proppserInfo)){
                    LOGGER.info("{},根据appCode={} customerName={}未查询到信息",LOG_TITLE,form.getForm().getAppCode(),form.getForm().getCustomerName());
                    return Result.suc(PageVO.newInstance(totalCount,carGpsReList));
                }
                List<String> appCodes = proppserInfo.stream().map(GpsQueryListVo::getAppCode).collect(toList());
                form.getForm().setAppCodeList(appCodes);
                customerNameMap = proppserInfo.stream().collect(Collectors.toMap(GpsQueryListVo::getAppCode,GpsQueryListVo::getCustomerName));
            }
            carGpsReList = gpsQueryListService.queryListForInstalledOnApply(form);
            totalCount = gpsQueryListService.queryCountForInstalledOnApply(form);
            if(!carGpsReList.isEmpty()){
                List<String> appCodeScope = carGpsReList.stream().map(record -> record.getAppCode()).collect(toList());
                // 1、组装数据结构模型
                Map<String,List<CarGps>> gpsDeviceMap = getGpsDeviceListByAppCodes(appCodeScope);
                //为true时说明未查询过客户姓名
                if (emptyFlag){
                    // 查询客户姓名
                    List<Integer> proppserIds = carGpsReList.stream().map(record -> record.getProppserId()).collect(toList());
                    customerNameMap = getCustomerNameMap(proppserIds);
                }
                //2、设置相关属性
                for(GpsQueryListVo record : carGpsReList ) {
                    String appCode = record.getAppCode();
                    List<CarGps> carGpsList = gpsDeviceMap.getOrDefault(appCode,new ArrayList<>());
                    List<String> autoAuditResult = carGpsList.stream().map(CarGps::getAutoAuditResult).collect(toList()).stream().filter(list->!StringUtils.isEmpty(list)).collect(toList());
                    List<String> auditSupplement = carGpsList.stream().map(CarGps::getAuditSupplement).collect(toList()).stream().filter(list->!StringUtils.isEmpty(list)).collect(toList());
                    List<String> manualAuditResult = carGpsList.stream().map(CarGps::getManualAuditResult).collect(toList()).stream().filter(list->!StringUtils.isEmpty(list)).collect(toList());
                    record.setAutoAuditResult(StringUtils.collectionToDelimitedString(autoAuditResult,","));
                    record.setAuditSupplement(StringUtils.collectionToDelimitedString(auditSupplement,","));
                    record.setManualAuditResult(StringUtils.collectionToDelimitedString(manualAuditResult,","));
                    record.setFlowStepDesc(getFlowStep(record));
                    record.setCarType(RuleConditionConstant.IsLcvEnum.getNameByIndex(Integer.valueOf(record.getCarType())));
                    record.setAppCodeSuffix(getAppCodeSuffix(record));
                    record.setGpsCount(com.mljr.gps.common.util.StringUtils.killNull(record.getGpsCount()));
                    record.setFlowRemark(com.mljr.gps.common.util.StringUtils.killNull(record.getFlowRemark()));
                    record.setGpsDesc(AppInfoIsGpsEnum.getNameByIndex(record.getIsGps()));
                    if (customerNameMap != null) {
                        record.setCustomerName(customerNameMap.get(record.getAppCode()));
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("{}异常,form={}",LOG_TITLE,JSON.toJSON(form),e);
            return Result.failInServer(PageVO.newInstance(totalCount,carGpsReList));
        }
        return Result.suc(PageVO.newInstance(totalCount,carGpsReList));
    }


    /**
     * GPS审核领单-列表查询
     * @param form
     * @return
     */
    @CacheParam(prefix = "queryListForGpsApprovePull",expire = 7*24*60*60)
    public Result<PageVO<GpsQueryListVo>> queryListForGpsApprovePull(PageForm<GpsQueryListForm> form){
        String LOG_TITLE = "GPS审核领单-列表查询";
        LOGGER.info("{},form={}",LOG_TITLE, JSON.toJSON(form));
        List<GpsQueryListVo> carGpsReList = new ArrayList<>();
        int totalCount = 0;
        try {
            if(StringTools.isNotEmpty(form.getForm().getAppTime())){
                form.getForm().setAppTime(form.getForm().getAppTime() + " 00:00:00");
            }
            if (StringUtils.isEmpty(form.getForm().getIsGps())){
                form.getForm().setIsGps("2");
            }
            form.getForm().setExcludeStatusScope(gpsComponent.getExcludeStatusScope(GpsComponent.ExcludeStatusScope.APPROVE_PULL_EXCLUDE_SCOPE));
            form.getForm().setBuzMark(QueryBuzMarkEnum.GpsApprovePull.getName());
            totalCount = gpsQueryListService.queryCountForGpsApprovePull(form);
            if(totalCount == 0){
                return Result.suc(PageVO.newInstance(totalCount,Collections.emptyList()));
            }
            carGpsReList = gpsQueryListService.queryListForGpsApprovePull(form);
            if(!carGpsReList.isEmpty()){
                List<String> appCodeScope = carGpsReList.stream().map(record -> record.getAppCode()).collect(toList());
                // 1、组装数据结构模型
                Map<String,List<CarGps>> gpsDeviceMap = getGpsDeviceListByAppCodes(appCodeScope);
                //2、设置相关属性
                carGpsReList.forEach(record -> {
                    String appCode = record.getAppCode();
                    LOGGER.info("{}-appCode:{}", LOG_TITLE, appCode);
                    List<CarGps> carGpsList = gpsDeviceMap.get(appCode);
                    if (!CollectionsTools.isEmpty(carGpsList)) {
                        List<String> manualAuditResult = carGpsList.stream().map(CarGps::getManualAuditResult).collect(Collectors.toList()).stream().filter(list->!StringUtils.isEmpty(list)).collect(toList());
                        if (!CollectionsTools.isEmpty(manualAuditResult)) {
                            record.setManualAuditResult(StringUtils.collectionToDelimitedString(manualAuditResult,","));
                        }
                        List<String> autoAuditResult = carGpsList.stream().map(CarGps::getAutoAuditResult).collect(toList()).stream().filter(list->!StringUtils.isEmpty(list)).collect(toList());
                        List<String> auditSupplement = carGpsList.stream().map(CarGps::getAuditSupplement).collect(toList()).stream().filter(list->!StringUtils.isEmpty(list)).collect(toList());
                        record.setAutoAuditResult(StringUtils.collectionToDelimitedString(autoAuditResult,","));
                        record.setAuditSupplement(StringUtils.collectionToDelimitedString(auditSupplement,","));
                    }
                    record.setIsScene("1".equals(record.getIsScene()) ? DictionaryConstant.YesOrNoEnum.YES.getName() : DictionaryConstant.YesOrNoEnum.NO.getName());
                    record.setFlowStepDesc(getFlowStep(record));
                    record.setCarType(RuleConditionConstant.IsLcvEnum.getNameByIndex(Integer.valueOf(record.getCarType())));
                    record.setAppCodeSuffix(getAppCodeSuffix(record));
                });
            }
            LOGGER.info("{} - 查询结果为:{}", LOG_TITLE, JSON.toJSON(carGpsReList));
        } catch (Exception e) {
            LOGGER.error("{}异常,form={}",LOG_TITLE,JSON.toJSON(form),e);
            return Result.failInServer(PageVO.newInstance(totalCount,carGpsReList));
        }
        return Result.suc(PageVO.newInstance(totalCount,carGpsReList));
    }

    /**
     * 我的GPS领单-列表查询
     * @param form
     * @return
     */
    @CacheParam(prefix = "queryListForGpsApproveMine",expire = 7*24*60*60)
    public Result<PageVO<GpsQueryListVo>> queryListForGpsApproveMine(PageForm<GpsQueryListForm> form){
        String LOG_TITLE = "我的GPS领单-列表查询";
        LOGGER.info("{},form={}",LOG_TITLE, JSON.toJSON(form));
        List<GpsQueryListVo> carGpsReList = new ArrayList<>();
        int totalCount = 0;
        try {
            if (form.getForm().getApprovalUserId() == null){
                form.getForm().setApprovalUserId(sessionUserComponent.getLoginSessionUser().getUserId());
            }
            if (StringUtils.isEmpty(form.getForm().getIsGps())){
                form.getForm().setIsGps("2");
            }
            form.getForm().setExcludeStatusScope(gpsComponent.getExcludeStatusScope(GpsComponent.ExcludeStatusScope.APPROVE_MINE_EXCLUDE_SCOPE));
            totalCount = gpsQueryListService.queryCountForInstalledGps(form);
            if(totalCount == 0){
                return Result.suc(PageVO.newInstance(totalCount,Collections.emptyList()));
            }
            carGpsReList = gpsQueryListService.queryListForInstalledGps(form);
            if(!carGpsReList.isEmpty()){
                List<String> appCodeScope = carGpsReList.stream().map(record -> record.getAppCode()).collect(toList());
                List<Integer> proppserIds = carGpsReList.stream().map(record -> record.getProppserId()).collect(toList());
                // 1、组装数据结构模型
                Map<String,List<CarGps>> gpsDeviceMap = getGpsDeviceListByAppCodes(appCodeScope);
                Map<String,String> proppserInfoMap = getCustomerNameMap(proppserIds);
                //曾经被驳回的GPS审批单
                List<String> rejectAppCodes = getGpsIsBackListByAppCodes(appCodeScope);
                //2、设置相关属性
                carGpsReList.forEach(record -> {
                    String appCode = record.getAppCode();
                    List<CarGps> carGpsList = gpsDeviceMap.get(appCode);
                    if (!CollectionsTools.isEmpty(carGpsList)) {
                        String proppserName = proppserInfoMap.get(appCode);
                        record.setCustomerName(com.mljr.gps.common.util.StringUtils.killNull(proppserName));
                        List<String> gpsNo = carGpsList.stream().map(CarGps::getGpsNo).collect(Collectors.toList()).stream().filter(str->!StringUtils.isEmpty(str)).collect(toList());
                        record.setGpsDeviceNo(StringUtils.collectionToDelimitedString(gpsNo,","));
                        List<String> autoAuditResult = carGpsList.stream().map(CarGps::getAutoAuditResult).collect(toList()).stream().filter(str->!StringUtils.isEmpty(str)).collect(toList());
                        List<String> auditSupplement = carGpsList.stream().map(CarGps::getAuditSupplement).collect(toList()).stream().filter(str->!StringUtils.isEmpty(str)).collect(toList());
                        record.setAutoAuditResult(StringUtils.collectionToDelimitedString(autoAuditResult,","));
                        record.setAuditSupplement(StringUtils.collectionToDelimitedString(auditSupplement,","));
                        List<String> manualAuditResult = carGpsList.stream().map(CarGps::getManualAuditResult).collect(Collectors.toList()).stream().filter(str->!StringUtils.isEmpty(str)).collect(toList());
                        if (!CollectionsTools.isEmpty(manualAuditResult)) {
                            record.setManualAuditResult(StringUtils.collectionToDelimitedString(manualAuditResult,","));
                            record.setManualAuditResult(record.getManualAuditResult()+"("+record.getAuditSupplement()+")");
                        }
                        List<Integer> installWay = carGpsList.stream().map(CarGps::getGpsInstallWay).collect(toList()).stream().filter(str -> !StringUtils.isEmpty(str)).collect(toList());
                        if (!CollectionsTools.isEmpty(installWay)) {
                            List<String> installWayStrList = new ArrayList<String>();
                            installWay.stream().forEach(i -> installWayStrList.add(DictionaryConstant.CarGpsInstallWayEnum.getNameByIndex(i)));
                            record.setGpsInstallType(StringUtils.collectionToDelimitedString(installWayStrList,","));
                        }

                    }
                    record.setFlowStepDesc(getFlowStep(record));
                    record.setCarType(RuleConditionConstant.IsLcvEnum.getNameByIndex(Integer.valueOf(record.getCarType())));
                    List<String> suffixList = getAppCodeSuffix(record);
                    //移除 其他含义上的 退
                    suffixList.remove(AppCodeSuffix.退.name());
                    //添加 被驳回 意义上的退
                    if(rejectAppCodes.contains(record.getAppCode())){
                        suffixList.add(AppCodeSuffix.退.name());
                    }
                    record.setAppCodeSuffix(suffixList);
                });
            }
            LOGGER.info("{} - 查询结果为:{}", LOG_TITLE, JSON.toJSON(carGpsReList));
        } catch (Exception e) {
            LOGGER.error("{}异常,form={}",LOG_TITLE,JSON.toJSON(form),e);
            return Result.failInServer(PageVO.newInstance(totalCount,carGpsReList));
        }
        return Result.suc(PageVO.newInstance(totalCount,carGpsReList));
    }

    private Map<String,String> getCustomerNameMap(List<Integer> proppserIds) {
        GpsQueryListForm form = new GpsQueryListForm();
        form.setProppserIds(proppserIds);
        List<GpsQueryListVo> proppserInfos = proppserInfoService.listByForm(form);
        Map<String,String> customerNameMap = proppserInfos.stream().collect(Collectors.toMap(GpsQueryListVo::getAppCode,GpsQueryListVo::getCustomerName));
        return customerNameMap;
    }

    /**
     * GPS综合查询-列表查询
     * @param form
     * @return
     */
    @CacheParam(prefix = "queryListForGpsGeneral",expire = 7*24*60*60)
    public Result<PageVO<GpsQueryListVo>> queryListForGpsGeneral(PageForm<GpsQueryListForm> form){
        String LOG_TITLE = "GPS综合查询-列表查询";
        LOGGER.info("{},form={}",LOG_TITLE, JSON.toJSON(form));

        List<GpsQueryListVo> carGpsReList = new ArrayList<>();
        int totalCount = 0;
        try {
            if(StringTools.isNotEmpty(form.getForm().getAppTime())){
                form.getForm().setAppTime(form.getForm().getAppTime() + " 00:00:00");
            }
            carGpsReList = gpsQueryListService.queryListForGpsGeneral(form);
            totalCount = gpsQueryListService.queryCountForGpsGeneral(form);
            if(!carGpsReList.isEmpty()){
                List<String> appCodeScope = carGpsReList.stream().map(record -> record.getAppCode()).collect(toList());
                // 1、组装数据结构模型
                Map<String,List<CarGps>> gpsDeviceMap = getGpsDeviceListByAppCodes(appCodeScope);
                //曾经被驳回的GPS审批单
                List<String> rejectAppCodes = getGpsIsBackListByAppCodes(appCodeScope);
                //2、设置相关属性
                carGpsReList.forEach(record -> {
                    String appCode = record.getAppCode();
                    List<CarGps> carGpsList = gpsDeviceMap.get(appCode);
                    if (!CollectionsTools.isEmpty(carGpsList)) {
                        List<Integer> installWay = carGpsList.stream().map(CarGps::getGpsInstallWay).collect(toList()).stream().filter(str -> !StringUtils.isEmpty(str)).collect(toList());
                        if (!CollectionsTools.isEmpty(installWay)) {
                            List<String> installWayStrList = new ArrayList<String>();
                            installWay.stream().forEach(i -> installWayStrList.add(DictionaryConstant.CarGpsInstallWayEnum.getNameByIndex(i)));
                            record.setGpsInstallType(StringUtils.collectionToDelimitedString(installWayStrList,","));
                        }
                    }
                    String isCredit = Optional.ofNullable(record.getIsCredit()).orElse(String.valueOf(IsCreditEnum.FO.getIndex()));
                    record.setIsCredit(IsCreditEnum.getNameByIndex(Integer.valueOf(isCredit)));
                    record.setFlowStepDesc(getFlowStep(record));
                    if (!StringUtils.isEmpty(record.getCarType())) {
                        record.setCarType(RuleConditionConstant.IsLcvEnum.getNameByIndex(Integer.valueOf(record.getCarType())));
                    }
                    List<String> suffixList = getAppCodeSuffix(record);
                    //移除 其他含义上的 退
                    suffixList.remove(AppCodeSuffix.退.name());
                    //添加 被驳回 意义上的退
                    if(rejectAppCodes.contains(record.getAppCode())){
                        suffixList.add(AppCodeSuffix.退.name());
                    }
                    record.setAppCodeSuffix(suffixList);
                    record.setGpsDesc(record.getIsGps() == 0 ? "未安装" : record.getIsGps() == 1 ? "已安装确认" : record.getIsGps() == 2 ? "已上传待确认" : "无GPS");
                });
            }
        } catch (Exception e) {
            LOGGER.error("{}异常,form={}",LOG_TITLE,JSON.toJSON(form),e);
            return Result.failInServer(PageVO.newInstance(totalCount,carGpsReList));
        }
        return Result.suc(PageVO.newInstance(totalCount,carGpsReList));
    }

    /**
     * 获取流程阶段
     * @param record
     * @return
     */
    private String getFlowStep(GpsQueryListVo record){
        boolean ZY = Optional.ofNullable(systemCode).orElse("100000").equals(100000);
        int status = record.getAppStatus();
        boolean status_29 = AppInfoStatusEnum._29 == AppInfoStatusEnum.getByIndex(status);
        boolean status_19 = AppInfoStatusEnum._19 == AppInfoStatusEnum.getByIndex(status);
        AppInfoIsGpsEnum appInfoIsGpsEnum = AppInfoIsGpsEnum.getByIndex(Optional.ofNullable(record.getIsGps()).orElse(AppInfoIsGpsEnum.UNINSTALL.getIndex()));
        BigDecimal gpsFee = Optional.ofNullable(record.getGpsFee()).orElse(BigDecimal.ZERO);
        boolean gpsCondition = AppInfoIsGpsEnum.INSTALLED != appInfoIsGpsEnum && gpsFee.compareTo(BigDecimal.ZERO) > 0;
        LoanFileStatusEnum loanFileStatusEnum = null;
        if(null != record.getLoanFileStatus()){
            loanFileStatusEnum = LoanFileStatusEnum.getByIndex((record.getLoanFileStatus()));
        }
        StringBuilder flowStep = new StringBuilder(AppInfoStatusEnum.getNameByIndex(status));
        if((null != record.getFlowSeq() && 3 != record.getFlowSeq()) && (status_29 || status_19 ) && gpsCondition){
            flowStep.append("（GPS待安装）");
        }else if(ZY && (status_29 ||status_19 ) && LoanFileStatusEnum.STATUS_PASS != loanFileStatusEnum){
            flowStep.append("（放款审核资料待确认）");
        }
        return flowStep.toString();
    }

    /**
     * 获取订单号后缀标示
     * @param record
     * @return
     */
    private List<String> getAppCodeSuffix(GpsQueryListVo record){
        List<String> suffix = new ArrayList<>();
        if("1".equals(record.getIsBack())){
            suffix.add(AppCodeSuffix.退.name());
        }
        return suffix;
    }

    /**
     * 车贷审批管理-GPS安装单未上传
     * @param form
     * @return
     */
    @CacheParam(prefix = "queryListForUnInstalledGps",expire = 7*24*60*60)
    public Result<PageVO<GpsQueryListVo>> queryListForUnInstalledGps(PageForm<GpsQueryListForm> form) {
        List<GpsQueryListVo> carGpsReList = new ArrayList<>();
        int totalCount = 0;
        String appCode;
        List<CarGps> carGpsList = Collections.EMPTY_LIST;
        List<String> manualAuditResult = Collections.EMPTY_LIST;
        List<Integer> gpsInstallWay = Collections.EMPTY_LIST;
        List<String> gpsNo = Collections.EMPTY_LIST;
        String LOG_TITLE = "车贷审批管理-GPS安装单未上传";
        try {
            if(StringTools.isNotEmpty(form.getForm().getAppTime())){
                form.getForm().setAppTime(form.getForm().getAppTime() + " 00:00:00");
            }
            form.getForm().setBuzMark(QueryBuzMarkEnum.UnInstalledGps.getName());
            form.getForm().setExcludeStatusScope(gpsComponent.getExcludeStatusScope(GpsComponent.ExcludeStatusScope.UNINSTALLED_GPS_EXCLUDE_SCOPE));
            LOGGER.info("{},form={}",LOG_TITLE, JSON.toJSON(form));
            carGpsReList = gpsQueryListService.queryListForUnInstalledGps(form);
            totalCount = gpsQueryListService.queryCountForUnInstalledGps(form);
            if(!carGpsReList.isEmpty()){
                List<String> appCodeScope = carGpsReList.stream().map(record -> record.getAppCode()).collect(toList());
                // 1、组装数据结构模型
                Map<String,List<CarGps>> gpsDeviceMap = getGpsDeviceListByAppCodes(appCodeScope);
                //2、设置相关属性
                for(GpsQueryListVo record : carGpsReList ){
                    appCode = record.getAppCode();
                    carGpsList = gpsDeviceMap.get(appCode);
                    if (CollectionsTools.isNotEmpty(carGpsList)){
                        manualAuditResult = carGpsList.stream().map(CarGps::getManualAuditResult).collect(toList()).stream().filter(list-> !StringUtils.isEmpty(list)).collect(toList());
                        gpsInstallWay = carGpsList.stream().map(CarGps::getGpsInstallWay).collect(toList()).stream().filter(list->list != null).collect(toList());
                        gpsNo = carGpsList.stream().filter(gps->!StringUtils.isEmpty(gps.getGpsNo())).map(CarGps::getGpsNo).collect(Collectors.toList());
                        List<String> installWayList = new ArrayList<>();
                        gpsInstallWay.forEach(installWay->{
                            if (CarGpsConstant.GpsInstallTypeEnum.getNameByIndex(installWay) != null) {
                                installWayList.add(CarGpsConstant.GpsInstallTypeEnum.getNameByIndex(installWay));
                            }
                        });
                        record.setManualAuditResult(StringUtils.collectionToDelimitedString(manualAuditResult,","));
                        record.setGpsDeviceNo(StringUtils.collectionToDelimitedString(gpsNo,","));
                        record.setGpsInstallType(StringUtils.collectionToDelimitedString(installWayList,","));
                    }
                    String isCredit = Optional.ofNullable(record.getIsCredit()).orElse(String.valueOf(IsCreditEnum.FO.getIndex()));
                    record.setIsCredit(IsCreditEnum.getNameByIndex(Integer.valueOf(isCredit)));
                    record.setSendAppr("6".equals(record.getSendAppr()) ? Const.YES : Const.NON);
                    record.setFlowStepDesc(getFlowStep(record));
                    List<String> suffixList = getAppCodeSuffix(record);
                    //移除 其他含义上的 退
                    suffixList.remove(AppCodeSuffix.退.name());
                    record.setAppCodeSuffix(suffixList);
                }
            }
        } catch (Exception e) {
            LOGGER.error("{}异常,form={}",LOG_TITLE,JSON.toJSON(form),e);
            return Result.failInServer(PageVO.newInstance(totalCount,carGpsReList));
        }
        return Result.suc(PageVO.newInstance(totalCount,carGpsReList));
    }

    /**
     * 根据订单号查询GPS设备返回一个Map数据结果
     * @param appCodeScope 订单号集合
     * @return Map<String,List<GpsAuditResultBo>>
     */
    private final Map<String,List<CarGps>> getGpsDeviceListByAppCodes(List<String> appCodeScope){
        CarGpsForm gpsForm = new CarGpsForm();
        gpsForm.setAppCodeScope(appCodeScope);
        List<CarGps> gpsList = carGpsService.queryList(gpsForm);
        // 1、组装数据结构模型
        Map<String,List<CarGps>> appCodeMap = gpsList.stream().collect(Collectors.groupingBy(CarGps::getAppCode));
        return appCodeMap;
    }

    /**
     * 车贷审批管理-GPS安装单已上传
     * @param form
     * @return
     */
    @CacheParam(prefix = "queryListForInstalledGps",expire = 7*24*60*60)
    public Result<PageVO<GpsQueryListVo>> queryListForInstalledGps(PageForm<GpsQueryListForm> form) {
        List<GpsQueryListVo> carGpsReList = new ArrayList<>();
        int totalCount = 0;
        List<CarGps> carGpsList;
        List<Integer> gpsInstallWay;
        List<String> gpsNo;
        Boolean emptyFlag = StringUtils.isEmpty(form.getForm().getCustomerName()) ? Boolean.TRUE : Boolean.FALSE;
        String LOG_TITLE = "车贷审批管理-GPS安装单已上传";
        Map<String,String> customerNameMap = null;
        try {
            form.getForm().setExcludeStatusScope(gpsComponent.getExcludeStatusScope(GpsComponent.ExcludeStatusScope.INSTALLED_GPS_EXCLUDE_SCOPE));
            if (StringUtils.isEmpty(form.getForm().getIsGps())){
                form.getForm().setIsGps("2");
            }
            LOGGER.info("{},form={}",LOG_TITLE, JSON.toJSON(form));
            String customerName = form.getForm().getCustomerName();
            List<GpsQueryListVo>  proppserInfo;
            if (StringTools.isNotEmpty(customerName)){
                //根据查询条件中的客户姓名进行查找
                proppserInfo = proppserInfoService.listByForm(form.getForm());
                if (CollectionUtils.isEmpty(proppserInfo)){
                    LOGGER.info("{},根据appCode={} customerName={}未查询到信息",LOG_TITLE,form.getForm().getAppCode(),customerName);
                    return Result.suc(PageVO.newInstance(totalCount,carGpsReList));
                }
                List<String> appCodes = proppserInfo.stream().map(GpsQueryListVo::getAppCode).collect(toList());
                form.getForm().setAppCodeList(appCodes);
                customerNameMap = proppserInfo.stream().collect(Collectors.toMap(GpsQueryListVo::getAppCode,GpsQueryListVo::getCustomerName));
            }

            carGpsReList = gpsQueryListService.queryListForInstalledGps(form);
            totalCount = gpsQueryListService.queryCountForInstalledGps(form);
            if(!carGpsReList.isEmpty()){
                List<String> appCodeScope = carGpsReList.stream().map(record -> record.getAppCode()).collect(toList());
                // 1、组装数据结构模型
                Map<String,List<CarGps>> gpsDeviceMap = getGpsDeviceListByAppCodes(appCodeScope);
                //为true时说明未查询过客户姓名
                if (emptyFlag){
                    // 查询客户姓名
                    List<Integer> proppserIds = carGpsReList.stream().map(record -> record.getProppserId()).collect(toList());
                    customerNameMap = getCustomerNameMap(proppserIds);
                }
                //曾经被驳回的GPS审批单
                List<String> rejectAppCodes = getGpsIsBackListByAppCodes(appCodeScope);
                //2、设置相关属性
                String appCode;
                for(GpsQueryListVo record : carGpsReList ){
                    appCode = record.getAppCode();
                    carGpsList = gpsDeviceMap.get(appCode);
                    if (CollectionsTools.isNotEmpty(carGpsList)){
                        gpsInstallWay = carGpsList.stream().map(CarGps::getGpsInstallWay).collect(toList()).stream().filter(list->list != null).collect(toList());
                        List<String> installWayList = new ArrayList<>();
                        gpsInstallWay.forEach(installWay -> installWayList.add(CarGpsConstant.GpsInstallTypeEnum.getNameByIndex(installWay)));
                        gpsNo = carGpsList.stream().map(CarGps::getGpsNo).collect(Collectors.toList());
                        record.setGpsDeviceNo(StringUtils.collectionToDelimitedString(gpsNo,","));
                        record.setGpsInstallType(StringUtils.collectionToDelimitedString(installWayList,","));
                    }
                    String isCredit = Optional.ofNullable(record.getIsCredit()).orElse(String.valueOf(IsCreditEnum.FO.getIndex()));
                    record.setIsCredit(IsCreditEnum.getNameByIndex(Integer.valueOf(isCredit)));
                    record.setSendAppr("6".equals(record.getSendAppr()) ? Const.YES : Const.NON);
                    record.setFlowStepDesc(getFlowStep(record));
                    record.setGpsFlowStatusDesc(GpsFlowConstant.GpsFlowStatusEnum.getNameByIndex(Optional.ofNullable(record.getGpsFlowStatus()).orElse(-1)));
                    record.setGpsFlowStepDesc(GpsFlowConstant.GpsFlowStepEnum.getNameByIndex(Optional.ofNullable(record.getGpsFlowStep()).orElse(-1)));
                    List<String> suffixList = getAppCodeSuffix(record);
                    //移除 其他含义上的 退
                    suffixList.remove(AppCodeSuffix.退.name());
                    //添加 被驳回 意义上的退
                    if(rejectAppCodes.contains(record.getAppCode())){
                        suffixList.add(AppCodeSuffix.退.name());
                    }
                    record.setAppCodeSuffix(suffixList);
                    if (customerNameMap != null){
                     record.setCustomerName(com.mljr.gps.common.util.StringUtils.killNull(customerNameMap.get(appCode)));
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("{}异常,form={}",LOG_TITLE,JSON.toJSON(form),e);
            return Result.failInServer(PageVO.newInstance(totalCount,carGpsReList));
        }
        return Result.suc(PageVO.newInstance(totalCount,carGpsReList));
    }

    /**
     * 前缀
     */
    private enum AppCodeSuffix{
        快,A,退
    }

    /**
     * 根据订单号查询订单是否被退回过
     * @param appCodeScope 订单号集合
     * @return Map<String,List<GpsAuditResultBo>>
     */
    private final List<String> getGpsIsBackListByAppCodes(List<String> appCodeScope){
        GpsOperateRecordForm gpsForm = GpsOperateRecordForm.builder()
                .appCodeScope(appCodeScope).flowStatus(GpsFlowConstant.GpsFlowStatusEnum.REJECTED.getIndex()).build();
        return gpsOperateRecordService.queryListByAppCodes(gpsForm);
    }

    /**
     * 风云系统调用-GPS安装单列表
     * @param dto
     * @return
     */
    @CacheParam(prefix = "queryListForInstalled",expire = 7*24*60*60)
    public Result<PageVO<GpsInstalledListVo>> queryListForInstalled(PageForm<GpsQueryListDTO> dto) {
        List<GpsInstalledListVo> carGpsReList = new ArrayList<>();
        PageForm<GpsQueryListForm> form = PageForm.newInstance(true,new GpsQueryListForm());
        int totalCount = 0;
        String LOG_TITLE = "风云系统调用-GPS安装单列表";
        try {
            if (StringTools.isNotEmpty(dto.getForm().getBeginTime())) {
                dto.getForm().setBeginTime(dto.getForm().getBeginTime() + " 00:00:00");
            }
            if (StringTools.isNotEmpty(dto.getForm().getEndTime())) {
                dto.getForm().setEndTime(dto.getForm().getEndTime() + " 23:59:59");
            }
            BeanUtils.copyProperties(dto.getForm(), form.getForm());
            form.setLimit(dto.getLimit());
            form.setStart(dto.getStart());
            form.getForm().setSystemCode(Optional.ofNullable(systemCode).orElse("200000"));
            LOGGER.info("{}queryByPage,form={}",LOG_TITLE, JSON.toJSON(form));
            form.getForm().setBuzMark(QueryBuzMarkEnum.InstalledOnApply.getName());
            LOGGER.info("{},form={}", LOG_TITLE, JSON.toJSON(form));
            carGpsReList = gpsQueryListService.queryListForInstalled(form);
            totalCount = gpsQueryListService.queryCountForInstalled(form);

            if (!carGpsReList.isEmpty()) {
                List<String> appCodeScope = carGpsReList.stream().map(record -> record.getAppCode()).collect(toList());
                // 1、组装数据结构模型
                Map<String, List<CarGps>> gpsDeviceMap = getGpsDeviceListByAppCodes(appCodeScope);
                //2、设置相关属性
                carGpsReList.forEach(record -> {
                    String appCode = record.getAppCode();
                    List<CarGps> carGpsList = gpsDeviceMap.getOrDefault(appCode, new ArrayList<>());
                    List<String> autoAuditResult = carGpsList.stream().map(CarGps::getAutoAuditResult).collect(toList()).stream().filter(list -> !StringUtils.isEmpty(list)).collect(toList());
                    List<String> manualAuditResult = carGpsList.stream().map(CarGps::getManualAuditResult).collect(toList()).stream().filter(list -> !StringUtils.isEmpty(list)).collect(toList());
                    record.setAutoAuditResult(StringUtils.collectionToDelimitedString(autoAuditResult, ","));
                    record.setManualAuditResult(StringUtils.collectionToDelimitedString(manualAuditResult, ","));
                    record.setGpsCount(com.mljr.gps.common.util.StringUtils.killNull(record.getGpsCount()));
                    record.setFlowRemark(com.mljr.gps.common.util.StringUtils.killNull(record.getFlowRemark()));
                    record.setSaleName(com.mljr.gps.common.util.StringUtils.killNull(record.getSaleName()));
                    record.setOperationName(com.mljr.gps.common.util.StringUtils.killNull(record.getOperationName()));
                    record.setAppStatusDesc(AppInfoStatusEnum.getNameByIndex(record.getAppStatus()));

                });
            }
        } catch (Exception e) {
            LOGGER.error("{}异常,form={}", LOG_TITLE, JSON.toJSON(form), e);
            return Result.failInServer(PageVO.newInstance(totalCount, carGpsReList));
        }
        return Result.suc(PageVO.newInstance(totalCount, carGpsReList));
        //订单号 客户姓名 经销商门店 销售姓名 所属运营  gps安装数量 人工审核状态  自动验证状态 人工批注 订单状态 提交时间 更新时间
    }
}
