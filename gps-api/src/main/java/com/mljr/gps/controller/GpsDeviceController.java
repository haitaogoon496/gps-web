package com.mljr.gps.controller;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.gpsprovider.re.GpsDeviceInfoRe;
import com.lyqc.gpsweb.dto.GpsDeviceModifyDTO;
import com.lyqc.gpsweb.dto.GpsRepeatDTO;
import com.lyqc.gpsweb.re.GpsRepeatRe;
import com.mljr.gps.entity.AppInfo;
import com.mljr.gps.facade.GpsDeviceFacade;
import com.mljr.gps.param.GpsDeviceParam;
import com.mljr.gps.service.AppInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: GPS设备controller
 * @Date : 2018/7/17 下午3:27
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/gpsDevice")
@Api(description = "【GPS设备操作】相关api",tags = "GpsDeviceController")
@Slf4j
public class GpsDeviceController extends AbstractController{
    @Autowired
    private GpsDeviceFacade gpsDeviceFacade;
    @Autowired
    private AppInfoService appInfoService;
    /**
     * 查询GPS设备号状态
     * @param ids
     * @return
     */
    @Deprecated
    @GetMapping("/getGpsInfos/{ids}")
    @ApiOperation("查询GPS设备号状态")
    public Result<List<GpsDeviceInfoRe>> getGpsInfos(@PathVariable String ids){
        return gpsDeviceFacade.getGpsInfos(ids);
    }

    /**
     * 查询GPS设备号状态
     * @param deviceParams
     * @return
     */
    @PostMapping("/getGpsInfos")
    @ApiOperation("查询GPS设备号状态")
    public Result<List<GpsDeviceInfoRe>> getGpsInfos(@RequestBody List<GpsDeviceParam> deviceParams){
        return gpsDeviceFacade.getGpsInfos(deviceParams);
    }

    /**
     * 查询GPS设备相应的重复申请单号
     * @param dto
     * @return
     */
    @PostMapping("/getRepeatAppCodes")
    @ApiOperation("查询GPS设备相应的重复申请单号")
    @ResponseBody
    public Result<List<GpsRepeatRe>> getRepeatAppCodes(@RequestBody GpsRepeatDTO dto){
        return gpsDeviceFacade.getRepeatAppCodes(dto);
    }

    /**
     * 更新申请单号对应的GPS设备号
     * @param dto
     * @return
     */
    @PostMapping("/updateGpsDevice")
    @ApiOperation("更新申请单号对应的GPS设备号")
    @ResponseBody
    public Result<String> updateGpsDevice(@RequestBody GpsDeviceModifyDTO dto){
        return gpsDeviceFacade.updateGpsDevice(dto);
    }

    @RequestMapping(value = "/isViewGpsAdjustButton", method = RequestMethod.GET)
    @ApiOperation("【GPS综合查询】是否显示gps调整按钮")
    public Result<Boolean> isViewGpsAdjustButton(HttpServletRequest request) {
        return this.gpsDeviceFacade.isViewGpsAdjustButton(getUserId(request));

    }

    @RequestMapping(value = "/getIsGpsStatus/{appCode}",method = RequestMethod.GET)
    @ApiOperation(value = "获取appInfo中isGps信息")
    public Result<String> getIsGpsStatus(HttpServletRequest request,@PathVariable String appCode) {
        Integer userId = getUserId(request);
        AppInfo caAppInfo = this.appInfoService.queryAppInfo(appCode);
        if (caAppInfo == null) {
            Result.fail(RemoteEnum.WARN_EMPTY_RECORD, "当前数据不存在,请核查后操作");
        }
        log.info("{} - userId:{}，返回结果:{}", userId, JSON.toJSON(caAppInfo));
        return Result.suc(caAppInfo.getIsGps());
    }

}
