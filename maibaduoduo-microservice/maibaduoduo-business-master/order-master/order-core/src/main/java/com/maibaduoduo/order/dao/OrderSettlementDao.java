/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.dao;

import com.maibaduoduo.order.entity.OrderSettlementEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单结算
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 16:41:56
 */
@Mapper
public interface OrderSettlementDao extends BaseMapper<OrderSettlementEntity> {
	
}
