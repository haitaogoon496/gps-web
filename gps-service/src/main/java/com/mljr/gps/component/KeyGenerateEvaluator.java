package com.mljr.gps.component;

import com.mljr.gps.entity.ParamKey;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @Date : 2019/1/28$ 14:40$
 * @Author : liht
 */

public class KeyGenerateEvaluator extends CachedExpressionEvaluator{

    private final Map<AnnotatedElementKey, Method> targetMethodCache = new ConcurrentHashMap<AnnotatedElementKey, Method>(64);

    private final ParameterNameDiscoverer paramNameDiscoverer = new DefaultParameterNameDiscoverer();

    private final Map<ExpressionKey, Expression> lockCache = new ConcurrentHashMap<ExpressionKey, Expression>(64);

    public EvaluationContext createEvaluationContext(ParamKey paramKey, Class<?> targetClass, Method method,
                                                     Object[] args) {

        Method targetMethod = getTargetMethod(targetClass, method);
        MethodBasedEvaluationContext evaluationContext = new MethodBasedEvaluationContext(paramKey, targetMethod, args,
                this.paramNameDiscoverer);
        return evaluationContext;
    }

    private Method getTargetMethod(Class<?> targetClass, Method method) {
        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, targetClass);
        Method targetMethod = this.targetMethodCache.get(methodKey);
        if (targetMethod == null) {
            targetMethod = AopUtils.getMostSpecificMethod(method, targetClass);
            if (targetMethod == null) {
                targetMethod = method;
            }
            this.targetMethodCache.put(methodKey, targetMethod);
        }
        return targetMethod;
    }

    public <T extends Object> T key(String keyExpression, AnnotatedElementKey methodKey, EvaluationContext evalContext,Class<T> t) {
        return getExpression(this.lockCache, methodKey, keyExpression).getValue(evalContext, t);
    }
}
