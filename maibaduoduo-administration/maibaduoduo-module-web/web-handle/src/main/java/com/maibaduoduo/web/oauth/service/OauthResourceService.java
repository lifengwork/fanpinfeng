package com.maibaduoduo.web.oauth.service;

import java.util.List;


import com.maibaduoduo.base.base.CrudService;
import com.maibaduoduo.common.entity.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maibaduoduo.web.oauth.entity.OauthResource;
import com.maibaduoduo.web.oauth.dao.OauthResourceDao;

/**
 * 认证资源表Service
 * @author maibaduoduo
 * @version 2021-08-25
 */
@Service
@Transactional(readOnly = true)
public class OauthResourceService extends CrudService<OauthResourceDao, OauthResource> {

	public OauthResource get(String id) {
		return super.get(id);
	}
	
	public List<OauthResource> findList(OauthResource oauthResource) {
		return super.findList(oauthResource);
	}
	
	public Page<OauthResource> findPage(Page<OauthResource> page, OauthResource oauthResource) {
		return super.findPage(page, oauthResource);
	}
	
	@Transactional(readOnly = false)
	public void save(OauthResource oauthResource) {
		super.save(oauthResource);
	}
	
	@Transactional(readOnly = false)
	public void delete(OauthResource oauthResource) {
		super.delete(oauthResource);
	}

	@Transactional(readOnly = false)
	public void batchDelete(String ...id) {
		dao.batchDelete(id);
	}

	@Transactional(readOnly = false)
	public void batchUpdate(OauthResource list) {
		dao.batchUpdate(list);
	}

	@Transactional(readOnly = false)
	public void insertBatch(OauthResource list) {
		dao.insertBatch(list);
	}
}