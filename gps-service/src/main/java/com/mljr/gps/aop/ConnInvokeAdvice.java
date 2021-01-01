package com.mljr.gps.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @Date : 2019/1/14$ 10:50$
 * @Author : liht
 */
@Component
public class ConnInvokeAdvice {

    private static Logger logger = LoggerFactory.getLogger(ConnInvokeAdvice.class);
    private static Map<Connection, Long> connTimeMap = new ConcurrentHashMap<>();


    @Pointcut("execution(* com.alibaba.druid.pool.DruidDataSource.getConnection*(..))")
    public void invokeFacade() {
    }

    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Class<?> targetClass = this.parseTargetClass(proceedingJoinPoint.getTarget());
        String className = targetClass.getName();
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        String target = className + "." + method.getName();
        logger.info("conn拦截-调用方法:{}", target);
        try {
            Object o = proceedingJoinPoint.proceed();
            if (o != null && o instanceof Connection) {
                connTimeMap.put((Connection) o, System.currentTimeMillis());
            }
            monitorConn();
            return o;
        } catch (Throwable throwable) {
            logger.error("ConnInvokeAdvice拦截器执行异常:{}", throwable.getMessage(), throwable);
            throw throwable;
        } finally {
            logger.info("conn拦截执行完毕");
        }
    }

    synchronized public static void monitorConn() throws Exception{
        if(connTimeMap!=null && connTimeMap.size()!=0){
            for(Iterator<Connection> iter = connTimeMap.keySet().iterator(); iter.hasNext();){
                Connection key = iter.next();
                try {
                    Long connTime = connTimeMap.get(key);
                    long time = System.currentTimeMillis() - connTime;
                    if(key.isClosed()){
                        connTimeMap.remove(key);
                    } else if (connTime != null){
                        logger.info("conn---->{},metaData:{}", key.createStatement(),key.getMetaData().getSchemas());
                        if (time > 160000) {
                            logger.info("长连接提醒：连接" + key.toString() + " 已运行" + time + "毫秒.");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
