package com.maibaduoduo.gateway.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * @Desc: 统计某个或者某种路由的处理时长//TODO
 * @Author: fanpingfeng
 * @Date 2023-4-21
 */
@Component
@Order(0)
public class AutoGatewayFilter implements GatewayFilter {

    private static final Logger log = LoggerFactory.getLogger( AutoGatewayFilter.class );
    private static final String START_TIME = "STARTTIME";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(START_TIME, Instant.now().toEpochMilli() );
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    long startTime = exchange.getAttribute(START_TIME);
                    long endTime=(Instant.now().toEpochMilli() - startTime);
                    log.info(exchange.getRequest().getURI().getRawPath() + ": " + endTime + "ms");
                })
        );
    }
}

