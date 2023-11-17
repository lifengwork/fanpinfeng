/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.finance.dao;

import com.maibaduoduo.finance.entity.FinancialAgreementEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 融资协议
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-17 15:01:07
 */
@Mapper
public interface FinancialAgreementDao extends BaseMapper<FinancialAgreementEntity> {
	
}
