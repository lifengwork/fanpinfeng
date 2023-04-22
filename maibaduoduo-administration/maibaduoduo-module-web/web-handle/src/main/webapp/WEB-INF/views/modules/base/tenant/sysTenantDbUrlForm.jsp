<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/base/tenant/sysTenantDbUrl/">单表列表</a></li>
		<li class="active"><a href="${ctx}/base/tenant/sysTenantDbUrl/form?id=${sysTenantDbUrl.id}">单表<shiro:hasPermission name="base:tenant:sysTenantDbUrl:edit">${not empty sysTenantDbUrl.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="base:tenant:sysTenantDbUrl:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	 <form:form id="inputForm" modelAttribute="sysTenantDbUrl" action="${ctx}/base/tenant/sysTenantDbUrl/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">租户编码：</label>
			<div class="controls">
				<form:input path="tenantId" htmlEscape="false" maxlength="32" class="input-xlarge required" disabled="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">员工编号：</label>
			<div class="controls">
				<form:input path="employee" htmlEscape="false" maxlength="255" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据源：</label>
			<div class="controls">
				<form:textarea path="dbUrl" htmlEscape="false" rows="10" maxlength="255" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">隔离模式：</label>
			<div class="controls">
				<form:input path="remarks" htmlEscape="false"  maxlength="255" class="input-xxlarge " disabled="true"/>
			</div>
		</div>
		 <c:if test="${sysTenantDbUrl.initData ne '1'}">
			 <div class="control-group">
				 <label class="control-label">数据源初始化：</label>
				 <div class="controls">
					 <form:select path="initData" class="input-xlarge ">
						 <form:option value="" label=""/>
						 <form:options items="${fns:getDictList('initData')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					 </form:select>
				 </div>
			 </div>
		 </c:if>
		<div class="form-actions">
			<c:if test="${sysTenantDbUrl.initData ne '1'}">
			    <shiro:hasPermission name="base:tenant:sysTenantDbUrl:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>