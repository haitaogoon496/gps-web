package com.mljr.gps.facade;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.gpsprovider.enums.GpsDeviceAgencyType;
import com.mljr.annotation.LogMonitor;
import com.mljr.annotation.OvalValidator;
import com.mljr.gps.common.consts.HeilCode;
import com.mljr.gps.common.enums.GpsStatisticEnum;
import com.mljr.gps.common.util.ValidateUtils;
import com.mljr.gps.param.GpsDeviceParam;
import com.mljr.gps.vo.ErrorGpsDeviceVo;
import com.mljr.gps.vo.statistic.GpsAuditVo;
import com.mljr.gps.vo.statistic.GpsFailTimesOfDeviceVo;
import com.mljr.gps.vo.statistic.GpsStatisticDataVo;
import com.mljr.gps.vo.statistic.GpsTypeAndAuditVo;
import com.mljr.redis.service.RedisUtil;
import com.mljr.util.CollectionsTools;
import com.mljr.util.TimeTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @description: gps设备统计相关处理
 * @Date : 2018/12/12$ 14:42$
 * @Author : liht
 */
@Component
@Slf4j
public class StatisticsFacade {

    @Autowired
    private RedisUtil redisUtil;

    private static String log_title = "gps设备数据统计";
    private String redisKeyPre = "GpsDingTalk:";

    private ThreadLocal<List<GpsAuditVo>> jjGpsAuditList = ThreadLocal.withInitial(() ->
            new ArrayList<GpsAuditVo>());
    private ThreadLocal<List<GpsAuditVo>> segGpsAuditList = ThreadLocal.withInitial(() -> new ArrayList<GpsAuditVo>());


    private ThreadLocal<Set<GpsFailTimesOfDeviceVo>> jjGpsFailTimesList = ThreadLocal.withInitial(() -> new HashSet<GpsFailTimesOfDeviceVo>());
    private ThreadLocal<Set<GpsFailTimesOfDeviceVo>> segGpsFailTimesList = ThreadLocal.withInitial(() -> new HashSet<GpsFailTimesOfDeviceVo>());

    @LogMonitor("gps认证失败统计数据")
    @OvalValidator("gps认证失败统计数据")
    public Result<List<GpsStatisticDataVo>> getGpsErrorDeviceNum(GpsDeviceParam gpsDeviceParam) {
        ValidateUtils.notEmpty(gpsDeviceParam.getStartDate(), HeilCode.E_500, "开始日期不能为空");
        ValidateUtils.notEmpty(gpsDeviceParam.getEndDate(), HeilCode.E_500, "结束日期不能为空");
        ValidateUtils.notNull(gpsDeviceParam.getGpsStatisticEnum(), HeilCode.E_500, "gps统计类别不能为空");
        if (gpsDeviceParam != null) {
            if(!StringUtils.isEmpty(gpsDeviceParam.getStartDate())){
                ValidateUtils.notNull(TimeTools.parseYYYY_MM_DD(gpsDeviceParam.getStartDate()), HeilCode.E_500, "日期格式不正确");
            }
            if (!StringUtils.isEmpty(gpsDeviceParam.getEndDate())) {
                ValidateUtils.notNull(TimeTools.parseYYYY_MM_DD(gpsDeviceParam.getStartDate()), HeilCode.E_500, "日期格式不正确");
            }
        }
        List<ErrorGpsDeviceVo> errorGpsDeviceVos = getGpsAuditOriginData(gpsDeviceParam);
        if (CollectionsTools.isEmpty(errorGpsDeviceVos)) {
            return Result.failInEmptyRecord(null);
        }

        log.info("{}-封装之后的数据:{}", log_title, JSON.toJSON(errorGpsDeviceVos));
        if (CollectionUtils.isEmpty(errorGpsDeviceVos)) {
            return Result.failInEmptyRecord(null);
        }
        List<GpsStatisticDataVo> gpsStatisticDataVos = getGpsAuditFailTimes(errorGpsDeviceVos, gpsDeviceParam.getGpsStatisticEnum());

        log.info("{} - 最终返回的数据:{}", log_title, JSON.toJSON(gpsStatisticDataVos));
        return Result.suc(gpsStatisticDataVos);
    }


