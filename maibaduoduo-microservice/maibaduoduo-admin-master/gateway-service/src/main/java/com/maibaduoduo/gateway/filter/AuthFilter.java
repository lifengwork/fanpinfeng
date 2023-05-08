package com.maibaduoduo.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.maibaduoduo.gateway.config.IgnoreUrlsConfig;
import com.maibaduoduo.gateway.exception.SaasException;
import com.maibaduoduo.jwt.TokenUtil;
import com.maibaduoduo.jwt.common.BaseContextHandler;
import com.maibaduoduo.jwt.model.AuthorizationInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.Objects;

/**
 * Token过滤器拦截请求获取header中的JWT数据转换成用户数据。
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    private static Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);
    private static final String  AUTHORIZATIONINFO = "token";
    public static final String USER="user_id";//动态解析用户信息会用到
    public static final String MOBILE="mobile";//动态解析用户信息会用到
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request =exchange.getRequest();
        if(true/*IgnoreUrlsConfig.urls.contains(request.getURI().getPath())*/){
            return chain.filter(exchange);
        }
        //获取用户Token
        String token = exchange.getRequest().getHeaders().getFirst(AUTHORIZATIONINFO);
        if (StrUtil.isEmpty(token)) {
            token = exchange.getRequest().getQueryParams().getFirst(AUTHORIZATIONINFO);
        }
        if(StringUtils.isBlank(token)){
            throw new SaasException(AUTHORIZATIONINFO + "不能为空", HttpStatus.UNAUTHORIZED.value());
        }
        //TOKEN认证信息
        try{
            AuthorizationInfo authorizationInfo = tokenUtil.parseJWT(token);
            if(Objects.nonNull(authorizationInfo)){
                BaseContextHandler.set(AUTHORIZATIONINFO,token);
            }
            LOGGER.info("AuthFilter:{}，OK",authorizationInfo);
            exchange = exchange.mutate().request(exchange.getRequest()
                    .mutate().header(AUTHORIZATIONINFO,token).build()).build();
            exchange = exchange.mutate().request(exchange.getRequest()
                    .mutate().header(USER,String.valueOf(authorizationInfo.getUserId())).build()).build();
            exchange = exchange.mutate().request(exchange.getRequest()
                    .mutate().header(MOBILE,authorizationInfo.getMobile()).build()).build();
        }catch (Exception e){
            throw new SaasException(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
