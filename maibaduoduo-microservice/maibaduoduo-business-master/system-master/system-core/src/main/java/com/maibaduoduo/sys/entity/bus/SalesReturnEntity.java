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
@TableName("tb_sales_return")
public class SalesReturnEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 退单编码
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String orderId;
	/**
	 * 
	 */
	private String productId;
	/**
	 * 
	 */
	private String memberId;
	/**
	 * 0退款关闭1正在退款2退款完成
	 */
	private Integer status;
	/**
	 * 发起退款日期
	 */
	private Date created;
	/**
	 * 
	 */
	private String agentId;
	/**
	 * 备注信息
	 */
	private String remark;
	/**
	 * 退货图片
	 */
	private String image;
	/**
	 * 商品单价
	 */
	private BigDecimal unitPrice;
	/**
	 * 退回件数
	 */
	private Integer number;
	/**
	 * 退款金额
	 */
	private BigDecimal salesReturnAmount;

}
