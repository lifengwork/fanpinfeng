package com.maibaduoduo.common.datasources;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据源动态切换
 * @Auth lf
 * @Date 2021-09-02
 */
@Order(1)
@Aspect
@Component
public class DynamicDataSourceAspect {
    /**
     * 使用DS注解动作之后清除
     * @param point
     */
    @After("@annotation(DS)")
    public void afterSwitchDS(JoinPoint point){
        System.out.println("清除当前数据源"+DataSourceContextHolder.getDB());
        DataSourceContextHolder.clearDB();
    }

    /**
     * 使用DS注解动态切换
     * @param point
     */
    @Before("@annotation(DS)")
    public void beforeSwitchDS(JoinPoint point){
        Class<?> className = point.getTarget().getClass();
        String methodName = point.getSignature().getName();
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        String dataSource = DataSourceContextHolder.DEFAULT_DS;
        try {
            Method method = className.getMethod(methodName, argClass);
            if (method.isAnnotationPresent(DS.class)) {
                DS annotation = method.getAnnotation(DS.class);
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataSourceContextHolder.setDB(dataSource);
    }

}