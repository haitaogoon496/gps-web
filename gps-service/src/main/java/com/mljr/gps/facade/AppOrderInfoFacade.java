package com.mljr.gps.facade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.base.Joiner;
import com.lyqc.base.common.Result;
import com.lyqc.gpsprovider.enums.CarGpsConstant.GpsProEnum;
import com.lyqc.base.enums.ConArgTypeEnum;
import com.mljr.annotation.LogMonitor;
import com.mljr.redis.service.RedisUtil;
import com.mljr.gps.common.consts.HeilCode;
import com.mljr.gps.common.enums.PoseidonProviderEnum;
import com.mljr.gps.entity.AppInfo;
import com.mljr.gps.entity.CarInfo;
import com.mljr.gps.entity.SyArgControl;
import com.mljr.gps.entity.SyUser;
import com.mljr.gps.service.AppInfoService;
import com.mljr.gps.service.CarInfoService;
import com.mljr.gps.vo.VehicleVo;
import com.mljr.gps.vo.orderVo.*;
import com.mljr.util.HttpUtils;
import com.mljr.util.MD5;
import lombok.extern.slf4j.Slf4j;
import com.mljr.gps.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description:订单详情
 * @Date : 2018/9/29$ 14:51$
 * @Author : liht
 */
@Component
@Slf4j
public class AppOrderInfoFacade {

    private ThreadLocal<OrderAllInfoVo> orderAllInfoVo = new ThreadLocal<>();

    private static final String encode = "utf-8";

    private volatile String userId = null;

    @Autowired
    private AppInfoService appInfoService;

    @Autowired
    private CarInfoService carInfoService;

    @Autowired
    private SyArgControlFacade syArgControlFacade;

    @Value("${poseidon.http.host}")
    private String poseidonUrl;
    @Autowired
    RedisUtil redisUtil;
    @Value("${spring.profiles.active}")
    private String channel;

    private boolean isZy = false;

    @PostConstruct
    public void init() {
        if (!StringUtils.isEmpty(channel)) {
            if (channel.contains("zy") || channel.contains("local")) {
                isZy = true;
            }
        }
    }

