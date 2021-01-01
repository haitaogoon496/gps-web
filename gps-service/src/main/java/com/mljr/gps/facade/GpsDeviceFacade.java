package com.mljr.gps.facade;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.gpsprovider.enums.GpsDeviceAgencyType;
import com.lyqc.gpsprovider.re.GpsDeviceInfoRe;
import com.lyqc.gpsweb.dto.CarGpsDTO;
import com.lyqc.gpsweb.dto.GpsDeviceModifyDTO;
import com.lyqc.gpsweb.dto.GpsRepeatDTO;
import com.lyqc.gpsweb.re.GpsRepeatRe;
import com.mljr.annotation.LogMonitor;
import com.mljr.annotation.OvalValidator;
import com.mljr.component.GpsDeviceInfoComponent;
import com.mljr.gps.common.consts.HeilCode;
import com.mljr.gps.common.util.ValidateUtils;
import com.mljr.gps.entity.AppInfo;
import com.mljr.gps.entity.CarGps;
import com.mljr.gps.entity.CarInfo;
import com.mljr.gps.entity.SyRole;
import com.mljr.gps.form.SyRoleForm;
import com.mljr.gps.param.GpsDeviceParam;
import com.mljr.gps.service.AppInfoService;
import com.mljr.gps.service.CarGpsService;
import com.mljr.gps.service.CarInfoService;
import com.mljr.gps.service.SyRoleService;
import com.mljr.util.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: GPS设备Facade
 * @Date : 2018/7/17 下午3:27
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Component
public class GpsDeviceFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private CarGpsService carGpsService;
    @Autowired
    private CarInfoService carInfoService;
    @Autowired
    private GpsDeviceInfoComponent gpsDeviceInfoComponent;
    @Autowired
    private SyRoleService syRoleService;
    /**
     * 根据GPS设备号，获取第三方GPS设备号状态
     * @param gpsDevices GPS设备号
     * @return
     */
    @Deprecated
    @LogMonitor("查询GPS设备认证")
    public Result<List<GpsDeviceInfoRe>> getGpsInfos(String gpsDevices){
        if(StringTools.isEmpty(gpsDevices)){
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM);
        }
        List<String> ids = Arrays.asList(gpsDevices.split(","));
        return gpsDeviceInfoComponent.getGpsInfos(ids);
    }

    /**
     * 根据GPS设备号，gps生产商标识，获取第三方GPS设备号状态
     * @param gpsDeviceParams GPS设备参数
     * @return
     */
    @LogMonitor("查询GPS设备认证")
    public Result<List<GpsDeviceInfoRe>> getGpsInfos(List<GpsDeviceParam> gpsDeviceParams){
        LOGGER.info("gps设备状态查询-入参:{}", JSON.toJSON(gpsDeviceParams));
        if(CollectionUtils.isEmpty(gpsDeviceParams)){
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM);
        }
        List<GpsDeviceInfoRe> gpsDeviceInfoReList = new ArrayList<>();

        gpsDeviceParams.forEach(gpsDeviceParam -> {
            GpsDeviceAgencyType gpsDeviceAgencyType = GpsDeviceAgencyType.getByGpsDealer(gpsDeviceParam.getGpsDealer());
            Result<List<GpsDeviceInfoRe>> gpsDeviceReResult = gpsDeviceInfoComponent.getGpsInfos(Arrays.asList(gpsDeviceParam.getDeviceNo()), gpsDeviceAgencyType);
            ValidateUtils.isTrue(gpsDeviceReResult.isSuccess(), HeilCode.E_500,gpsDeviceReResult.getMsg());
            ValidateUtils.notEmpty(gpsDeviceReResult.getData(), HeilCode.E_500, "获取数据为空,请稍后重试");
            gpsDeviceInfoReList.add(gpsDeviceReResult.getData().get(0));
        });
        return Result.suc(gpsDeviceInfoReList);
    }

    /**
     * 查询GPS设备相应的重复申请单号
     * @param dto
     * @return
     */
    @OvalValidator("查询GPS设备认证")
    public Result<List<GpsRepeatRe>> getRepeatAppCodes(GpsRepeatDTO dto){
        String appCode = dto.getAppCode();
        Set<String> ids = dto.getDeviceList();
        List<GpsRepeatRe> list = new ArrayList<>(ids.size());
        try {
            ids.forEach(deviceNo -> {
                List<String> repeatQueryList = carGpsService.queryAppCodesByGpsNoForCaCarGps(deviceNo);
                Set<String> repeatList = repeatQueryList.stream().filter(each -> !each.equals(appCode)).collect(Collectors.toSet());
                list.add(GpsRepeatRe.newInstance(deviceNo,repeatList));
            });
        } catch (Exception e) {
            return Result.failInServer(Collections.emptyList());
        }
        return Result.suc(list);
    }

    /**
     * 更新申请单号对应的GPS设备号
     * @param dto
     * @return
     */
    @OvalValidator("GPS设备更新")
    @LogMonitor("GPS设备更新")
    public Result<String> updateGpsDevice(GpsDeviceModifyDTO dto){
        List<CarGpsDTO> gpsList = dto.getGpsList();
        try {
            //1、更新ca_gps表
            gpsList.forEach(gps -> {
                CarGps carGps = new CarGps();
                carGps.setTid(Integer.valueOf(gps.getTid()));
                carGps.setGpsNo(gps.getGpsNo());
                carGpsService.updateSelective(carGps);
            });
            //2、更新车辆表相关GPS信息
            AppInfo appInfo = appInfoService.queryAppInfo(dto.getAppCode());
            if(null == appInfo){
                LOGGER.info("操作失败,未找到相应申请单号,dto={}",dto);
                return Result.failInBusiness("操作失败,未找到相应申请单号");
            }
            if(null == appInfo.getCarId()){
                LOGGER.info("操作失败,未找到相应申请单号的车辆ID,dto={}",dto);
                return Result.failInBusiness("操作失败,未找到相应申请单号的车辆ID");
            }
            CarInfo updateCarInfo = new CarInfo();
            List<String> gpsNoList = gpsList.stream().map(gps -> gps.getGpsNo()).collect(Collectors.toList());
            updateCarInfo.setCarId(appInfo.getCarId());
            updateCarInfo.setGpsNo(StringTools.valueOfList(gpsNoList));
            carInfoService.updateGpsInfo(updateCarInfo);
        } catch (Exception e) {
            LOGGER.error("更新申请单号对应的GPS设备号异常,dto={}",dto,e);
            return Result.failInServer("操作失败");
        }
        return Result.suc();
    }

    public Result<Boolean> isViewGpsAdjustButton(Integer userId) {
        String title = "gps综合查询是否显示gps设备调整按钮";
        LOGGER.info("{} - userId:{}", title, userId);
        // 查询用户所有角色
        List<SyRole> userRoleList = syRoleService.queryList(new SyRoleForm(userId));
        if (CollectionUtils.isEmpty(userRoleList)) {
            LOGGER.warn("{}-当前用户没有角色-userId:{}", title, userId);
            return Result.suc(false);
        }
        List<String> list = userRoleList.stream().map(item -> item.getRoleName()).collect(Collectors.toList());
        LOGGER.info("{} - userId:{},所包含的role:{}", title, userId, JSON.toJSON(list));
        userRoleList = userRoleList.stream().filter(item -> item.getRoleName().contains("GPS设备号调整")).collect(Collectors.toList());
        if (userRoleList.size() > 0) {
            return Result.suc(true);
        }
        return Result.suc(false);
    }

}
