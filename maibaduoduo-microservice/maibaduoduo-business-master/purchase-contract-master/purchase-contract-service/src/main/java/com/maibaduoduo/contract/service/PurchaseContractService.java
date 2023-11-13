/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.contract.entity.PurchaseContractEntity;

import java.util.Map;

/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-13 21:59:29
 */
public interface PurchaseContractService extends IService<PurchaseContractEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 创建采购协议
     * @param purchaseContractEntity
     */
    void createContract(PurchaseContractEntity purchaseContractEntity);

    /**
     * 协议生效
     * 采购者 签章后协议生效
     * @param purchaseContractEntity
     */
    void validateContract(PurchaseContractEntity purchaseContractEntity);
}

