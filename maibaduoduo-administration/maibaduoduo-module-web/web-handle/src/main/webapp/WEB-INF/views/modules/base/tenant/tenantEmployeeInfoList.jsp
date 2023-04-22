<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>开通登录权限管理</title>
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
		<li class="active"><a href="${ctx}/base/tenant/tenantEmployeeInfo/">开通登录权限列表</a></li>
		<shiro:hasPermission name="base:tenant:tenantEmployeeInfo:edit"><li><a href="${ctx}/base/tenant/tenantEmployeeInfo/form">开通登录权限添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tenantEmployeeInfo" action="${ctx}/base/tenant/tenantEmployeeInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>租户名称：</label>
				<form:input path="tenantName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>员工名称：</label>
				<form:input path="tenantEmployeeName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户编码</th>
				<th>员工编码</th>
				<th>租户名称</th>
				<th>员工名称</th>
				<th>更新日期</th>
				<shiro:hasPermission name="base:tenant:tenantEmployeeInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tenantEmployeeInfo">
			<tr>
				<td><a href="${ctx}/base/tenant/tenantEmployeeInfo/form?id=${tenantEmployeeInfo.tenantEmployeeId}">
					${tenantEmployeeInfo.tenantId}
				</a></td>
				<td>
					${tenantEmployeeInfo.tenantEmployeeId}
				</td>
				<td>
					${tenantEmployeeInfo.tenantName}
				</td>
				<td>
					${tenantEmployeeInfo.tenantEmployeeName}
				</td>
				<td>
					<fmt:formatDate value="${tenantEmployeeInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="base:tenant:tenantEmployeeInfo:edit"><td>
    				<a href="${ctx}/base/tenant/tenantEmployeeInfo/form?id=${tenantEmployeeInfo.tenantEmployeeId}">开通</a>
					<a href="${ctx}/base/tenant/tenantEmployeeInfo/delete?id=${tenantEmployeeInfo.tenantEmployeeId}" onclick="return confirmx('确认要删除该开通登录权限吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>