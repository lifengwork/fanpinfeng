/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.order.entity;

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
 * @date 2023-04-16 13:00:03
 */
@Data
@TableName("saas_order")
public class SaasOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单编码
	 */
	@TableId
	private Long id;
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
