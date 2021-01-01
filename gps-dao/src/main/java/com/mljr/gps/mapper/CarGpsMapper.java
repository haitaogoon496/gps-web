package com.mljr.gps.mapper;

import com.mljr.gps.base.mapper.BaseMapper;
import com.mljr.gps.entity.CarGps;
import com.mljr.gps.form.CarGpsForm;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @description: 车辆GPS信息Mapper
 * @Date : 2018/6/4 下午5:08
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface CarGpsMapper extends BaseMapper<CarGps,Integer,CarGpsForm> {
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
     * 根据GPS设备号,去ca_car_gps表，查询相关单据号，只筛选 未删除、人工审核通过的单据号
     * @param gpsNo
     * @return
     */
    List<String> queryAppCodesByGpsNoForCaCarGps(String gpsNo);


    int updateStatusByAppCode(CarGpsForm carGpsForm);
    /**
     * 根据appCode 查询当前退回原因项，用于显示退回原因（显示人工审核原因+补充内容）
     * @param appCode
     * @return
     */
    List<CarGps> queryListForCurrentBack(String appCode);
    /**
     * 根据历史记录id查询记录
     * historyId（关联ca_gps_history表主键id）
     * @param appCode appCode
     * @param historyId historyId
     * @return
     */
    List<CarGps> queryListForBackReasonView(@Param("appCode") String appCode,@Param("historyId")Integer historyId);
}