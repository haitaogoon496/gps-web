package com.mljr.gps.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.gps.AbstractTest;
import com.mljr.gps.entity.SyUser;
import com.mljr.gps.form.SyUserForm;
import com.mljr.gps.service.SyUserService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @description:
 * @Date : 2018/3/16$ 14:57$
 * @Author : liht
 */
public class SyUserServiceImplTest extends AbstractTest{

    @Resource
    SyUserService syUserService;

    static SyUser syUser;

    @BeforeClass
    public static void before9() {
        syUser = new SyUser();
        syUser.setUserId(34234324);
        syUser.setUserName("lihtjunit");
        syUser.setAddress("望京");
        syUser.setBirthday("1992-01-01");
        syUser.setCardId("2321321");
        syUser.setEmail("324.com");
        syUser.setCity("beijign");
        syUser.setTrueName("liht");
        syUser.setPassword("it");
        syUser.setPhone("2132132");
        syUser.setUserStatus("1");
        syUser.setUserType("2");
    }

    @Test
    public void queryList() throws Exception {
        SyUserForm form = new SyUserForm();
        form.setUserName("liht");

        List<SyUser> list = syUserService.queryList(form);
        assertTrue(list.size() > 0);

    }

    @Test
    public void queryByUsername() throws Exception {

        SyUser syUser = syUserService.queryByUsername("liht");
        assertTrue(syUser != null);

    }

    @Test
    public void queryByPage() throws Exception {
        SyUserForm form = new SyUserForm();
        form.setUserName("liht");

        List<SyUser> list = syUserService.queryByPage(new PageForm<>(form));

        assertTrue(list.size() > 0);
    }

    @Test
    public void queryCount() throws Exception {
        SyUserForm form = new SyUserForm();
        form.setUserName("liht");

        int count = syUserService.queryCount(new PageForm<>(form));

        assertTrue(count > 0);
    }

    @Test
    public void queryRecord() throws Exception {
        SyUser syUser = syUserService.queryRecord(1);
        assertTrue(syUser != null);

    }

    @Test
    public void insertRecord() throws Exception {
        int id = syUserService.insertRecord(syUser);
        assertTrue(id ==1);
    }

    @Test
    public void updateRecord() throws Exception {
        int id = syUserService.insertRecord(syUser);
        syUser.setUserStatus("3");
        syUserService.updateRecord(syUser);
        SyUser syUser = syUserService.queryRecord(34234324);
        assertEquals("3",syUser.getUserStatus());
    }

    @Test
    public void deleteRecord() throws Exception {
        int id = syUserService.insertRecord(syUser);
        int i = syUserService.deleteRecord(34234324);
        assertTrue(i ==1);
    }

}