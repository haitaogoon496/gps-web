package com.mljr.gps.component;

import com.lyqc.base.common.Const;
import com.mljr.gps.base.entity.BaseEntity;
import com.mljr.redis.service.RedisUtil;
import com.mljr.gps.entity.SyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description: entity设置工具类
 * @Date : 下午4:15 2018/3/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Component
public class EntityComponent {
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 设置entity 创建人、创建时间相关信息
     * @param entity
     */
    public void setCreateInfo(BaseEntity entity){
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            SyUser user = redisUtil.get(session.getId(),SyUser.class);
            entity.setUserId(user.getUserId());
            entity.setUserName(user.getUserName());
        } catch (Exception e) {
            entity.setUserId(Const.SYSTEM_USER_ID);
            entity.setUserName(Const.SYSTEM_USER_NAME);
        }
    }
}
