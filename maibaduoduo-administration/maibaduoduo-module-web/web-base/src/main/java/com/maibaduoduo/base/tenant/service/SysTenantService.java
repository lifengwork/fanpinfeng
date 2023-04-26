package com.maibaduoduo.base.tenant.service;

import com.maibaduoduo.base.base.CrudService;
import com.maibaduoduo.base.tenant.dao.SysTenantDao;
import com.maibaduoduo.base.tenant.dao.SysTenantDbUrlDao;
import com.maibaduoduo.base.tenant.entity.SysTenant;
import com.maibaduoduo.base.tenant.entity.SysTenantDbUrl;
import com.maibaduoduo.base.tenant.entity.TenantEmployeeInfo;
import com.maibaduoduo.base.utils.TenantData;
import com.maibaduoduo.common.entity.Page;
import com.maibaduoduo.common.event.TenantEventInfo;
import com.maibaduoduo.common.redis.KeyPrefix;
import com.maibaduoduo.common.redis.RedisService;
import com.maibaduoduo.common.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 租户信息Service
 * @author maibaduoduo
 * @version 2021-09-01
 */
@Service
@Transactional(readOnly = true)
public class SysTenantService extends CrudService<SysTenantDao, SysTenant> {

	@Autowired
	private SysTenantDbUrlDao sysTenantDbUrlDao;

	@Autowired
	private RedisService redisService;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private TenantEmployeeInfoService tenantEmployeeInfoService;

	public SysTenant get(String id) {
		return super.get(id);
	}
	
	public List<SysTenant> findList(SysTenant sysTenant) {
		return super.findList(sysTenant);
	}
	
	public Page<SysTenant> findPage(Page<SysTenant> page, SysTenant sysTenant) {
		return super.findPage(page, sysTenant);
	}
	
