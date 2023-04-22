package com.maibaduoduo.base.tenant.dao;

import com.maibaduoduo.base.tenant.entity.SysTenant;
import com.maibaduoduo.common.annotation.CrudDao;
import com.maibaduoduo.common.annotation.MyBatisDao;
/**
 * 租户信息DAO接口
 * @author maibaduoduo
 * @version 2021-09-01
 */
@MyBatisDao
public interface SysTenantDao extends CrudDao<SysTenant> {
  void	batchDelete(String... array);
  void	batchUpdate(SysTenant list);
  void	insertBatch(SysTenant list);
}