package com.mljr.gps.facade;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.page.PageForm;
import com.mljr.annotation.LogMonitor;
import com.mljr.annotation.OvalValidator;
import com.mljr.gps.common.consts.HeilCode;
import com.mljr.gps.common.util.ValidateUtils;
import com.mljr.gps.entity.SyRole;
import com.mljr.gps.entity.SyUser;
import com.mljr.gps.entity.SyUserRoleKey;
import com.mljr.gps.form.SyRoleForm;
import com.mljr.gps.form.SyUserForm;
import com.mljr.gps.form.SyUserRoleForm;
import com.mljr.gps.param.UpdateUserParam;
import com.mljr.gps.param.UserRoleParam;
import com.mljr.gps.service.SyRoleService;
import com.mljr.gps.service.SyUserRoleService;
import com.mljr.gps.service.SyUserService;
import com.mljr.gps.vo.SyRoleVo;
import com.mljr.gps.vo.SyUserRoleVo;
import com.mljr.gps.vo.SyUserVo;
import com.mljr.gps.vo.orderVo.RiskInfoVo;
import com.mljr.gps.voconvertor.SyRoleVoConvertor;
import com.mljr.gps.voconvertor.SyUserVoConvertor;
import com.mljr.util.CollectionsTools;
import com.mljr.util.MD5;
import com.sun.org.apache.bcel.internal.generic.INEG;
import com.sun.tools.corba.se.idl.StringGen;
import lombok.extern.slf4j.Slf4j;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @Date : 2018/12/5$ 16:48$
 * @Author : liht
 */
@Component
@Slf4j
public class UserFacade {

    @Autowired
    private SyUserService syUserService;
    @Autowired
    private SyRoleService syRoleService;
    @Autowired
    private SyUserRoleService syUserRoleService;

    @LogMonitor("修改密码")
    @OvalValidator("修改密码")
    public Result<String> updatePassword(SyUser syUser, UpdateUserParam updateUserParam) {
        if (syUser == null) {
            return Result.fail(HeilCode.E_500, "请登录");
        }
        log.info("用户id:{},更新信息:{}", syUser.getUserId(), JSON.toJSON(updateUserParam));
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(updateUserParam);
        if (CollectionsTools.isNotEmpty(violations)) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM.getIndex(), violations.get(0).getMessage());
        }

        if (updateUserParam.getPassword().equals(updateUserParam.getNewPassword())) {
            return Result.fail(HeilCode.E_500, "原始密码和新密码一样！");
        }

        if (!updateUserParam.getNewPassword().equals(updateUserParam.getConfirmPassword())) {
            return Result.fail(HeilCode.E_500, "新密码和确认密码不一致！");
        }

        if (!syUser.getPassword().equals(MD5.getMD5Str(updateUserParam.getPassword()))) {
            return Result.fail(HeilCode.E_500, "原始密码输入不正确！");
        }

        syUser.setPassword(MD5.getMD5Str(updateUserParam.getNewPassword()));
        syUserService.updateRecord(syUser);
        return Result.suc();
    }

    @LogMonitor("添加用户")
    @OvalValidator("添加用户校验")
    public Result<String> addUser(SyUser syUser) {
        int id = syUserService.insertRecord(syUser);
        return Result.suc(String.valueOf(id));
    }

    @LogMonitor("获取gps菜单角色")
    public Result<List<SyRoleVo>> getRoleList() {

        SyRoleForm form = new SyRoleForm();
        form.setRoleName("GPS");
        List<SyRole> roleList = this.syRoleService.queryByPage(new PageForm<>(form));
        if (CollectionsTools.isEmpty(roleList)) {
            return Result.failInEmptyRecord(null);
        }
        List<SyRoleVo> roleVos = new SyRoleVoConvertor().convertList(roleList);
        roleVos.forEach(syRoleVo -> {
            if (!StringUtils.isEmpty(syRoleVo.getRoleId())) {
                List<SyUser> users = syUserService.queryUsersByUserRoleForm(new SyUserRoleForm(null,Integer.parseInt(syRoleVo.getRoleId())));
                syRoleVo.setUsers(users);
            }
        });
        log.info("getRoleList - 所有gps角色:{}", JSON.toJSON(roleList));
        return Result.suc(roleVos);
    }

    @LogMonitor("用户查询")
    public Result<List<SyUserVo>> getUsers(SyUserForm syUserForm) {
        List<SyUser> users = syUserService.queryList(syUserForm);
        ValidateUtils.notEmpty(users, HeilCode.E_500, "当前用户不存在");
        List<SyUserVo> userVos = new SyUserVoConvertor().convertList(users);
        return Result.suc(userVos);
    }
    @LogMonitor(value = "添加用户角色")
    public Result<String> addUserRoles(List<SyUserRoleKey> syUserRoleKeys) {
        int count = syUserRoleService.batchInsertIgnore(syUserRoleKeys);
        log.info("添加用户角色影响的行数:{}", count);
        return Result.suc(String.valueOf(count));
    }

    @LogMonitor(value = "删除用户角色")
    public Result<String> delUserRoles(List<SyUserRoleKey> syUserRoleKeys) {
        int count = syUserRoleService.batchDelete(syUserRoleKeys);
        log.info("删除用户角色影响的行数:{}", count);
        return Result.suc(String.valueOf(count));
    }

    @LogMonitor(value = "gps用户角色保存")
    public Result<Map<String,Integer>> saveUserRoles(UserRoleParam userRoleParam) {
        Boolean boll = CollectionsTools.isEmpty(userRoleParam.getDeleteUsers()) && CollectionsTools.isEmpty(userRoleParam.getInsertUsers());
        ValidateUtils.isFalse(boll, HeilCode.E_500, "参数不能为空");
        Map<String, Integer> result = new HashMap<>(2);
        if (CollectionsTools.isNotEmpty(userRoleParam.getDeleteUsers())) {
            int count = syUserRoleService.batchDelete(userRoleParam.getDeleteUsers());
            log.info("删除用户角色影响的行数:{}", count);
            result.put("del", count);
        }
        if (CollectionsTools.isNotEmpty(userRoleParam.getInsertUsers())) {
            int count = syUserRoleService.batchInsertIgnore(userRoleParam.getInsertUsers());
            log.info("添加用户角色影响的行数:{}", count);
            result.put("insert", count);
        }
        return Result.suc(result);
    }
}
