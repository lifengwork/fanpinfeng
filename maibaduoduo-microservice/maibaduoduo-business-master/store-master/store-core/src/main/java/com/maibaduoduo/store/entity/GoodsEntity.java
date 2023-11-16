/*
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved. lifengwork@yeah.net
 * SAAS系统设计研发交流
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.store.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 采购的产品
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 18:04:34
 */
@Data
@TableName("saas_goods")
public class GoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 物品ID
	 */
	@TableId
	private Long id;
	/**
	 * 物品编码
	 */
	private String goodsNo;
	/**
	 * 物品名称
	 */
	private String goodsName;
	/**
	 * 物品类型（塑胶件、冲裁件、服务件）
	 */
	private String goodsType;
	/**
	 * 物品数量
	 */
	private Long goodsCount;
	/**
	 * 物品重量
	 */
	private Double goodsWeight;
	/**
	 * 物品有效期
	 */
	private Date goodsExpiry;
	/**
	 * SPUID
	 */
	private String goodsSpuId;
	/**
	 * SKUID
	 */
	private String goodsSkuId;
	/**
	 * SNID
	 */
	private Integer goodsSnId;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 创建者ID
	 */
	private Long createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 租户编码
	 */
	private String tenantId;

}
