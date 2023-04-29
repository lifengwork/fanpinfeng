/**
 * Copyright (c) 2019-2023 SAAS开源 All rights reserved.
 *
 * SAAS系统设计研发交流
 *
 * https://www.maibaduoduo.com
 */
package com.maibaduoduo.configuration.utils.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * XSS过滤
 *
 * @author Mark lifengwork@yeah.net
 */
public class XssFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		XssHttpServletRequestWrapper tenantRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
		chain.doFilter(tenantRequest, response);
	}

	@Override
	public void destroy() {
	}

}
