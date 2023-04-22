package com.sansuolou.adminservice;

import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Description: //TODO
 * @date: 2023/4/21 15:07
 * @Author: pm2022
 */
@Component
public class AdminHttpHeaderSecurityFilter extends HttpHeaderSecurityFilter {

    private final Log log = LogFactory.getLog(HttpHeaderSecurityFilter.class);
    private static final String HSTS_HEADER_NAME = "Strict-Transport-Security";
    private boolean hstsEnabled = true;
    private int hstsMaxAgeSeconds = 0;
    private boolean hstsIncludeSubDomains = false;
    private boolean hstsPreload = false;
    private String hstsHeaderValue;
    private static final String ANTI_CLICK_JACKING_HEADER_NAME = "X-Frame-Options";
    private boolean antiClickJackingEnabled = true;
    private AdminHttpHeaderSecurityFilter.XFrameOption antiClickJackingOption;
    private URI antiClickJackingUri;
    private String antiClickJackingHeaderValue;
    private static final String BLOCK_CONTENT_TYPE_SNIFFING_HEADER_NAME = "X-Content-Type-Options";
    private static final String BLOCK_CONTENT_TYPE_SNIFFING_HEADER_VALUE = "nosniff";
    private boolean blockContentTypeSniffingEnabled;
    private static final String XSS_PROTECTION_HEADER_NAME = "X-XSS-Protection";
    private static final String XSS_PROTECTION_HEADER_VALUE = "1; mode=block";
    private boolean xssProtectionEnabled;

    public AdminHttpHeaderSecurityFilter() {
        this.antiClickJackingOption = AdminHttpHeaderSecurityFilter.XFrameOption.DENY;
        this.blockContentTypeSniffingEnabled = true;
        this.xssProtectionEnabled = true;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        StringBuilder hstsValue = new StringBuilder("max-age=");
        hstsValue.append(this.hstsMaxAgeSeconds);
        if (this.hstsIncludeSubDomains) {
            hstsValue.append(";includeSubDomains");
        }

        if (this.hstsPreload) {
            hstsValue.append(";preload");
        }

        this.hstsHeaderValue = hstsValue.toString();
        StringBuilder cjValue = new StringBuilder(this.antiClickJackingOption.headerValue);
        if (this.antiClickJackingOption == AdminHttpHeaderSecurityFilter.XFrameOption.ALLOW_FROM) {
            cjValue.append(' ');
            cjValue.append(this.antiClickJackingUri);
        }

        this.antiClickJackingHeaderValue = cjValue.toString();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (response instanceof HttpServletResponse) {
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            if (response.isCommitted()) {
                throw new ServletException(sm.getString("httpHeaderSecurityFilter.committed"));
            }

            if (this.hstsEnabled && request.isSecure()) {
                httpResponse.setHeader("Strict-Transport-Security", this.hstsHeaderValue);
            }

            if (this.antiClickJackingEnabled) {
                httpResponse.setHeader("X-Frame-Options", this.antiClickJackingHeaderValue);
            }

            if (this.blockContentTypeSniffingEnabled) {
                httpResponse.setHeader("X-Content-Type-Options", "nosniff");
            }

            if (this.xssProtectionEnabled) {
                httpResponse.setHeader("X-XSS-Protection", "1; mode=block");
            }
        }

        chain.doFilter(request, response);
    }

    protected Log getLogger() {
        return this.log;
    }

    protected boolean isConfigProblemFatal() {
        return true;
    }

    public boolean isHstsEnabled() {
        return this.hstsEnabled;
    }

    public void setHstsEnabled(boolean hstsEnabled) {
        this.hstsEnabled = hstsEnabled;
    }

    public int getHstsMaxAgeSeconds() {
        return this.hstsMaxAgeSeconds;
    }

    public void setHstsMaxAgeSeconds(int hstsMaxAgeSeconds) {
        if (hstsMaxAgeSeconds < 0) {
            this.hstsMaxAgeSeconds = 0;
        } else {
            this.hstsMaxAgeSeconds = hstsMaxAgeSeconds;
        }

    }

    public boolean isHstsIncludeSubDomains() {
        return this.hstsIncludeSubDomains;
    }

    public void setHstsIncludeSubDomains(boolean hstsIncludeSubDomains) {
        this.hstsIncludeSubDomains = hstsIncludeSubDomains;
    }

    public boolean isHstsPreload() {
        return this.hstsPreload;
    }

    public void setHstsPreload(boolean hstsPreload) {
        this.hstsPreload = hstsPreload;
    }

    public boolean isAntiClickJackingEnabled() {
        return this.antiClickJackingEnabled;
    }

    public void setAntiClickJackingEnabled(boolean antiClickJackingEnabled) {
        this.antiClickJackingEnabled = antiClickJackingEnabled;
    }

    public String getAntiClickJackingOption() {
        return this.antiClickJackingOption.toString();
    }

    public void setAntiClickJackingOption(String antiClickJackingOption) {
        AdminHttpHeaderSecurityFilter.XFrameOption[] var2 = AdminHttpHeaderSecurityFilter.XFrameOption.values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            AdminHttpHeaderSecurityFilter.XFrameOption option = var2[var4];
            if (option.getHeaderValue().equalsIgnoreCase(antiClickJackingOption)) {
                this.antiClickJackingOption = option;
                return;
            }
        }

        throw new IllegalArgumentException(sm.getString("httpHeaderSecurityFilter.clickjack.invalid", new Object[]{antiClickJackingOption}));
    }

    public String getAntiClickJackingUri() {
        return this.antiClickJackingUri.toString();
    }

    public boolean isBlockContentTypeSniffingEnabled() {
        return this.blockContentTypeSniffingEnabled;
    }

    public void setBlockContentTypeSniffingEnabled(boolean blockContentTypeSniffingEnabled) {
        this.blockContentTypeSniffingEnabled = blockContentTypeSniffingEnabled;
    }

    public void setAntiClickJackingUri(String antiClickJackingUri) {
        URI uri;
        try {
            uri = new URI(antiClickJackingUri);
        } catch (URISyntaxException var4) {
            throw new IllegalArgumentException(var4);
        }

        this.antiClickJackingUri = uri;
    }

    public boolean isXssProtectionEnabled() {
        return this.xssProtectionEnabled;
    }

    public void setXssProtectionEnabled(boolean xssProtectionEnabled) {
        this.xssProtectionEnabled = xssProtectionEnabled;
    }

    private static enum XFrameOption {
        DENY("DENY"),
        SAME_ORIGIN("SAMEORIGIN"),
        ALLOW_FROM("ALLOW-FROM");

        private final String headerValue;

        private XFrameOption(String headerValue) {
            this.headerValue = headerValue;
        }

        public String getHeaderValue() {
            return this.headerValue;
        }
    }
}
