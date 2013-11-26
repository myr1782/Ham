<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.opensymphony.xwork2.util.ValueStack"%>

<!-- 分页的隐藏域 -->
<!-- 当前所在页 -->
<s:hidden id="currentPage" name="currentPage" />
<!-- 排序项 -->
<s:hidden id="sortItem" name="sortItem" />
<s:hidden id="sortType" name="sortType" />
				
<ul class="pagination">
	<s:if test="currentPage == 1">
		<li class="disabled"><a href="#">«</a></li>
	</s:if>
	<s:else>
		<li><a href="javascript:gotoPrePage('<s:property value="currentPage"/>')">«</a></li>
	</s:else>
	<%
		ValueStack vs = (ValueStack) request.getAttribute("struts.valueStack");
		Integer startPage = (Integer) vs.findValue("startPage");
		Integer endPage = (Integer) vs.findValue("endPage");
		Integer currentPage = (Integer) vs.findValue("currentPage");
		for (int i = startPage; i <= endPage; i++) {
			if (i == currentPage) {
				out.println("<li class='active'><a href='#'>" + i + "</a></li>");
			} else {
				out.println("<li><a href='javascript:gotoPage(" + i + ")'>" + i + "</a></li>");
			}
		}
	%>
	<s:if test="currentPage == pageNumber">
		<li class="disabled"><a href="#">»</a></li>
	</s:if>
	<s:else>
		<li><a href="javascript:gotoNextPage('<s:property value="currentPage"/>')">»</a></li>
	</s:else>
</ul>