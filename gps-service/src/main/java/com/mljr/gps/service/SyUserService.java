package com.mljr.gps.service;

import com.mljr.gps.base.service.BaseService;
import com.mljr.gps.entity.SyUser;
import com.mljr.gps.form.SyUserForm;
import com.mljr.gps.form.SyUserRoleForm;
import com.mljr.gps.vo.SyUserVo;

import java.util.List;

/**
 * @description: 用户Service
 * @Date : 2018/3/4 上午10:12
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface SyUserService extends BaseService<SyUser,Integer,SyUserForm>{
    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     */
    SyUser queryByUserId(Integer userId);
    /**
     * 查询列表
     * @param form
     * @return
     */
    List<SyUser> queryList(SyUserForm form);
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    SyUser queryByUsername(String username);

    /**
     * 查询审批人
     * @return
     */
    List<SyUserVo> listApprovalUserList();

    /**
     * 查询所属门店
     * @return
     */
    List<Integer> getDealerCodeScopeByUserId(Integer userId);

    List<SyUser> queryUsersByUserRoleForm(SyUserRoleForm syUserRoleForm);
}
