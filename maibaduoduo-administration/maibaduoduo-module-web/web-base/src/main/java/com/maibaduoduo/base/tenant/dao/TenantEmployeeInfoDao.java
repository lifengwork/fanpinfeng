package com.maibaduoduo.base.tenant.dao;

import com.maibaduoduo.base.tenant.entity.TenantEmployeeInfo;
import com.maibaduoduo.common.annotation.CrudDao;
import com.maibaduoduo.common.annotation.MyBatisDao;

import java.util.List;

/**
 * 开通登录权限DAO接口
 * @author admin
 * @version 2023-04-11
 */
@MyBatisDao
public interface TenantEmployeeInfoDao extends CrudDao<TenantEmployeeInfo> {
  void	batchDelete(String... array);
  void	batchUpdate(List<TenantEmployeeInfo> list);
  void	insertBatch(List<TenantEmployeeInfo> list);
}