package com.maibaduoduo.database.datasource.dynamic.aspect;

import com.maibaduoduo.database.datasource.dynamic.annotation.DS;
import com.maibaduoduo.database.datasource.utils.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 档期那线程数据源动态切换
 * 扫描添加<P>@DS</P>注解的方法进行数据源切换
 */
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DataSourceAspect {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(com.maibaduoduo.database.datasource.dynamic.annotation.DS) " +
            "|| @within(com.maibaduoduo.database.datasource.dynamic.annotation.DS)")
    public void dataSourcePointCut() {}

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class targetClass = point.getTarget().getClass();
        Method method = signature.getMethod();

        DS targetDataSource = (DS) targetClass.getAnnotation(DS.class);
        DS methodDataSource = method.getAnnotation(DS.class);
        if (targetDataSource != null || methodDataSource != null) {
            String value;
            if (methodDataSource != null) {
                value = methodDataSource.value();
            } else {
                value = targetDataSource.value();
            }
            DynamicDataSourceContextHolder.setDataSourceKey(value);
            logger.debug("set datasource is {}", value);
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceKey();
            logger.debug("clean datasource");
        }
    }
}