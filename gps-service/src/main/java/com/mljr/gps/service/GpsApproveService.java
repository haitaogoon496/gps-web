package com.mljr.gps.service;

import com.lyqc.base.tuple.Tuple;
import com.mljr.gps.entity.SyUser;

import java.util.function.Predicate;

/**
 * @description: GPS审批处理相关接口，相当于一个门面
 * @Date : 下午5:57 2017/11/28
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface GpsApproveService {
    /**
     * @description: 用于车贷申请管理》待上传GPS安装单 需要初始化 GPS审批记录和历史记录
     * @Date : 2017/11/15 下午4:41
     * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
     * @param appCode
     * @param sessionUser
     */
    Tuple<Integer> insertRecord(String appCode, SyUser sessionUser);
    /**
     * @description: 用于车贷审批管理》GPS安装单未上传 保存提交时，保存GPS审批记录和历史记录
     * @Date : 2017/11/15 下午4:41
     * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
     * @param appCode
     * @param sessionUser
     * @param isApproveSuccess
     */
    Tuple<Integer> updateRecord(String appCode,SyUser sessionUser,boolean isApproveSuccess);
    /**
     * @description: 适用于对接放款管理》退回审批
     * 流程阶段：【新建待提交】或者 【复审审批】
     * @Date : 2018/1/13 下午10:34
     * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
     */
    void backApprove(String appCode,Predicate<String> predicate);

    /**
     * @description: 适用提交人工审核时判断订单是否退回至复审审批
     * 流程阶段：【人工审核】
     * @Date : 2018/9/21
     * @Author : zhaoxin
     */
    String canOperateGps(String appCode);
}
