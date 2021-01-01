package com.mljr.gps.controller;

import com.lyqc.base.common.Result;
import com.mljr.gps.entity.SyUser;
import com.mljr.gps.entity.SyUserRoleKey;
import com.mljr.gps.facade.UserFacade;
import com.mljr.gps.form.SyUserForm;
import com.mljr.gps.param.UpdateUserParam;
import com.mljr.gps.param.UserRoleParam;
import com.mljr.gps.vo.SyRoleVo;
import com.mljr.gps.vo.SyUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Date : 2018/12/5$ 16:47$
 * @Author : liht
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户操作")
public class UserController extends AbstractController{

    @Autowired
    private UserFacade userFacade;

    @ApiOperation("用户密码修改")
    @PostMapping("/updatePassword")
    public Result<String> updatePassword(HttpServletRequest request, @RequestBody UpdateUserParam updateUserParam) {
        SyUser syUser = getUser(request);
        return this.userFacade.updatePassword(syUser, updateUserParam);
    }
    @ApiOperation("查询用户")
    @PostMapping("/getUsers")
    public Result<List<SyUserVo>> getUsers(@RequestBody SyUserForm syUserForm) {
        return this.userFacade.getUsers(syUserForm);
    }

    @ApiOperation("查询gps所有的角色")
    @GetMapping("/getGpsRoles")
    public Result<List<SyRoleVo>> getGpsRoles() {
        return this.userFacade.getRoleList();
    }

    @ApiOperation("添加用户角色")
    @PostMapping("/addUserRoles")
    public Result<String> addUserRoles(@RequestBody List<SyUserRoleKey> userRoleKeys) {
        return this.userFacade.addUserRoles(userRoleKeys);
    }
    @ApiOperation("删除用户角色")
    @PostMapping("/delUserRole")
    public Result<String> delUserRoles(@RequestBody List<SyUserRoleKey> userRoleKeys) {
        return this.userFacade.delUserRoles(userRoleKeys);
    }
    @ApiOperation("保存用户角色")
    @PostMapping("/saveUserRoles")
    public Result<Map<String,Integer>> saveUserRoles(@RequestBody UserRoleParam userRoleParam) {
        return this.userFacade.saveUserRoles(userRoleParam);
    }
 }
