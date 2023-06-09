/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.maibaduoduo.configuration.utils.CacheUtils;
import com.maibaduoduo.configuration.utils.Constant;
import com.maibaduoduo.configuration.utils.MapUtils;
import com.maibaduoduo.service.SysMenuService;
import com.maibaduoduo.service.SysRoleMenuService;
import com.maibaduoduo.service.SysUserService;
import com.maibaduoduo.sys.dao.SysMenuDao;
import com.maibaduoduo.sys.entity.SysMenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
	@Autowired
	private CacheUtils cacheUtils;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenuEntity> menuList = null;
		Object cacheValue = cacheUtils.get(String.valueOf(parentId));
		if(Objects.isNull(cacheValue)){
			menuList = queryListParentId(parentId);
			cacheUtils.put(CacheUtils.SYS_CACHE,String.valueOf(parentId),menuList);
		}else{
			menuList = (List<SysMenuEntity>)cacheValue;
		}

		if(menuIdList == null){
			return menuList;
		}
		
		List<SysMenuEntity> userMenuList = new ArrayList<>();
		for(SysMenuEntity menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId) {
		return baseMapper.queryListParentId(parentId);
	}

	@Override
	public List<SysMenuEntity> queryNotButtonList() {
		return baseMapper.queryNotButtonList();
	}

	@Override
	public List<SysMenuEntity> getUserMenuList(Long userId) {
		//系统管理员，拥有最高权限
		if(userId == Constant.SUPER_ADMIN){
			return getAllMenuList(null);
		}
		//用户菜单列表
		List<Long> menuIdList =null;
		Object cacheValue = cacheUtils.get(String.valueOf(userId));
		if(Objects.isNull(cacheValue)){
			menuIdList = sysUserService.queryAllMenuId(userId);
			cacheUtils.put(CacheUtils.SYS_CACHE,String.valueOf(userId),menuIdList);
		}else{
			menuIdList = (List<Long>)cacheValue;
		}
		return getAllMenuList(menuIdList);
	}

	@Override
	public void delete(Long menuId){
		//删除菜单
		this.removeById(menuId);
		//删除菜单与角色关联
		sysRoleMenuService.removeByMap(new MapUtils().put("menu_id", menuId));
	}

	@Override
	public List<SysMenuEntity> getList(Wrapper<SysMenuEntity> queryWrapper) {
		List<SysMenuEntity> sysMenuEntityList = Lists.newArrayList();
		Object cacheValue = cacheUtils.get("ALLMenuList");
		if(Objects.isNull(cacheValue)){
			sysMenuEntityList = this.list();
			cacheUtils.put("ALLMenuList",sysMenuEntityList);
		}else{
			sysMenuEntityList = (List<SysMenuEntity>)cacheValue;
		}
		return this.list();
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList){
		List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();
		
		for(SysMenuEntity entity : menuList){
			//目录
			if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		
		return subMenuList;
	}
}
