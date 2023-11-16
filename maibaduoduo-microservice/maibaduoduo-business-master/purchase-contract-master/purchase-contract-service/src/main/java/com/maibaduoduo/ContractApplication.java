/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.maibaduoduo"})
@MapperScan(
		basePackages = {"com.maibaduoduo.*.dao","com.maibaduoduo.program.*.dao"}, annotationClass = Mapper.class)
@ServletComponentScan
@RefreshScope
@EnableFeignClients(basePackages = {"com.maibaduoduo"})
@EnableHystrix
@EnableHystrixDashboard
@EnableAsync
public class ContractApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ContractApplication.class).banner(new SystemServerBanner()).run(args);
	}

}