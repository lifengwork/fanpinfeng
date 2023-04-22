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
@TableName("tb_order_item")
public class OrderItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String itemId;
	/**
	 * 
	 */
	private String orderId;
	/**
	 * 
	 */
	private String productName;
	/**
	 * 
	 */
	private String productImageTitle;
	/**
	 * 
	 */
	private String agentName;
	/**
	 * 下单区域
	 */
	private String area;
	/**
	 * 
	 */
	private BigDecimal orderTotalAmount;
	/**
	 * 
	 */
	private BigDecimal orderActualTotalAmount;
	/**
	 * 
	 */
	private BigDecimal discountTotalAmount;
	/**
	 * 商品数量
	 */
	private Integer productNumber;

}
