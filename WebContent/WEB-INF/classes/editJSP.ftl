<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<form id="editForm" class="form-horizontal" method="post" role="form">
		<!-- 隐藏域 -->
		<!-- 主键值 -->
		<s:hidden name="id" />
		<#list itemList as item>
		<div class="form-group">
			<label class="col-md-2 control-label">${item[2]}</label>
			<div class="col-md-10">
				<s:textfield cssClass="form-control" name="${item[0]}" />
			</div>
		</div>
		</#list>
				
		<div class="form-group">
			<div class="col-md-offset-2 col-md-10">
				<button type="button" onclick="save()" class="btn btn-primary btn-lg">保存</button>
			</div>
		</div>
	</form>
