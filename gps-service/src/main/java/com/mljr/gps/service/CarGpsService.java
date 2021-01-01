package com.mljr.gps.service;
import com.mljr.gps.entity.CarGps;
import com.mljr.gps.form.CarGpsForm;

import java.util.List;

/**
 * @description:  车辆GPS信息Service
 * @Date : 2018/6/4 下午5:10
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface CarGpsService  {
    /**
     * 新增记录
     * @param carGps
     * @return
     */
    int insertRecord(CarGps carGps);
    /**
     * 按照订单号逻辑删除操作
     * 说明：del_status重置为0
     * @param appCode
     */
    int deleteByLogical(String appCode);
    /**
     * 根据appCode和ids，更新相关记录的isBack=0
     * @param appCode
     */
    int updateIsBack0(String appCode);
    /**
     * 根据appCode和ids，更新相关记录的isCurrent=0
     * @param appCode
     */
    int updateIsCurrent0(String appCode);
    /**
     * 查询记录
     * @param form
     * @return
     */
    List<CarGps> queryList(CarGpsForm form);

    /**
     * 根据GPS设备号,去ca_car_gps表，查询相关单据号，只筛选 未删除、人工审核通过的单据号
     * @param gpsNo
     * @return
     */
    List<String> queryAppCodesByGpsNoForCaCarGps(String gpsNo);

    /**
     * 根据appcode,status原值 把caCargps.status进行修改
     */
    int updateGpsAuditStatus(CarGpsForm carGpsForm);
    /**
     * 选择性更新字段
     * @param carGps
     * @return
     */
    int updateSelective(CarGps carGps);
    /**
     * 根据appCode和historyId查询isBack=1的记录，用于显示退回原因（显示人工审核原因+补充内容）
     * @param appCode
     * @param historyId
     * @return
     */
    List<CarGps> queryListForBackReasonView(String appCode,Integer historyId);
    /**
     * 根据appCode 查询当前退回原因项，用于显示退回原因（显示人工审核原因+补充内容）
     * @param appCode
     * @return
     */
    List<CarGps> queryListForCurrentBack(String appCode);
}
