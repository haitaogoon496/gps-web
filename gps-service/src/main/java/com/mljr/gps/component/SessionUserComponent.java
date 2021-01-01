package com.mljr.gps.component;

import com.lyqc.base.common.Const;
import com.mljr.redis.service.RedisUtil;
import com.mljr.gps.entity.SyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description: 基于session user 工具类
 * @Date : 下午6:30 2018/3/2
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Component
public class SessionUserComponent {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取session user
     * @return
     */
    public SyUser getSessionUser(){
        SyUser user = new SyUser();
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            user = redisUtil.get(session.getId(),SyUser.class);
            if(user == null){
                user = new SyUser();
                user.setUserId(Const.SYSTEM_USER_ID);
                user.setUserName(Const.SYSTEM_USER_NAME);
            }else {
                user.setUserId(user.getUserId());
                user.setUserName(user.getUserName());
            }
        } catch (Exception e) {
            if (user == null) {
                user = new SyUser();
            }
            user.setUserId(Const.SYSTEM_USER_ID);
            user.setUserName(Const.SYSTEM_USER_NAME);
        }
        return user;
    }

    /**
     * 获取session user
     * 业务场景：相关查询列表，优先从Redis取，取不到再从Session中获取
     * @return
     */
    public SyUser getLoginSessionUser(){
        SyUser user = new SyUser();
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            user = redisUtil.get(session.getId(),SyUser.class);
            if(null == user){
                Object object = session.getAttribute("user");
                if(object != null) {
                    user = (SyUser) object;
                }
            }
            user.setUserId(user.getUserId());
            user.setUserName(user.getUserName());
        } catch (Exception e) {
            if (user == null) {
                user = new SyUser();
            }
            user.setUserId(Const.SYSTEM_USER_ID);
            user.setUserName(Const.SYSTEM_USER_NAME);
        }
        return user;
    }

    /**
     * 获取session 用户id
     * @return
     */
    public Integer getSessionUserId(){
        return getSessionUser().getUserId();
    }
    /**
     * 获取session 用户名
     * @return
     */
    public String getSessionUserName(){
        return getSessionUser().getUserName();
    }
    /**
     * 获取session 用户真是姓名
     * @return
     */
    public String getSessionTrueName(){
        return getSessionUser().getTrueName();
    }
    /**
     * 获取userId
     * @return
     */
    public Integer getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if (session == null) {
            return null;
        }
        SyUser user = (SyUser) session.getAttribute("user");
        if (user == null) {
            user = redisUtil.get(session.getId(), SyUser.class);
        }
        if (user == null) {
            return null;
        }
        return user.getUserId();
    }
}
