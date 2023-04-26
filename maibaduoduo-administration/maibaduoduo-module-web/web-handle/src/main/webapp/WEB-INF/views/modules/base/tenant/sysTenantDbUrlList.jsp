<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
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
		function tenantInit(){
			showTip('正在进行租户数据源初始化，请稍等...','info',5000,500);
			$.ajax({
				type: "get",
				url: "${ctx}/base/tenant/sysTenantDbUrl/init",
				dataType: 'json',
				success: function(data, textStatus) {
					if (data.code==0) {
						console.log("租户数据源初始化成功.");
					} else {
						console.log("租户数据源初始化失败.");
					}
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/base/tenant/sysTenantDbUrl/">单表列表</a></li>
		<shiro:hasPermission name="base:tenant:sysTenantDbUrl:edit"><li><a href="${ctx}/base/tenant/sysTenantDbUrl/form">单表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysTenantDbUrl" action="${ctx}/base/tenant/sysTenantDbUrl/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>租户编码：</label>
				<form:input path="tenantId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>员工：</label>
				<form:input path="employee" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>数据源：</label>
				<form:input path="dbUrl" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>隔离模式：</label>
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>创建人：</label>
				<form:input path="createBy.id" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>创建日期：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${sysTenantDbUrl.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input onclick="tenantInit()" class="btn btn-primary" type="button" value="数据源初始化"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>租户编码</th>
				<th>员工</th>
				<th>数据源</th>
				<th>初始化</th>
				<th>隔离模式</th>
				<th>创建日期</th>
				<shiro:hasPermission name="base:tenant:sysTenantDbUrl:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysTenantDbUrl">
			<tr>
				<td><a href="${ctx}/base/tenant/sysTenantDbUrl/form?id=${sysTenantDbUrl.id}">
					${sysTenantDbUrl.tenantId}
				</a></td>
				<td>
					${sysTenantDbUrl.employee}
				</td>
				<td>
					${sysTenantDbUrl.dbUrl}
				</td>
				<td>
					${fns:getDictLabel(sysTenantDbUrl.initData, 'initData', '')}
				</td>
				<td>
					${sysTenantDbUrl.remarks}
				</td>
				<td>
					<fmt:formatDate value="${sysTenantDbUrl.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="base:tenant:sysTenantDbUrl:edit"><td>
					<c:if test="${sysTenantDbUrl.initData ne '1'}">
    				<a href="${ctx}/base/tenant/sysTenantDbUrl/form?id=${sysTenantDbUrl.id}">初始化</a>
					</c:if>
					<a href="${ctx}/base/tenant/sysTenantDbUrl/delete?id=${sysTenantDbUrl.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>