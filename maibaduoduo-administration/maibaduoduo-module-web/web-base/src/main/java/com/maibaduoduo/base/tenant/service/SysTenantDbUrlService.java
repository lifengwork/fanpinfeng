package com.maibaduoduo.base.tenant.service;

import com.maibaduoduo.base.base.CrudService;
import com.maibaduoduo.base.tenant.dao.SysTenantDbUrlDao;
import com.maibaduoduo.base.tenant.entity.SysTenantDbUrl;
import com.maibaduoduo.common.entity.Page;
import com.maibaduoduo.common.event.TenantInitDataSourceInfo;
import com.maibaduoduo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 单表生成Service
 * @author admin
 * @version 2023-04-08
 */
@Service
@Transactional(readOnly = true)
public class SysTenantDbUrlService extends CrudService<SysTenantDbUrlDao, SysTenantDbUrl> {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public SysTenantDbUrl get(String id) {
		return super.get(id);
	}
	
	public List<SysTenantDbUrl> findList(SysTenantDbUrl sysTenantDbUrl) {
		return super.findList(sysTenantDbUrl);
	}
	
	public Page<SysTenantDbUrl> findPage(Page<SysTenantDbUrl> page, SysTenantDbUrl sysTenantDbUrl) {
		return super.findPage(page, sysTenantDbUrl);
	}
	
	@Transactional(readOnly = false)
	public void save(SysTenantDbUrl sysTenantDbUrl) {
		if(StringUtils.isNotEmpty(sysTenantDbUrl.getId())){
			sysTenantDbUrl.setIsNewRecord(false);
		}
		super.save(sysTenantDbUrl);
		TenantInitDataSourceInfo tenantInitDataSourceInfo = new TenantInitDataSourceInfo("tenant_data_init");
		tenantInitDataSourceInfo.setDbKey(sysTenantDbUrl.getDbName());
		tenantInitDataSourceInfo.setDbUrl(sysTenantDbUrl.getDbUrl());
		applicationEventPublisher.publishEvent(tenantInitDataSourceInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysTenantDbUrl sysTenantDbUrl) {
		super.delete(sysTenantDbUrl);
	}

	@Transactional(readOnly = false)
	public void batchDelete(String ...id) {
		dao.batchDelete(id);
	}

	@Transactional(readOnly = false)
	public void batchUpdate(List<SysTenantDbUrl> list) {
		dao.batchUpdate(list);
	}

	@Transactional(readOnly = false)
	public void insertBatch(List<SysTenantDbUrl> list) {
		dao.insertBatch(list);
	}
}