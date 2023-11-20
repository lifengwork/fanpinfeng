/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.payment.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;

import com.maibaduoduo.payment.dao.PurchasePaymentDao;
import com.maibaduoduo.payment.entity.PurchasePaymentEntity;
import com.maibaduoduo.payment.service.PurchasePaymentService;


@Service("purchasePaymentService")
public class PurchasePaymentServiceImpl extends ServiceImpl<PurchasePaymentDao, PurchasePaymentEntity> implements PurchasePaymentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchasePaymentEntity> page = this.page(
                new Query<PurchasePaymentEntity>().getPage(params),
                new QueryWrapper<PurchasePaymentEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void pay(PurchasePaymentEntity purchasePaymentEntity) {
        /**
         * 金额入账
         * 记录明细
         * TODO
         */
    }

    /**
     * 银行回调
     * @param callBackInfo
     */
    @Override
    public void callback(String callBackInfo) {
        /**
         * 入账失败
         * TODO
         * 记录明细
         * 并通知采购方
         * 返回
         */

        /**
         * 入账成功
         * 记录明细
         * TODO
         */
        this.debtCallBack("");
    }

    @Override
    public void debtCallBack(String debtInfo) {
        /**
         * 更新交易状态
         * TODO
         */
    }

    private void debtRepayment(String debtInfo){
       /**
        * 偿还所融资金本息，调用金融机构提供的偿还接口
        * 记录明细
        * TODO
        */
   }

}