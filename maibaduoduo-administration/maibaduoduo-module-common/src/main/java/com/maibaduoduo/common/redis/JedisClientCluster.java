package com.maibaduoduo.common.redis;

import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * redis集群客户端
 */
public class JedisClientCluster implements JedisClient {

    @Resource
    private JedisCluster jedisCluster;

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    /**
     * 以秒为单位返回key的剩余过期时间。
     * 1，当key不存在时返回-2，
     * 2，当key存在没有设置过期时间时，返回-1
     * 具体使用：ttl key_name
     * @param key
     * @return
     */
    @Override
    public long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    @Override
    public long expire(String key, int second) {
        return jedisCluster.expire(key, second);
    }

    @Override
    public long incr(String key) {
        return jedisCluster.incr(key);
    }

    @Override
    public long hset(String hkey, String key, String value) {
        return jedisCluster.hset(hkey, key, value);
    }

    @Override
    public String hget(String hkey, String key) {
        return jedisCluster.hget(hkey, key);
    }

    @Override
    public long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public long hdel(String hkey, String key) {
        return jedisCluster.del(hkey, key);
    }
}