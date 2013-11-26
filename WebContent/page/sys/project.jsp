<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>部门管理</title>
</head>
<body>
	<div class="container">
		<form id="mainForm" class="form-horizontal" method="post">
			<!-- 隐藏域 -->
			<!-- 编辑行的主键值 -->
			<s:hidden id="editKey" name="id" />
			<div class="row">
								<div class="col-md-6">
					<label class="col-md-2 control-label search-label">项目名</label>
					<div class="col-md-10 text-left">
						<s:textfield name="projectName" cssClass="form-control" />
					</div>
				</div>
				<div class="col-md-6">
					<label class="col-md-2 control-label search-label">项目描述</label>
					<div class="col-md-10 text-left">
						<s:textfield name="projectDesc" cssClass="form-control" />
					</div>
				</div>
			</div>
			<div class="row row-space">
				<div class="col-md-6 text-left">
					<button onclick="search()" class="btn btn-primary btn-md">
						<span class="glyphicon glyphicon-search"></span> 检索
					</button>
				</div>
				<div class="col-md-6 text-right">
					<button type="button" onclick="forwardEdit()" class="btn btn-primary btn-md">
						<span class="glyphicon glyphicon-plus"></span> 添加
					</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>No</th>
								<th id="projectName" class="sortable">项目名</th>
								<th id="projectDesc" class="sortable">项目描述</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="entityList" status="st">
								<tr>
									<td><s:property value="#st.count" /></td>
									<div class="col-md-6">
										<td><s:property value="projectName" /></td>
									</div>
									<div class="col-md-6">
										<td><s:property value="projectDesc" /></td>
									</div>
									<td><button type="button" onclick="forwardEdit(<s:property value="id" />)" class="btn btn-primary btn-sm">
											<span class="glyphicon glyphicon-edit"></span> 编辑
										</button>
										<button type="button" onclick="confirm(<s:property value="id" />)" class="btn btn-primary btn-sm">
											<span class="glyphicon glyphicon-remove"></span> 删除
										</button>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row text-center">
				<!-- 分页Bar -->
				<jsp:include page="../common/pagination.jsp"></jsp:include>
			</div>
		</form>
	</div>
</body>
</html>
