/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.configuration.props;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class ConsoleLog {
    public static void out(Environment env){
        try {
            log.info("\n----------------------------------------------------------\n\t" +
                            "应用 '{}' 运行成功! 访问连接:\n\t" +
                            "Swagger文档: \t\thttp://{}:{}/doc.html\n\t" +
                            "数据库监控: \t\thttp://{}:{}/druid\n" +
                            "----------------------------------------------------------",
                    env.getProperty("spring.application.name"),
                    InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"),
                    InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));
        } catch (UnknownHostException e) {
           log.error("输出信息错误，请检查{},cause,{}",ConsoleLog.class.getSimpleName(), e);
        }
    }
}
