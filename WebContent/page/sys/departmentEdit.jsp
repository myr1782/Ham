<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<form id="editForm" class="form-horizontal" method="post" role="form">
		<!-- 隐藏域 -->
		<!-- 主键值 -->
		<s:hidden name="id" />
				<div class="form-group">
			<label class="col-md-2 control-label">部门名称</label>
			<div class="col-md-10">
				<s:textfield cssClass="form-control" name="DepartmentName" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">部门描述</label>
			<div class="col-md-10">
				<s:textfield cssClass="form-control" name="DepartmentDesc" />
			</div>
		</div>
				
		<div class="form-group">
			<div class="col-md-offset-2 col-md-10">
				<button type="button" onclick="save()" class="btn btn-primary btn-lg">保存</button>
			</div>
		</div>
	</form>
