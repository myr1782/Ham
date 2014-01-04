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
			<#list itemList as item>
			<#if item[3] == "1">
			<div class="col-xs-6 row-space">
				<label class="col-xs-2 control-label search-label">${item[2]}</label>
				<div class="col-xs-10 text-left">
					<s:textfield name="${item[0]}" cssClass="form-control" />
				</div>
			</div>
			</#if>
			</#list>
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
		<#list itemList as item>
		<#if item[4] == "1">
			<m:gridCol title="${item[2]}" field="${item[0]}" sortable="true"></m:gridCol>
		<#else>
			<m:gridCol title="${item[2]}" field="${item[0]}"></m:gridCol>
		</#if>
		</#list>	
	</m:grid> 
	
	</form>			
</body>
</html>
