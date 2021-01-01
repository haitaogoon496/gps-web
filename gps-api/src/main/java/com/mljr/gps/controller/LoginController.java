package com.mljr.gps.controller;

import com.lyqc.base.common.Result;
import com.mljr.gps.facade.LoginFacade;
import com.mljr.gps.param.LoginParam;
import com.mljr.gps.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 用户登录
 * @Date : 上午9:54 2018/3/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/login")
@Api(description = "【用户登录】相关api",tags = "LoginController")
public class LoginController {
    @Autowired
    private LoginFacade loginFacade;

    /**
     * 用户登录
     * @param request
     * @param param 登录参数
     * @return Result<String>
     */
    @ApiOperation("【用户登录】用户登录")
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public Result<LoginVo> login(HttpServletRequest request, @RequestBody LoginParam param){
        return this.loginFacade.login(request,param);
    }

    /**
     * 用户退出
     * @param request
     * @return Result<String>
     */
    @ApiOperation("【用户登录】用户退出")
    @RequestMapping(value = "/logout",method = {RequestMethod.POST})
    public Result<String> logout(HttpServletRequest request){
        return this.loginFacade.logout(request);
    }

}