	@Transactional(readOnly = false)
	public void save(SysTenant sysTenant) {
		super.save(sysTenant);
		SysTenantDbUrl sysTenantDbUrl = new SysTenantDbUrl();
		sysTenantDbUrl.setTenantId(sysTenant.getId());
		List<SysTenantDbUrl> sysTenantDb = sysTenantDbUrlDao.findList(sysTenantDbUrl);
		if(!sysTenantDb.isEmpty()){return;}

		String dbName="column_master";
		List<SysTenantDbUrl> sysTenantDbUrlList = new ArrayList<SysTenantDbUrl>();

		StringBuilder stringBuilderMaster = new StringBuilder();
		stringBuilderMaster.append("jdbc:mysql://");
		stringBuilderMaster.append(sysTenant.getMysqlServerIp());
		stringBuilderMaster.append(":");
		stringBuilderMaster.append(sysTenant.getMysqlPort());
		stringBuilderMaster.append("/");

		SysTenantDbUrl sysTenantDbUrlMaster = new SysTenantDbUrl();
		sysTenantDbUrlMaster.setEmployee(sysTenant.getId());
		sysTenantDbUrlMaster.setTenantId(sysTenant.getId());
		sysTenantDbUrlMaster.setCreateBy(UserUtils.getUser().getCreateBy());
		sysTenantDbUrlMaster.setCreateDate(new Date());
		sysTenantDbUrlMaster.setRemarks(sysTenant.getMultiTenantType());

		if("datasource".equals(sysTenant.getMultiTenantType())){
			if(1==sysTenant.getIsMasterSlave()){
				dbName = "datasource_master_"+sysTenant.getId();
				stringBuilderMaster.append(dbName);
				stringBuilderMaster.append(TenantData.DB_URL_PARAM);
				sysTenantDbUrlMaster.setDbUrl(stringBuilderMaster.toString());
				sysTenantDbUrlList.add(sysTenantDbUrlMaster);
                //加入缓存
				redisService.set(new KeyPrefix() {
									 @Override
									 public int expireSeconds() {
										 return 999999999;
									 }
									 @Override
									 public String getPrefix() {
										 return  "TENANT_";
									 }
								 },"DB_URL_"+sysTenantDbUrlMaster.getEmployee(),
						new TenantInfo().setTenantInfo(sysTenantDbUrlMaster.getTenantId(),
								sysTenantDbUrlMaster.getEmployee(),sysTenantDbUrlMaster.getDbUrl()
								,dbName
								,sysTenantDbUrlMaster.getRemarks()));

				SysTenantDbUrl sysTenantDbUrlSlave = new SysTenantDbUrl();
				sysTenantDbUrlSlave.setEmployee(sysTenant.getId());
				sysTenantDbUrlSlave.setTenantId(sysTenant.getId());
				sysTenantDbUrlSlave.setCreateBy(UserUtils.getUser().getCreateBy());
				sysTenantDbUrlSlave.setCreateDate(new Date());
				sysTenantDbUrlSlave.setInitData("0");
				sysTenantDbUrlSlave.setRemarks(sysTenant.getMultiTenantType());

				StringBuilder stringBuilderSlave = new StringBuilder();
				stringBuilderSlave.append("jdbc:mysql://");
				stringBuilderSlave.append(sysTenant.getMysqlServerIp());
				stringBuilderSlave.append(":");
				stringBuilderSlave.append(sysTenant.getMysqlPort());
				stringBuilderSlave.append("/");
				dbName = "datasource_slave_"+sysTenant.getId();
				stringBuilderSlave.append(dbName);
				stringBuilderSlave.append(TenantData.DB_URL_PARAM);
				sysTenantDbUrlSlave.setDbUrl(stringBuilderSlave.toString());
				sysTenantDbUrlList.add(sysTenantDbUrlSlave);
				//加入缓存
				redisService.set(new KeyPrefix() {
					@Override
					public int expireSeconds() {
						return 999999999;
					}

					@Override
					public String getPrefix() {
						return  "TENANT_";
					}
				},"DB_URL_"+sysTenantDbUrlSlave.getEmployee(),
						new TenantInfo().setTenantInfo(sysTenantDbUrlSlave.getTenantId(),
								sysTenantDbUrlSlave.getEmployee(),sysTenantDbUrlSlave.getDbUrl()
								,dbName
								,sysTenantDbUrlSlave.getRemarks()));
			}else{
				dbName = "datasource_master"+sysTenant.getId();
				stringBuilderMaster.append(dbName);
				stringBuilderMaster.append(TenantData.DB_URL_PARAM);
				sysTenantDbUrlMaster.setInitData("0");
				sysTenantDbUrlMaster.setDbUrl(stringBuilderMaster.toString());
				sysTenantDbUrlList.add(sysTenantDbUrlMaster);
				//加入缓存
				redisService.set(new KeyPrefix() {
									 @Override
									 public int expireSeconds() {
										 return 999999999;
									 }
									 @Override
									 public String getPrefix() {
										 return  "TENANT_";
									 }
								 },"DB_URL_"+sysTenantDbUrlMaster.getEmployee(),
						new TenantInfo().setTenantInfo(sysTenantDbUrlMaster.getTenantId(),
								sysTenantDbUrlMaster.getEmployee(),sysTenantDbUrlMaster.getDbUrl()
								,dbName,
								sysTenantDbUrlMaster.getRemarks()));
			}
		}else{
			if("1".equals(sysTenant.getIsMasterSlave())){
				stringBuilderMaster.append(dbName);
				stringBuilderMaster.append(TenantData.DB_URL_PARAM);
				sysTenantDbUrlMaster.setDbUrl(stringBuilderMaster.toString());
				sysTenantDbUrlList.add(sysTenantDbUrlMaster);
				//加入缓存
				redisService.set(new KeyPrefix() {
									 @Override
									 public int expireSeconds() {
										 return 999999999;
									 }
									 @Override
									 public String getPrefix() {
										 return  "TENANT_";
									 }
								 },"DB_URL_"+sysTenantDbUrlMaster.getEmployee(),
						new TenantInfo().setTenantInfo(sysTenantDbUrlMaster.getTenantId(),
								sysTenantDbUrlMaster.getEmployee(),sysTenantDbUrlMaster.getDbUrl()
								,dbName,sysTenantDbUrlMaster.getRemarks()));

				SysTenantDbUrl sysTenantDbUrlSlave = new SysTenantDbUrl();
				sysTenantDbUrlSlave.setEmployee(sysTenant.getId());
				sysTenantDbUrlSlave.setTenantId(sysTenant.getId());
				sysTenantDbUrlSlave.setCreateBy(UserUtils.getUser().getCreateBy());
				sysTenantDbUrlSlave.setCreateDate(new Date());
				sysTenantDbUrlSlave.setInitData("1");
				sysTenantDbUrlSlave.setRemarks(sysTenant.getMultiTenantType());

				StringBuilder stringBuilderSlave = new StringBuilder();
				stringBuilderSlave.append("jdbc:mysql://");
				stringBuilderSlave.append(sysTenant.getMysqlServerIp());
				stringBuilderSlave.append(":");
				stringBuilderSlave.append(sysTenant.getMysqlPort());
				stringBuilderSlave.append("/");
				dbName = "cloumn_slave";
				stringBuilderSlave.append(dbName);
				stringBuilderSlave.append(TenantData.DB_URL_PARAM);
				sysTenantDbUrlSlave.setDbUrl(stringBuilderSlave.toString());
				sysTenantDbUrlList.add(sysTenantDbUrlSlave);
				//加入缓存
				redisService.set(new KeyPrefix() {
									 @Override
									 public int expireSeconds() {
										 return 999999999;
									 }
									 @Override
									 public String getPrefix() {
										 return "TENANT_";
									 }
								 },"DB_URL_"+sysTenantDbUrlSlave.getEmployee(),
						new TenantInfo().setTenantInfo(sysTenantDbUrlSlave.getTenantId(),
								sysTenantDbUrlSlave.getEmployee(),sysTenantDbUrlSlave.getDbUrl(),
								dbName,sysTenantDbUrlSlave.getRemarks()));
			}else{
				stringBuilderMaster.append(dbName);
				stringBuilderMaster.append(TenantData.DB_URL_PARAM);
				sysTenantDbUrlMaster.setDbUrl(stringBuilderMaster.toString());
				sysTenantDbUrlMaster.setInitData("1");
				sysTenantDbUrlList.add(sysTenantDbUrlMaster);
				//加入缓存
				redisService.set(new KeyPrefix() {
									 @Override
									 public int expireSeconds() {
										 return 999999999;
									 }
									 @Override
									 public String getPrefix() {
										 return "TENANT_";
									 }
								 },"DB_URL_"+sysTenantDbUrlMaster.getEmployee(),
						new TenantInfo().setTenantInfo(sysTenantDbUrlMaster.getTenantId(),
								sysTenantDbUrlMaster.getEmployee(),sysTenantDbUrlMaster.getDbUrl(),
								dbName,sysTenantDbUrlMaster.getRemarks()));
			}
		}
		sysTenantDbUrlDao.insertBatch(sysTenantDbUrlList);

		//初始化登录账号，租户是最高管理员，username等于租户编码
		TenantEmployeeInfo tenantEmployeeInfo = new TenantEmployeeInfo();
		tenantEmployeeInfo.setIsNewRecord(true);
		tenantEmployeeInfo.setTenantEmployeeId(sysTenant.getId());
		tenantEmployeeInfo.setTenantEmployeeName(sysTenant.getTenantName());
		tenantEmployeeInfo.setTenantId(sysTenant.getId());
		tenantEmployeeInfo.setTenantName(sysTenant.getTenantName());
		tenantEmployeeInfo.setStatus(0);
		tenantEmployeeInfoService.save(tenantEmployeeInfo);

		TenantEventInfo tenantEventInfo = new TenantEventInfo(1);
		tenantEventInfo.setTenantId(sysTenant.getId());
		applicationEventPublisher.publishEvent(tenantEventInfo);

	}
	
