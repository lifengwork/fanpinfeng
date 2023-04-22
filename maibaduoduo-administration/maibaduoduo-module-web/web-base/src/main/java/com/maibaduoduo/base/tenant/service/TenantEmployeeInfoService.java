package com.maibaduoduo.base.tenant.service;

import com.maibaduoduo.base.base.CrudService;
import com.maibaduoduo.base.tenant.dao.TenantEmployeeInfoDao;
import com.maibaduoduo.base.tenant.entity.TenantEmployeeInfo;
import com.maibaduoduo.common.entity.Page;
import com.maibaduoduo.common.event.TenantAuthorizationInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 开通登录权限Service
 * @author admin
 * @version 2023-04-11
 */
@Service
@Transactional(readOnly = true)
public class TenantEmployeeInfoService extends CrudService<TenantEmployeeInfoDao, TenantEmployeeInfo> {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public TenantEmployeeInfo get(String id) {
		return super.get(id);
	}

	public List<TenantEmployeeInfo> findList(TenantEmployeeInfo tenantEmployeeInfo) {
		return super.findList(tenantEmployeeInfo);
	}
	
	public Page<TenantEmployeeInfo> findPage(Page<TenantEmployeeInfo> page, TenantEmployeeInfo tenantEmployeeInfo) {
		return super.findPage(page, tenantEmployeeInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(TenantEmployeeInfo tenantEmployeeInfo) {
		if(!tenantEmployeeInfo.getIsNewRecord()){
			TenantAuthorizationInfo tenantAuthorizationInfo = new TenantAuthorizationInfo("author");
			tenantAuthorizationInfo.setEmployeeId(tenantEmployeeInfo.getTenantEmployeeId());
			tenantAuthorizationInfo.setTenantId(tenantEmployeeInfo.getTenantId());
			tenantAuthorizationInfo.setStatus(tenantEmployeeInfo.getStatus());
			applicationEventPublisher.publishEvent(tenantAuthorizationInfo);
		}
		super.save(tenantEmployeeInfo);

	}
	
	@Transactional(readOnly = false)
	public void delete(TenantEmployeeInfo tenantEmployeeInfo) {
		super.delete(tenantEmployeeInfo);
	}

	@Transactional(readOnly = false)
	public void batchDelete(String ...id) {
		dao.batchDelete(id);
	}

	@Transactional(readOnly = false)
	public void batchUpdate(List<TenantEmployeeInfo> list) {
		dao.batchUpdate(list);
/*        list.stream().forEach((tei)->{
			TenantAuthorizationInfo tenantAuthorizationInfo = new TenantAuthorizationInfo("author");
			tenantAuthorizationInfo.setEmployeeId(tei.getTenantEmployeeId());
			tenantAuthorizationInfo.setTenantId(tei.getTenantId());
			tenantAuthorizationInfo.setStatus(tei.getStatus());
			applicationEventPublisher.publishEvent(tenantAuthorizationInfo);
		});*/
	}

	@Transactional(readOnly = false)
	public void insertBatch(List<TenantEmployeeInfo> list) {
		dao.insertBatch(list);
	}
}