package com.mljr.gps.facade;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.mljr.annotation.Action;
import com.mljr.annotation.LogMonitor;
import com.mljr.gps.common.consts.GpsWebConstant;
import com.mljr.gps.common.enums.AutoSendGpsBillStatusEnum;
import com.mljr.gps.common.util.DateUtils;
import com.mljr.gps.common.util.StringUtils;
import com.mljr.gps.component.SessionUserComponent;
import com.mljr.gps.component.UserOperateLogComponent;
import com.mljr.gps.entity.SyButton;
import com.mljr.gps.entity.SyDealer;
import com.mljr.gps.entity.SyItem;
import com.mljr.gps.entity.SyRole;
import com.mljr.gps.entity.SyUser;
import com.mljr.gps.form.GpsMonitorForm;
import com.mljr.gps.form.SyDealerForm;
import com.mljr.gps.form.SyRoleForm;
import com.mljr.gps.service.GpsFlowService;
import com.mljr.gps.service.SyButtonService;
import com.mljr.gps.service.SyDealerService;
import com.mljr.gps.service.SyItemService;
import com.mljr.gps.service.SyRoleService;
import com.mljr.gps.vo.GpsMonitorVo;
import com.mljr.gps.vo.SyItemVo;
import com.mljr.gps.vo.SyUserVo;
import com.mljr.gps.voconvertor.SyItemVoConvertor;
import com.mljr.model.PdConfigParams;
import com.mljr.redis.service.RedisUtil;
import com.mljr.service.PdConfigParamsService;
import com.mljr.util.CollectionsTools;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:
 * @Date : 2018/3/21$ 17:10$
 * @Author : liht
 */
@Service
@Slf4j
public class SyItemFacade  {

//    private final UserOperateLogConstant.AuthModelEnum authModelEnum = UserOperateLogConstant.AuthModelEnum.PD_CONFIG_PARAM;

    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.PD_CONFIG_PARAM;


    private static final Logger logger = LoggerFactory.getLogger(SyItemFacade.class);
    @Resource
    private SyItemService syItemService;
    @Resource
    private SyRoleService syRoleService;
    @Autowired
    private SessionUserComponent sessionUserComponent;
    @Autowired
    private PdConfigParamsService configParamsService;
    @Autowired
    private GpsFlowService gpsFlowService;
    @Autowired
    private SyDealerService syDealerService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private SyButtonService syButtonService;
    @Autowired
    RedisUtil redisUtil;

    public Result<List<SyItemVo>> querySyItemsByRoles(SyUser syUser) {

        SyRoleForm form = new SyRoleForm();
        form.setUserId(syUser.getUserId());
        List<SyRole> roleList = this.syRoleService.queryList(form);
        log.info("querySyItemsByRoles - userRole:{}", JSON.toJSON(roleList));
        if (CollectionsTools.isEmpty(roleList)) {
            return Result.failInEmptyRecord(null);
        }
        List<Integer> roleIds = roleList.stream().map(record -> record.getRoleId()).collect(Collectors.toList());
        List<SyItem> syItems = syItemService.selectMenuItemByRoles(roleIds);
        List<SyButton> syButtons = syButtonService.selectSyButtonsByRoles(roleIds).stream().filter(syButton -> !StringUtils.isEmpty(syButton.getItemId())).collect(Collectors.toList());
        log.info("当前用户的所有按钮数据:{}", JSON.toJSON(syButtons));

        syItems = syItems.stream().filter(syItem ->  syItem.getItemTitle().contains("GPS")).collect(Collectors.toList());

        if (CollectionsTools.isEmpty(syItems)) {
            return Result.failInEmptyRecord(null);
        }
        List<SyItemVo> syItemVos = new SyItemVoConvertor().convertList(syItems);
        syItemVos.forEach(syItemVo -> {
            List<SyButton> syButtonListTemp = syButtons.stream().filter(syButton -> syButton.getItemId().equals(syItemVo.getItemId())).collect(Collectors.toList());
            syItemVo.setSyButtons(syButtonListTemp);
        });
        log.info("根据角色获取菜单 - querySyItemsByRoles-结果:{}", JSON.toJSON(syItemVos));
        return Result.suc(syItemVos);
    }

