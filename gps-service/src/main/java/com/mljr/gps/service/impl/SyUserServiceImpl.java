package com.mljr.gps.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.gps.entity.SyUser;
import com.mljr.gps.form.SyUserForm;
import com.mljr.gps.form.SyUserRoleForm;
import com.mljr.gps.mapper.SyUserMapper;
import com.mljr.gps.service.SyUserService;
import com.mljr.gps.vo.SyUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 用户Service
 * @Date : 2018/3/4 上午10:13
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Service
public class SyUserServiceImpl implements SyUserService{

    @Autowired
    private SyUserMapper syUserMapper;

    @Override
    public SyUser queryByUserId(Integer userId) {
        return syUserMapper.queryByUserId(userId);
    }

    @Override
    public List<SyUser> queryList(SyUserForm form) {
        return syUserMapper.queryList(form);
    }

    @Override
    public SyUser queryByUsername(String username) {
        return syUserMapper.queryByUsername(username);
    }

    @Override
    public List<SyUser> queryByPage(PageForm<SyUserForm> form) {
        return syUserMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<SyUserForm> form) {
        return syUserMapper.queryCount(form);
    }

    @Override
    public SyUser queryRecord(Integer primaryKey) {
        return syUserMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public int insertRecord(SyUser record) {
        return syUserMapper.insert(record);
    }

    @Override
    public int updateRecord(SyUser record) {
        return syUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRecord(Integer primaryKey) {
        return syUserMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    public List<SyUserVo> listApprovalUserList() {
        List<SyUser> userList = syUserMapper.listApprovalUserList();
        List<SyUserVo> voList = new ArrayList<>();
        SyUserVo userVo;
        for(SyUser userEntity : userList){
            userVo = new SyUserVo();
            BeanUtils.copyProperties(userEntity,userVo);
            voList.add(userVo);
        }
        return voList;
    }

    @Override
    public List<Integer> getDealerCodeScopeByUserId(Integer userId) {
        return syUserMapper.getDealerCodeScopeByUserId(userId);
    }

    @Override
    public List<SyUser> queryUsersByUserRoleForm(SyUserRoleForm syUserRoleForm) {
        return syUserMapper.queryUsersByUserRoleForm(syUserRoleForm);
    }

}
