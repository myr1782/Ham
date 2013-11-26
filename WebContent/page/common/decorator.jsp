<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/sitemesh-decorator.tld" prefix="decorator"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><decorator:title></decorator:title></title>
<jsp:include page="../common/inc.jsp"></jsp:include>
<decorator:head></decorator:head>
<script type="text/javascript">
	$(function() {
		// 确认对话框初始化
		$("#dialog-confirm").dialog(
				{
					autoOpen : false,
					resizable : false,
					height : 170,
					width : 400,
					modal : true,
					buttons : {
						"确定" : function() {
							// 执行删除
							$("form").attr("action",
									$("#bizCode").val() + "!doDel.action")
									.submit();
							$(this).dialog("close");
						},
						"取消" : function() {
							$(this).dialog("close");
						}
					}
				});

		// 编辑对话框初始化
		$("#dialog").dialog({
			autoOpen : false,
			height : 400,
			width : 700,
		});

		$("table>thead>tr>th.sortable")
				.each(
						function() {
							var colName = $(this).attr("id");
							var htmlContent = "<span class='title-sortable' onclick='sort(this,\""
									+ colName
									+ "\")'>"
									+ $(this).text()
									+ "</span>";
							var sortItem = '<s:property value="sortItem"/>';
							var sortType = '<s:property value="sortType"/>';
							if (sortItem == colName) {
								var sortIconCss = "";
								if (sortType == "desc") {
									sortIconCss = "glyphicon-sort-by-attributes-alt";
								} else {
									sortIconCss = "glyphicon-sort-by-attributes";
								}
								htmlContent += "<span id='sortSpan' class='glyphicon " + sortIconCss + " title-sortable'></span>";
							}
							$(this).html(htmlContent);
						});

	});

	// 新增或编辑
	function forwardEdit(id) {
		// 新增的场合，不传递参数ID
		var params = "";
		if (id != undefined) {
			params = "id=" + id;
		}
		$.ajax({
			url : $("#bizCode").val() + "!doInitEdit.action",
			data : params,
			type : "POST",
			dataType : 'html',
			success : function(resHtml) {
				$("#dialog").html(resHtml).dialog("open");
			}
		});
	}

	// 弹出删除确认对话框
	function confirm(id) {
		$('#editKey').val(id);
		$("#dialog-confirm").dialog("open");
	}

	// 检索
	function search() {
		// 页数跳转到第一页
		$('#currentPage').val(1);
		// 清空排序项
		$('#sortItem').val("");
		$('#sortType').val("");
		$("#mainForm").attr("action", $("#bizCode").val() + "!doSearch.action")
				.submit();
	}

	// 保存
	function save() {
		if (validateForm("editForm")) {
			$("#editForm").attr("action",
					$("#bizCode").val() + "!doSave.action").submit();
		}
	}

	// 跳转页面
	function gotoPage(pageNum) {
		$('#currentPage').val(pageNum);
		$("#mainForm").attr("action",
				$("#bizCode").val() + "!doGotoPage.action").submit();
	}

	// 跳转到上一页
	function gotoPrePage(pageNum) {
		gotoPage(parseInt(pageNum) - 1);
	}

	// 跳转到下一页
	function gotoNextPage(pageNum) {
		gotoPage(parseInt(pageNum) + 1);
	}

	// 排序
	function sort(element, sortItem) {
		// 页数跳转到第一页
		$('#currentPage').val(1);
		// 设置当前排序项
		$('#sortItem').val(sortItem);
		if ($("#sortSpan").hasClass("glyphicon-sort-by-attributes")) {
			$('#sortType').val("desc");
		} else {
			$('#sortType').val("asc");
		}
		$("#mainForm").attr("action", $("#bizCode").val() + "!doSort.action")
				.submit();
	}

	// 隐藏菜单
	function hideMenu() {
		$("#menu").css("display", "none");
		$("#nav-bar").removeClass("col-xs-10");
		$("#nav-bar").removeClass("col-xs-12");
	}
</script>
<style type="text/css">
/* 重新定义最大宽度，使屏幕左右不出现较大空隙 */
/* TODO 提取到共通，其他分辨率下可能会出现问题 */
@media ( min-width : 1200px) {
	.container {
		max-width: 100% !important;
	}
}
</style>
</head>
<body>
	<!-- 功能Code -->
	<s:hidden id="bizCode" name="bizCode" />
	<!-- 确认对话框 -->
	<div id="dialog-confirm" title="确认">
		<p>
			<span class="ui-icon ui-icon-alert"
				style="float: left; margin: 0 7px 20px 0;"></span>确定删除该记录吗？
		</p>
	</div>
	<!-- 编辑对话框 -->
	<div id="dialog" title="编辑	" style="width: 200px; height: 200px;"></div>

	<!-- navbar start -->
	<div class="navbar navbar-inverse">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">APM</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#about">About</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">欢迎您，Admin</a></li>
			</ul>
		</div>
	</div>
	<!-- navbar end -->

	<!-- main start -->
	<div class="container">
		<div class="row">
			<div class="col-xs-2" id="menu">
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="#">Home</a></li>
					<s:iterator value="#session.menuList" status="st">
						<li><a href="<s:property value='linkUrl' />"><s:property value="menuName" /></a></li>
					</s:iterator>
				</ul>
				<%-- <span class="glyphicon glyphicon-circle-arrow-left" onclick="hideMenu();"></span> --%>
			</div>
			<div id="nav-bar" class="col-xs-10">
				<div class="row">
					<ol class="breadcrumb">
						<li><a href="#"><span class="glyphicon glyphicon-home"></span>
								Home</a></li>
						<li><a href="#"><span class="glyphicon glyphicon-wrench"></span>
								系统管理</a></li>
						<li class="active">部门管理</li>
					</ol>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<decorator:body></decorator:body>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- main end -->
</body>
</html>