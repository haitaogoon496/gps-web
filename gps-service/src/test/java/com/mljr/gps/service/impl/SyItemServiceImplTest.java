package com.mljr.gps.service.impl;

import com.alibaba.druid.sql.dialect.mysql.ast.MysqlForeignKey;
import com.mljr.gps.AbstractTest;
import com.mljr.gps.entity.SyUser;
import com.mljr.gps.facade.SyItemFacade;
import com.mljr.gps.service.SyUserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @Date : 2018/10/30$ 11:18$
 * @Author : liht
 */
public class SyItemServiceImplTest extends AbstractTest{

    @Resource
    private SyItemFacade syItemFacade;

    @Resource
    private SyUserService syUserService;

    @Test
    public void testAddProductUser() {
        SyUser syUser = syUserService.queryByUsername("Y15726922157");
        SyUser syUser1 = syUserService.queryByUsername("Y18287238185");
        SyUser syUser2 = syUserService.queryByUsername("Y13738856395");
        SyUser syUser3 = syUserService.queryByUsername("Y18758819978");
        SyUser syUser4 = syUserService.queryByUsername("Y18182592323");
        SyUser syUser5 = syUserService.queryByUsername("Y18687176975");
        List<SyUser> list = Arrays.asList(syUser, syUser1, syUser2, syUser3, syUser4, syUser5);
        list.stream().forEach(syUser6 ->{
            syItemFacade.updateSystemSendBill(syUser6, "ON");
        });


    }
    @Test
    public void testAddTestUser() {
        SyUser syUser = syUserService.queryByUsername("Y17706582986");
        SyUser syUser1 = syUserService.queryByUsername("Y15050833768");
        SyUser syUser2 = syUserService.queryByUsername("Y13185353399");
        SyUser syUser3 = syUserService.queryByUsername("Y18167898750");
        SyUser syUser4 = syUserService.queryByUsername("Y13276856057");
        List<SyUser> list = Arrays.asList(syUser, syUser1, syUser2, syUser3, syUser4);
        list.stream().forEach(syUser6 ->{
            syItemFacade.updateSystemSendBill(syUser6, "ON");
        });
    }
}
