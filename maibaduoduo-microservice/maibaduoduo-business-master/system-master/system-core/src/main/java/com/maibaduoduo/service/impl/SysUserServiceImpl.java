/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maibaduoduo.configuration.exception.SaasException;
import com.maibaduoduo.configuration.utils.Constant;
import com.maibaduoduo.configuration.utils.PageUtils;
import com.maibaduoduo.configuration.utils.Query;
import com.maibaduoduo.configuration.utils.RedisUtils;
import com.maibaduoduo.event.EmployeeInfo;
import com.maibaduoduo.service.SysRoleService;
import com.maibaduoduo.service.SysUserRoleService;
import com.maibaduoduo.service.SysUserService;
import com.maibaduoduo.sys.dao.SysUserDao;
import com.maibaduoduo.sys.entity.SysUserEntity;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	@Autowired
	private RedisUtils redisUtils;
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");
		Long createUserId = (Long)params.get("createUserId");

		IPage<SysUserEntity> page = this.page(
			new Query<SysUserEntity>().getPage(params),
			new QueryWrapper<SysUserEntity>()
				.like(StringUtils.isNotBlank(username),"username", username)
				.eq(createUserId != null,"create_user_id", createUserId)
		);

		return new PageUtils(page);
	}

	@Override
	public List<String> queryAllPerms(Long userId) {
		return baseMapper.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return baseMapper.queryByUserName(username);
	}

	@Override
	public SysUserEntity queryByMobile(String mobile) {
		return baseMapper.queryByMobile(mobile);
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUser(SysUserEntity user) {
		user.setCreateTime(new Date());
		user.setStatus(0);//内部用户创建默认是禁用
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		user.setTenantId(user.getTenantId());
		user.setSalt(salt);
		this.save(user);

		//检查角色是否越权
		checkRole(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());

		//用户信息放入缓存
		redisUtils.set(user.getUsername(),user);

		//业务端创建租户下的用户后通知SAAS运营端
		EmployeeInfo employeeInfo = new EmployeeInfo(1);
		employeeInfo.setEmployeeId(user.getUsername());
		employeeInfo.setTenantId(user.getTenantId());
		employeeInfo.setEmployeeName(user.getUsername());
		employeeInfo.setTenantName(user.getUsername());
		applicationEventPublisher.publishEvent(employeeInfo);
	}

	/**
	 * 接收运营端的数据并保存，创建租户在业务端的管理账号
	 * @param user
	 */
	@Transactional(rollbackFor = RuntimeException.class)
	public void saveUserFromSaas(SysUserEntity user) {
		//设置租户为系统管理员角色
		List<Long> roleIdList = new ArrayList<Long>();
		roleIdList.add(1L);
		user.setRoleIdList(roleIdList);
		user.setCreateTime(new Date());
		user.setStatus(0);//内部用户创建默认是禁用
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		user.setTenantId(user.getTenantId());
		user.setSalt(salt);
		this.save(user);

		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());

		//用户信息放入缓存
		redisUtils.set(user.getUsername(),user);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUserEntity user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
		}
		this.updateById(user);
		
		//检查角色是否越权
		checkRole(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	public void deleteBatch(Long[] userId) {
		this.removeByIds(Arrays.asList(userId));
	}

	@Override
	public boolean updatePassword(Long userId, String password, String newPassword) {
		SysUserEntity userEntity = new SysUserEntity();
		userEntity.setPassword(newPassword);
		return this.update(userEntity,
				new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
	}
	
	/**
	 * 检查角色是否越权
	 */
	private void checkRole(SysUserEntity user){
		if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
			return;
		}
		//如果不是超级管理员，则需要判断用户的角色是否自己创建
		if(user.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户创建的角色列表
		List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

		//判断是否越权
		if(!roleIdList.containsAll(user.getRoleIdList())){
			throw new SaasException("新增用户所选角色，不是本人创建");
		}
	}
}