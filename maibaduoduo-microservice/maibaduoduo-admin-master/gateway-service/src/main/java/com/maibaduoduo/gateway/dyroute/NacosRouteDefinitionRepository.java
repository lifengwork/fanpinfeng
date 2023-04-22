package com.maibaduoduo.gateway.dyroute;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.concurrent.Executor;

/**
 * @Desc: 动态路由//TODO
 * @Author: fanpingfeng
 * @Date 2023-4-21
 */
@Slf4j
public class NacosRouteDefinitionRepository implements RouteDefinitionRepository {
    private static final String GATEWAY_DATA_ID= "dev_01";
    private String GATEWAY_GROUP_ID="DEFAULT_GROUP";
    private ApplicationEventPublisher applicationEventPublisher;
    private NacosConfigManager ncosConfigManager;
    public NacosRouteDefinitionRepository(){}
    public NacosRouteDefinitionRepository(ApplicationEventPublisher applicationEventPublisher,NacosConfigManager ncosConfigManager,String gateway_group_id){
        this.applicationEventPublisher = applicationEventPublisher;
        this.ncosConfigManager=ncosConfigManager;
        this.GATEWAY_GROUP_ID=gateway_group_id;
        this.nacosRoteListener();
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        try {
            String routes = ncosConfigManager.getConfigService().getConfig(GATEWAY_DATA_ID,GATEWAY_GROUP_ID,5000);
            if(StrUtil.isNotBlank(routes)){
                return  Flux.fromIterable(JSONUtil.toList(JSONUtil.parseArray(routes),RouteDefinition.class));
            }
        } catch (NacosException e) {
           log.error("从Nacos获取路由失败，{}", ExceptionUtil.stacktraceToString(e));
        }
        return Flux.fromIterable(Collections.EMPTY_LIST);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }

    /**
     * 配置变化监听器
     */
    private void nacosRoteListener(){
        try {
            ncosConfigManager.getConfigService().addListener(GATEWAY_DATA_ID,GATEWAY_GROUP_ID,new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }
                @Override
                public void receiveConfigInfo(String s) {
                    applicationEventPublisher.publishEvent(new RefreshRoutesEvent(s));
                }
            });
        } catch (NacosException e) {
            log.error("Nacos监听配置错误，{}", ExceptionUtil.stacktraceToString(e));
        }
    }
}
