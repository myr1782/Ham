<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<form id="editForm" class="form-horizontal" method="post" role="form">
		<!-- 隐藏域 -->
		<!-- 主键值 -->
		<s:hidden name="id" />
				<div class="form-group">
			<label class="col-sm-2 control-label">项目名</label>
			<div class="col-sm-10">
				<s:textfield cssClass="form-control ck-required" name="projectName" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">项目描述</label>
			<div class="col-sm-10">
				<s:textfield cssClass="form-control ck-required" name="projectDesc" />
			</div>
		</div>
				
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="button" onclick="save()" class="btn btn-primary btn-lg">保存</button>
			</div>
		</div>
	</form>
