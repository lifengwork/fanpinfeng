/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.utils;

import cn.hutool.core.convert.Convert;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * @author maibaduoduo
 * @createTime 2017-12-13 16:52
 */
public class BaseContextHandler {
    private static final ThreadLocal<Map<String, String>> THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, String> map = getLocalMap();
        map.put(key, value == null ? StringUtils.EMPTY: value.toString());
    }

    public static <T> T get(String key, Class<T> type) {
        Map<String, String> map = getLocalMap();
        return Convert.convert(type, map.get(key));
    }

    public static <T> T get(String key, Class<T> type, Object def) {
        Map<String, String> map = getLocalMap();
        return Convert.convert(type, map.getOrDefault(key, String.valueOf(def == null ? "" : def)));
    }

    public static String get(String key) {
        Map<String, String> map = getLocalMap();
        return map.getOrDefault(key, "");
    }

    public static Map<String, String> getLocalMap() {
        Map<String, String> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>(10);
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static void setLocalMap(Map<String, String> threadLocalMap) {
        THREAD_LOCAL.set(threadLocalMap);
    }

    public static void remove() {
        if (THREAD_LOCAL != null) {
            THREAD_LOCAL.remove();
        }
    }

}