    /**
     * 获取gps存储的元数据
     * @param gpsDeviceParam
     * @return
     */
    public List<ErrorGpsDeviceVo> getGpsAuditOriginData(GpsDeviceParam gpsDeviceParam) {

        // 如果日期不为空,获取日期之间的数据
        Date start = TimeTools.parseYYYY_MM_DD(gpsDeviceParam.getStartDate());
        Date end = TimeTools.parseYYYY_MM_DD(gpsDeviceParam.getEndDate());
        int diff = TimeTools.getDiffOfDate(start, end);
        log.info("{} - startDate:{},endDate:{},差值:{}", log_title, gpsDeviceParam.getStartDate(), gpsDeviceParam.getEndDate(), diff);
        ValidateUtils.isFalse(diff < 0, HeilCode.E_500, "结束日期必须大于开始日期");
        ValidateUtils.isFalse(diff > 7, HeilCode.E_500, "时间间隔不能超过7天");
        List<ErrorGpsDeviceVo> errorGpsDeviceVos = new ArrayList<>();

        for (int i = 0; i <= diff; i++) {
            String nextDateStr = TimeTools.format4YYYYMMDD(DateUtils.addDays(start, i));
            ErrorGpsDeviceVo vo = new ErrorGpsDeviceVo();
            vo.setDate(nextDateStr);
            String newDataStr = nextDateStr.replace("-", "");
            Map<String, Object> deviceMap = redisUtil.hashOperations().entries(redisKeyPre + newDataStr);
            log.info("{}-当前日期:{}-查询的redisKey:{},对应数据:{}", log_title, nextDateStr, redisKeyPre + newDataStr, deviceMap);
            if (deviceMap == null || deviceMap.size() == 0) {
                continue;
            }
            vo.setDeviceMap(deviceMap);
            errorGpsDeviceVos.add(vo);
        }

        return errorGpsDeviceVos;
    }

    /**
     * 根据设备类型获取对应存储的设备号
     * @param gpsDeviceAgencyType
     * @return
     */
    private Set<String> getGpsNoForAgency(GpsDeviceAgencyType gpsDeviceAgencyType) {

        String key = redisKeyPre + gpsDeviceAgencyType.name();
        log.info("{} - 获取不同设备类型的redisKey:{}", log_title, key);
        Set<Object> set = redisUtil.setOperations().members(key);
        log.info("{} - 当前gps供应商类型:{},里面存储的gps设备号:{}", log_title, key, JSON.toJSON(set));
        if (CollectionsTools.isNotEmpty(set)) {
            Set<String> newSet = new HashSet<>();
            set.stream().forEach(o -> newSet.add((String) o));
            return newSet;
        }
        return new HashSet<>();
    }

    /**
     *获取gps统计的封装过后的数据
     * @param errorGpsDeviceVos
     * @param gpsStatisticEnum
     * @return
     */
    private List<GpsStatisticDataVo> getGpsAuditFailTimes(List<ErrorGpsDeviceVo> errorGpsDeviceVos, GpsStatisticEnum gpsStatisticEnum) {
        log.info("{} - gps源数据,每天的GPS设备对应失败次数:{}", log_title, JSON.toJSON(errorGpsDeviceVos));
        List<GpsStatisticDataVo> gpsStatisticDataVos = new ArrayList<>();

        Set<String> jjGps = getGpsNoForAgency(GpsDeviceAgencyType.jjGPS);
        Set<String> segGps = getGpsNoForAgency(GpsDeviceAgencyType.segGPS);

        errorGpsDeviceVos.stream().forEach(vo -> {

            log.info("{} - 当前日期:{},gps设备数据:{}", log_title, vo.getDate(), JSON.toJSON(vo.getDeviceMap()));
            GpsStatisticDataVo gpsStatisticDataVo = new GpsStatisticDataVo();
            gpsStatisticDataVo.setDate(vo.getDate());
            // 每天有两种类型的GPS数据
            List<GpsTypeAndAuditVo> gpsTypeAndAuditVos = new ArrayList<GpsTypeAndAuditVo>();
            // jj数据，包含设备类型和gps设备认证失败的数据
            GpsTypeAndAuditVo jjGpsTypeAndAuditVo = new GpsTypeAndAuditVo();
            // seg数据，包含设备类型和gps设备认证失败的数据
            GpsTypeAndAuditVo segGpsTypeAndAuditVo = new GpsTypeAndAuditVo();

            if (gpsStatisticEnum == GpsStatisticEnum.GPS_AUDIT_DEVICE) {
                if (CollectionsTools.isNotEmpty(this.jjGpsAuditList.get())) {
                    this.jjGpsAuditList.set(new ArrayList<>());
                }
                if (CollectionsTools.isNotEmpty(this.segGpsAuditList.get())) {
                    this.segGpsAuditList.set(new ArrayList<>());
                }
                wrapperGpsDeviceAndTimes(vo, jjGps, segGps, jjGpsTypeAndAuditVo, segGpsTypeAndAuditVo);
                if (CollectionsTools.isNotEmpty(jjGpsAuditList.get())) {
                    jjGpsTypeAndAuditVo.setGpsAuditVos(jjGpsAuditList.get());
                    gpsTypeAndAuditVos.add(jjGpsTypeAndAuditVo);
                }
                if (CollectionsTools.isNotEmpty(segGpsAuditList.get())) {
                    segGpsTypeAndAuditVo.setGpsAuditVos(segGpsAuditList.get());
                    gpsTypeAndAuditVos.add(segGpsTypeAndAuditVo);
                }
            } else if (gpsStatisticEnum == GpsStatisticEnum.GPS_AUDIT_TIMES) {
                if (CollectionsTools.isNotEmpty(this.jjGpsFailTimesList.get())) {
                    this.jjGpsFailTimesList.set(new HashSet<>());
                }
                if (CollectionsTools.isNotEmpty(this.segGpsFailTimesList.get())) {
                    this.segGpsFailTimesList.set(new HashSet<>());
                }
                wrapperGpsTimes(vo, jjGps, segGps, jjGpsTypeAndAuditVo, segGpsTypeAndAuditVo);
                if (CollectionsTools.isNotEmpty(jjGpsFailTimesList.get())) {
                    jjGpsTypeAndAuditVo.setData(jjGpsFailTimesList.get());
                    gpsTypeAndAuditVos.add(jjGpsTypeAndAuditVo);
                }
                if (CollectionsTools.isNotEmpty(segGpsFailTimesList.get())) {
                    segGpsTypeAndAuditVo.setData(segGpsFailTimesList.get());
                    gpsTypeAndAuditVos.add(segGpsTypeAndAuditVo);
                }
            }
            if (!CollectionsTools.isEmpty(gpsTypeAndAuditVos)) {
                gpsStatisticDataVo.setGpsTypeAndAuditVos(gpsTypeAndAuditVos);
                gpsStatisticDataVos.add(gpsStatisticDataVo);
            }
        });
        return gpsStatisticDataVos;
    }


