package com.mljr.gps.controller;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.gps.entity.GpsContract;
import com.mljr.gps.facade.GpsContractFacade;
import com.mljr.gps.form.GpsContractForm;
import com.mljr.gps.vo.GpsContractVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @description:
 * @Date : 2018/8/21 15:01
 * @Author : lihaitao
 */
@RestController
@RequestMapping("/gpsContract")
@Api(description = "【GPS安装联系人】相关api",tags = "GpsContractController")
public class GpsContractController {

    @Autowired
    private GpsContractFacade gpsContractFacade;
    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<FundVo>>
     */
    @ApiOperation("【GPS安装联系人】查询列表")
    @RequestMapping(value = "/loadRecords",method = {RequestMethod.POST})
    public Result<PageVO<GpsContractVo>> loadRecords(@RequestBody PageForm<GpsContractForm> form){
        return this.gpsContractFacade.loadRecords(form);
    }
    /**
     * 查询详情
     * @param id
     * @return Result<FundVo>
     */
    @ApiOperation("【GPS安装联系人】查询详情")
    @RequestMapping(value = "/queryRecord/{id}",method = {RequestMethod.GET})
    public Result<GpsContractVo> queryRecord(@PathVariable Integer id){
        return this.gpsContractFacade.queryRecord(id);
    }
    /**
     * 查询详情
     * @param appCode
     * @return Result<FundVo>
     */
    @ApiOperation("【GPS安装联系人】查询详情")
    @RequestMapping(value = "/queryRecordByAppCode/{appCode}",method = {RequestMethod.GET})
    public Result<GpsContractVo> queryRecordByAppCode(@PathVariable String appCode){
        return this.gpsContractFacade.queryRecordByAppCode(appCode);
    }
    /**
     * 删除
     * @param id
     * @return Result<String>
     */
    @ApiOperation("【GPS安装联系人】删除记录")
    @RequestMapping(value = "/deleteRecord/{id}",method = {RequestMethod.GET})
    public Result<String> deleteRecord(@PathVariable Integer id){
        return this.gpsContractFacade.deleteRecord(id);
    }
    /**
     * 保存
     * @param record
     * @return Result<String>
     */
    @ApiOperation("【GPS安装联系人】新增/修改记录")
    @RequestMapping(value = "/saveRecord",method = {RequestMethod.POST})
    public Result<String> saveRecord(@RequestBody GpsContract record){
        return this.gpsContractFacade.saveRecord(record);
    }

}
