package com.maibaduoduo.database.datasource.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

/**
 * 动态数据源上下文
 */
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<Deque<String>> contextHolder = new InheritableThreadLocal<Deque<String>>() {
        @Override
        protected Deque<String> initialValue() {
            return new ArrayDeque();
        }
    };

    /**
     * 切换数据源
     *
     * @param key
     */
    public static void setDataSourceKey(String key) {
        if(StringUtils.isNotEmpty(key)){
            contextHolder.get().push(key);
        }
    }

    /**
     * 获取数据源
     *
     * @return
     */
    public static String getDataSourceKey() {
        return contextHolder.get().peek();
    }

    /**
     * 重置数据源
     */
    public static void clearDataSourceKey() {
        Deque<String> deque = contextHolder.get();
        deque.poll();
        if (deque.isEmpty()) {
            contextHolder.remove();
        }
    }

    /**
     * 判断是否包含数据源
     *
     * @param key 数据源key
     * @return
     */
    public static boolean containDataSourceKey(String key) {
        return contextHolder.get().contains(key);
    }

    /**
     * 添加数据源keys
     *
     * @param keys
     * @return
     */
    public static boolean addDataSourceKeys(Collection keys) {
        return contextHolder.get().addAll(keys);
    }

}
