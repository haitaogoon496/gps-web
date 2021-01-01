package com.mljr.gps.service.impl;

import com.mljr.gps.AbstractTest;
import com.mljr.gps.entity.SyUserRoleKey;
import com.mljr.gps.service.SyUserRoleService;
import com.mljr.util.CollectionsTools;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @description:
 * @Date : 2018/3/16$ 14:44$
 * @Author : liht
 */
public class SyUserRoleServiceImplTest extends AbstractTest{

    @Resource
    SyUserRoleService syUserRoleService;
    static SyUserRoleKey key;

    @BeforeClass
    public static void before() {
        key = new SyUserRoleKey();
        key.setUserId(12);
        key.setRoleId(11);
    }




    @Test
    public void insertRecord() throws Exception {
        int i = syUserRoleService.insertRecord(key);

        assertTrue(1 == i);
    }


    @Test
    public void deleteRecord() throws Exception {
        syUserRoleService.insertRecord(key);
        int i = syUserRoleService.deleteRecord(key);
        assertTrue(i == 1);
    }

}