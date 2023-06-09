/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * Spring Context 工具类
 *
 * @author Mark lifengwork@yeah.net
 */
@Component
public class SaasSpringContextUtil implements BeanFactoryPostProcessor, ApplicationContextAware {

	/** 日志记录器 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SaasSpringContextUtil.class);

	/** ApplicationContext */
	private static ApplicationContext context;

	/** ServletContext */
	private static ServletContext servletContext;

	/** Environment */
	private static Environment environment;

	/** MessageSourceAccessor */
	private static MessageSourceAccessor messages;

	/**
	 * Constructor<br>
	 * 私有化工具类的构造函数
	 */
	private SaasSpringContextUtil() {
	}

	/**
	 * get ApplicationContext<br>
	 *
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return context;
	}

	/**
	 * get {@link ServletContext}<br>
	 *
	 * @return {@link ServletContext}
	 */
	public static ServletContext getServletContext() {

		return servletContext;
	}

	/**
	 * get Environment<br/>
	 *
	 * @return
	 */
	public static Environment getEnvironment() {

		return environment;
	}

	/**
	 * 根据名字获得spring context中的bean<br>
	 *
	 * @param name bean的名称
	 * @return bean
	 */
	public static Object getBean(String name) {

		return context.getBean(name);
	}

	/**
	 * 根据类型获得spring context中的bean<br>
	 *
	 * @param requiredType bean的类型
	 * @return bean
	 */
	public static <T> T getBean(Class<T> requiredType) {

		return context.getBean(requiredType);
	}

	/**
	 * 根据名称和类型获得spring context中的bean<br>
	 *
	 * @param name         bean 的名称
	 * @param requiredType bean的类型
	 * @return bean
	 */
	public static <T> T getBean(String name, Class<T> requiredType) {

		return context.getBean(name, requiredType);
	}

	/**
	 * 获取properties的值，没有获取到返回null<br>
	 *
	 * @return 该key对应的value值
	 */
	public static String getProperty(String key) {

		return environment.getProperty(key);
	}

	/**
	 * 获取properties的值，没有获取到抛出异常<br>
	 *
	 * @throws IllegalStateException if the key cannot be resolved
	 * @return 该key对应的value值
	 */
	public static String getRequiredProperty(String key) {

		return environment.getRequiredProperty(key);
	}

	/**
	 * set Servlet Context<br>
	 *
	 * @param sc ServletContext
	 */
	public static void setServletContext(ServletContext sc) {

		servletContext = sc;
	}

	/**
	 * 根据名称和类型获得spring context中的bean<br>
	 * @param interfaceType
	 * @return
	 */
	public static <T> Map<String, T> getBeansOfType(@Nullable Class<T> interfaceType){
		return context.getBeansOfType(interfaceType);
	}

	/**
	 * 获取国际化访问工具<br>
	 *
	 * @return 国际化访问工具
	 */
	public static MessageSourceAccessor getMessageSourceAccessor() {
		return messages;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		init(applicationContext);
	}

	/**
	 * 对相关的属性进行赋值<br/>
	 *
	 * @param applicationContext ApplicationContext
	 */
	private static void init(ApplicationContext applicationContext) {
		context = applicationContext;
		if(Objects.isNull(context)){
			LOGGER.error("获取SpringContext失败，属性属性注入失败。");
		}
		environment = context.getEnvironment();
		if (context instanceof WebApplicationContext) {
			servletContext = ((WebApplicationContext) context).getServletContext();
		}
		messages = new MessageSourceAccessor(context, Locale.SIMPLIFIED_CHINESE);
	}
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		LOGGER.info("Spring context holder initialized successful");
	}
}