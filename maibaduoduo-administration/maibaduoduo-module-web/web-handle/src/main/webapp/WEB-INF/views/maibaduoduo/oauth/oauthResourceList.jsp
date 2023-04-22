<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资源操作成功管理</title>
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
		<li class="active"><a href="${ctx}/oauth/oauthResource/">资源操作成功列表</a></li>
		<shiro:hasPermission name="oauth:oauthResource:edit"><li><a href="${ctx}/oauth/oauthResource/form">资源操作成功添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oauthResource" action="${ctx}/oauth/oauthResource/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>路由前缀：</label>
				<form:input path="prefix" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>资源路径：</label>
				<form:input path="oauthResourcePath" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>服务id：</label>
				<form:input path="oauthServiceId" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>角色编号：</label>
				<form:select path="roleId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oauthResource.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oauthResource.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>资源id</th>
				<th>路由前缀</th>
				<th>资源路径</th>
				<th>服务id</th>
				<th>创建者</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="oauth:oauthResource:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oauthResource">
			<tr>
				<td><a href="${ctx}/oauth/oauthResource/form?id=${oauthResource.id}">
					${oauthResource.oauthResourceId}
				</a></td>
				<td>
					${oauthResource.prefix}
				</td>
				<td>
					${oauthResource.oauthResourcePath}
				</td>
				<td>
					${oauthResource.oauthServiceId}
				</td>
				<td>
					${oauthResource.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${oauthResource.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${oauthResource.remarks}
				</td>
				<shiro:hasPermission name="oauth:oauthResource:edit"><td>
    				<a href="${ctx}/oauth/oauthResource/form?id=${oauthResource.id}">修改</a>
					<a href="${ctx}/oauth/oauthResource/delete?id=${oauthResource.id}" onclick="return confirmx('确认要删除该资源操作成功吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>