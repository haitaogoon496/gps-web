package com.mljr.gps.aop;

import com.lyqc.base.common.Result;
import com.mljr.gps.component.SessionUserComponent;
import com.mljr.util.TimeTools;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * @description: facade 切面类
 * @Date : 2018/3/7 16:03
 * @Author : lihaitao
 */
@Component
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FacadeInvokeAdvice {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SessionUserComponent userComponent;

    @Pointcut("execution(public * com.mljr.gps.facade.*.*(..))")
    public void invokeFacade() {
    }

    @Around("invokeFacade()")
    public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> targetClass = this.parseTargetClass(joinPoint.getTarget());
        String className = targetClass.getName();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String target = className + "." + method.getName();
        long start = System.currentTimeMillis();
        try {
            Object ret = joinPoint.proceed();
            if (ret instanceof Result) {
                Result resp = (Result) ret;
                if (!resp.isSuccess()) {
                    log.info("{} {}-{}", target, resp.getCode(), resp.getMsg());
                }
            } else {
                log.warn("api:{} 接口返回类型({})不符合规范", target, ret == null ? null : ret.getClass());
            }
            return ret;
        } finally {
        	log.info("sessionUserId:{},target:{},duration:{}",userComponent.getUserId(), target, TimeTools.spend(System.currentTimeMillis() - start));
        }
    }

    protected Class<?> parseTargetClass(Object target) {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(target);
        if (targetClass == null) {
            targetClass = target.getClass();
        }
        return targetClass;
    }

}
