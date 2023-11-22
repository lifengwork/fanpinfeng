/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.inventory.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;
import com.maibaduoduo.inventory.dao.InventoryDao;
import com.maibaduoduo.inventory.entity.InventoryEntity;
import com.maibaduoduo.inventory.service.InventoryService;

@Service("inventoryService")
public class InventoryServiceImpl extends ServiceImpl<InventoryDao, InventoryEntity> implements InventoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InventoryEntity> page = this.page(
                new Query<InventoryEntity>().getPage(params),
                new QueryWrapper<InventoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public InventoryEntity judgeWarehouse(Map<String, Object> judgeInfo) {
        /**
         * TODO
         * 根据仓库地址和采购商地址选择最优仓库
         */
        return null;
    }


}