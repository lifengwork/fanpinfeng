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
@TableName("saas_goods_spu")
public class GoodsSpuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * SPUID（商品信息聚合的最小单位）
	 */
	@TableId
	private Long id;
	/**
	 * spu编码(平【品牌+型号：iPhone 12)
	 */
	private String spuNo;
	/**
	 * SPU项
	 */
	private String spuItem1;
	/**
	 * 
	 */
	private String spuItem2;
	/**
	 * 
	 */
	private String spuItem3;
	/**
	 * 
	 */
	private String spuItem4;
	/**
	 * 
	 */
	private String spuItem5;
	/**
	 * 
	 */
	private String spuItem6;
	/**
	 * 
	 */
	private String spuItem7;
	/**
	 * 
	 */
	private String spuItem8;
	/**
	 * 
	 */
	private String spuItem9;
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
