package com.maibaduoduo.gateway;

import com.maibaduoduo.gateway.api.GateWayResult;
import com.maibaduoduo.gateway.api.ResultStatusCode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc: 网关//TODO
 * @Author: fanpingfeng
 * @Date 2023-4-21
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.maibaduoduo"})
@RefreshScope
@RestController
@RibbonClients
public class GatewayApplication {
    @RequestMapping("/fallback")
    public GateWayResult fallback(){
        return GateWayResult.failed(ResultStatusCode.FAILED);
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
