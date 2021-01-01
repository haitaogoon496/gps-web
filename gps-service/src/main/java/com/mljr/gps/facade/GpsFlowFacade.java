package com.mljr.gps.facade;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.GpsFlowConstant;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.page.PageForm;
import com.lyqc.gpsprovider.enums.CarGpsConstant;
import com.lyqc.gpsweb.dto.GpsSendDTO;
import com.mljr.annotation.LogMonitor;
import com.mljr.annotation.OvalValidator;
import com.mljr.common.TwoTuple;
import com.mljr.enums.LogTitleEnum;
import com.mljr.facade.PdConfigParamsFacade;
import com.mljr.gps.common.consts.GpsWebConstant;
import com.mljr.gps.common.consts.HeilCode;
import com.mljr.gps.common.enums.AutoSendGpsBillStatusEnum;
import com.mljr.gps.common.enums.QueryBuzMarkEnum;
import com.mljr.gps.common.util.DateUtils;
import com.mljr.gps.common.util.StringUtils;
import com.mljr.gps.component.GpsComponent;
import com.mljr.gps.component.SessionUserComponent;
import com.mljr.gps.entity.AppInfo;
import com.mljr.gps.entity.GpsAuditor;
import com.mljr.gps.entity.GpsFlow;
import com.mljr.gps.entity.SyRole;
import com.mljr.gps.form.GpsQueryListForm;
import com.mljr.gps.form.SyRoleForm;
import com.mljr.gps.service.*;
import com.mljr.gps.vo.GpsFlowVo;
import com.mljr.gps.vo.GpsQueryListVo;
import com.mljr.gps.vo.SyUserVo;
import com.mljr.gps.voconvertor.GpsFlowVoConvertor;
import com.mljr.model.PdConfigParams;
import com.mljr.redis.service.RedisUtil;
import com.mljr.service.PdConfigParamsService;
import com.mljr.util.CollectionsTools;
import com.mljr.util.NumberUtil;
import com.mljr.util.StringTools;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: GPS审批流程Facade
 * @Date : 2018/7/28 下午6:24
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Component
public class GpsFlowFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.GPS_APPROVE.getName();

    private final String AUTO_SEND_BILL_LOG_TITLE = "【GPS审核系统自动分单】";
    @Autowired
    private GpsFlowService gpsFlowService;
    @Autowired
    private GpsOperateRecordService gpsOperateRecordService;
    @Autowired
    private SessionUserComponent sessionUserComponent;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private GpsQueryListService gpsQueryListService;
    @Autowired
    private GpsComponent gpsComponent;

    @Autowired
    private PdConfigParamsService configParamsService;

    @Autowired
    private SyRoleService syRoleService;

    @Autowired
    private AppInfoService appInfoService;

    @Autowired
    private GpsApproveService gpsApproveService;

    @Autowired
    private PdConfigParamsFacade pdConfigParamsFacade;

    //@PostConstruct
    public void init() {
        PdConfigParams pdConfigParams = new PdConfigParams();
        String value = pdConfigParamsFacade.getValueByParamKey("gpsSendNum", String.class);
        if (StringUtils.isEmpty(value)) {
            pdConfigParams.setParamKey("gpsSendNum");
            pdConfigParams.setParamValue("3");
            pdConfigParams.setParamDesc("派单数量上限");
            pdConfigParamsFacade.insert(pdConfigParams);
            LOGGER.info("初始化PdConfigParam数据:派单数量上限");
        }
    }

    /**
     * GPS领单
     * @param appCode
     * @return
     */
    @LogMonitor("【GPS领单】")
    public Result<String> acceptBill(String appCode){
        try {
            Integer submitUserId = sessionUserComponent.getSessionUserId();
            String submitUserName = sessionUserComponent.getSessionTrueName();

            if(NumberUtil.isNull0(gpsFlowService.getHasPullCountByApproveUserId(submitUserId)) >= 5){
                return Result.fail(RemoteEnum.FAILURE,"亲：领单最多不能超过5单，请先去【我的GPS领单】审批去吧！");
            }
            return  allotOrder(appCode,submitUserId,submitUserName,submitUserId,submitUserName,"领单",false);
        } catch (Exception e) {
            LOGGER.error("GPS领单异常,appCode={}",LOG_TITLE,appCode,e);
            return Result.failInServer("领单失败");
        }
    }

    /**
     * 订单分配操作
     * @param appCode 订单号
     * @param submitUserId  提交人id
     * @param submitUserName 提交人name
     * @param approvalUserId 审批人id
     * @param approvalUserName 审批人name
     * @param remark  操作
     * @param hmSendBill 是否手动分单
     * @return
     */
    private Result<String> allotOrder(String appCode,Integer submitUserId,String submitUserName, Integer approvalUserId,
                                      String approvalUserName,String remark, boolean hmSendBill){
        boolean lockResult = false;
        String redisKey = redisUtil.getKeyWithSystemCode(MessageFormat.format("gpsAccept_{0}",appCode));
        String submitTime = TimeTools.format4YYYYMMDDHHMISS(TimeTools.createNowTime());
        try {
            //判断是否可以领单，测试人员只能领测试单，审核人员只能领线上单子
            if(!determineUserCanOrNotAcceptBill(appCode,approvalUserId)){
                return Result.fail(RemoteEnum.FAILURE,"亲：测试人员只能领取测试单子，审核人员只能领取正式单子。");
            }
            if(null != redisUtil.get(redisKey,String.class)){
                return Result.fail(RemoteEnum.FAILURE,"亲：申请单正在处理中，请勿操作！");
            }
            lockResult = redisUtil.lock(redisKey);
            if(lockResult) {
                GpsFlow gpsFlow = gpsFlowService.queryByAppCode(appCode);
                boolean hasFlow = null != gpsFlow;
                if (!hasFlow) {
                    gpsFlow = new GpsFlow();
                    gpsFlow.setAppCode(appCode);
                    gpsFlow.setSubmitProps(submitUserId, submitUserName, submitTime);
                } else {
                    GpsFlowConstant.GpsFlowStepEnum flowStepEnum = GpsFlowConstant.GpsFlowStepEnum.getByIndex(gpsFlow.getFlowStep());
                    if (GpsFlowConstant.GpsFlowStepEnum.PULL == flowStepEnum && !hmSendBill) {
                        return Result.fail(RemoteEnum.FAILURE, "亲：该申请单已被领单！");
                    }
                    if (GpsFlowConstant.GpsFlowStepEnum.APPROVAL == flowStepEnum && !hmSendBill) {
                        return Result.fail(RemoteEnum.FAILURE, "亲：该申请单审批中！");
                    }
                    if (GpsFlowConstant.GpsFlowStepEnum.END == flowStepEnum) {
                        return Result.fail(RemoteEnum.FAILURE, "亲：该申请单已审批完毕！");
                    }
                }
                gpsFlow.setApprovalProps(approvalUserId, approvalUserName, submitTime);
                gpsFlow.setFlowStep(GpsFlowConstant.GpsFlowStepEnum.PULL.getIndex());
                gpsFlow.setFlowStatus(GpsFlowConstant.GpsFlowStatusEnum.PROCESSING.getIndex());
                if (hasFlow) {
                    gpsFlowService.updateRecord(gpsFlow);
                } else {
                    gpsFlow.setViolationSituation(CarGpsConstant.ViolationSituationEnum.NO.getIndex());
                    gpsFlow.setViolationType("");
                    gpsFlowService.insertRecord(gpsFlow);
                }
                this.gpsOperateRecordService.insertRecord(GpsFlowConstant.GpsFlowStepEnum.PULL, (record) -> {
                    record.setSubmitProps(submitUserId, submitUserName, submitTime);
                    record.setAppCode(appCode);
                    record.setRemark(remark+"操作");
                });
            }else {
                return Result.fail(RemoteEnum.FAILURE, "亲：该申请单已经被领取！");
            }
        } catch (Exception e) {
            LOGGER.error("GPS"+remark+"异常,appCode={}", LOG_TITLE, appCode, e);
            return Result.failInServer(remark+"失败");
        } finally {
            if (lockResult) {
                redisUtil.unlock(redisKey);
            }
        }
        return Result.suc(remark+"成功");
    }

    /**
     * GPS派单
     * @param gpsSendDTO
     * @return
     */
    @LogMonitor("【GPS派单】")
    @OvalValidator("【GPS派单】")
    public Result<String> sendBill(GpsSendDTO gpsSendDTO) {
        String appCode = gpsSendDTO.getAppCode();
        /*String redisKey = redisUtil.getKeyWithSystemCode(MessageFormat.format("gpsSend_{0}",appCode));*/
        try {
            Integer submitUserId = sessionUserComponent.getSessionUserId();
            String submitUserName = sessionUserComponent.getSessionTrueName();
            Integer approveUserId = gpsSendDTO.getApproveUserId();
            String approveUserName = gpsSendDTO.getApproveUserName();

            //判断订单是否被退回至复审审批
            String message = gpsApproveService.canOperateGps(appCode);
            if (StringTools.isNotEmpty(message)){
                return Result.fail(HeilCode.E_400, message);
            }

            AppInfo appInfo = appInfoService.queryAppInfo(appCode);
            if (appInfo == null) {
                return Result.fail(RemoteEnum.WARN_EMPTY_RECORD);
            }
            if (StringUtils.isEmpty(appInfo.getIsGps())||!String.valueOf(CarGpsConstant.AppInfoIsGpsEnum.UPLOADED.getIndex()).equals(appInfo.getIsGps())) {
                CarGpsConstant.AppInfoIsGpsEnum gpsEnum = CarGpsConstant.AppInfoIsGpsEnum.getByIndex(Integer.parseInt(appInfo.getIsGps()));
                return Result.fail(RemoteEnum.ERROR_IN_SERVER, MessageFormat.format("当前订单状态不能派单,状态为【{0}】", gpsEnum.getName()));
            }
            GpsQueryListForm form = new GpsQueryListForm();
            form.setApprovalUserId(approveUserId);
            form.setApprovalUserName(approveUserName);
            form.setIsGps("2");
            form.setBuzMark(QueryBuzMarkEnum.GpsApproveMine.getName());
            form.setExcludeStatusScope(gpsComponent.getExcludeStatusScope(GpsComponent.ExcludeStatusScope.APPROVE_MINE_EXCLUDE_SCOPE));

            Integer count = gpsQueryListService.queryCountForGpsApproveMine(new PageForm<>(form));
            LOGGER.info("派单处理-appCode:{}-要派给的user:{}-{},该用户目前的拥有的单量:{}",appCode,approveUserId,approveUserName,count);
            Integer limit = pdConfigParamsFacade.getValueByParamKey("gpsSendNum", Integer.class);
            limit = limit == null ? 0 : limit;
            if (count >= limit) {
                return Result.fail(HeilCode.E_500, "您的领单列表已经达到上限,请先去处理吧");
            }
            return allotOrder(appCode,submitUserId,submitUserName,approveUserId,approveUserName,"派单",true);

        } catch (Exception e) {
            LOGGER.error("GPS派单异常,appCode={}",LOG_TITLE,appCode,e);
            return Result.failInServer("领单失败");
        }
    }

    @LogMonitor("根据appCode获取GpsFlow数据")
    public Result<GpsFlowVo> queryGpsFlow(String appCode) {
        GpsFlow gpsFlow = gpsFlowService.queryByAppCode(appCode);
        if (gpsFlow == null) {
            return Result.failInEmptyRecord(null);
        }
        GpsFlowVo gpsFlowVo = new GpsFlowVoConvertor().convert(gpsFlow);
        return Result.suc(gpsFlowVo);
    }

    public Result<String> autoSendBill(){
        Result<String> result= Result.suc("系统自动派单成功");
        LOGGER.info("{},GPS系统分单,>>>>>>>>>>>>>>>>开始此次系统自动分单",AUTO_SEND_BILL_LOG_TITLE);
        long start = System.currentTimeMillis();
        try {
            //1.如果系统自动开关关闭的，则不进行系统派单
            PdConfigParams configParams =  configParamsService.queryByParamKey("gpsWebAutoSendBillSwitch");
            if(configParams == null ||! "1".equals(configParams.getParamValue())){
                LOGGER.warn("{},GPS系统分单,加入系统派单开关关闭，不进行系统自动分单", AUTO_SEND_BILL_LOG_TITLE);
                result = Result.suc("加入系统派单开关关闭，不进行系统自动分单");
                return result;
            }
            //2.判断当前系统中有没有审核人员加入
            String currentDay = DateUtils.getCurrentDay(new Date());
            List<SyUserVo> gpsAcceptUserCacheVoList = redisUtil.get(redisUtil.getKeyWithSystemCode(GpsWebConstant.GPS_AUDITOR_JOIN_ACCEPT_BILL_POOL + currentDay), List.class);
            List<SyUserVo> gpsAcceptTestUserCacheVoList = redisUtil.get(redisUtil.getKeyWithSystemCode(GpsWebConstant.GPS_TEST_AUDITOR_JOIN_ACCEPT_BILL_POOL + currentDay), List.class);
            if(CollectionsTools.isNotEmpty(gpsAcceptUserCacheVoList)) {
                gpsAcceptUserCacheVoList = gpsAcceptUserCacheVoList.stream().filter(gpsvo -> (AutoSendGpsBillStatusEnum.ON.getIndex() == gpsvo.getAutoSendBillStatus().intValue())).collect(Collectors.toList());
            }
            if(CollectionsTools.isNotEmpty(gpsAcceptTestUserCacheVoList)) {
                gpsAcceptTestUserCacheVoList = gpsAcceptTestUserCacheVoList.stream().filter(gpsvo -> (AutoSendGpsBillStatusEnum.ON.getIndex() == gpsvo.getAutoSendBillStatus().intValue())).collect(Collectors.toList());
            }
            LOGGER.info("{}-gpsAcceptUserCacheVoList:{},gpsAcceptTestUserCacheVoList:{}", LOG_TITLE, JSON.toJSON(gpsAcceptUserCacheVoList), JSON.toJSON(gpsAcceptTestUserCacheVoList));
            if (CollectionsTools.isEmpty(gpsAcceptUserCacheVoList) && CollectionsTools.isEmpty(gpsAcceptTestUserCacheVoList) ) {
                LOGGER.warn("{},GPS系统分单,没有审核人员加入派单，不进行系统自动分单", AUTO_SEND_BILL_LOG_TITLE);
                result = Result.suc("没有审核人员加入派单，不进行系统自动分单");
                return result;
            }
            LOGGER.info("{},GPS系统分单,当前加入自动分单人员：{}", AUTO_SEND_BILL_LOG_TITLE,JSON.toJSONString(gpsAcceptUserCacheVoList));
            //3. 查询gps领单列表，如果没查询到，则不进行自动派单
            PageForm<GpsQueryListForm> form = new PageForm<>();
            GpsQueryListForm gpsQueryListForm = new GpsQueryListForm();
            gpsQueryListForm.setIsGps(String.valueOf(CarGpsConstant.AppInfoIsGpsEnum.UPLOADED.getIndex()));
            gpsQueryListForm.setExcludeStatusScope(gpsComponent.getExcludeStatusScope(GpsComponent.ExcludeStatusScope.APPROVE_PULL_EXCLUDE_SCOPE));
            form.setForm(gpsQueryListForm);
            List<GpsQueryListVo> gpsQueryListVoList = gpsQueryListService.queryListForGpsApprovePull(form);
            if(CollectionsTools.isEmpty(gpsQueryListVoList)) {
                LOGGER.info("{},GPS系统分单,目前系统中没有待领单的单子，不进行系统自动分单", AUTO_SEND_BILL_LOG_TITLE);
                result = Result.suc("目前系统中没有待领单的单子，不进行系统自动分单");
                return result;
            }
            //4. 将gps领单列表分为测试单、生产单两个列表，分别进行派单
            int upperLimit = 1;
            PdConfigParams upperLimitConfigParams = configParamsService.queryByParamKey(GpsWebConstant.GPS_WEB_AUTO_SEND_BILL_UPPER_LIMIT);
            if(upperLimitConfigParams != null && StringUtils.isIntegeric(upperLimitConfigParams.getParamValue())) {
                upperLimit = StringUtils.toInteger(upperLimitConfigParams.getParamValue()) > 1 ? StringUtils.toInteger(upperLimitConfigParams.getParamValue()).intValue() : 1;
            }
            PdConfigParams pdConfigParams = configParamsService.queryByParamKey(GpsWebConstant.GPS_ACCEPT_BILL_TEST_DEALERCODE);
            List<String> dealerCodes = new ArrayList<>();
            if(pdConfigParams != null && pdConfigParams.getParamValue() != null){
                dealerCodes = Arrays.asList(pdConfigParams.getParamValue().split(",")).stream().map(s -> s.trim()).collect(Collectors.toList());
            }
            List<String> finalDealerCodes = dealerCodes;
            List<GpsQueryListVo> testGpsList = gpsQueryListVoList.stream().filter(gpsVo -> finalDealerCodes.contains(gpsVo.getDealerCode())).collect(Collectors.toList());
            LOGGER.info("{}-测试单量:{}", LOG_TITLE, testGpsList);
            List<GpsQueryListVo> gpsList = gpsQueryListVoList.stream().filter(gpsVo -> !finalDealerCodes.contains(gpsVo.getDealerCode())).collect(Collectors.toList());
            LOGGER.info("{}-生产单量:{}",LOG_TITLE,gpsList);
            if(CollectionsTools.isNotEmpty(testGpsList)){
                for(GpsQueryListVo gpsQueryListVo : testGpsList){
                    TwoTuple<Integer, String> twoTuple = gpsSystemFenDan(gpsQueryListVo.getAppCode(), Boolean.TRUE, upperLimit);
                    if(twoTuple != null && twoTuple.getA().intValue() == -1) {
                        break;
                    }
                }
            }
            if(CollectionsTools.isNotEmpty(gpsList)){
                for(GpsQueryListVo gpsQueryListVo : gpsList){
                    TwoTuple<Integer, String> twoTuple = gpsSystemFenDan(gpsQueryListVo.getAppCode(), Boolean.FALSE, upperLimit);
                    if(twoTuple != null && twoTuple.getA().intValue() == -1) {
                        break;
                    }
                }
            }
        }catch (Exception e){
            LOGGER.error("gpsWeb系统自动分单失败,异常=",e);
        }finally {
            LOGGER.info("{},GPS系统分单,<<<<<<<<<<<<<<<<<此次系统自动分单结束,耗时：{}",AUTO_SEND_BILL_LOG_TITLE,(System.currentTimeMillis() - start));
        }
        return  result;
    }

    /**
     * 开始自动派单
     * @param appCode 申请单号
     * @param isTestOrder 是否测试单，true 是，false 不是
     * @param upperLimit 持有单量上限
     * @return
     */
    public TwoTuple<Integer,String> gpsSystemFenDan(String appCode, boolean isTestOrder, int upperLimit){
        LOGGER.info("{}-appCode:{},{},limit:{}", LOG_TITLE, appCode, isTestOrder ? "测试订单" : "生产订单", upperLimit);
        TwoTuple<Integer,String> twoTuple = new TwoTuple<>(1,"派单成功");
        /*boolean lockResult = false;
        String redisKey = redisUtil.getKeyWithSystemCode(MessageFormat.format("gpsAccept_{0}",appCode));*/
        try {
            // 1. 获取分单人员集合，不用前面的原因是避免在遍历单子的时候审核人员已经退出了
            String auditorJoinAccpetBillPool = isTestOrder ? GpsWebConstant.GPS_TEST_AUDITOR_JOIN_ACCEPT_BILL_POOL:GpsWebConstant.GPS_AUDITOR_JOIN_ACCEPT_BILL_POOL;
            String currentDay = DateUtils.getCurrentDay(new Date());
            List<SyUserVo> gpsAcceptUserCacheVoList = redisUtil.get(redisUtil.getKeyWithSystemCode(auditorJoinAccpetBillPool + currentDay), List.class);
            if (CollectionsTools.isNotEmpty(gpsAcceptUserCacheVoList)) {
                gpsAcceptUserCacheVoList = gpsAcceptUserCacheVoList.stream().filter(gpsvo -> (AutoSendGpsBillStatusEnum.ON.getIndex() == gpsvo.getAutoSendBillStatus().intValue())).collect(Collectors.toList());
            }
            LOGGER.warn("{},GPS系统分单,当前加入系统分单的人员： {}:{}", AUTO_SEND_BILL_LOG_TITLE, auditorJoinAccpetBillPool,JSON.toJSONString(gpsAcceptUserCacheVoList));
            if (CollectionsTools.isEmpty(gpsAcceptUserCacheVoList)) {
                LOGGER.warn("{},GPS系统分单,没有审核人员加入派单，不进行系统自动分单,appCode={}", AUTO_SEND_BILL_LOG_TITLE, appCode);
                twoTuple = new TwoTuple<>(-1,"没有审核人员加入派单，不进行系统自动分单");
                return twoTuple;
            }
            // 2. 获取每个审核人员目前拥有的单量，没有单子默认为 0
            List<Integer> userIds = gpsAcceptUserCacheVoList.stream().map(SyUserVo::getUserId).collect(Collectors.toList());
            List<GpsAuditor> gpsAuditors = gpsFlowService.getMinGpsApprUsers(userIds);
            Map<Integer, Integer> gpsAuditorsMap = gpsAuditors.stream().collect(Collectors.toMap(GpsAuditor::getApprovalUserId, GpsAuditor::getNum, (k1, k2) -> k1));
            userIds.stream().forEach(per -> {
                if (!gpsAuditorsMap.containsKey(per)) {
                    gpsAuditorsMap.put(per, 0);
                }
            });
            LOGGER.info("{},GPS系统分单,目前审核人员所持有单子的单量：{}", AUTO_SEND_BILL_LOG_TITLE, JSON.toJSONString(gpsAuditorsMap));
            // 3. 如果最少持有单量超过上限，则不进行自动派单
            List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(gpsAuditorsMap.entrySet());
            Integer num = entryList.stream().min((o1, o2) -> (o1.getValue() - o2.getValue())).get().getValue();
            if(num != null && num.intValue() >= upperLimit){
                LOGGER.warn("{},GPS系统分单,加入{}自动分单队列的人员都拥有{}单，暂时不进行分单,appCode={}", AUTO_SEND_BILL_LOG_TITLE,isTestOrder?"测试":"正式", upperLimit, appCode);
                twoTuple = new TwoTuple<>(-1,MessageFormat.format("加入自动分单队列的人员都拥有{0}单，暂时不进行分单",upperLimit));
                return twoTuple;
            }
            // 4. 获取所有持有单量最少的人列表，最终筛选出来的需要分单的用户，筛选出来单量持有最少，并且最久未领单的人员，进行开始分单
            List<Integer> minOrderUser = entryList.stream().filter(ent -> Objects.equals(ent.getValue(), num)).map(i -> i.getKey()).collect(Collectors.toList());
            SyUserVo fenDanUser = null;
            Long timeStamp = null;
            for (SyUserVo vo : gpsAcceptUserCacheVoList) {
                if (minOrderUser.contains(vo.getUserId())) {
                    Long perRedisTimeStamp = redisUtil.get(redisUtil.getKeyWithSystemCode(GpsWebConstant.GPS_AUDITOR_ACCPET_BILL_TIME_STAMP + vo.getUserId() + ":" + currentDay), Long.class);
                    // 假如审核人员今日没有审过单子，则直接将单子给他
                    if(perRedisTimeStamp == null){
                        fenDanUser = vo;
                        break;
                    }
                    if (timeStamp == null) {
                        timeStamp = perRedisTimeStamp;
                        fenDanUser = vo;
                    } else {
                        if (perRedisTimeStamp < timeStamp) {
                            timeStamp = perRedisTimeStamp;
                            fenDanUser = vo;
                        }
                    }
                }
            }
            if (fenDanUser == null) {
                LOGGER.warn("{},GPS系统分单,没有审核人员可以分单,appCode={}", AUTO_SEND_BILL_LOG_TITLE, appCode);
                twoTuple = new TwoTuple<>(-1,"没有审核人员可以分单");
                return twoTuple;
            }

            // 5. 开始分单
            Integer approvalUserId = fenDanUser.getUserId();
            String approvalUserName = fenDanUser.getTrueName();

            Integer submitUserId = -1;
            String submitUserName = "系统";

            Result<String> result = allotOrder(appCode,submitUserId,submitUserName,approvalUserId,approvalUserName,"系统自动分单",false);
            if(!result.isSuccess()){
                twoTuple = new TwoTuple<>(0,result.getMsg());
            }

            /*String submitTime = TimeTools.format4YYYYMMDDHHMISS(TimeTools.createNowTime());
            if (null != redisUtil.get(redisKey, String.class)) {
                LOGGER.warn("{},GPS系统分单,申请单正在处理中,appCode={}", AUTO_SEND_BILL_LOG_TITLE, appCode);
                twoTuple = new TwoTuple<>(0,"GPS系统分单,申请单正在处理中...");
                return twoTuple;
            }
            lockResult = redisUtil.lock(redisKey);
            if (lockResult) {
                GpsFlow gpsFlow = gpsFlowService.queryByAppCode(appCode);
                boolean hasFlow = null != gpsFlow;
                if (!hasFlow) {
                    gpsFlow = new GpsFlow();
                    gpsFlow.setAppCode(appCode);
                    gpsFlow.setSubmitProps(submitUserId, submitUserName, submitTime);
                } else {
                    GpsFlowConstant.GpsFlowStepEnum flowStepEnum = GpsFlowConstant.GpsFlowStepEnum.getByIndex(gpsFlow.getFlowStep());
                    if (GpsFlowConstant.GpsFlowStepEnum.PULL == flowStepEnum) {
                        LOGGER.warn("{},GPS系统分单,该申请单已被领单,appCode={}", AUTO_SEND_BILL_LOG_TITLE, appCode);
                        twoTuple = new TwoTuple<>(0,"GPS系统分单,该申请单已被领单");
                        return twoTuple;
                    }
                    if (GpsFlowConstant.GpsFlowStepEnum.APPROVAL == flowStepEnum) {
                        LOGGER.warn("{},GPS系统分单,该申请单审批中,appCode={}", AUTO_SEND_BILL_LOG_TITLE, appCode);
                        twoTuple = new TwoTuple<>(0,"GPS系统分单,该申请单审批中");
                        return twoTuple;
                    }
                    if (GpsFlowConstant.GpsFlowStepEnum.END == flowStepEnum) {
                        LOGGER.warn("{},GPS系统分单,该申请单已审批完毕,appCode={}", AUTO_SEND_BILL_LOG_TITLE, appCode);
                        twoTuple = new TwoTuple<>(0,"GPS系统分单,该申请单已审批完毕");
                        return twoTuple;
                    }
                }
                gpsFlow.setApprovalProps(approvalUserId, approvalUserName, submitTime);
                gpsFlow.setFlowStep(GpsFlowConstant.GpsFlowStepEnum.PULL.getIndex());
                gpsFlow.setFlowStatus(GpsFlowConstant.GpsFlowStatusEnum.PROCESSING.getIndex());
                if (hasFlow) {
                    gpsFlowService.updateRecord(gpsFlow);
                } else {
                    gpsFlow.setViolationSituation(CarGpsConstant.ViolationSituationEnum.NO.getIndex());
                    gpsFlow.setViolationType("");
                    gpsFlowService.insertRecord(gpsFlow);
                }
                this.gpsOperateRecordService.insertRecord(GpsFlowConstant.GpsFlowStepEnum.PULL, (record) -> {
                    record.setSubmitProps(submitUserId, submitUserName, submitTime);
                    record.setAppCode(appCode);
                    record.setRemark("系统自动分单操作");
                });

                //领单成功，更新此人上次领单时间戳
                redisUtil.set(redisUtil.getKeyWithSystemCode(GpsWebConstant.GPS_AUDITOR_ACCPET_BILL_TIME_STAMP+fenDanUser.getUserId()+":"+currentDay),System.currentTimeMillis(),12*60*60);

                LOGGER.info("{},GPS系统分单,分单成功，审核人员：{}，订单号：{}", AUTO_SEND_BILL_LOG_TITLE, approvalUserId, appCode);
            } else {
                LOGGER.warn("{},GPS系统分单,该申请单已经被领取,appCode={}", AUTO_SEND_BILL_LOG_TITLE, appCode);
                twoTuple = new TwoTuple<>(0,"GPS系统分单,该申请单已经被领取");
                return twoTuple;
            }*/
        } catch (Exception e) {
            LOGGER.error("GPS系统分单异常,appCode={}",AUTO_SEND_BILL_LOG_TITLE,appCode,e);
        }/*finally {
            if (lockResult) {
                redisUtil.unlock(redisKey);
            }
        }*/
        return twoTuple;
    }

    /**
     * 判断是否可以领单，测试人员只能领测试单，审核人员只能领线上单子
     * @param appCode
     * @param userId
     * @return
     */
    private boolean determineUserCanOrNotAcceptBill(String appCode, Integer userId){
        try {
            boolean isTestUser = false;
            boolean isTestDealer = false;
            // 查询用户所有角色
            List<SyRole> userRoleList = syRoleService.queryList(new SyRoleForm(userId, "GPS审核领单测试人员"));
            if(userRoleList != null && userRoleList.size() > 0) {
                isTestUser = true;
            }
            isTestDealer = determineOrderIsTest(appCode);
            return isTestUser == isTestDealer;
        }catch (Exception e){
            LOGGER.warn("当前用户：{}，校验当前单号是否可以领取：{}异常，默认成功。");
           return true;
        }
    }

    /**
     * 判断订单是否为测试订单
     * @return
     */
    private boolean determineOrderIsTest(String appCode){
        AppInfo appInfo = appInfoService.queryAppInfo(appCode);
        PdConfigParams pdConfigParams = configParamsService.queryByParamKey(GpsWebConstant.GPS_ACCEPT_BILL_TEST_DEALERCODE);
        List<Integer> dealerCodes = new ArrayList<>();
        if(pdConfigParams != null && pdConfigParams.getParamValue() != null){
            dealerCodes = Arrays.asList(pdConfigParams.getParamValue().split(",")).stream().map(s -> Integer.valueOf(s.trim())).collect(Collectors.toList());
        }
        if(appInfo != null && dealerCodes != null && dealerCodes.contains(appInfo.getDealerCode())){
            return true;
        }else {
            return false;
        }
    }

}
