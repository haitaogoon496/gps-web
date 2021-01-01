package com.mljr.gps.service;

import com.lyqc.base.page.PageForm;
import com.mljr.gps.AbstractTest;
import com.mljr.gps.entity.SyRole;
import com.mljr.gps.form.SyRoleForm;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Date : 2018/3/16$ 14:18$
 * @Author : liht
 */
public class SyRoleServiceTest extends AbstractTest{

    @Resource
    SyRoleService syRoleService;

    static SyRole syRole;

    @BeforeClass
    public static void before() {
        syRole = new SyRole();
        syRole.setRoleName("liht管理");
        syRole.setRoleType(1);

    }

    @Test
    public void testInsert() {
        int i = syRoleService.insertRecord(syRole);
        Assert.assertTrue(i > 0);
    }

    @Test
    public void testUpdate() {
        int id = syRoleService.insertRecord(syRole);
        SyRole syRole = syRoleService.queryRecord(id);
        syRole.setRoleName("liht修改");
        syRoleService.updateRecord(syRole);
        syRole = syRoleService.queryRecord(id);
        Assert.assertEquals("liht修改", syRole.getRoleName());
    }

    @Test
    public void testDel() {
        int id = syRoleService.insertRecord(syRole);
        syRoleService.deleteRecord(id);
        SyRole syRole = syRoleService.queryRecord(id);
        Assert.assertTrue(syRole == null);
    }

    @Test
    public void testQueryPage() {
        SyRoleForm syRoleForm = new SyRoleForm();
        syRoleForm.setRoleName("系统管理员");
        List<SyRole> list = syRoleService.queryByPage(new PageForm<>(syRoleForm));
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testQuertCount() {
        SyRoleForm syRoleForm = new SyRoleForm();
        syRoleForm.setRoleName("系统管理员");
        int count = syRoleService.queryCount(new PageForm<>(syRoleForm));
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testQuertList() {
        SyRoleForm syRoleForm = new SyRoleForm();
        syRoleForm.setRoleName("系统管理员");
        List<SyRole> list = syRoleService.queryList(syRoleForm);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void testQueryRecord() {
        int id = syRoleService.insertRecord(syRole);
        Assert.assertTrue(syRoleService.queryRecord(id) != null);
    }
}
