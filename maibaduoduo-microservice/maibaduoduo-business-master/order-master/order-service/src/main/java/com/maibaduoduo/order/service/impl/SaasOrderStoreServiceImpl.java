/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.order.service.SaasOrderStoreService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;
import com.maibaduoduo.configuration.utils.R;
import com.maibaduoduo.lock.Callback;
import com.maibaduoduo.lock.redis.RedisDistributedLockTemplate;
import com.maibaduoduo.order.dao.SaasOrderStoreDao;
import com.maibaduoduo.order.entity.SaasOrderStoreEntity;
import com.maibaduoduo.store.entity.SaasStoreEntity;
import com.maibaduoduo.store.facade.api.StoreFacade;
/*import org.dromara.myth.annotation.Myth;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("saasOrderStoreService")
public class SaasOrderStoreServiceImpl extends ServiceImpl<SaasOrderStoreDao, SaasOrderStoreEntity> implements SaasOrderStoreService {

    @Autowired
    private StoreFacade storeFacade;
    @Autowired
    private RedisDistributedLockTemplate redisDistributedLockTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SaasOrderStoreEntity> page = this.page(
                new Query<SaasOrderStoreEntity>().getPage(params),
                new QueryWrapper<SaasOrderStoreEntity>()
        );

        return new PageUtils(page);
    }

    //@Myth
    @Override
    public SaasStoreEntity querySassStoreEntity(Long orderId) {
        //this.list();
        //TEST STORE Lock 获取锁超时时间5000
        SaasStoreEntity saasStoreEntity = new SaasStoreEntity();
        saasStoreEntity.setId(orderId);
        R r = storeFacade.info(orderId);
        saasStoreEntity.setStoreDescription(r.toString());
        return saasStoreEntity;
       /* redisDistributedLockTemplate.execute("测试库存分布式锁", 5000, new Callback() {
            @Override
            public Object onGetLock() throws InterruptedException {
                SaasStoreEntity saasStoreEntity = new SaasStoreEntity();
                saasStoreEntity.setId(orderId);
                R r = storeFacade.info(orderId);
                saasStoreEntity.setStoreDescription(r.toString());
                return saasStoreEntity;
            }
            @Override
            public Object onTimeout() throws InterruptedException {
                return "获取锁超时请检查";
            }
        });*/
        //return null;
    }

}