package com.mljr.gps.controller;

import com.lyqc.base.common.Result;
import com.lyqc.gpsweb.re.CarGpsRe;
import com.mljr.gps.facade.CarGpsFacade;
import com.lyqc.gpsweb.vo.GpsOperateRecordVo;
import com.lyqc.gpsweb.vo.GpsHistoryRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: GPS安装单Controller
 * @Date : 2018/6/4 下午3:02
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/carGps")
@Api(description = "【GPS安装单】相关api",tags = "CarGpsController")
public class CarGpsController {
    @Autowired
    CarGpsFacade carGpsFacade;

    /**
     * 查询GPS安装单列表
     * @param appCode
     * @return
     */
    @RequestMapping(value = "/queryInstallList/{appCode}",method = {RequestMethod.GET})
    @ApiOperation("查询GPS安装单列表")
    public Result<List<CarGpsRe>> queryInstallList(@PathVariable String appCode){
        return carGpsFacade.queryInstallList(appCode);
    }

    /**
     * 查询GPS操作记录
     * @param appCode
     * @return
     */
    @GetMapping("/queryOperateRecordList/{appCode}")
    @ApiOperation("查询GPS操作记录")
    public Result<List<GpsOperateRecordVo>> queryOperateRecordList(@PathVariable String appCode){
        return carGpsFacade.queryOperateRecordList(appCode);
    }

    /**
     * 查询GPS历史记录
     * @param appCode
     * @return
     */
    @GetMapping("/queryHistoryList/{appCode}")
    @ApiOperation("查询GPS历史记录")
    public Result<List<GpsHistoryRecordVo>> queryHistoryList(@PathVariable String appCode){
        return carGpsFacade.queryHistoryList(appCode);
    }
}
