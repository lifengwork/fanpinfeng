/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.payment.entity.PurchasePaymentEntity;

import java.util.Map;

/**
 * 
 *
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-20 20:56:57
 */
public interface PurchasePaymentService extends IService<PurchasePaymentEntity> {

    PageUtils queryPage(Map<String, Object> params);
    /**
     * 付款
     */
    public void pay(PurchasePaymentEntity purchasePaymentEntity);
    /**
     * 银行回调
     */
    public void callback(String callBackInfo);

    /**
     * 偿还所融资金本息回调
     * @param debtInfo
     */
    public void debtCallBack(String debtInfo);
}

