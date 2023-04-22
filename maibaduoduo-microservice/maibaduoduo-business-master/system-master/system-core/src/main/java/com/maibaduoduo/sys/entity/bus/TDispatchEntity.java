package com.maibaduoduo.sys.entity.bus;

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
 * @date 2020-10-23 17:25:09
 */
@Data
@TableName("t_dispatch")
public class TDispatchEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String orderId;
	/**
	 * 会员编码
	 */
	private String memberId;
	/**
	 * 代理商编码
	 */
	private String agentId;
	/**
	 * 快递员名称
	 */
	private String name;
	/**
	 * 快递员手机号
	 */
	private String phone;
	/**
	 * 
	 */
	private String toAddress;
	/**
	 * 
	 */
	private String fromAddress;
	/**
	 * 配送日期
	 */
	private Date dispatchDate;
	/**
	 * 下单日期
	 */
	private Date created;
	/**
	 * 0交易关闭1购买完成2正在配送
	 */
	private Integer status;

}
