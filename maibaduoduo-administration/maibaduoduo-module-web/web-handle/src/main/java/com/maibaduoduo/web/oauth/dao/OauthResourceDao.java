package com.maibaduoduo.web.oauth.dao;

import com.maibaduoduo.common.annotation.CrudDao;
import com.maibaduoduo.common.annotation.MyBatisDao;
import com.maibaduoduo.web.oauth.entity.OauthResource;

/**
 * 认证资源表DAO接口
 * @author maibaduoduo
 * @version 2021-08-25
 */
@MyBatisDao
public interface OauthResourceDao extends CrudDao<OauthResource> {
  void	batchDelete(String ...array);
  void	batchUpdate(OauthResource list);
  void	insertBatch(OauthResource list);
}