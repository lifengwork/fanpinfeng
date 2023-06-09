/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dromara.myth.admin.spi;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.dromara.myth.admin.service.LogService;
import org.dromara.myth.admin.service.log.JdbcLogServiceImpl;
import org.dromara.myth.admin.service.log.RedisLogServiceImpl;
import org.dromara.myth.admin.service.log.ZookeeperLogServiceImpl;
import org.dromara.myth.common.jedis.JedisClient;
import org.dromara.myth.common.jedis.JedisClientCluster;
import org.dromara.myth.common.jedis.JedisClientSentinel;
import org.dromara.myth.common.jedis.JedisClientSingle;
import org.dromara.myth.common.serializer.ObjectSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import javax.sql.DataSource;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * CompensationConfiguration.
 *
 * @author xiaoyu
 */
@Configuration
public class CompensationConfiguration {

    /**
     * spring.profiles.active = {}.
     */
    @Configuration
    @Profile("db")
    static class JdbcConfiguration {

        private final Environment env;

        @Autowired
        JdbcConfiguration(final Environment env) {
            this.env = env;
        }

        @Bean
        public DataSource dataSource() {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName(env.getProperty("myth.db.driver"));
            dataSource.setUrl(env.getProperty("myth.db.url"));
            //用户名
            dataSource.setUsername(env.getProperty("myth.db.username"));
            //密码
            dataSource.setPassword(env.getProperty("myth.db.password"));
            dataSource.setInitialSize(2);
            dataSource.setMaxActive(20);
            dataSource.setMinIdle(0);
            dataSource.setMaxWait(60000);
            dataSource.setValidationQuery("SELECT 1");
            dataSource.setTestOnBorrow(false);
            dataSource.setTestWhileIdle(true);
            dataSource.setPoolPreparedStatements(false);
            return dataSource;
        }

        @Bean
        @Qualifier("jdbcLogService")
        public LogService jdbcLogService() {
            JdbcLogServiceImpl jdbcLogService = new JdbcLogServiceImpl();
            jdbcLogService.setDbType(env.getProperty("maibaduoduo.db.driver"));
            return jdbcLogService;
        }
    }

    @Configuration
    @Profile("redis")
    static class RedisConfiguration {

        private final Environment env;

        private final ObjectSerializer objectSerializer;

        @Autowired
        RedisConfiguration(final Environment env, final ObjectSerializer objectSerializer) {
            this.env = env;
            this.objectSerializer = objectSerializer;
        }

        @Bean
        @Qualifier("redisLogService")
        public LogService redisLogService() {
            JedisPool jedisPool;
            JedisPoolConfig config = new JedisPoolConfig();
            JedisClient jedisClient;
            final Boolean cluster = env.getProperty("myth.redis.cluster", Boolean.class);
            final Boolean sentinel = env.getProperty("myth.redis.sentinel", Boolean.class);
            final String password = env.getProperty("myth.redis.password");
            if (cluster) {
                final String clusterUrl = env.getProperty("myth.redis.clusterUrl");
                final Set<HostAndPort> hostAndPorts = Splitter.on(";")
                        .splitToList(clusterUrl).stream()
                        .map(HostAndPort::parseString).collect(Collectors.toSet());
                JedisCluster jedisCluster = new JedisCluster(hostAndPorts, config);
                jedisClient = new JedisClientCluster(jedisCluster);
            } else if (sentinel) {
                final String sentinelUrl = env.getProperty("myth.redis.sentinelUrl");
                final Set<String> hostAndPorts =
                        new HashSet<>(Splitter.on(";")
                                .splitToList(sentinelUrl));
                final String master = env.getProperty("myth.redis.master");
                JedisSentinelPool pool =
                        new JedisSentinelPool(master, hostAndPorts,
                                config, password);
                jedisClient = new JedisClientSentinel(pool);
            } else {
                final String port = env.getProperty("myth.redis.port");
                final String hostName = env.getProperty("myth.redis.hostName");
                if (StringUtils.isNoneBlank(password)) {
                    jedisPool = new JedisPool(config, hostName,
                            Integer.parseInt(port), 30, password);
                } else {
                    jedisPool = new JedisPool(config, hostName,
                            Integer.parseInt(port), 30);
                }
                jedisClient = new JedisClientSingle(jedisPool);
            }
            return new RedisLogServiceImpl(jedisClient, objectSerializer);
        }
    }

    @Configuration
    @Profile("zookeeper")
    static class ZookeeperConfiguration {

        private static final Lock LOCK = new ReentrantLock();

        private final Environment env;

        private final ObjectSerializer objectSerializer;

        @Autowired
        ZookeeperConfiguration(final Environment env, final ObjectSerializer objectSerializer) {
            this.env = env;
            this.objectSerializer = objectSerializer;
        }

        @Bean
        @Qualifier("zookeeperLogService")
        public LogService zookeeperLogService() {
            ZooKeeper zooKeeper = null;
            try {
                final String host = env.getProperty("myth.zookeeper.host");
                final String sessionTimeOut = env.getProperty("myth.zookeeper.sessionTimeOut");
                zooKeeper = new ZooKeeper(host, Integer.parseInt(sessionTimeOut), watchedEvent -> {
                    if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
                        // 放开闸门, wait在connect方法上的线程将被唤醒
                        LOCK.unlock();
                    }
                });
                LOCK.lock();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ZookeeperLogServiceImpl(zooKeeper, objectSerializer);
        }
    }

}
