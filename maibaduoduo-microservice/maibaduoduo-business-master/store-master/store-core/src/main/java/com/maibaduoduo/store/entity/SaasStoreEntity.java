/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
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
 * @author lf
 * @email lifengwork@yeah.net
 * @date 2023-04-16 13:00:02
 */
@Data
@TableName("saas_store")
public class SaasStoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 仓库编码
	 */
	@TableId
	private Long id;
	/**
	 * 仓库名称
	 */
	private String storeName;
	/**
	 * 仓库简述
	 */
	private String storeDescription;
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
