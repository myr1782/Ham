<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>登录</title>
<jsp:include page="../common/inc.jsp"></jsp:include>
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	font-size: 16px;
	height: auto;
	padding: 10px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="text"] {
	margin-bottom: -1px;
	border-bottom-left-radius: 0;
	border-bottom-right-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<!-- TODO 提取到共通 -->
			<s:fielderror />
			<s:actionerror />
			<form action="login!doLogin.action" class="form-signin" method="post">
				<h2 class="form-signin-heading">Please sign in</h2>
				<input name="username" class="form-control" id="username" placeholder="邮箱/账号" value="apm"> <input name="password"
					type="password" class="form-control" placeholder="请输入密码" value="123456"> <input type="checkbox" value="remember-me">
				下次自动登录 </label>
				<button class="btn btn-lg btn-primary btn-block" type="submit"><span class="glyphicon glyphicon-log-in"></span> 登录</button>
			</form>
		</div>
	</div>
</body>
</html>