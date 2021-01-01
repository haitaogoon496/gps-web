package com.mljr.gps.service.impl;

import com.mljr.gps.AbstractTest;
import com.mljr.gps.entity.SyUserLog;
import com.mljr.gps.service.SyUserLogService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @description:
 * @Date : 2018/3/16$ 14:33$
 * @Author : liht
 */
public class SyUserLogServiceImplTest extends AbstractTest{

    @Resource
    SyUserLogService syUserLogService;

    static SyUserLog syUserLog;

    @BeforeClass
    public static void before() {
        syUserLog = new SyUserLog();
        syUserLog.setIpAddress("1.0.0.0");
        syUserLog.setBrowser("liht");
        syUserLog.setType(1);
        syUserLog.setUserId(32432432);

    }

    @Test
    public void queryRecord() throws Exception {
        long id = syUserLogService.insertRecord(syUserLog);
        SyUserLog syUserLog = syUserLogService.queryRecord(id);
        Assert.assertTrue(syUserLog != null);
    }

    @Test
    public void insertRecord() throws Exception {
        long id = syUserLogService.insertRecord(syUserLog);

        Assert.assertTrue(id > 0);
    }

    @Test
    public void updateRecord() throws Exception {
        long id = syUserLogService.insertRecord(syUserLog);
        SyUserLog syUserLog = syUserLogService.queryRecord(id);
        syUserLog.setUserName("liht1");
        syUserLogService.updateRecord(syUserLog);
        Assert.assertEquals("liht1", syUserLog.getUserName());
    }

    @Test
    public void deleteRecord() throws Exception {
        long id = syUserLogService.insertRecord(syUserLog);
        syUserLogService.deleteRecord(id);
        SyUserLog syUserLog = syUserLogService.queryRecord(id);
        Assert.assertTrue(syUserLog == null);

    }

}