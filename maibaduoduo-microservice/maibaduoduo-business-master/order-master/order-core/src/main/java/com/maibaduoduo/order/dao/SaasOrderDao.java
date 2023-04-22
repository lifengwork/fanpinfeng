/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maibaduoduo.order.entity.SaasOrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author lf
 * @email lifengwork@yeah.net
 * @date 2023-04-16 13:00:03
 */
@Mapper
public interface SaasOrderDao extends BaseMapper<SaasOrderEntity> {
	
}
