<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>租户信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tenant/sysTenant/">租户信息列表</a></li>
		<shiro:hasPermission name="tenant:sysTenant:edit"><li><a href="${ctx}/tenant/sysTenant/form">租户信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysTenant" action="${ctx}/tenant/sysTenant/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>机构编码：</label>
                <form:input path="orgCode" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>机构名称：</label>
				<form:input path="orgName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>域名：</label>
				<form:input path="enterpriseDomain" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>隔离模式：</label>
				<form:select path="multiTenantType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('multiTenantType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>多地登录：</label>
				<form:radiobuttons path="isMultipleLogin" items="${fns:getDictList('MultipleLogin')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>租户状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('tenantStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${sysTenant.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${sysTenant.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>租户名称：</label>
				<form:input path="tenantName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>租户手机：</label>
				<form:input path="tenantPhone" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>主库IP：</label>
				<form:input path="mysqlServerIp" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>从库IP：</label>
				<form:input path="slaveServerIp" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>主从：</label>
				<form:radiobuttons path="isMasterSlave" items="${fns:getDictList('isMasterSlave')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>机构名称</th>
				<th>域名</th>
				<th>数据隔离模式</th>
				<th>是否多地登录</th>
				<th>租户状态</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<th>租户名称</th>
				<th>租户手机</th>
				<th>主库IP</th>
				<th>开启主从</th>
				<th>从库IP</th>
				<shiro:hasPermission name="tenant:sysTenant:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysTenant">
			<tr>
				<td>
					<a href="${ctx}/tenant/sysTenant/form?id=${sysTenant.id}">
							${sysTenant.orgName}</a>
				</td>
				<td>
					${sysTenant.enterpriseDomain}
				</td>
				<td>
					${fns:getDictLabel(sysTenant.multiTenantType, 'multiTenantType', '')}
				</td>
				<td>
					${fns:getDictLabel(sysTenant.isMultipleLogin, 'MultipleLogin', '')}
				</td>
				<td>
					${fns:getDictLabel(sysTenant.status, 'tenantStatus', '')}
				</td>
				<td>
					<fmt:formatDate value="${sysTenant.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${sysTenant.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sysTenant.tenantName}
				</td>
				<td>
					${sysTenant.tenantPhone}
				</td>
				<td>
					${sysTenant.mysqlServerIp}
				</td>
				<td>
					${fns:getDictLabel(sysTenant.isMasterSlave, 'isMasterSlave', '')}
				</td>
				<td>
					${sysTenant.slaveServerIp}
				</td>
				<shiro:hasPermission name="tenant:sysTenant:edit"><td>
    				<a href="${ctx}/tenant/sysTenant/form?id=${sysTenant.id}">修改</a>
					<a href="${ctx}/tenant/sysTenant/delete?id=${sysTenant.id}" onclick="return confirmx('确认要删除该租户信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>