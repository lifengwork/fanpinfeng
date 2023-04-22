package com.maibaduoduo.web.handle;

import com.maibaduoduo.base.base.BaseController;
import com.maibaduoduo.base.tenant.entity.SysTenantDbUrl;
import com.maibaduoduo.base.tenant.service.SysTenantDbUrlService;
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
 * 单表生成Controller
 * @author admin
 * @version 2023-04-08
 */
@Controller
@RequestMapping(value = "${adminPath}/base/tenant/sysTenantDbUrl")
public class SysTenantDbUrlController extends BaseController {

	@Autowired
	private SysTenantDbUrlService sysTenantDbUrlService;
	
	@ModelAttribute
	public SysTenantDbUrl get(@RequestParam(required=false) String id) {
		SysTenantDbUrl entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysTenantDbUrlService.get(id);
		}
		if (entity == null){
			entity = new SysTenantDbUrl();
		}
		return entity;
	}
	
	@RequiresPermissions("base:tenant:sysTenantDbUrl:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysTenantDbUrl sysTenantDbUrl, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysTenantDbUrl> page = sysTenantDbUrlService.findPage(new Page<SysTenantDbUrl>(request, response), sysTenantDbUrl); 
		model.addAttribute("page", page);
		return "modules/base/tenant/sysTenantDbUrlList";
	}

	@RequiresPermissions("base:tenant:sysTenantDbUrl:view")
	@RequestMapping(value = "form")
	public String form(SysTenantDbUrl sysTenantDbUrl, Model model) {
		model.addAttribute("sysTenantDbUrl", sysTenantDbUrl);
		return "modules/base/tenant/sysTenantDbUrlForm";
	}

	@RequiresPermissions("base:tenant:sysTenantDbUrl:edit")
	@RequestMapping(value = "save")
	public String save(SysTenantDbUrl sysTenantDbUrl, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysTenantDbUrl)){
			return form(sysTenantDbUrl, model);
		}
		sysTenantDbUrlService.save(sysTenantDbUrl);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/base/tenant/sysTenantDbUrl/?repage";
	}
	
	@RequiresPermissions("base:tenant:sysTenantDbUrl:edit")
	@RequestMapping(value = "delete")
	public String delete(SysTenantDbUrl sysTenantDbUrl, RedirectAttributes redirectAttributes) {
		sysTenantDbUrl.setDelFlag("0");
		sysTenantDbUrlService.delete(sysTenantDbUrl);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/base/tenant/sysTenantDbUrl/?repage";
	}

    @RequiresPermissions("base:tenant:sysTenantDbUrl:edit")
	@RequestMapping(value = "batchDelete")
	public String batchDelete(String id, RedirectAttributes redirectAttributes) {
		sysTenantDbUrlService.batchDelete(id.split(","));
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/base/tenant/sysTenantDbUrl/?repage";
	}

    @RequiresPermissions("base:tenant:sysTenantDbUrl:edit")
	@RequestMapping(value = "batchUpdate")
	public String batchUpdate(String ids, RedirectAttributes redirectAttributes) {
	    List<SysTenantDbUrl> sysTenantDbUrlList  = new ArrayList<SysTenantDbUrl>();
	    SysTenantDbUrl sysTenantDbUrl =null;
	    for(String id:ids.split(",")){
	      sysTenantDbUrl = new SysTenantDbUrl();
	      sysTenantDbUrl.setId(id);
	      sysTenantDbUrlList.add(sysTenantDbUrl);
	    }
		sysTenantDbUrlService.batchUpdate(sysTenantDbUrlList);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/base/tenant/sysTenantDbUrl/?repage";
	}

}