package com.mljr.gps.controller;

import com.lyqc.base.common.Result;
import com.lyqc.base.enums.AnnexTypeEnum;
import com.lyqc.gpsweb.dto.GpsApproveDTO;
import com.lyqc.gpsweb.vo.GpsApplyFormVo;
import com.mljr.gps.facade.GpsApproveFacade;
import com.mljr.gps.vo.GpsInstallDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: GPS审批Controller
 * @Date : 2018/6/28 下午4:31
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/gpsApprove")
@Api(description = "【GPS审批】相关api",tags = "GpsApproveController")
public class GpsApproveController {
    @Autowired
    GpsApproveFacade gpsApproveFacade;

    /**
     * 查询GPS申请单
     * @param appCode
     * @return
     */
    @GetMapping("/queryApplyForm/{appCode}")
    @ApiOperation("查询GPS申请单")
    public Result<GpsApplyFormVo> queryApplyForm(@PathVariable String appCode){
        return gpsApproveFacade.queryApplyForm(appCode);
    }
    /**
     * 保存GPS设备信息
     * @param dto
     * @return
     */
    @PostMapping("/apply")
    @ApiOperation("保存GPS设备信息")
    public Result<String> apply(@RequestBody GpsApproveDTO dto){
        return gpsApproveFacade.apply(dto);
    }
    /**
     * GPS人工审核
     * @param dto
     * @return
     */
    @PostMapping("/approve")
    @ApiOperation("GPS人工审核")
    public Result<String> approve(@RequestBody GpsApproveDTO dto){
        return gpsApproveFacade.doApprove(dto);
    }

    /**
     * gps安装单详情
     * @param appCode
     * @return
     */
    @GetMapping("/gpsInstallDetail/{appCode}")
    @ApiOperation("gps安装单详情")
    public Result<GpsInstallDetailVo> getGpsInstallDetail(@PathVariable String appCode) {
        return gpsApproveFacade.getDetail(appCode);
    }

    /**
     * gps安装单详情
     * @param appCode
     * @param historyId
     * @return
     */
    @GetMapping("/gpsInstallDetail")
    @ApiOperation("gps安装单详情")
    public Result<GpsInstallDetailVo> getGpsInstallDetail(@RequestParam("appCode") String appCode,@RequestParam("historyId") Integer historyId) {
        return gpsApproveFacade.getDetail(appCode,historyId);
    }
    @PostMapping("/uploadGpsFile")
    @ApiOperation("上传gps附件")
    public Result<String> uploadGpsFile(MultipartFile file, String appCode, AnnexTypeEnum annexType, String annexName) {
        return gpsApproveFacade.uploadGpsFile(file, appCode, annexType, annexName);
    }


    @GetMapping("/hookGps/{appCode}")
    @ApiOperation("gps订单勾回操作")
    public Result<String> hookGps(@PathVariable String appCode) {
        return gpsApproveFacade.hookGps(appCode);
    }
}
