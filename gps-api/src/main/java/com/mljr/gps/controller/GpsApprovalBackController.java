package com.mljr.gps.controller;

import com.lyqc.base.common.Result;
import com.mljr.gps.facade.GpsApprovalBackFacade;
import com.mljr.gps.form.GpsApprovalBackForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 退回审批修改GPS信息Controller
 * @author zhaoxin
 * @date 2018/7/5 下午5:45
 **/
@RestController
@RequestMapping("/gpsApproval")
@Api(description = "【GPS退回审批修改】相关api",tags = "GpsApprovalBackController")
public class GpsApprovalBackController {

    @Autowired
    GpsApprovalBackFacade gpsApprovalBackFacade;

    /**
     * 终审审批通过修改GPS信息
     * @param appCode
     * @return
     */
    @RequestMapping(value = "/updateGpsApprovalInfo/{appCode}",method = {RequestMethod.GET})
    @ApiOperation("GPS退回审批修改")
    public Result<String> updateGpsInfo(@PathVariable String appCode){
        return gpsApprovalBackFacade.updateGpsInfo(appCode);
    }

    /**
     * 直接审批经历审批通过后修改GPS信息
     * @param form
     * @return
     */
    @RequestMapping(value = "/bactchUpdateGpsApprovalInfo",method = {RequestMethod.POST})
    @ApiOperation("终审审批通过修改GPS信息")
    public Result<String> bactchUpdateGpsApprovalInfo(@RequestBody GpsApprovalBackForm form){
        return gpsApprovalBackFacade.bactchUpdateGpsApprovalInfo(form);
    }


    /**
     * GPS审批退回
     * @param form
     * @return
     */
    @RequestMapping(value = "/insertOperateRecord",method = {RequestMethod.POST})
    @ApiOperation("GPS审批退回添加操作记录")
    public Result<String> insertOperateRecord(@RequestBody GpsApprovalBackForm form){
        return gpsApprovalBackFacade.insertOperateRecord(form);
    }

}
