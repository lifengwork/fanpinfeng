package com.maibaduoduo.web.handle;

import com.maibaduoduo.base.base.BaseController;
import com.maibaduoduo.base.tenant.entity.TenantEmployeeInfo;
import com.maibaduoduo.base.tenant.service.TenantEmployeeInfoService;
import com.maibaduoduo.common.entity.Page;
import com.maibaduoduo.common.utils.Global;
import com.maibaduoduo.common.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 开通登录权限Controller
 * @author admin
 * @version 2023-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/base/tenant/tenantEmployeeInfo")
public class TenantEmployeeInfoController extends BaseController {

	@Autowired
	private TenantEmployeeInfoService tenantEmployeeInfoService;
	
	@ModelAttribute
	public TenantEmployeeInfo get(@RequestParam(required=false) String id) {
		TenantEmployeeInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tenantEmployeeInfoService.get(id);
		}
		if (entity == null){
			entity = new TenantEmployeeInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("base:tenant:tenantEmployeeInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(TenantEmployeeInfo tenantEmployeeInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TenantEmployeeInfo> page = tenantEmployeeInfoService.findPage(new Page<TenantEmployeeInfo>(request, response), tenantEmployeeInfo);
		model.addAttribute("page", page);
		return "modules/base/tenant/tenantEmployeeInfoList";
	}

	@RequiresPermissions("base:tenant:tenantEmployeeInfo:view")
	@RequestMapping(value = "form")
	public String form(TenantEmployeeInfo tenantEmployeeInfo, Model model) {
		model.addAttribute("tenantEmployeeInfo", tenantEmployeeInfo);
		return "modules/base/tenant/tenantEmployeeInfoForm";
	}

	@RequiresPermissions("base:tenant:tenantEmployeeInfo:edit")
	@RequestMapping(value = "save")
	public String save(TenantEmployeeInfo tenantEmployeeInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tenantEmployeeInfo)){
			return form(tenantEmployeeInfo, model);
		}
		tenantEmployeeInfoService.save(tenantEmployeeInfo);
		addMessage(redirectAttributes, "保存开通登录权限成功");
		return "redirect:"+Global.getAdminPath()+"/base/tenant/tenantEmployeeInfo/?repage";
	}
	
	@RequiresPermissions("base:tenant:tenantEmployeeInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(TenantEmployeeInfo tenantEmployeeInfo, RedirectAttributes redirectAttributes) {
		tenantEmployeeInfoService.delete(tenantEmployeeInfo);
		addMessage(redirectAttributes, "删除开通登录权限成功");
		return "redirect:"+Global.getAdminPath()+"/base/tenant/tenantEmployeeInfo/?repage";
	}

    @RequiresPermissions("base:tenant:tenantEmployeeInfo:edit")
	@RequestMapping(value = "batchDelete")
	public String batchDelete(String id, RedirectAttributes redirectAttributes) {
		tenantEmployeeInfoService.batchDelete(id.split(","));
		addMessage(redirectAttributes, "删除开通登录权限成功");
		return "redirect:"+Global.getAdminPath()+"/base/tenant/tenantEmployeeInfo/?repage";
	}

    @RequiresPermissions("base:tenant:tenantEmployeeInfo:edit")
	@RequestMapping(value = "batchUpdate")
	public String batchUpdate(String ids, RedirectAttributes redirectAttributes) {
	    List<TenantEmployeeInfo> tenantEmployeeInfoList  = new ArrayList<TenantEmployeeInfo>();
	    TenantEmployeeInfo tenantEmployeeInfo =null;
	    for(String id:ids.split(",")){
	      tenantEmployeeInfo = new TenantEmployeeInfo();
	      tenantEmployeeInfo.setTenantEmployeeId(id);
	      tenantEmployeeInfo.setStatus(1);
	      tenantEmployeeInfoList.add(tenantEmployeeInfo);
	    }
		tenantEmployeeInfoService.batchUpdate(tenantEmployeeInfoList);
		addMessage(redirectAttributes, "保存开通登录权限成功");
		return "redirect:"+Global.getAdminPath()+"/base/tenant/tenantEmployeeInfo/?repage";
	}

}