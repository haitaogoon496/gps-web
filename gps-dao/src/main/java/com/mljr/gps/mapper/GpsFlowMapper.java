package com.mljr.gps.mapper;

import com.lyqc.gpsweb.vo.GpsApplyFormVo;
import com.mljr.gps.base.mapper.BaseMapper;
import com.mljr.gps.entity.GpsAuditor;
import com.mljr.gps.entity.GpsFlow;
import com.mljr.gps.form.GpsFlowForm;

import java.util.List;

/**
 * @description: GPS审批流程Mapper
 * @Date : 2018/6/4 下午5:13
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface GpsFlowMapper extends BaseMapper<GpsFlow,Integer,GpsFlowForm>{
    /**
     * 查询某个审批人 流程步骤为 【领单】操作的数据量
     * @param userId 审批人
     * @return
     */
    int getHasPullCountByApproveUserId(Integer userId);

    /**
     * 获取当前审批人中 持有最少量我的gps审核领单单子的人
     */
    List<GpsAuditor> getMinGpsApprUsers(List<Integer> list);
    /**
     * 查询GPS申请单
     * @param appCode
     * @return
     */
    GpsApplyFormVo queryApplyForm(String appCode);
    /**
     * 根据appCode查询
     * @param appCode
     * @return CaGpsFlow
     */
    GpsFlow queryByAppCode(String appCode);
    /**
     * 插入一条记录
     * @param caGpsFlow
     */
    int insertRecord(GpsFlow caGpsFlow);

    /**
     * 通过参数修改审批记录
     * @param caGpsFlow
     */
    void updateRecord(GpsFlow caGpsFlow);

    /**
     * 修改审批记录
     * @param caGpsFlow
     */
    void updateRecordByAppCode(GpsFlow caGpsFlow);
}