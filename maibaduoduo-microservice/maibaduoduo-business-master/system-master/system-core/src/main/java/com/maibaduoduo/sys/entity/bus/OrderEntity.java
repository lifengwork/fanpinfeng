package com.maibaduoduo.sys.entity.bus;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lf
 * @email lifengwork@yeah.net
 * @date 2020-10-23 17:25:09
 */
@Data
@TableName("tb_order")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String productId;
	/**
	 * 
	 */
	private String agentId;
	/**
	 * 
	 */
	private String memberId;
	/**
	 * 
	 */
	private BigDecimal totalAmount;
	/**
	 * 
	 */
	private BigDecimal actualAmount;
	/**
	 * 订单状态
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date created;
	/**
	 * 
	 */
	private String createdBy;

}
