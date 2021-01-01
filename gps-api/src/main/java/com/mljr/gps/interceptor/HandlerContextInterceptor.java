package com.mljr.gps.interceptor;

import com.mljr.redis.service.RedisUtil;
import com.mljr.gps.common.consts.CodeConst;
import com.mljr.gps.common.exception.AlertException;
import com.mljr.gps.entity.SyUser;
import com.mljr.gps.service.SyUserService;
import com.mljr.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

/**
 * @Description
 * @Date：Created in 下午10:40 2018/1/28
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HandlerContextInterceptor implements HandlerInterceptor{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SyUserService syUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        Map<String, String> params = HttpUtils.getRequestParams(request);
        Integer sessionUserId = Integer.valueOf(params.getOrDefault("sessionUserId","-1"));
        if(-1 == sessionUserId){
            sessionUserId = Integer.valueOf(Optional.ofNullable(request.getHeader("sessionUserId")).orElse("1"));
        }
        boolean sessionIntercept = Boolean.valueOf(Optional.ofNullable(params.get("sessionIntercept")).orElse("true"));
        if(sessionIntercept){
            sessionIntercept = Boolean.valueOf(Optional.ofNullable(request.getHeader("sessionIntercept")).orElse("true"));
        }
        String ip = HttpUtils.getRemoteIP(request);
        request.setAttribute("ip", ip);
        logger.info("ip:[{}] method:{} - uri:{} - 执行开始---------", ip,request.getMethod(), request.getRequestURI());
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        String sessionId = session.getId();
        user = Optional.ofNullable(user).orElse(redisUtil.get(sessionId,SyUser.class));
        if (user == null) {
            logger.info("Interceptor-sessionId:{}", sessionId);
            if(sessionIntercept){
                logger.info("当前用户没有登录已拦截");
                throw new AlertException(CodeConst.E_401, "请登录");
            }else{
                session.setAttribute("user",queryUserAndResetRedis(sessionUserId,sessionId));
            }
        }
        return true;
    }

    /**
     * 根据request Header中sessionUserId 或者 parameter中 参数，重新查询数据库，然后刷新到redis中
     * @param sessionUserId
     * @param sessionId
     * @return
     */
    private SyUser queryUserAndResetRedis(Integer sessionUserId,String sessionId){
        SyUser queryUser = syUserService.queryByUserId(sessionUserId);
        if(queryUser != null){
            redisUtil.set(sessionId,queryUser);
        }
        return queryUser;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
