package com.maibaduoduo.database.datasource;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.google.common.collect.Lists;
import com.maibaduoduo.database.datasource.tenant.MyTenantHandler;
import com.maibaduoduo.database.datasource.tenant.MyTenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;

/**
 * mybatis-plus配置,添加租户编码
 */
@Configuration
public class MybatisPlusConfig {

    @ConfigurationProperties(prefix = "maibaduoduo.table.filter")
    private FilterTableProperties filterTableProperties() {
        return new FilterTableProperties();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        TenantSqlParser tenantSqlParser = new MyTenantSqlParser()
                .setTenantHandler(new MyTenantHandler() {
                    private final List<String> TENANT_TABLES = filterTableProperties().getServerList();
                    @Override
                    public boolean doAdminFilter() {
                        return ObjectUtil.isNull(getTenantId(true));
                    }

                    @Override
                    public Expression getTenantId(boolean select) {
                        TenantDataSource.AuthorizationInfo authorizationInfo = TenantDataSource.instanceSingletonObject()
                                .tenant(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());

                        if (Objects.isNull(authorizationInfo)||StringUtils.isEmpty(authorizationInfo.getTenantId())
                                ||"0".equals(authorizationInfo.getTenantId())) {
                            return null;
                        }
                        return new StringValue(authorizationInfo.getTenantId());
                    }
                    @Override
                    public String getTenantIdColumn() {
                        return "tenant_id";
                    }
                    @Override
                    public boolean doTableFilter(String tableName) {
                        return TENANT_TABLES.stream().noneMatch((e) -> e.equalsIgnoreCase(tableName));
                    }
                });
               paginationInterceptor.setSqlParserList(Lists.newArrayList(tenantSqlParser));
        return paginationInterceptor;
    }
}
