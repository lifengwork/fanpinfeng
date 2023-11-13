/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.purchase.service.impl;

import com.maibaduoduo.purchase.dao.PurchaseItemDao;
import com.maibaduoduo.purchase.entity.PurchaseItemEntity;
import com.maibaduoduo.purchase.service.PurchaseItemService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;


@Service("purchaseItemService")
public class PurchaseItemServiceImpl extends ServiceImpl<PurchaseItemDao, PurchaseItemEntity> implements PurchaseItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseItemEntity> page = this.page(
                new Query<PurchaseItemEntity>().getPage(params),
                new QueryWrapper<PurchaseItemEntity>()
        );

        return new PageUtils(page);
    }

}