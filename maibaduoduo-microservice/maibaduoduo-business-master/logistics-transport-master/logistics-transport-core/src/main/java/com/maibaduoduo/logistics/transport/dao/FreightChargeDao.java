/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.transport.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maibaduoduo.logistics.transport.entity.FreightChargeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 运费
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 11:48:30
 */
@Mapper
public interface FreightChargeDao extends BaseMapper<FreightChargeEntity> {
	
}
