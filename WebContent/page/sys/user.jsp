<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/custom.tld" prefix="m"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>部门管理</title>
</head>
<body>
	<form id="mainForm" class="form-horizontal" method="post">
		<!-- 删除时使用 -->
		<s:hidden id="editKey" name="id" />
		<div class="row">
						<div class="col-xs-6 row-space">
				<label class="col-xs-2 control-label search-label">用户名</label>
				<div class="col-xs-10 text-left">
					<s:textfield name="Username" cssClass="form-control" />
				</div>
			</div>
			<div class="col-xs-6 row-space">
				<label class="col-xs-2 control-label search-label">电话</label>
				<div class="col-xs-10 text-left">
					<s:textfield name="Telphone" cssClass="form-control" />
				</div>
			</div>
		</div>
		<div class="row row-space">
			<div class="col-xs-6 text-left">
				<button onclick="search()" class="btn btn-primary btn-md">
					<span class="glyphicon glyphicon-search"></span> 检索
				</button>
			</div>
			<div class="col-xs-6 text-right">
				<button type="button" onclick="forwardEdit()"
					class="btn btn-primary btn-md">
					<span class="glyphicon glyphicon-plus"></span> 添加
				</button>
			</div>
		</div>

	<m:grid dataSource="entityList">
			<m:gridCol title="用户名" field="Username" sortable="true"></m:gridCol>
			<m:gridCol title="密码" field="Password"></m:gridCol>
			<m:gridCol title="电话" field="Telphone"></m:gridCol>
			<m:gridCol title="电子邮件" field="Email"></m:gridCol>
	</m:grid> 
	
	</form>			
</body>
</html>
