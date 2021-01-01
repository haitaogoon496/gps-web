package com.mljr.gps.controller;

import com.lyqc.base.common.Result;
import com.mljr.gps.entity.SyButton;
import com.mljr.gps.entity.SyUser;
import com.mljr.gps.facade.SyItemFacade;
import com.mljr.gps.form.GpsMonitorForm;
import com.mljr.gps.vo.GpsMonitorVo;
import com.mljr.gps.vo.SyItemVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: 权限菜单
 * @Date : 2018/8/17$ 11:22$
 * @Author : liht
 */
@RestController
@RequestMapping("/syItem")
@Api(description = "【权限菜单】相关api",tags = "SyItemController")
public class SyItemController extends AbstractController{

    @Resource
    private SyItemFacade syItemFacade;

    /**
     * 按角色查询功能菜单
     * @param request
     * @return
     */
    @GetMapping("/querySyItemsByRoles")
    public Result<List<SyItemVo>> querySyItemsByRoles(HttpServletRequest request) {
        SyUser syUser = getUser(request);
        return syItemFacade.querySyItemsByRoles(syUser);
    }

    @GetMapping("/updateSystemSendBill/{yesOrNo}")
    public Result<Object> addSystemSendBill(@PathVariable("yesOrNo") String yesOrNo,HttpServletRequest request){
       SyUser syUser = getUser(request);
       return syItemFacade.updateSystemSendBill(syUser,yesOrNo);
    }
    @GetMapping("/isAddedSystemSendBill")
    public Result<String> isAddedSystemSendBill(HttpServletRequest request){
        SyUser syUser = getUser(request);
        return syItemFacade.isAddedSystemSendBill(syUser);
    }

    @GetMapping("/queryGpsMonitor")
    @ApiOperation("GPS监控设置查询")
    public Result<GpsMonitorVo> queryGpsMonitor() {
        return syItemFacade.queryGpsMonitor();
    }

    @PostMapping("/saveGpsMonitor")
    @ApiOperation("GPS监控设置保存")
    public Result<GpsMonitorVo> saveGpsMonitor(@RequestBody GpsMonitorForm gpsMonitorForm, HttpServletRequest request) {
        SyUser syUser = getUser(request);
        return syItemFacade.saveGpsMonitor(gpsMonitorForm, syUser);
    }

    @GetMapping("/queryButtonPermission")
    @ApiOperation("查询按钮权限")
    public Result<List<SyButton>> queryButtonPermission(HttpServletRequest request, String syItemId) {
        Integer userId = getUserId(request);
        return syItemFacade.queryButtonPermission(userId, syItemId);
    }

/*    @GetMapping("/isManageSendBillSwitch")
    public Result<String> isManageSendBillSwitch(){
        return syItemFacade.isManageSendBillSwitch();
    }

    @GetMapping("/updateManageSendBillSwitch/{yesOrNo}")
    public Result<Object> updateManageSendBillSwitch(@PathVariable("yesOrNo") String yesOrNo){
        return syItemFacade.updateManageSendBillSwitch(yesOrNo);
    }*/
}
