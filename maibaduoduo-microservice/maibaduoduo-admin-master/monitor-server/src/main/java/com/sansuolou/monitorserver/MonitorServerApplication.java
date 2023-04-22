package com.sansuolou.monitorserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @Desc: 收集Hystrix.stream->Dashboard
 * @Author: fanpingfeng
 * @Date 2023-4-21
 */
@EnableTurbine
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrixDashboard
@EnableHystrix
public class MonitorServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MonitorServerApplication.class).banner(new MonitoServerBanner()).run(args);
    }

}

