package com.mljr.gps.controller;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.gps.entity.GpsCompositeQuery;
import com.mljr.gps.facade.GpsCompositeQueryFacade;
import com.mljr.gps.form.GpsCompositeQueryForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: GPS综合查询Controller
 * @Date : 2018/6/4 下午3:02
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/gpsCompositeQuery")
@Api(description = "【GPS综合查询】相关api",tags = "GpsCompositeQueryController")
public class GpsCompositeQueryController {
    @Autowired
    GpsCompositeQueryFacade gpsCompositeQueryFacade;

    /**
     * GPS综合查询
     * @param form
     * @return
     */
    @RequestMapping(value = "/query",method = {RequestMethod.POST})
    @ApiOperation("excel导出")
    public Result<PageVO<GpsCompositeQuery>> query(@RequestBody PageForm<GpsCompositeQueryForm> form){
        return gpsCompositeQueryFacade.query(form);
    }

    /**
     * 我的GPS审核领单数据导出
     * @param form
     * @return
     */
    @RequestMapping(value = "/mineApprExportQuery",method = {RequestMethod.POST})
    @ApiOperation("excel导出")
    public Result<PageVO<GpsCompositeQuery>> mineApprExportQuery(@RequestBody PageForm<GpsCompositeQueryForm> form){
        return gpsCompositeQueryFacade.mineApprExportQuery(form);
    }

    /**
     * 综合查询重置审批人
     * gps领单后 被退回到新建待提交／复审审批，
     * 审批过程中订单被取消或删除，需重置审批人
     * @param appCode
     * @return
     */
    @RequestMapping(value = "/resetApprovalUser/{appCode}",method = {RequestMethod.GET})
    @ApiOperation("重置审批人")
    public Result<String> resetApprovalUser(@PathVariable String appCode){
        return gpsCompositeQueryFacade.resetApprovalUser(appCode);
    }
}
