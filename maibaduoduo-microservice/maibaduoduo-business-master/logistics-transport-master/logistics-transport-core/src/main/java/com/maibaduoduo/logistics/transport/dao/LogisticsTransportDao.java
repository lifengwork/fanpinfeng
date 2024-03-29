/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.logistics.transport.dao;

import com.maibaduoduo.logistics.transport.entity.LogisticsTransportEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 运输
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-10-25 18:03:06
 */
@Mapper
public interface LogisticsTransportDao extends BaseMapper<LogisticsTransportEntity> {
	
}
