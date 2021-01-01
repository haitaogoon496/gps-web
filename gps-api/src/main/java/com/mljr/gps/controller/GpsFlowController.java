package com.mljr.gps.controller;

import com.lyqc.base.common.Result;
import com.lyqc.gpsweb.dto.GpsSendDTO;
import com.mljr.gps.facade.GpsFlowFacade;
import com.mljr.gps.vo.GpsFlowVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: GPS安装单Controller
 * @Date : 2018/6/4 下午3:02
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/gpsFlow")
@Api(description = "【GPS审批操作】相关api",tags = "GpsFlowController")
public class GpsFlowController {
    @Autowired
    GpsFlowFacade gpsFlowFacade;

    /**
     * GPS领单
     * @param appCode
     * @return
     */
    @GetMapping("/acceptBill/{appCode}")
    @ApiOperation("GPS领单")
    public Result<String> acceptBill(@PathVariable String appCode){
        return gpsFlowFacade.acceptBill(appCode);
    }

    /**
     * GPS自动领单
     * @return
     */
/*    @GetMapping("/autoAcceptBill")
    @ApiOperation("GPS自动领单")
    public Result<String> acceptBill(){
        return gpsFlowFacade.autoAcceptBill();
    }*/

    /**
     * GPS派单
     * @return
     */
    @PostMapping("/sendBill")
    @ApiOperation("GPS派单")
    public Result<String> sendBill(@RequestBody GpsSendDTO gpsSendDTO){
        return gpsFlowFacade.sendBill(gpsSendDTO);
    }
    @GetMapping("/getRecord/{appCode}")
    @ApiOperation("获取gpsFlow的信息")
    public Result<GpsFlowVo> getRecord(@PathVariable String appCode) {
        return gpsFlowFacade.queryGpsFlow(appCode);
    }

    /**
     * GPS自动派单
     * @return
     */
    @GetMapping("/autoSendBill")
    @ApiOperation("GPS派单")
    public Result<String> autoSendBill(){
        return gpsFlowFacade.autoSendBill();
    }
}
