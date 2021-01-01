package com.mljr.gps.common.consts;

import java.util.Arrays;
import java.util.List;

/**
 * @description:  gps-web所用常量值
 * @Date : 2018/9/6 19:01
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public class GpsWebConstant {

    /**
     * gps审核领单，管理员操作是否开始系统派单开关
     */
    public static final String GPS_MANAGE_SEND_BILL_SWITCH = "manageSendBillSwitch:";

    /**
     * gps审核人员加入审核领单池集合。
     */
    public static final String GPS_AUDITOR_JOIN_ACCEPT_BILL_POOL = "auditorJoinAcceptBillPool:";

    /**
     * gps测试审核人员加入审核领单池集合。
     */
    public static final String GPS_TEST_AUDITOR_JOIN_ACCEPT_BILL_POOL = "testAuditorJoinAcceptBillPool:";

    /**
     * GPS 自动派单审核人员持有单量上限
     */
    public static final String GPS_WEB_AUTO_SEND_BILL_UPPER_LIMIT = "gpsWebAutoSendBillUpperLimit";

    /**
     * gps审核人员上次领单时间戳
     */
    public static final String GPS_AUDITOR_ACCPET_BILL_TIME_STAMP = "auditorAcceptBillTimeStamp:";

    /**
     * 当gps审核人员加入或移除领单池时是操作同一对象，所以加锁。
     */
    public static final String GPS_AUDITOR_POOL_STATUS_OPERATE_LOCK = "auditorAcceptBillPoolOperateLock";

    /**
     * 所配置的测试门店，从configParams中获取
     */
    public static final String GPS_ACCEPT_BILL_TEST_DEALERCODE = "gpsWebAcceptBillTestDealerCode";

    public static final List<String> EXCLUDE_SCOPE = Arrays.asList("7","8","9","10","11","12","13","20","21","22","23","24","30","32");
    /**
     * GPS审核领单 - 要过滤的订单状态(说白了，就是发生在终审之后，并不包含退回复审审批阶段)
     */
    public static final List<String> APPROVE_PULL_EXCLUDE_SCOPE = EXCLUDE_SCOPE;
    /**
     * 我的GPS领单 - 要过滤的订单状态(说白了，就是发生在终审之后，并不包含退回复审审批阶段)
     */
    public static final List<String> APPROVE_MINE_EXCLUDE_SCOPE = EXCLUDE_SCOPE;
    /**
     * GPS安装单(未上传/已驳回) - 要过滤的订单状态(说白了，就是发生在终审之后，并不包含退回复审审批阶段)
     */
    public static final List<String> UNINSTALLED_GPS_EXCLUDE_SCOPE = EXCLUDE_SCOPE;
    /**
     * GPS安装单已上传 - 要过滤的订单状态(说白了，就是发生在终审之后，并不包含退回复审审批阶段)
     */
    public static final List<String> INSTALLED_GPS_EXCLUDE_SCOPE = EXCLUDE_SCOPE;
}
