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
 * 
 * @author saas
 * @email lifengwork@yeah.net
 * @date 2023-11-12 18:04:34
 */
@Data
@TableName("saas_goods_sku")
public class GoodsSkuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * SKUID（库存进出量的基本单元）
	 */
	@TableId
	private Long id;
	/**
	 * SKU编码（苹果 iPhone 12 白色 64G）
	 */
	private String skuNo;
	/**
	 * SPUID
	 */
	private String spuNo;
	/**
	 * SKU项
	 */
	private String skuItem1;
	/**
	 * 
	 */
	private String skuItem2;
	/**
	 * 
	 */
	private String skuItem3;
	/**
	 * 
	 */
	private String skuItem4;
	/**
	 * 
	 */
	private String skuItem5;
	/**
	 * 
	 */
	private String skuItem6;
	/**
	 * 
	 */
	private String skuItem7;
	/**
	 * 
	 */
	private String skuItem8;
	/**
	 * 
	 */
	private String skuItem9;
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
