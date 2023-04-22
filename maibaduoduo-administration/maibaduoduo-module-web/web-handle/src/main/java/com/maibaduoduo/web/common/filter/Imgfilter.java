package com.maibaduoduo.web.common.filter;

import com.maibaduoduo.common.utils.DateUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2019/10/25 0025.
 */
public class Imgfilter implements Filter {
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    /**
     * @param servletRequest
     * @param servletResponse
     * @param filterChain:
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String cache_period = filterConfig.getInitParameter("cachepPeriod");
        response.setHeader("Accept-Ranges","bytes");
        response.setHeader("cache-control","max-age="+cache_period+",must-revalidate");
        response.setHeader("Content-Type",request.getContentType());
        response.setHeader("Connection","keep-alive");
        response.setHeader("Date", DateUtils.getDate());
        response.setHeader("Expires",DateUtils.getDateFormatYMDHMS(new Date(Long.sum(new Date().getTime(),Long.valueOf(cache_period)*1000))));
        response.setHeader("Content-Length",String.valueOf(request.getContentLength()));
        filterChain.doFilter(request,response);
    }
    @Override
    public void destroy() {
         this.filterConfig = null;
    }
}
