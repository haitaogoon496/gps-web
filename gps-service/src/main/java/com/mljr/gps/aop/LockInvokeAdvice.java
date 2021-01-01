package com.mljr.gps.aop;

import com.mljr.gps.common.enums.LimitLock;
import com.mljr.gps.component.KeyGenerateEvaluator;
import com.mljr.gps.entity.ParamKey;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description:
 * @Date : 2019/1/28$ 10:14$
 * @Author : liht
 */
@Aspect
@Component
@Slf4j
public class LockInvokeAdvice {

    private KeyGenerateEvaluator keyGenerateEvaluator = new KeyGenerateEvaluator();

    @Pointcut("@annotation(com.mljr.gps.common.enums.LimitLock)")
    public void invoke() {

    }

    @Around("invoke()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {

        String className = joinPoint.getTarget().getClass().getName();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        LimitLock limitLock = method.getAnnotation(LimitLock.class);
        if (limitLock != null) {
            ParamKey paramKey = new ParamKey(limitLock.key(),limitLock.condition());

            EvaluationContext evaluationContext = keyGenerateEvaluator.createEvaluationContext(paramKey, joinPoint.getTarget().getClass(), method, joinPoint.getArgs());
            AnnotatedElementKey annotatedElementKey = new AnnotatedElementKey(method,joinPoint.getTarget().getClass());
            boolean bool =  keyGenerateEvaluator.key(paramKey.getCondition(), annotatedElementKey, evaluationContext,Boolean.class);
            String value = null;
            if (bool) {
                value = keyGenerateEvaluator.key(paramKey.getKey(), annotatedElementKey, evaluationContext,String.class);
            }
            log.info("spel ---->:{}----bool:{}", value, bool);
        }
        return joinPoint.proceed();
    }
}
