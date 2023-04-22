package com.maibaduoduo.web.handle;

import com.maibaduoduo.base.base.BaseController;
import com.maibaduoduo.common.entity.Page;
import com.maibaduoduo.common.service.SystemService;
import com.maibaduoduo.common.utils.Global;
import com.maibaduoduo.common.utils.IdGen;
import com.maibaduoduo.common.utils.StringUtils;

import com.maibaduoduo.common.utils.UserUtils;
import com.maibaduoduo.web.oauth.entity.OauthResource;
import com.maibaduoduo.web.oauth.service.OauthResourceService;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 认证资源表Controller
 * @author maibaduoduo
 * @version 2021-08-25
 */
@Controller
@RequestMapping(value = "${adminPath}/oauth/oauthResource")
public class OauthResourceController extends BaseController {

	@Autowired
	private OauthResourceService oauthResourceService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private IdGen idGen;

	@ModelAttribute
	public OauthResource get(@RequestParam(required=false) String id) {
		OauthResource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oauthResourceService.get(id);
		}
		if (entity == null){
			entity = new OauthResource();
		}
		return entity;
	}
	
	@RequiresPermissions("oauth:oauthResource:view")
	@RequestMapping(value = {"list", ""})
	public String list(OauthResource oauthResource, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OauthResource> page = oauthResourceService.findPage(new Page<OauthResource>(request, response), oauthResource);
		model.addAttribute("page", page);
		return "maibaduoduo/oauth/oauthResourceList";
	}

	@RequiresPermissions("oauth:oauthResource:view")
	@RequestMapping(value = "form")
	public String form(OauthResource oauthResource, Model model) {
		model.addAttribute("oauthResource", oauthResource);
		model.addAttribute("allRoles", systemService.findAllRole());
		return "maibaduoduo/oauth/oauthResourceForm";
	}

	@RequiresPermissions("oauth:oauthResource:edit")
	@RequestMapping(value = "save")
	public String save(OauthResource oauthResource, Model model, RedirectAttributes redirectAttributes) {
		oauthResource.setCreateDate(new Date());
		oauthResource.setCreateBy(UserUtils.getUser());
		oauthResource.setOauthResourceId(idGen.SnowFlake());
		if (!beanValidator(model, oauthResource)){
			return form(oauthResource, model);
		}
		oauthResourceService.save(oauthResource);
		addMessage(redirectAttributes, "保存资源操作成功成功");
		return "redirect:"+Global.getAdminPath()+"/oauth/oauthResource/?repage";
	}
	
	@RequiresPermissions("oauth:oauthResource:edit")
	@RequestMapping(value = "delete")
	public String delete(OauthResource oauthResource, RedirectAttributes redirectAttributes) {
		oauthResourceService.delete(oauthResource);
		addMessage(redirectAttributes, "删除资源操作成功成功");
		return "redirect:"+ Global.getAdminPath()+"/oauth/oauthResource/?repage";
	}

    @RequiresPermissions("oauth:oauthResource:edit")
	@RequestMapping(value = "batchDelete")
	public String batchDelete(String id, RedirectAttributes redirectAttributes) {
		oauthResourceService.batchDelete(id.split(","));
		addMessage(redirectAttributes, "删除资源操作成功成功");
		return "redirect:"+Global.getAdminPath()+"/oauth/oauthResource/?repage";
	}

    @RequiresPermissions("oauth:oauthResource:edit")
	@RequestMapping(value = "batchUpdate")
	public String batchUpdate(String ids, RedirectAttributes redirectAttributes) {
	    List<OauthResource> oauthResourceList  = new ArrayList<OauthResource>();
	    OauthResource oauthResource =null;
	    for(String id:ids.split(",")){
	      oauthResource = new OauthResource();
	      oauthResource.setId(id);
	      oauthResourceList.add(oauthResource);
	    }
		oauthResource.setUpdateDate(new Date());
		oauthResource.setUpdateBy(UserUtils.getUser());
		oauthResourceService.batchUpdate(oauthResource);
		addMessage(redirectAttributes, "保存资源操作成功成功");
		return "redirect:"+Global.getAdminPath()+"/oauth/oauthResource/?repage";
	}

}