    @LogMonitor("获取poseidon订单信息")
    public Result<OrderInfoVo> getOrderInfo(String appCode) {
        PoseidonProviderEnum orderInfoEnum = PoseidonProviderEnum.GET_ORDER_INFO;
        String orderUrl = poseidonUrl + orderInfoEnum.getUrl();
        log.info("{} - url：{}", orderInfoEnum.getDesc(),orderUrl);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appCode", appCode);
        setParam(requestMap);
        String orderInfoRes = HttpUtils.doPost(orderUrl,requestMap, requestMap, encode);
        log.info("{} - 请求结果:{}", orderInfoEnum.getDesc(), JSON.toJSON(orderInfoRes));
        if (StringUtils.isEmpty(orderInfoRes)) {
            log.error("{}-{}获取数据失败,数据为空", appCode, orderInfoEnum.getDesc());
            return Result.fail(new OrderInfoVo());
        }
        Result<OrderInfoVo> resultObj = JSONObject.parseObject(orderInfoRes, new TypeReference<Result<OrderInfoVo>>(){});
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", appCode, orderInfoEnum.getDesc(),resultObj.getMsg());
            return Result.fail(new OrderInfoVo());
        }
        if (resultObj.getData() == null) {
            log.error("{}-{}请求成功,但返回data数据为空", appCode, orderInfoEnum.getDesc());
            return Result.fail(new OrderInfoVo());
        }
        OrderInfoVo orderInfoVo = resultObj.getData();
        orderInfoVo.setIsZy(isZy);
        orderAllInfoVo.get().setOrderInfoVo(orderInfoVo);
        return resultObj;
    }

    @LogMonitor("获取poseidon批复贷款金额")
    public Result<ReplyLoanFeeVO> getReplyLoanFee(String appCode) {
        PoseidonProviderEnum replyLoanFeeEnum = PoseidonProviderEnum.GET_REPLY_LOAN_FEE;
        String orderUrl = poseidonUrl + replyLoanFeeEnum.getUrl();
        log.info("{} - url：{}", replyLoanFeeEnum.getDesc(),orderUrl);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appCode", appCode);
        setParam(requestMap);
        String orderInfoRes = HttpUtils.doPost(orderUrl,requestMap, requestMap, encode);
        log.info("{} - 请求结果:{}", replyLoanFeeEnum.getDesc(), JSON.toJSON(orderInfoRes));
        if (StringUtils.isEmpty(orderInfoRes)) {
            log.error("{}-{}获取数据失败,数据为空", appCode, replyLoanFeeEnum.getDesc());
            return Result.fail(new ReplyLoanFeeVO());
        }
        Result<ReplyLoanFeeVO> resultObj = JSONObject.parseObject(orderInfoRes, new TypeReference<Result<ReplyLoanFeeVO>>(){});
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", appCode, replyLoanFeeEnum.getDesc(),resultObj.getMsg());
            return Result.fail(new ReplyLoanFeeVO());
        }
        if (resultObj.getData() == null) {
            log.error("{}-{}请求成功,但返回data数据为空", appCode, replyLoanFeeEnum.getDesc());
            return Result.fail(new ReplyLoanFeeVO());
        }
        orderAllInfoVo.get().setReplyLoanFeeVO(resultObj.getData());
        return resultObj;
    }

    @LogMonitor("获取申请人信息接口")
    public Result<CaProppserInfoVo> getProppserInfo(String appCode) {
        PoseidonProviderEnum proppserInfoEnum = PoseidonProviderEnum.GET_PROPPSER_INFO;
        String orderUrl = poseidonUrl + proppserInfoEnum.getUrl();
        log.info("{} - url：{}", proppserInfoEnum.getDesc(),orderUrl);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appCode", appCode);
        setParam(requestMap);
        String orderInfoRes = HttpUtils.doPost(orderUrl,requestMap, requestMap, encode);
        log.info("{} - 请求结果:{}", proppserInfoEnum.getDesc(), JSON.toJSON(orderInfoRes));
        if (StringUtils.isEmpty(orderInfoRes)) {
            log.error("{}-{}获取数据失败,数据为空", appCode, proppserInfoEnum.getDesc());
            return Result.fail(new CaProppserInfoVo());
        }
        Result<CaProppserInfoVo> resultObj = JSONObject.parseObject(orderInfoRes, new TypeReference<Result<CaProppserInfoVo>>(){});
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", appCode, proppserInfoEnum.getDesc(),resultObj.getMsg());
            return Result.fail(new CaProppserInfoVo());
        }
        if (resultObj.getData() == null) {
            log.error("{}-{}请求成功,但返回data数据为空", appCode, proppserInfoEnum.getDesc());
            return Result.fail(new CaProppserInfoVo());
        }
        orderAllInfoVo.get().setCaProppserInfoVo(resultObj.getData());
        return resultObj;
    }

    @LogMonitor("获取配偶信息接口")
    public Result<CaRelativesInfoVO> getSpouseInfo(String appCode) {
        PoseidonProviderEnum spouseInfoEnum = PoseidonProviderEnum.GET_SPOUSE_INFO;
        String orderUrl = poseidonUrl + spouseInfoEnum.getUrl();
        log.info("{} - url：{}", spouseInfoEnum.getDesc(),orderUrl);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appCode", appCode);
        setParam(requestMap);
        String orderInfoRes = HttpUtils.doPost(orderUrl,requestMap, requestMap, encode);
        log.info("{} - 请求结果:{}", spouseInfoEnum.getDesc(), JSON.toJSON(orderInfoRes));
        if (StringUtils.isEmpty(orderInfoRes)) {
            log.error("{}-{}获取数据失败,数据为空", appCode, spouseInfoEnum.getDesc());
            return Result.fail(new CaRelativesInfoVO());
        }
        Result<CaRelativesInfoVO> resultObj = JSONObject.parseObject(orderInfoRes, new TypeReference<Result<CaRelativesInfoVO>>(){});
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", appCode, spouseInfoEnum.getDesc(),resultObj.getMsg());
            return Result.fail(new CaRelativesInfoVO());
        }
        if (resultObj.getData() == null) {
            log.error("{}-{}请求成功,但返回data数据为空", appCode, spouseInfoEnum.getDesc());
            return Result.fail(new CaRelativesInfoVO());
        }
        orderAllInfoVo.get().setCaRelativesInfoVO(resultObj.getData());
        return resultObj;
    }

    @LogMonitor("获取联系人信息")
    public Result<EmContactsVo> getEmContactsInfo(String appCode) {
        PoseidonProviderEnum emContactsInfoEnum = PoseidonProviderEnum.GET_EM_CONTACTS_INFO;
        String orderUrl = poseidonUrl + emContactsInfoEnum.getUrl();
        log.info("{} - url：{}", emContactsInfoEnum.getDesc(),orderUrl);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appCode", appCode);
        setParam(requestMap);
        String orderInfoRes = HttpUtils.doPost(orderUrl,requestMap, requestMap, encode);
        log.info("{} - 请求结果:{}", emContactsInfoEnum.getDesc(), JSON.toJSON(orderInfoRes));
        if (StringUtils.isEmpty(orderInfoRes)) {
            log.error("{}-{}获取数据失败,数据为空", appCode, emContactsInfoEnum.getDesc());
            return Result.fail(new EmContactsVo());
        }
        Result<EmContactsVo> resultObj = JSONObject.parseObject(orderInfoRes, new TypeReference<Result<EmContactsVo>>(){});
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", appCode, emContactsInfoEnum.getDesc(),resultObj.getMsg());
            return Result.fail(new EmContactsVo());
        }
        if (resultObj.getData() == null) {
            log.error("{}-{}请求成功,但返回data数据为空", appCode, emContactsInfoEnum.getDesc());
            return Result.fail(new EmContactsVo());
        }
        orderAllInfoVo.get().setEmContactsVo(resultObj.getData());
        return resultObj;
    }

    @LogMonitor("获取商户信息")
    public Result<DealerInfoVo> getDealerInfo(String appCode) {
        PoseidonProviderEnum dealerInfoEnum = PoseidonProviderEnum.GET_DEALER_INFO;
        String orderUrl = poseidonUrl + dealerInfoEnum.getUrl();
        log.info("{} - url：{}", dealerInfoEnum.getDesc(),orderUrl);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appCode", appCode);
        setParam(requestMap);
        String orderInfoRes = HttpUtils.doPost(orderUrl,requestMap, requestMap, encode);
        log.info("{} - 请求结果:{}", dealerInfoEnum.getDesc(), JSON.toJSON(orderInfoRes));
        if (StringUtils.isEmpty(orderInfoRes)) {
            log.error("{}-{}获取数据失败,数据为空", appCode, dealerInfoEnum.getDesc());
            return Result.fail(new DealerInfoVo());
        }
        Result<DealerInfoVo> resultObj = JSONObject.parseObject(orderInfoRes, new TypeReference<Result<DealerInfoVo>>(){});
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", appCode, dealerInfoEnum.getDesc(),resultObj.getMsg());
            return Result.fail(new DealerInfoVo());
        }
        if (resultObj.getData() == null) {
            log.error("{}-{}请求成功,但返回data数据为空", appCode, dealerInfoEnum.getDesc());
            return Result.fail(new DealerInfoVo());
        }
        orderAllInfoVo.get().setDealerInfoVo(resultObj.getData());
        return resultObj;
    }

    @LogMonitor("获取车辆信息")
    public Result<CaCarInfoVo> getCarInfo(String appCode) {
        PoseidonProviderEnum carInfoEnum = PoseidonProviderEnum.GET_CAR_INFO;
        String orderUrl = poseidonUrl + carInfoEnum.getUrl();
        log.info("{} - url：{}", carInfoEnum.getDesc(),orderUrl);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appCode", appCode);
        setParam(requestMap);
        String orderInfoRes = HttpUtils.doPost(orderUrl,requestMap, requestMap, encode);
        log.info("{} - 请求结果:{}", carInfoEnum.getDesc(), JSON.toJSON(orderInfoRes));
        if (StringUtils.isEmpty(orderInfoRes)) {
            log.error("{}-{}获取数据失败,数据为空", appCode, carInfoEnum.getDesc());
            return Result.fail(new CaCarInfoVo());
        }
        Result<CaCarInfoVo> resultObj = JSONObject.parseObject(orderInfoRes, new TypeReference<Result<CaCarInfoVo>>(){});
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", appCode, carInfoEnum.getDesc(),resultObj.getMsg());
            return Result.fail(new CaCarInfoVo());
        }
        if (resultObj.getData() == null) {
            log.error("{}-{}请求成功,但返回data数据为空", appCode, carInfoEnum.getDesc());
            return Result.fail(new CaCarInfoVo());
        }
        orderAllInfoVo.get().setCaCarInfoVo(resultObj.getData());
        return resultObj;
    }

    @LogMonitor("获取贷款信息")
    public Result<CreditInfoVo> getCreditInfo(String appCode) {
        PoseidonProviderEnum creditInfoEnum = PoseidonProviderEnum.GET_CREDIT_INFO;
        String orderUrl = poseidonUrl + creditInfoEnum.getUrl();
        log.info("{} - url：{}", creditInfoEnum.getDesc(),orderUrl);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appCode", appCode);
        setParam(requestMap);
        String orderInfoRes = HttpUtils.doPost(orderUrl,requestMap, requestMap, encode);
        log.info("{} - 请求结果:{}", creditInfoEnum.getDesc(), JSON.toJSON(orderInfoRes));
        if (StringUtils.isEmpty(orderInfoRes)) {
            log.error("{}-{}获取数据失败,数据为空", appCode, creditInfoEnum.getDesc());
            return Result.fail(new CreditInfoVo());
        }
        Result<CreditInfoVo> resultObj = JSONObject.parseObject(orderInfoRes, new TypeReference<Result<CreditInfoVo>>(){});
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", appCode, creditInfoEnum.getDesc(),resultObj.getMsg());
            return Result.fail(new CreditInfoVo());
        }
        if (resultObj.getData() == null) {
            log.error("{}-{}请求成功,但返回data数据为空", appCode, creditInfoEnum.getDesc());
            return Result.fail(new CreditInfoVo());
        }
        orderAllInfoVo.get().setCreditInfoVo(resultObj.getData());
        return resultObj;
    }

    @LogMonitor("获取附件信息")
    public Result<List<CaAppAnnexVo>> getAnnexList(String appCode) {
        PoseidonProviderEnum annexListInfoEnum = PoseidonProviderEnum.GET_ANNEX_LIST_INFO;
        String orderUrl = poseidonUrl + annexListInfoEnum.getUrl();
        log.info("{} - url：{}", annexListInfoEnum.getDesc(),orderUrl);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appCode", appCode);
        setParam(requestMap);
        String orderInfoRes = HttpUtils.doPost(orderUrl,requestMap, requestMap, encode);
        log.info("{} - 请求结果:{}", annexListInfoEnum.getDesc(), JSON.toJSON(orderInfoRes));
        if (StringUtils.isEmpty(orderInfoRes)) {
            log.error("{}-{}获取数据失败,数据为空", appCode, annexListInfoEnum.getDesc());
            return Result.fail(Collections.emptyList());
        }
        Result<List<CaAppAnnexVo>> resultObj = JSONObject.parseObject(orderInfoRes, new TypeReference<Result<List<CaAppAnnexVo>>>(){});
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", appCode, annexListInfoEnum.getDesc(),resultObj.getMsg());
            return Result.fail(Collections.emptyList());
        }
        if (resultObj.getData() == null) {
            log.error("{}-{}请求成功,但返回data数据为空", appCode, annexListInfoEnum.getDesc());
            return Result.fail(Collections.emptyList());
        }
        orderAllInfoVo.get().setCaAppAnnexVoList(resultObj.getData());
        return resultObj;
    }

    @LogMonitor("获取审批记录信息")
    public Result<List<CaAppApprovalVo>> getCaAppApprovalList(String appCode) {
        PoseidonProviderEnum appApprovalListInfoEnum = PoseidonProviderEnum.GET_CA_APP_APPROVAL_LIST_INFO;
        String orderUrl = poseidonUrl + appApprovalListInfoEnum.getUrl();
        log.info("{} - url：{}", appApprovalListInfoEnum.getDesc(),orderUrl);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appCode", appCode);
        setParam(requestMap);
        String orderInfoRes = HttpUtils.doPost(orderUrl,requestMap, requestMap, encode);
        log.info("{} - 请求结果:{}", appApprovalListInfoEnum.getDesc(), JSON.toJSON(orderInfoRes));
        if (StringUtils.isEmpty(orderInfoRes)) {
            log.error("{}-{}获取数据失败,数据为空", appCode, appApprovalListInfoEnum.getDesc());
            return Result.fail(Collections.emptyList());
        }
        Result<List<CaAppApprovalVo>> resultObj = JSONObject.parseObject(orderInfoRes, new TypeReference<Result<List<CaAppApprovalVo>>>(){});
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", appCode, appApprovalListInfoEnum.getDesc(),resultObj.getMsg());
            return Result.fail(Collections.emptyList());
        }
        if (resultObj.getData() == null) {
            log.error("{}-{}请求成功,但返回data数据为空", appCode, appApprovalListInfoEnum.getDesc());
            return Result.fail(Collections.emptyList());
        }
        orderAllInfoVo.get().setCaAppApprovalVoList(resultObj.getData());
        return resultObj;
    }

    @LogMonitor("获取留言信息")
    public Result<List<CaAppMessageVo>> getMessageList(String appCode) {
        PoseidonProviderEnum messageListInfoEnum = PoseidonProviderEnum.GET_MESSAGE_LIST_INFO;
        String orderUrl = poseidonUrl + messageListInfoEnum.getUrl();
        log.info("{} - url：{}", messageListInfoEnum.getDesc(),orderUrl);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appCode", appCode);
        setParam(requestMap);
        String orderInfoRes = HttpUtils.doPost(orderUrl,requestMap, requestMap, encode);
        log.info("{} - 请求结果:{}", messageListInfoEnum.getDesc(), JSON.toJSON(orderInfoRes));
        if (StringUtils.isEmpty(orderInfoRes)) {
            log.error("{}-{}获取数据失败,数据为空", appCode, messageListInfoEnum.getDesc());
            return Result.fail(Collections.emptyList());
        }
        Result<List<CaAppMessageVo>> resultObj = JSONObject.parseObject(orderInfoRes, new TypeReference<Result<List<CaAppMessageVo>>>(){});
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", appCode, messageListInfoEnum.getDesc(),resultObj.getMsg());
            return Result.fail(Collections.emptyList());
        }
        if (resultObj.getData() == null) {
            log.error("{}-{}请求成功,但返回data数据为空", appCode, messageListInfoEnum.getDesc());
            return Result.fail(Collections.emptyList());
        }
        orderAllInfoVo.get().setCaAppMessageVoList(resultObj.getData());
        return resultObj;
    }

    @LogMonitor("获取经销商显示")
    public Result<List<OrderTipsVo>> getOrderTips(String appCode) {
        PoseidonProviderEnum orderTipsInfoEnum = PoseidonProviderEnum.GET_ORDER_TIPS_INFO;
        String orderUrl = poseidonUrl + orderTipsInfoEnum.getUrl();
        log.info("{} - url：{}", orderTipsInfoEnum.getDesc(),orderUrl);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appCode", appCode);
        setParam(requestMap);
        String orderInfoRes = HttpUtils.doPost(orderUrl,requestMap, requestMap, encode);
        log.info("{} - 请求结果:{}", orderTipsInfoEnum.getDesc(), JSON.toJSON(orderInfoRes));
        if (StringUtils.isEmpty(orderInfoRes)) {
            log.error("{}-{}获取数据失败,数据为空", appCode, orderTipsInfoEnum.getDesc());
            return Result.fail(Collections.emptyList());
        }
        Result<List<OrderTipsVo>> resultObj = JSONObject.parseObject(orderInfoRes, new TypeReference<Result<List<OrderTipsVo>>>(){});
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", appCode, orderTipsInfoEnum.getDesc(),resultObj.getMsg());
            return Result.fail(Collections.emptyList());
        }
        if (resultObj.getData() == null) {
            log.error("{}-{}请求成功,但返回data数据为空", appCode, orderTipsInfoEnum.getDesc());
            return Result.fail(Collections.emptyList());
        }
        orderAllInfoVo.get().setOrderTipsVoList(resultObj.getData());
        return resultObj;
    }

    @LogMonitor("获取担保人信息")
    public Result<CaGuaranteeInfoVo> getGuaranteeInfo(String appCode) {
        PoseidonProviderEnum guaranteeInfoEnum = PoseidonProviderEnum.GET_GUARANTEE_INFO;
        String orderUrl = poseidonUrl + guaranteeInfoEnum.getUrl();
        log.info("{} - url：{}", guaranteeInfoEnum.getDesc(),orderUrl);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appCode", appCode);
        setParam(requestMap);
        String orderInfoRes = HttpUtils.doPost(orderUrl,requestMap, requestMap, encode);
        log.info("{} - 请求结果:{}", guaranteeInfoEnum.getDesc(), JSON.toJSON(orderInfoRes));
        if (StringUtils.isEmpty(orderInfoRes)) {
            log.error("{}-{}获取数据失败,数据为空", appCode, guaranteeInfoEnum.getDesc());
            return Result.fail(new CaGuaranteeInfoVo());
        }
        Result<CaGuaranteeInfoVo> resultObj = JSONObject.parseObject(orderInfoRes, new TypeReference<Result<CaGuaranteeInfoVo>>(){});
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", appCode, guaranteeInfoEnum.getDesc(),resultObj.getMsg());
            return Result.fail(new CaGuaranteeInfoVo());
        }
        if (resultObj.getData() == null) {
            log.error("{}-{}请求成功,但返回data数据为空", appCode, guaranteeInfoEnum.getDesc());
            return Result.failInEmptyRecord(null);
        }
        orderAllInfoVo.get().setCaGuaranteeInfoVo(resultObj.getData());
        return resultObj;
    }

    @LogMonitor("获取风控信息")
    public Result<RiskInfoVo> getRiskInfo(String appCode) {
        PoseidonProviderEnum riskInfoEnum = PoseidonProviderEnum.GET_RISK_INFO;
        String orderUrl = poseidonUrl + riskInfoEnum.getUrl();
        log.info("{} - url：{}", riskInfoEnum.getDesc(),orderUrl);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appCode", appCode);
        setParam(requestMap);
        String orderInfoRes = HttpUtils.doPost(orderUrl,requestMap, requestMap, encode);
        log.info("{} - 请求结果:{}", riskInfoEnum.getDesc(), JSON.toJSON(orderInfoRes));
        if (StringUtils.isEmpty(orderInfoRes)) {
            log.error("{}-{}获取数据失败,数据为空", appCode, riskInfoEnum.getDesc());
            return Result.fail(new RiskInfoVo());
        }
        Result<RiskInfoVo> resultObj = JSONObject.parseObject(orderInfoRes, new TypeReference<Result<RiskInfoVo>>(){});
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", appCode, riskInfoEnum.getDesc(),resultObj.getMsg());
            return Result.fail(new RiskInfoVo());
        }
        if (resultObj.getData() == null) {
            log.error("{}-{}请求成功,但返回data数据为空", appCode, riskInfoEnum.getDesc());
            return Result.failInEmptyRecord(null);
        }
        orderAllInfoVo.get().setRiskInfoVo(resultObj.getData());
        return resultObj;
    }

    @LogMonitor("获取审批信息")
    public Result<ApprovalInfoVo> getApprovalInfo(String appCode) {
        PoseidonProviderEnum approvalInfoEnum = PoseidonProviderEnum.GET_APPROVAL_INFO;
        String orderUrl = poseidonUrl + approvalInfoEnum.getUrl();
        log.info("{} - url：{}", approvalInfoEnum.getDesc(),orderUrl);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appCode", appCode);
        setParam(requestMap);
        String orderInfoRes = HttpUtils.doPost(orderUrl,requestMap, requestMap, encode);
        log.info("{} - 请求结果:{}", approvalInfoEnum.getDesc(), JSON.toJSON(orderInfoRes));
        if (StringUtils.isEmpty(orderInfoRes)) {
            log.error("{}-{}获取数据失败,数据为空", appCode, approvalInfoEnum.getDesc());
            return Result.fail(new ApprovalInfoVo());
        }
        Result<ApprovalInfoVo> resultObj = JSONObject.parseObject(orderInfoRes, new TypeReference<Result<ApprovalInfoVo>>(){});
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", appCode, approvalInfoEnum.getDesc(),resultObj.getMsg());
            return Result.fail(new ApprovalInfoVo());
        }
        if (resultObj.getData() == null) {
            log.error("{}-{}请求成功,但返回data数据为空", appCode, approvalInfoEnum.getDesc());
            return Result.failInEmptyRecord(null);
        }
        orderAllInfoVo.get().setApprovalInfoVo(resultObj.getData());
        return resultObj;
    }

    @LogMonitor("获取详情页面总的订单数据")
    public Result<OrderAllInfoVo> getOrderAllInfo(String appCode) {
        if (orderAllInfoVo.get() == null) {
            orderAllInfoVo.set(new OrderAllInfoVo());
        }
        //判断订单是否存在
        AppInfo appInfo = appInfoService.queryAppInfo(appCode);
        if (appInfo != null){
            getOrderInfo(appCode);
            getReplyLoanFee(appCode);
            Result<CaProppserInfoVo> caProppserInfoVoResult = getProppserInfo(appCode);
            if (caProppserInfoVoResult != null && caProppserInfoVoResult.isSuccess() && caProppserInfoVoResult.getData() != null) {
                CaProppserInfoVo caProppserInfoVo = caProppserInfoVoResult.getData();
                //配偶
                String spou = caProppserInfoVo.getMarriage();
                if ("1".equals(spou)) {
                    getSpouseInfo(appCode);
                }
                //担保人
                String isAssure = caProppserInfoVo.getIsAssure();
                if (!"0".equals(isAssure)) {
                    getGuaranteeInfo(appCode);
                }
            } else {
                orderAllInfoVo.get().setCaGuaranteeInfoVo(null);
                orderAllInfoVo.get().setCaRelativesInfoVO(null);
            }
            getEmContactsInfo(appCode);
            getDealerInfo(appCode);
            getCarInfo(appCode);
            getCreditInfo(appCode);
            getAnnexList(appCode);
            getMessageList(appCode);
            getOrderTips(appCode);
            getCaAppApprovalList(appCode);
            getRiskInfo(appCode);
            getApprovalInfo(appCode);
            if (appInfo.getCarId() != null){
                getCarGPSInfo(appInfo.getCarId());
            }

            return Result.suc(orderAllInfoVo.get());
        }
        return Result.fail("订单不存在");
    }

    @LogMonitor("获取GPS信息")
    public Result<GPSInfoVo> getCarGPSInfo(Integer carId) {
        GPSInfoVo gpsInfoVo = new GPSInfoVo();
        if (carId != null) {
            CarInfo carInfo = carInfoService.queryByCarId(carId);
            List<String> gpsDealerName = new ArrayList<>();
            if (carInfo != null) {
                if( carInfo.getGpsDealer() != null){
                    List<String> gpsDealerList = Arrays.asList(carInfo.getGpsDealer().split(","));
                    List<SyArgControl> argControlList = syArgControlFacade.queryList(ConArgTypeEnum.GPS_DEALER);
                    Map<String, SyArgControl> argControl = argControlList.stream().collect(Collectors.toMap(SyArgControl::getConArgValue, Function.identity()));
                    gpsDealerList.forEach(gpsDealer -> {
                        if (argControl.get(gpsDealer) != null) {
                            gpsDealerName.add(argControl.get(gpsDealer).getConArgName());
                        }
                    });
                }
                gpsInfoVo.setGpsCount(carInfo.getGpsCount());
                gpsInfoVo.setGpsDealerStr(Joiner.on(",").join(gpsDealerName));

                gpsInfoVo.setGpsProStr(!StringUtils.isEmpty(carInfo.getGpsPro()) ? GpsProEnum.getNameByIndex(Integer.valueOf(carInfo.getGpsPro())) : "");
                gpsInfoVo.setGpsNo(StringUtils.killNull(carInfo.getGpsNo()));
                gpsInfoVo.setGpsPro(StringUtils.killNull(carInfo.getGpsPro()));
                gpsInfoVo.setGpsDealer(StringUtils.killNull(carInfo.getGpsDealer()));
                orderAllInfoVo.get().setGpsInfoVo(gpsInfoVo);
            }
        }else {
            orderAllInfoVo.get().setGpsInfoVo(null);
        }
        return  Result.suc(gpsInfoVo);
    }

    private void setParam(Map<String, String> paramMap) {
        if (paramMap == null) {
            paramMap = new HashMap<>();
        }
        String appCode = paramMap.get("appCode");
        SyUser user = getUser();
        paramMap.put("userId", user.getUserId().toString());
        paramMap.put("userType", user.getUserType());
        String md5 = MD5.getMD5Str(user.getUserId() + appCode).toUpperCase();
        paramMap.put("keySign", md5);
        paramMap.put("channel", isZy ? "ZY" : "QD");
        log.info("调用poseidon入参appCode:{}- 参数:{}",appCode, JSON.toJSON(paramMap));
    }

    public SyUser getUser(){

        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            if (session == null) {
                return null;
            }
            SyUser user = (SyUser) session.getAttribute("user");
            if (user == null) {
                user = redisUtil.get(session.getId(), SyUser.class);
            }
            return user;
        } catch (Exception e) {
            log.error("获取用户id异常",e);
        }
        return new SyUser();
    }

    @LogMonitor("vin码查询-调用poseidon")
    public Result<VehicleVo> getCsByVinCode(String vinCode) {
        log.info("vin码查询->vinCode:{}", vinCode);
        PoseidonProviderEnum vinEnum = PoseidonProviderEnum.GET_CS_VIN;
        String orderUrl = poseidonUrl + vinEnum.getUrl();
        log.info("{} - url：{}", vinEnum.getDesc(),orderUrl);
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("userId", getUser().getUserId().toString());
        headerMap.put("isZhiYing", isZy+"");
        Map<String, String> param = new HashMap<>();
        param.put("vinCode", vinCode);
        String vinResult = HttpUtils.post(orderUrl,JSONObject.toJSONString(param),headerMap);
        log.info("{} - 请求结果:{}", vinEnum.getDesc(), JSON.toJSON(vinResult));
        if (StringUtils.isEmpty(vinResult)) {
            log.error("{}-{}获取数据失败,数据为空", vinCode, vinEnum.getDesc());
            return Result.fail(HeilCode.E_500, "请求失败啦,返回结果为空");
        }
        Result<VehicleVo> resultObj;
        try {
            resultObj = JSONObject.parseObject(vinResult, new TypeReference<Result<VehicleVo>>(){});
        } catch (Exception e) {
            return Result.fail(HeilCode.E_500, "请求数据返回错误，请稍后重试");
        }
        if (!resultObj.isSuccess()) {
            log.error("{}-{}请求失败,返回信息:{}", vinCode, vinEnum.getDesc(),resultObj.getMsg());
            return Result.fail(resultObj.getCode(), resultObj.getMsg());
        }

        return resultObj;
    }

}
