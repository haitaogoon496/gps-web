package com.mljr.gps.mapper;

import com.mljr.gps.base.mapper.BaseMapper;
import com.mljr.gps.entity.SyUser;
import com.mljr.gps.form.SyUserForm;
import com.mljr.gps.form.SyUserRoleForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 用户Mapper
 * @Date : 2018/3/4 上午10:11
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface SyUserMapper extends BaseMapper<SyUser,Integer,SyUserForm>{
    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     */
    SyUser queryByUserId(Integer userId);
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    SyUser queryByUsername(@Param("userName") String username);

    /**
     * 查询审批人
     * @return
     */
    List<SyUser> listApprovalUserList();

    /**
     * 根据userId获取门店Id
      * @param userId
     * @return
     */
    List<Integer> getDealerCodeScopeByUserId(Integer userId);


    List<SyUser> queryUsersByUserRoleForm(SyUserRoleForm syUserRoleForm);
}