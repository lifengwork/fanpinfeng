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
@TableName("tb_member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String passwod;
	/**
	 * 
	 */
	private String phone;
	/**
	 * 
	 */
	private String cardid;
	/**
	 * 
	 */
	private String registryarea;
	/**
	 * 
	 */
	private String currentarea;
	/**
	 * 
	 */
	private String accountid;
	/**
	 * 0代理商1普通会员
	 */
	private Integer type;
	/**
	 * 0禁用1启用
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date created;
	/**
	 * 
	 */
	private String createdby;
	/**
	 * 
	 */
	private String wechat;

}