    /**
     * 设备号和失败次数一一对应
     * @param vo
     * @param jjGps
     * @param segGps
     * @param jjGpsTypeAndAuditVo
     * @param segGpsTypeAndAuditVo
     */
    private void wrapperGpsDeviceAndTimes(ErrorGpsDeviceVo vo, Set<String> jjGps, Set<String> segGps,
                                          GpsTypeAndAuditVo jjGpsTypeAndAuditVo,GpsTypeAndAuditVo segGpsTypeAndAuditVo) {
        log.info("{} - wrapperGpsDeviceAndTimes方法,当前日期:{}", log_title, vo.getDate());
        vo.getDeviceMap().forEach((k, v) -> {
            // 包含gps设备号和对应失败次数
            GpsAuditVo gpsAuditVo = new GpsAuditVo();
            if (jjGps.contains(k)) {
                jjGpsTypeAndAuditVo.setAgencyType(GpsDeviceAgencyType.jjGPS.getName());
                gpsAuditVo.setGpsNo(k);
                String s = (String) v;
                gpsAuditVo.setTimes(Integer.parseInt(s));
                jjGpsAuditList.get().add(gpsAuditVo);
            } else if (segGps.contains(k)) {
                segGpsTypeAndAuditVo.setAgencyType(GpsDeviceAgencyType.segGPS.getName());
                gpsAuditVo.setGpsNo(k);
                String s = (String) v;
                gpsAuditVo.setTimes(Integer.parseInt(s));
                segGpsAuditList.get().add(gpsAuditVo);
            }
        });
    }

    /**
     * 以失败次数为基准，存储相同次数的设备号
     * @param vo
     * @param jjGps
     * @param segGps
     * @param jjGpsTypeAndAuditVo
     * @param segGpsTypeAndAuditVo
     */
    private void wrapperGpsTimes(ErrorGpsDeviceVo vo, Set<String> jjGps, Set<String> segGps,
                                          GpsTypeAndAuditVo jjGpsTypeAndAuditVo,GpsTypeAndAuditVo segGpsTypeAndAuditVo) {
        log.info("{} - wrapperGpsTimes方法,日期:{}", log_title, vo.getDate());
        Map<String, Set<String>> segMap = new TreeMap<>();
        Map<String, Set<String>> jjMap = new TreeMap<>();
        vo.getDeviceMap().forEach((k, v) -> {
            Set<String> jjList = new HashSet<>();
            Set<String> segList = new HashSet<>();
            // 包含gps设备号和对应失败次数
            GpsFailTimesOfDeviceVo gpsFailTimesOfDeviceVo = new GpsFailTimesOfDeviceVo();
            if (jjGps.contains(k)) {
                jjGpsTypeAndAuditVo.setAgencyType(GpsDeviceAgencyType.jjGPS.getName());
                String s = (String) v;
                gpsFailTimesOfDeviceVo.setTimes(Integer.parseInt(s));
                jjList.add(k);
                if (jjMap.get(s) != null) {
                    jjMap.get(s).addAll(jjList);
                }else{
                    jjMap.put(s, jjList);
                }
                gpsFailTimesOfDeviceVo.setGpsNos(jjList);
                jjGpsFailTimesList.get().add(gpsFailTimesOfDeviceVo);
            } else if (segGps.contains(k)) {
                segGpsTypeAndAuditVo.setAgencyType(GpsDeviceAgencyType.segGPS.getName());
                String s = (String) v;
                gpsFailTimesOfDeviceVo.setTimes(Integer.parseInt(s));
                segList.add(k);
                if (segMap.get(s) != null) {
                    segMap.get(s).addAll(segList);
                }else{
                    segMap.put(s, segList);
                }
                gpsFailTimesOfDeviceVo.setGpsNos(segList);

                segGpsFailTimesList.get().add(gpsFailTimesOfDeviceVo);
            }
        });
        log.info("{} - seg设备Map存储数据为:{}", log_title, segMap);
        log.info("{} - jj设备Map存储数据为:{}", log_title, jjMap);
    }
}
