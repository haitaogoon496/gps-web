package com.mljr.gps.service;

import com.lyqc.gpsweb.vo.GpsApplyFormVo;
import com.mljr.gps.entity.GpsAuditor;
import com.mljr.gps.entity.GpsFlow;

import java.util.List;
import java.util.function.Consumer;

/**
 * @description: GPS审批流程 Service
 * @Date : 2018/6/4 下午5:13
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface GpsFlowService{
    /**
     * 查询某个审批人 流程步骤为 【领单】操作的数据量
     * @param userId 审批人
     * @return
     */
    int getHasPullCountByApproveUserId(Integer userId);
    /**
     * 根据appCode查询
     * @param appCode
     * @return CaGpsFlow
     */
    GpsFlow queryByAppCode(String appCode);
    /**
     * 新增
     * @param gpsFlow
     */
    int insertRecord(GpsFlow gpsFlow);
    /**
     * 根据主键选择性更新
     * @param gpsFlow
     */
    void updateRecord(GpsFlow gpsFlow);
    /**
     * 查询GPS申请单
     * @param appCode
     * @return
     */
    GpsApplyFormVo queryApplyForm(String appCode);

    /**
     * 重新发起申请|第一次发起申请
     * @param appCode
     * @param consumer
     */
    void start(String appCode, Consumer<GpsFlow> consumer);

    /**
     * 审批
     * @param appCode
     * @param consumer
     */
    void approve(String appCode, Consumer<GpsFlow> consumer);
    /**
     * 重新发起申请|第一次发起申请
     * @param appCode
     * @return OpResult<Boolean>
     */
    void start(String appCode);
    /**
     * 获取当前审批人中 持有最少量我的gps审核领单单子的人
     */
    List<GpsAuditor> getMinGpsApprUsers(List<Integer> list);

    /**
     * 重置审批人，清空审批人信息
     * @param gpsFlow
     */
    void updateRecordByAppCode(GpsFlow gpsFlow);
}
