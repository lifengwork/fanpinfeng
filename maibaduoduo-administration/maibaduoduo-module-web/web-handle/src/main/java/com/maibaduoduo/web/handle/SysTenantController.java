package com.maibaduoduo.web.handle;

import com.maibaduoduo.base.base.BaseController;
import com.maibaduoduo.base.tenant.entity.SysTenant;
import com.maibaduoduo.base.tenant.service.SysTenantService;
import com.maibaduoduo.common.entity.Page;
import com.maibaduoduo.common.redis.RedisService;
import com.maibaduoduo.common.utils.*;
import com.maibaduoduo.init.BaseCreate;
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
 * 租户信息Controller
 *
 * @author maibaduoduo
 * @version 2021-09-01
 */
@Controller
@RequestMapping(value = "${adminPath}/tenant/sysTenant")
public class SysTenantController extends BaseController {
    @Autowired
    private SysTenantService sysTenantService;
    @Autowired
    private BaseCreate baseCreate;


    private SnowflakeUtil snowflakeUtil = new SnowflakeUtil();

    @ModelAttribute
    public SysTenant get(@RequestParam(required = false) String id) {
        SysTenant entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = sysTenantService.get(id);
        }
        if (entity == null) {
            entity = new SysTenant();
        }
        return entity;
    }

    @RequiresPermissions("tenant:sysTenant:view")
    @RequestMapping(value = {"list", ""})
    public String list(SysTenant sysTenant, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<SysTenant> page = sysTenantService.findPage(new Page<SysTenant>(request, response), sysTenant);
        model.addAttribute("page", page);
        return "maibaduoduo/tenant/sysTenantList";
    }

    @RequiresPermissions("tenant:sysTenant:view")
    @RequestMapping(value = "form")
    public String form(SysTenant sysTenant, Model model) {
        model.addAttribute("sysTenant", sysTenant);
        return "maibaduoduo/tenant/sysTenantForm";
    }
	
    /**
	*
	* 租户支持读写分离时注意:
	        底层数据库服务器主库和从库要在不同的服务器。
			支持一主一从。
			选择非独立数据源模式时不创建数据库。
			服务器的IP地址和端口不能为空，如果勾选支持读写分离从库服务器ip不能为空。
	*
    **/	
    @RequiresPermissions("tenant:sysTenant:edit")
    @RequestMapping(value = "save")
    public String save(SysTenant sysTenant, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, sysTenant)) {
            return form(sysTenant, model);
        }
        if(!StringUtils.isNotEmpty(sysTenant.getId())){
            sysTenant.setIsNewRecord(true);
        }
        sysTenant.setId(String.valueOf(snowflakeUtil.nextId()));
        sysTenantService.save(sysTenant);
        if (sysTenant.getIsNewRecord()) {
            if (sysTenant.getMultiTenantType().equals("datasource")) {
                baseCreate.create(sysTenant);
            }
        }
        addMessage(redirectAttributes, "保存租户信息成功");
        return "redirect:" + Global.getAdminPath() + "/tenant/sysTenant/?repage";
    }

    @RequiresPermissions("tenant:sysTenant:edit")
    @RequestMapping(value = "delete")
    public String delete(SysTenant sysTenant, RedirectAttributes redirectAttributes) {
        sysTenantService.delete(sysTenant);
        addMessage(redirectAttributes, "删除租户信息成功");
        return "redirect:" + Global.getAdminPath() + "/tenant/sysTenant/?repage";
    }

    @RequiresPermissions("tenant:sysTenant:edit")
    @RequestMapping(value = "batchDelete")
    public String batchDelete(String id, RedirectAttributes redirectAttributes) {
        sysTenantService.batchDelete(id.split(","));
        addMessage(redirectAttributes, "删除租户信息成功");
        return "redirect:" + Global.getAdminPath() + "/tenant/sysTenant/?repage";
    }

    @RequiresPermissions("tenant:sysTenant:edit")
    @RequestMapping(value = "batchUpdate")
    public String batchUpdate(String ids, RedirectAttributes redirectAttributes) {
        List<SysTenant> sysTenantList = new ArrayList<SysTenant>();
        SysTenant sysTenant = null;
        for (String id : ids.split(",")) {
            sysTenant = new SysTenant();
            sysTenant.setId(id);
            sysTenantList.add(sysTenant);
        }
        sysTenantService.batchUpdate(sysTenant);
        addMessage(redirectAttributes, "保存租户信息成功");
        return "redirect:" + Global.getAdminPath() + "/tenant/sysTenant/?repage";
    }

}