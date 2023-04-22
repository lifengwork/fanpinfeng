package com.maibaduoduo.base.tenant.dao;

import com.maibaduoduo.base.tenant.entity.SysTenantDbUrl;
import com.maibaduoduo.common.annotation.CrudDao;
import com.maibaduoduo.common.annotation.MyBatisDao;

import java.util.List;

/**
 * 单表生成DAO接口
 * @author admin
 * @version 2023-04-08
 */
@MyBatisDao
public interface SysTenantDbUrlDao extends CrudDao<SysTenantDbUrl> {
  void	batchDelete(String... array);
  void	batchUpdate(List<SysTenantDbUrl> list);
  void	insertBatch(List<SysTenantDbUrl> list);
}