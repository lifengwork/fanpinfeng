<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>租户信息管理</title>
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
		<li><a href="${ctx}/tenant/sysTenant/">租户信息列表</a></li>
		<li class="active"><a href="${ctx}/tenant/sysTenant/form?id=${sysTenant.id}">租户信息<shiro:hasPermission name="tenant:sysTenant:edit">${not empty sysTenant.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="tenant:sysTenant:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysTenant" action="${ctx}/tenant/sysTenant/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">机构编码：</label>
			<div class="controls">
				<sys:treeselect id="orgCode" name="orgCode" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}"
								title="公司" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构名称：</label>
			<div class="controls">
				<form:input path="orgName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">logo地址：</label>
			<div class="controls">
				<form:input path="logo" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业简介：</label>
			<div class="controls">
				<form:input path="enterpriseDescribe" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">域名：</label>
			<div class="controls">
				<form:input path="enterpriseDomain" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据隔离模式：</label>
			<div class="controls">
				<form:select path="multiTenantType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('multiTenantType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">责任人：</label>
			<div class="controls">
				<form:input path="duty" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否多地登录：</label>
			<div class="controls">
				<form:radiobuttons path="isMultipleLogin" items="${fns:getDictList('MultipleLogin')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">租户状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('tenantStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">租户名称：</label>
			<div class="controls">
				<form:input path="tenantName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">租户密码：</label>
			<div class="controls">
				<form:input path="tenantPassword" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">租户手机：</label>
			<div class="controls">
				<form:input path="tenantPhone" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据库IP：</label>
			<div class="controls">
				<form:input path="mysqlServerIp" htmlEscape="false" maxlength="15" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据库端口：</label>
			<div class="controls">
				<form:input path="mysqlPort" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据库密码：</label>
			<div class="controls">
				<form:input path="mysqlPassword" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据库用户名：</label>
			<div class="controls">
				<form:input path="msyqlUserName" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据库名称：</label>
			<div class="controls">
				<form:input path="mysqlDbName" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开启主从：</label>
			<div class="controls">
				<form:radiobuttons path="isMasterSlave" items="${fns:getDictList('isMasterSlave')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">从库服务器：</label>
			<div class="controls">
				<form:input path="slaveServerIp" htmlEscape="false" maxlength="15" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="tenant:sysTenant:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>