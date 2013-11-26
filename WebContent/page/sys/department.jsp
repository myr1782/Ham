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
		<div class="row">
			<div class="col-xs-6">
				<label class="col-xs-2 control-label search-label">部门名称</label>
				<div class="col-xs-10 text-left">
					<s:textfield name="departmentName"
						cssClass="form-control ck-required" />
				</div>
			</div>
			<div class="col-xs-6">
				<label class="col-xs-2 control-label search-label">部门描述</label>
				<div class="col-xs-10 text-left">
					<s:textfield name="departmentDesc" cssClass="form-control" />
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
		<m:gridCol title="部门名称" field="departmentName" sortable="true"></m:gridCol>
		<m:gridCol title="部门描述" field="departmentDesc"></m:gridCol>
	</m:grid>
	
	</form>			
</body>
</html>
