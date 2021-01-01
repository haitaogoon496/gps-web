package com.mljr.gps.controller;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.gpsweb.vo.GpsInstalledListVo;
import com.mljr.gps.component.CommonComponent;
import com.mljr.gps.facade.GpsQueryListFacade;
import com.mljr.gps.form.GpsQueryListForm;
import com.mljr.gps.vo.GpsQueryListVo;
import com.mljr.gps.vo.SyUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lyqc.gpsweb.dto.GpsQueryListDTO;

import java.util.List;

/**
 * @description: GPS查询列表Controller
 * @Date : 2018/6/4 下午3:02
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/gpsQueryList")
@Api(description = "【GPS安装单查询】相关api",tags = "GpsQueryListController")
public class GpsQueryListController {
    @Autowired
    GpsQueryListFacade gpsQueryListFacade;

    @Autowired
    CommonComponent commonComponent;
    /**
     * 车贷申请管理-待上传GPS安装单
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryListForTobeUploadOnApply",method = {RequestMethod.POST})
    @ApiOperation("车贷申请管理-待上传GPS安装单")
    public Result<PageVO<GpsQueryListVo>> queryListForTobeUploadOnApply(@RequestBody PageForm<GpsQueryListForm> form){
        return gpsQueryListFacade.queryListForTobeUploadOnApply(form);
    }
    /**
     * 车贷申请管理-GPS安装单列表
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryListForInstalledOnApply",method = {RequestMethod.POST})
    @ApiOperation("车贷申请管理-GPS安装单列表")
    public Result<PageVO<GpsQueryListVo>> queryListForInstalledOnApply(@RequestBody PageForm<GpsQueryListForm> form){
        return gpsQueryListFacade.queryListForInstalledOnApply(form);
    }

    /**
     * 车贷审批管理-GPS安装单未上传
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryListForUnInstalledGps",method = {RequestMethod.POST})
    @ApiOperation("车贷审批管理-GPS安装单未上传")
    public Result<PageVO<GpsQueryListVo>> queryListForUnInstalledGps(@RequestBody PageForm<GpsQueryListForm> form){
        return gpsQueryListFacade.queryListForUnInstalledGps(form);
    }

    /**
     * 车贷审批管理-GPS安装单已上传
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryListForInstalledGps",method = {RequestMethod.POST})
    @ApiOperation("车贷审批管理-GPS安装单已上传")
    public Result<PageVO<GpsQueryListVo>> queryListForInstalledGps(@RequestBody PageForm<GpsQueryListForm> form){
        return gpsQueryListFacade.queryListForInstalledGps(form);
    }

    /**
     * 获取GPS审批人列表
     * @return
     */
    @RequestMapping(value = "listApprovalUserList", method = {RequestMethod.GET})
    @ResponseBody
    public Result<List<SyUserVo>> listApprovalUserList() {
        return commonComponent.listApprovalUserList();
    }

    /**
     * 车贷审批管理-GPS审核领单列表
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryListForGpsApprovePull",method = {RequestMethod.POST})
    @ApiOperation("车贷申请管理-GPS审核领单列表")
    public Result<PageVO<GpsQueryListVo>> queryListForGpsApprovePull(@RequestBody PageForm<GpsQueryListForm> form){
        return gpsQueryListFacade.queryListForGpsApprovePull(form);
    }

    /**
     * 车贷审批管理-我的GPS领单列表
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryListForGpsApproveMine",method = {RequestMethod.POST})
    @ApiOperation("车贷申请管理-我的GPS领单列表")
    public Result<PageVO<GpsQueryListVo>> queryListForGpsApproveMine(@RequestBody PageForm<GpsQueryListForm> form){
        return gpsQueryListFacade.queryListForGpsApproveMine(form);
    }

    /**
     * 车贷申请管理-GPS综合查询
     * @param form
     * @return
     */
    @RequestMapping(value = "/queryListForGpsGeneral",method = {RequestMethod.POST})
    @ApiOperation("车贷申请管理-gps综合查询")
    public Result<PageVO<GpsQueryListVo>> queryListForGeneral(@RequestBody PageForm<GpsQueryListForm> form){
        return gpsQueryListFacade.queryListForGpsGeneral(form);
    }

    /**
     * 风云系统调用-GPS安装单列表
     * @param
     * @return
     */
    @RequestMapping(value = "/queryListForInstalled",method = {RequestMethod.POST})
    @ApiOperation("风云系统-GPS安装单列表")
    public Result<PageVO<GpsInstalledListVo>> queryListForInstalled(@RequestBody PageForm<GpsQueryListDTO> form){
        return gpsQueryListFacade.queryListForInstalled(form);
    }
}
