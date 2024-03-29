/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.contract.service.impl;

import com.alibaba.fastjson.JSON;
import com.maibaduoduo.constants.RabbitConstants;
import com.maibaduoduo.contract.dao.PurchaseContractDao;
import com.maibaduoduo.contract.entity.PurchaseContractEntity;
import com.maibaduoduo.contract.service.PurchaseContractService;
import com.maibaduoduo.mq.sender.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

@Service("purchaseContractService")
public class PurchaseContractServiceImpl extends ServiceImpl<PurchaseContractDao, PurchaseContractEntity> implements PurchaseContractService {
    @Autowired
    private RabbitSender rabbitSender;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseContractEntity> page = this.page(
                new Query<PurchaseContractEntity>().getPage(params),
                new QueryWrapper<PurchaseContractEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void createContract(PurchaseContractEntity purchaseContractEntity) {

    }

    /**
     * 合同签章生效
     * @param purchaseContractEntity
     */
    @Override
    public void validateContract(PurchaseContractEntity purchaseContractEntity) {
        /**
         * TODO
         */
        String CONTRACT_EXCHANGE = "";
        String CONTRACT_QUEUE = "";
        rabbitSender.sendMessage(CONTRACT_EXCHANGE,CONTRACT_QUEUE, JSON.toJSON(purchaseContractEntity));
    }

}