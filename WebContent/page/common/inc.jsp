<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href="<%=basePath%>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>/css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
<link href="<%=basePath%>/css/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" media="screen">
<link href="<%=basePath%>/css/core.css" rel="stylesheet" media="screen">

<script type="text/javascript" src="<%=basePath%>/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/core.js"></script>
<!-- TODO 不使用则取消 -->
<script type="text/javascript" src="<%=basePath%>/js/highcharts.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/exporting.js"></script>

