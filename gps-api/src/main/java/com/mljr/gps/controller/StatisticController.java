package com.mljr.gps.controller;

import com.lyqc.base.common.Result;
import com.mljr.gps.facade.StatisticsFacade;
import com.mljr.gps.param.GpsDeviceParam;
import com.mljr.gps.vo.ErrorGpsDeviceVo;
import com.mljr.gps.vo.statistic.GpsStatisticDataVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Date : 2018/12/17$ 18:37$
 * @Author : liht
 */
@RestController
@Api(description = "【GPS数据统计】相关api", tags = "StatisticController")
@RequestMapping("/gpsStatistic")
public class StatisticController {

    @Resource
    private StatisticsFacade statisticsFacade;


    @ApiOperation("获取错误设备商统计量")
    @PostMapping("/getGpsErrorDeviceNum")
    public Result<List<GpsStatisticDataVo>> getGpsErrorDeviceNum(@RequestBody GpsDeviceParam gpsDeviceParam) {
        return this.statisticsFacade.getGpsErrorDeviceNum(gpsDeviceParam);
    }

}
