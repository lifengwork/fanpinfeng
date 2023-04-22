package com.maibaduoduo.web.handle;

import com.maibaduoduo.common.entity.Page;
import com.maibaduoduo.common.sys.entity.Dict;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "${adminPath}/index")
public class IndexController {

    @RequiresPermissions("sys:index:view")
    @RequestMapping(value = {"list", ""})
    public String list(Dict dict, HttpServletRequest request, HttpServletResponse response, Model model) {
        return "maibaduoduo/index/index";
    }

}
