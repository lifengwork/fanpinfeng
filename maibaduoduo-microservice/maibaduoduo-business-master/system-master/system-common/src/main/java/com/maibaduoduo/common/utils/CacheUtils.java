package com.maibaduoduo.common.utils;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

/**
 * Cache工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
@Component
public class CacheUtils {
	private static Logger logger = LoggerFactory.getLogger(CacheUtils.class);
	@Autowired
	private static CacheManager ehcacheManager;
	public static final String SYS_CACHE = "saas_sys_cache_local";

	/**
	 * 获得已知cache的键值对
	 * @param cacheName    cache名称
	 * @param key    键
	 * @return
	 */
	public Object get(String cacheName, String key) {
		Cache cache = getCache(cacheName);
		Element element = cache.get(key);
		return element == null ? null : element.getObjectValue();
	}

	/**
	 * 获取SYS_CACHE缓存
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return get(SYS_CACHE, key);
	}
	
	/**
	 * 获取SYS_CACHE缓存
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public Object get(String key, Object defaultValue) {
		Object value = get(key);
		return value != null ? value : defaultValue;
	}
	
	/**
	 * 写入SYS_CACHE缓存
	 * @param key
	 * @return
	 */
	public void put(String key, Object value) {
		put(SYS_CACHE, key, value);
	}
	
	/**
	 * 从SYS_CACHE缓存中移除
	 * @param key
	 * @return
	 */
	public void remove(String key) {
		remove(SYS_CACHE, key);
	}

	/**
	 * 获取缓存
	 * @param cacheName
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public Object get(String cacheName, String key, Object defaultValue) {
		Object value = get(cacheName, getKey(key));
		return value != null ? value : defaultValue;
	}
	
	/**
	 * 写入缓存，默认永久有效
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public void put(String cacheName, String key, Object value) {
		getCache(cacheName).put(new Element(getKey(key),value,true), true);
	}

	/**
	 * 在已知cache中添加键值对（element级别上设置过期时间）
	 * @param cacheName  缓存名称
	 * @param key
	 * @param value
	 * @param eternal   是否永久有效
	 * @param time   时间
	 */
	public void put(String cacheName, String key, Object value,boolean eternal,int time) {
		Cache cache = getCache(cacheName);
		Element element = new Element(key, value);
		element.setEternal(eternal);
		element.setTimeToLive(time);
		cache.put(element);
	}

	/**
	 * 从缓存中移除
	 * @param cacheName
	 * @param key
	 */
	public void remove(String cacheName, String key) {
		getCache(cacheName).remove(getKey(key));
	}

	/**
	 * 从缓存中移除所有
	 * @param cacheName
	 */
	public void removeAll(String cacheName) {
		Cache cache = getCache(cacheName);
		List<String> keys = cache.getKeys();
		for (Iterator<String> it = keys.iterator(); it.hasNext();){
			cache.remove(it.next());
		}
		logger.info("清理缓存： {} => {}", cacheName, keys);
	}

	/**
	 * 获取缓存键名，多数据源下增加数据源名称前缀
	 * @param key
	 * @return
	 */
	private String getKey(String key){
//		String dsName = DataSourceHolder.getDataSourceName();
//		if (StringUtils.isNotBlank(dsName)){
//			return dsName + "_" + key;
//		}
		return key;
	}
	
	/**
	 * 获得一个Cache，没有则显示日志。
	 * @param cacheName
	 * @return
	 */
	private Cache getCache(String cacheName){
		Cache cache = ehcacheManager.getCache(cacheName);
		if (cache == null){
			throw new RuntimeException("当前系统中没有定义“"+cacheName+"”这个缓存。");
		}
		return cache;
	}

	/**
	 * 返回cache的size
	 * @param cache
	 * @return
	 */
	public int getcacheSize(Cache cache){
		List<String> keys = cache.getKeys();
		for(String key:keys){
			cache.get(key);
		}
		return cache.getSize();
	}
}
