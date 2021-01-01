package com.mljr.gps.controller;

import com.mljr.gps.common.exception.AlertException;
import com.mljr.gps.entity.SyUser;
import com.mljr.redis.service.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * @Date : 2018/8/17$ 11:36$
 * @Author : liht
 */
@Slf4j
public abstract class AbstractController {

    @Autowired
    private RedisUtil redisUtil;

    public SyUser getUser(HttpServletRequest request) {
        log.info("gps-web 获取当前登录用户");
        HttpSession session = request.getSession();
        SyUser user = (SyUser) session.getAttribute("user");
        if (user == null) {
            user =  redisUtil.get(session.getId(), SyUser.class);
            if (user == null) {
                throw new AlertException("请登录");
            }
            return user;
        }
        return user;
    }

    public Integer getUserId(HttpServletRequest request) {
        SyUser syUser = getUser(request);
        return syUser.getUserId();
    }
}