    /**
     * 审核人员加入领单队列或移除
     * @param syUser
     * @param yesOrNo
     * @return
     */
    public Result<Object> updateSystemSendBill(SyUser syUser, String yesOrNo) {
        String lockKey = null;
        boolean isLock = false;
        try {
            String currentDay = DateUtils.getCurrentDay(new Date());
            if (syUser == null || syUser.getUserId() == null || syUser.getUserName() == null || yesOrNo == null) {
                return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM.getIndex(), "审核人员信息为空");
            }
            //判断是不是测试人员
            boolean isTestUser =isTestUser(syUser.getUserId());
            //根据用户是否为测试人员来加入不同的审核人员领单池子。
            String auditorJoinAccpetBillPool = isTestUser?GpsWebConstant.GPS_TEST_AUDITOR_JOIN_ACCEPT_BILL_POOL:GpsWebConstant.GPS_AUDITOR_JOIN_ACCEPT_BILL_POOL;
            lockKey = redisUtil.getKeyWithSystemCode(GpsWebConstant.GPS_AUDITOR_POOL_STATUS_OPERATE_LOCK);
            isLock = redisUtil.lock(lockKey,60);
            if (isLock) {
                List<SyUserVo> gpsAcceptUserCacheVoList = redisUtil.get(redisUtil.getKeyWithSystemCode(auditorJoinAccpetBillPool + currentDay), List.class);
                if (gpsAcceptUserCacheVoList == null) {
                    gpsAcceptUserCacheVoList = new ArrayList<>();
                }
                //已经加入系统派单的审核人员id
                List<Integer> userIds = gpsAcceptUserCacheVoList.stream().map(SyUserVo::getUserId).collect(Collectors.toList());
                if (AutoSendGpsBillStatusEnum.ON.getCode().equals(yesOrNo)) {
                    //加入
                    if (!userIds.contains(syUser.getUserId())) {
                        SyUserVo gpsAcceptUserCacheVo = new SyUserVo();
                        gpsAcceptUserCacheVo.setUserId(syUser.getUserId());
                        gpsAcceptUserCacheVo.setUserName(syUser.getUserName());
                        gpsAcceptUserCacheVo.setTrueName(syUser.getTrueName());
                        gpsAcceptUserCacheVo.setAutoSendBillStatus(AutoSendGpsBillStatusEnum.ON.getIndex());
                        gpsAcceptUserCacheVoList.add(gpsAcceptUserCacheVo);
                        logger.info("【gps审核人员加入自动领单开关】gps审核人员加入领单队列，userId:{}",syUser.getUserId());
                    }else {
                        gpsAcceptUserCacheVoList.forEach(per ->{
                            if(per.getUserId().intValue() == syUser.getUserId().intValue()){
                                per.setAutoSendBillStatus(AutoSendGpsBillStatusEnum.ON.getIndex());
                            }
                        });
                    }
                } else if (AutoSendGpsBillStatusEnum.OFF.getCode().equals(yesOrNo)) {
                    //移除
                    if (userIds.contains(syUser.getUserId())) {
                        gpsAcceptUserCacheVoList.forEach(per ->{
                            if(per.getUserId().intValue() == syUser.getUserId().intValue()){
                                per.setAutoSendBillStatus(AutoSendGpsBillStatusEnum.OFF.getIndex());
                            }
                        });
                        logger.info("【gps审核人员加入自动领单开关】gps审核人员离开领单队列,userId:{}",syUser.getUserId());
                    }
                }
                redisUtil.set(redisUtil.getKeyWithSystemCode(auditorJoinAccpetBillPool + currentDay), gpsAcceptUserCacheVoList, 12 * 60 * 60);
                logger.info("【gps审核人员加入自动领单开关】当前加入{}领单队列人员：{}",isTestUser?"测试":"正式",JSON.toJSONString(gpsAcceptUserCacheVoList));
            }else {
                return Result.fail();
            }
        }catch (Exception e){
            logger.warn("gps审核人员加入/移除领单队列异常",e);
            return Result.fail();
        }finally {
            if(isLock){
                redisUtil.del(lockKey);
            }
        }
        return Result.suc();
    }

    /**
     * 判断当前用户是否加入了系统
     * @param syUser
     * @return
     */
    public Result<String> isAddedSystemSendBill(SyUser syUser){
        try {
            PdConfigParams configParams =  configParamsService.queryByParamKey("gpsWebAutoSendBillSwitch");
            //灰度开关
            if(configParams != null && "1".equals(configParams.getParamValue())) {
                String currentDay = DateUtils.getCurrentDay(new Date());
                if (syUser == null || syUser.getUserId() == null || syUser.getUserName() == null) {
                    return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM.getIndex(), "审核人员信息为空");
                }
                //判断是不是测试人员
                boolean isTestUser = isTestUser(syUser.getUserId());
                //根据用户是否为测试人员来加入不同的审核人员领单池子。
                String auditorJoinAccpetBillPool = isTestUser ? GpsWebConstant.GPS_TEST_AUDITOR_JOIN_ACCEPT_BILL_POOL : GpsWebConstant.GPS_AUDITOR_JOIN_ACCEPT_BILL_POOL;
                List<SyUserVo> gpsAcceptUserCacheVoList = redisUtil.get(redisUtil.getKeyWithSystemCode(auditorJoinAccpetBillPool + currentDay), List.class);
                if (gpsAcceptUserCacheVoList == null) {
                    gpsAcceptUserCacheVoList = new ArrayList<>();
                }
                for(SyUserVo per: gpsAcceptUserCacheVoList){
                    if(per.getUserId().intValue() == syUser.getUserId().intValue() && per.getAutoSendBillStatus() == AutoSendGpsBillStatusEnum.ON.getIndex()){
                        return Result.suc("ON");
                    }
                }
                return Result.suc("OFF");
            }
        }catch (Exception e){
            logger.warn("判断当前用户是否加入了系统异常",e);
            return  Result.fail(RemoteEnum.USER_NO_PERMISSION,"用户无访问权限");
        }
        return  Result.fail(RemoteEnum.USER_NO_PERMISSION,"用户无访问权限");
    }

    private boolean isTestUser(Integer userId){
        try {
            List<SyRole> userRoleList = syRoleService.queryList(new SyRoleForm(userId, "GPS审核领单测试人员"));
            if(userRoleList != null && userRoleList.size() > 0) {
                return true;
            }
        }catch (Exception e){
            logger.warn("获取当前用户角色异常");
        }
        return false;
    }

    /**
     * 获取GPS指定自动派单审核人员池人员列表
     * @param isTest 是否为测试列表，true 是，false 不是
     * @return
     */
    public List<SyUserVo> getGpsAutoSendBillAuditorPoolList(boolean isTest) {
        String key = isTest ? GpsWebConstant.GPS_TEST_AUDITOR_JOIN_ACCEPT_BILL_POOL : GpsWebConstant.GPS_AUDITOR_JOIN_ACCEPT_BILL_POOL;
        String currentDay = DateUtils.getCurrentDay(new Date());
        List<SyUserVo> users = redisUtil.get(redisUtil.getKeyWithSystemCode(key + currentDay), List.class);
        if(CollectionsTools.isEmpty(users)) {
            return new ArrayList<>(0);
        }
        return users;
    }

    /**
     * 将指定人员移除指定自动派单审核人员列表
     * @param isTest 是否为测试列表，true 是，false 不是
     * @param userIds 要移除的审核人员ID列表
     */
    public String removeUserSystemSendBillList(boolean isTest, List<Integer> userIds, SyUser syUser) {
        if(CollectionsTools.isEmpty(userIds)) {
            return "至少指定一个用户";
        }
        String currentDay = DateUtils.getCurrentDay(new Date());
        String key = isTest ? GpsWebConstant.GPS_TEST_AUDITOR_JOIN_ACCEPT_BILL_POOL : GpsWebConstant.GPS_AUDITOR_JOIN_ACCEPT_BILL_POOL;
        try {
            List<SyUserVo> gpsAcceptUserCacheVoList = redisUtil.get(redisUtil.getKeyWithSystemCode(key + currentDay), List.class);
            if(CollectionsTools.isEmpty(gpsAcceptUserCacheVoList)) {
                return "审核池中目前没有审核人员";
            }
            gpsAcceptUserCacheVoList.forEach(per ->{
                if(userIds.contains(per.getUserId())) {
                    per.setAutoSendBillStatus(AutoSendGpsBillStatusEnum.OFF.getIndex());
                }
            });
            logger.info("【gps自动派单批量从{}审核人员列表池中移除】移除人员：{}，移除后领单队列人员：{}，操作人员ID：{}", isTest ? "测试" : "生产", JSON.toJSONString(userIds),JSON.toJSONString(gpsAcceptUserCacheVoList),syUser.getUserId());
            redisUtil.set(redisUtil.getKeyWithSystemCode(key + currentDay), gpsAcceptUserCacheVoList, 12 * 60 * 60);
            return null;
        } catch (Exception e) {
            logger.warn("GPS监控设置保存异常", e);
            e.printStackTrace();
            return "系统异常";
        }
    }

    /**
     * 查询 GPS 监控信息
     * @return
     */
    @LogMonitor(value = "GPS监控设置", action = @Action("查询GPS监控信息"))
    public Result<GpsMonitorVo> queryGpsMonitor() {
        try {
            List<SyUserVo> gpsAcceptUserCacheVoList = getGpsAutoSendBillAuditorPoolList(Boolean.FALSE);
            List<SyUserVo> testGpsAcceptUserCacheVoList = getGpsAutoSendBillAuditorPoolList(Boolean.TRUE);
            int upperLimit = 1;
            PdConfigParams configParams = configParamsService.queryByParamKey(GpsWebConstant.GPS_WEB_AUTO_SEND_BILL_UPPER_LIMIT);
            if(configParams != null && StringUtils.isIntegeric(configParams.getParamValue())) {
                upperLimit = StringUtils.toInteger(configParams.getParamValue()) > 1 ? StringUtils.toInteger(configParams.getParamValue()).intValue() : 1;
            }
            configParams = configParamsService.queryByParamKey(GpsWebConstant.GPS_ACCEPT_BILL_TEST_DEALERCODE);
            List<Integer> dealerCodes = new ArrayList<>(0);
            if(configParams != null && !StringUtils.isEmpty(configParams.getParamValue())){
                dealerCodes = Arrays.asList(configParams.getParamValue().split(",")).stream().map(s -> Integer.valueOf(s.trim())).collect(Collectors.toList());
            }
            GpsMonitorVo build = GpsMonitorVo.builder().gpsAcceptUserCacheVoList(gpsAcceptUserCacheVoList)
                    .testGpsAcceptUserCacheVoList(testGpsAcceptUserCacheVoList).upperLimit(upperLimit)
                    .gpsWebAcceptBillTestDealerCode(dealerCodes).build();
            return Result.suc(build);
        } catch (Exception e) {
            logger.warn("获取GPS自动派单审核人员池人员列表异常", e);
            return Result.fail(RemoteEnum.ERROR_IN_RUNTIME, "获取GPS自动派单审核人员池人员列表异常");
        }
    }

    /**
     * 保存GPS监控信息
     * @param gpsMonitorForm
     * @param syUser
     * @return
     */
    @LogMonitor(value = "GPS监控设置", action = @Action("保存GPS监控信息"))
    public Result<GpsMonitorVo> saveGpsMonitor(GpsMonitorForm gpsMonitorForm, SyUser syUser) {
        GpsMonitorVo monitorVo = GpsMonitorVo.builder().upperLimitFlag(Boolean.TRUE).dealerCodeFlag(Boolean.TRUE)
                .gpsRemoveFlag(Boolean.TRUE).gpsRemoveTestFlag(Boolean.TRUE).build();
        try {
            if(gpsMonitorForm == null) {
                return Result.fail(RemoteEnum.ILLEGAL_ARGUMENTS, "参数不能为空");
            }
            // 1. 从生产审核人员自动派单池中移除指定人员
            if(CollectionsTools.isNotEmpty(gpsMonitorForm.getRemoveGpsUserIds())) {
                String msg = removeUserSystemSendBillList(Boolean.FALSE, gpsMonitorForm.getRemoveGpsUserIds(), syUser);
                if(msg != null) {
                    monitorVo.setGpsRemoveFlag(Boolean.FALSE);
                    monitorVo.setFailGpsRemoveDesc(msg);
                }
            }
            // 2. 从测试审核人员自动派单池中移除指定人员
            if(CollectionsTools.isNotEmpty(gpsMonitorForm.getRemoveTestGpsUserIds())) {
                String msg = removeUserSystemSendBillList(Boolean.TRUE, gpsMonitorForm.getRemoveTestGpsUserIds(), syUser);
                if(msg != null) {
                    monitorVo.setGpsRemoveTestFlag(Boolean.FALSE);
                    monitorVo.setFailGpsRemoveTestDesc(msg);
                }
            }
            // 3. 设置审核人员持有单量上限
            if(gpsMonitorForm.getUpperLimit() != null) {
                PdConfigParams pdConfigParams = PdConfigParams.builder().paramKey(GpsWebConstant.GPS_WEB_AUTO_SEND_BILL_UPPER_LIMIT)
                        .paramValue(gpsMonitorForm.getUpperLimit().toString()).paramDesc("【gps审批平台】 自动派单审核人持有单量上限（持有量包括此值，未配置 or 配置为 0 默认为 1）")
                        .status(1).build();
                int params = configParamsService.savePdConfigParams(pdConfigParams);
                if(params == 0) {
                    monitorVo.setUpperLimitFlag(Boolean.FALSE);
                    monitorVo.setFailUpperLimitDesc("系统异常");
                }
                saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("修改param_key={0},param_value={1}", GpsWebConstant.GPS_WEB_AUTO_SEND_BILL_UPPER_LIMIT, gpsMonitorForm.getUpperLimit()));
            }
            // 4. 设置测试门店
            if(CollectionsTools.isNotEmpty(gpsMonitorForm.getGpsWebAcceptBillTestDealerCode())) {
                List<Integer> dealerCodes = gpsMonitorForm.getGpsWebAcceptBillTestDealerCode();
                List<SyDealer> syDealers = syDealerService.loadRecords(new PageForm<>(false, SyDealerForm.builder().dealerCodes(dealerCodes).build()));
                List<Integer> dealerCodesList = syDealers.stream().map(SyDealer::getDealerCode).collect(Collectors.toList());
                Set<Integer> difference = Sets.difference(Sets.newHashSet(dealerCodes),Sets.newHashSet(dealerCodesList));
                Set<Integer> intersection = Sets.intersection(Sets.newHashSet(dealerCodes),Sets.newHashSet(dealerCodesList));
                String intersectionStr = intersection.stream().map(id -> id.toString()).collect(Collectors.joining(","));
                PdConfigParams pdConfigParams = PdConfigParams.builder().paramKey(GpsWebConstant.GPS_ACCEPT_BILL_TEST_DEALERCODE)
                        .paramValue(intersectionStr).paramDesc("gps领单中所认为的测试门店，只有测试人员才能领取测试门店下的单子。格式：dealerCode,dealerCode")
                        .status(1).build();
                int params = configParamsService.savePdConfigParams(pdConfigParams);
                if(params == 0) {
                    monitorVo.setDealerCodeFlag(Boolean.FALSE);
                    monitorVo.setFailDealerCode(dealerCodes);
                    monitorVo.setFailDealerCodeDesc("系统异常");
                } else if (!CollectionsTools.isEmpty(difference)){
                    monitorVo.setDealerCodeFlag(Boolean.FALSE);
                    monitorVo.setFailDealerCode(difference.stream().collect(Collectors.toList()));
                    monitorVo.setFailDealerCodeDesc("门店不存在，请核对是否有误");
                }
                saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("修改param_key={0},param_value={1}", GpsWebConstant.GPS_ACCEPT_BILL_TEST_DEALERCODE, intersectionStr));
            }
            return Result.suc(monitorVo);
        } catch (Exception e) {
            logger.warn("GPS监控设置保存异常", e);
            return Result.fail(RemoteEnum.ERROR_IN_RUNTIME, "GPS监控设置保存异常");
        }
    }

    @LogMonitor("查询按钮权限")
    public Result<List<SyButton>> queryButtonPermission(Integer userId, String syItemId) {
        log.info("查询按钮权限-userId:{},syItemId:{}", userId, syItemId);
        SyRoleForm form = new SyRoleForm();
        form.setUserId(userId);
        List<SyRole> roleList = this.syRoleService.queryList(form);
        log.info("querySyItemsByRoles - userRole:{}", JSON.toJSON(roleList));
        if (CollectionsTools.isEmpty(roleList)) {
            return Result.failInEmptyRecord(null);
        }
        List<Integer> roleIds = roleList.stream().map(record -> record.getRoleId()).collect(Collectors.toList());
        if (StringUtils.isEmpty(syItemId)) {
            List<SyButton> syButtons = syButtonService.selectSyButtonsByRoles(roleIds);
            return Result.suc(syButtons);
        }
        List<SyButton> syButtons = syButtonService.selectSyButtonsByRoles(roleIds).stream().filter(syButton -> !StringUtils.isEmpty(syButton.getItemId())&&syButton.getItemId().equals(syItemId)).collect(Collectors.toList());
        log.info("当前用户的所有按钮数据:{}", JSON.toJSON(syButtons));
        return Result.suc(syButtons);
    }

    /**
     * 保存操作日志
     * @param authTypeEnum
     * @param authDetail
     */
    public void saveLog(UserOperateLogConstant.AuthTypeEnum authTypeEnum, String authDetail){
        this.userOperateLogComponent.log(log -> {
            log.setAuthDetail(authDetail);
            log.setAuthModel(authModelEnum);
            log.setAuthType(authTypeEnum);
        });
    }
}
