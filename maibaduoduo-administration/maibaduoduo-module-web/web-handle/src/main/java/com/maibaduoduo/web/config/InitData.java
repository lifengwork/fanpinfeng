package com.maibaduoduo.web.config;

import com.maibaduoduo.base.tenant.entity.SysTenantDbUrl;
import com.maibaduoduo.base.tenant.service.SysTenantDbUrlService;
import com.maibaduoduo.common.redis.KeyPrefix;
import com.maibaduoduo.common.redis.RedisService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

public class InitData{
    private Logger log= Logger.getLogger(InitData.class);
    @Autowired
    private RedisService redisService;
    @Autowired
    private SysTenantDbUrlService sysTenantDbUrlService;

    @PostConstruct
    public void initData(){
        SysTenantDbUrl sysTenantDbUrl = new SysTenantDbUrl();
        sysTenantDbUrl.setDelFlag("0");
        List<SysTenantDbUrl> sysTenantDbUrlList = sysTenantDbUrlService.findList(sysTenantDbUrl);
        for(SysTenantDbUrl dbUrl:sysTenantDbUrlList){
            redisService.set(new KeyPrefix() {
                @Override
                public int expireSeconds() {
                    return 999999999;
                }
                @Override
                public String getPrefix() {
                    return "TENANT_";
                }},"DB_URL_"+dbUrl.getEmployee(),dbUrl);
            log.info("租户数据源初始化成功。。。。。");
        }
    }
}