	@Transactional(readOnly = false)
	public void delete(SysTenant sysTenant) {
		super.delete(sysTenant);
	}

	@Transactional(readOnly = false)
	public void batchDelete(String ...id) {
		dao.batchDelete(id);
	}

	@Transactional(readOnly = false)
	public void batchUpdate(SysTenant list) {
		dao.batchUpdate(list);
	}

	@Transactional(readOnly = false)
	public void insertBatch(SysTenant list) {
		dao.insertBatch(list);
	}

	static class TenantInfo{
		private String tenantId;		// tenant_id
		private String employee;		// employee
		private String dbUrl;		// db_url
		private String dbName;
		private String dataType;

		public TenantInfo setTenantInfo(String tenantId,String employee,String dbUrl,String dbName,String dataType){
			this.dbUrl=dbUrl;
			this.tenantId=tenantId;
			this.employee=employee;
			this.dbName=dbName;
			this.dataType=dataType;
			return this;
		}

		public String getTenantId() {
			return tenantId;
		}

		public void setTenantId(String tenantId) {
			this.tenantId = tenantId;
		}

		public String getEmployee() {
			return employee;
		}

		public void setEmployee(String employee) {
			this.employee = employee;
		}

		public String getDbUrl() {
			return dbUrl;
		}

		public void setDbUrl(String dbUrl) {
			this.dbUrl = dbUrl;
		}

		public String getDbName() {
			return dbName;
		}

		public void setDbName(String dbName) {
			this.dbName = dbName;
		}

		public String getDataType() {
			return dataType;
		}

		public void setDataType(String dataType) {
			this.dataType = dataType;
		}
	}
}
