<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>自动生成</title>
<script type="text/javascript">
	function read() {
		$("#mainForm").attr("action", $("#bizCode").val() + "!doRead.action")
				.submit();
	}
	function gen() {
		$("#mainForm").attr("action", $("#bizCode").val() + "!doGen.action")
				.submit();
	}
</script>
</head>
<body>
	<form id="mainForm" class="form-horizontal" method="post">
		<div class="container">
			<div class="row row-space">
				<div class="col-md-6">
					<label class="col-md-2 control-label search-label">功能编号</label>
					<div class="col-md-10 text-left">
						<s:textfield name="genBizCode" cssClass="form-control ck-required" />
					</div>
				</div>
				<div class="col-md-6">
					<label class="col-md-2 control-label search-label">功能名称</label>
					<div class="col-md-10 text-left">
						<s:textfield name="genBizName" cssClass="form-control" />
					</div>
				</div>
			</div>
			<div class="row row-space">
				<div class="col-md-6">
					<label class="col-md-2 control-label search-label">表名</label>
					<div class="col-md-10 text-left">
						<s:textfield name="tableName" cssClass="form-control" />
					</div>
				</div>
			</div>
			<div class="row row-space">
				<div class="col-md-6 text-left">
					<button type="button" class="btn btn-primary btn-lg" onclick="read()">从数据库读入表结构</button>
					<button type="button" class="btn btn-primary btn-lg" onclick="gen()">生成代码</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
