/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring Context 工具类
 *
 * @author Mark lifengwork@yeah.net
 */
@Component
public class SpringContextLockUtil implements ApplicationContextAware {
	public static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextLockUtil.applicationContext = applicationContext;
	}
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
	public static <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}
	public static <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}
	public static boolean isSingleton(String name) {
		return applicationContext.isSingleton(name);
	}
	public static Class<? extends Object> getType(String name) {
		return applicationContext.getType(name);
	}
}