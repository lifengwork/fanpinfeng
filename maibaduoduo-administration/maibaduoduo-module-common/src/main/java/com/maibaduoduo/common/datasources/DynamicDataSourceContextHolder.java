package com.maibaduoduo.common.datasources;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

/**
 * 动态数据源上下文
 *
 * @author lf
 * @date 2019/05/20
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<Deque<String>> contextHolder = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return new ArrayDeque();
        }
    };

    /**
     * 切换数据源
     *
     * @param key
     */
    public static void setDataSourceKey(String key) {
        contextHolder.get().push(key);
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
