package com.maibaduoduo.utils;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * SpringBeanUtils.
 *
 * @author xiaoyu
 */
public final class CommonBeanUtils {

    private static final CommonBeanUtils INSTANCE = new CommonBeanUtils();

    private ConfigurableApplicationContext cfgContext;

    private CommonBeanUtils() {
        if (INSTANCE != null) {
            throw new Error("error");
        }
    }

    /**
     * get SpringBeanUtils.
     *
     * @return SpringBeanUtils
     */
    public static CommonBeanUtils getInstance() {
        return INSTANCE;
    }

    /**
     * acquire spring bean.
     *
     * @param type type
     * @param <T>  class
     * @return bean
     */
    public <T> T getBean(final Class<T> type) {
        AssertUtils.notNull(type);
        return cfgContext.getBean(type);
    }

    /**
     * register bean in spring ioc.
     *
     * @param beanName bean name
     * @param obj      bean
     */
    public void registerBean(final String beanName, final Object obj) {
        AssertUtils.notNull(beanName);
        AssertUtils.notNull(obj);
        cfgContext.getBeanFactory().registerSingleton(beanName, obj);
    }

    /**
     * set application context.
     *
     * @param cfgContext application context
     */
    public void setCfgContext(final ConfigurableApplicationContext cfgContext) {
        this.cfgContext = cfgContext;
    }
}
