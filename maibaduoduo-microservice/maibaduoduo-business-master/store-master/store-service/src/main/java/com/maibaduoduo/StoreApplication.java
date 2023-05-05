/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
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

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.maibaduoduo"})
@MapperScan(
		basePackages = {"com.maibaduoduo.*.dao"}, annotationClass = Mapper.class)
@EnableFeignClients
@ServletComponentScan
@RefreshScope
@EnableHystrix
@EnableHystrixDashboard
public class StoreApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(StoreApplication.class).banner(new SystemServerBanner()).run(args);
	}

}