package cn.sjcloud.apm.core.taglib;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import cn.sjcloud.apm.core.util.StringUtil;

import com.opensymphony.xwork2.util.ValueStack;

public class GridTag extends TagSupport {

	private static final long serialVersionUID = -7781052981352484129L;

	private String title;

	private String dataSource;

	@Override
	public int doStartTag() throws JspException {
		pageContext.getRequest().setAttribute("itemMap", new HashMap<String, List<String>>());
		StringBuilder sb = new StringBuilder();
		try {
			JspWriter jw = pageContext.getOut();
			sb.append("<div class='row'>");
			sb.append("<div class='col-md-12'>");
			sb.append("<table class='table table-striped table-bordered table-condensed'>");
			jw.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int doEndTag() throws JspException {
		StringBuilder sb = new StringBuilder();
		Map<String, List<String>> itemMap = (Map<String, List<String>>) pageContext.getRequest()
				.getAttribute("itemMap");
		// 标题行
		List<String> titleList = itemMap.get("title");
		List<String> sortableList = itemMap.get("sortable");
		List<String> fieldList = itemMap.get("field");

		sb.append("<thead><tr>");
		for (int i = 0; i < titleList.size(); i++) {
			if (!StringUtils.isEmpty(sortableList.get(i)) && "true".equals(sortableList.get(i))) {
				sb.append("<th id='" + fieldList.get(i) + "' class='sortable'>");
			} else {
				sb.append("<th>");
			}
			sb.append(titleList.get(i));

			sb.append("</th>");
		}
		sb.append("<th>操作</th>");
		sb.append("</tr></thead>");
		// 数据行
		ValueStack vs = (ValueStack) pageContext.getRequest().getAttribute("struts.valueStack");
		List<Object> dataList = (List<Object>) vs.findValue(dataSource);
		try {
			sb.append("<tbody>");
			for (Object dataRow : dataList) {
				sb.append("<tr>");
				for (String field : fieldList) {
					sb.append("<td>");
					Method method = dataRow.getClass().getDeclaredMethod(makeupGetMethodName(field));
					sb.append(method.invoke(dataRow));
					sb.append("</td>");
				}
				// 编辑和删除按钮
				sb.append("<td>");

				Method method = dataRow.getClass().getDeclaredMethod(makeupGetMethodName("id"));
				String id = method.invoke(dataRow).toString();
				sb.append("<button type='button' onclick='forwardEdit(");
				sb.append(id);
				sb.append(")' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-edit'></span> 编辑</button>&nbsp;");
				sb.append("<button type='button' onclick='confirm(");
				sb.append(id);
				sb.append(")' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-remove'></span> 删除</button>");
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</tbody></table>");

			// 分页
			// 隐藏域
			sb.append("<div class='row text-center'>");
			
			sb.append("<input type='hidden' id='currentPage' name='currentPage' value='" + vs.findString("currentPage") + "'>");
			sb.append("<input type='hidden' id='sortItem' name='sortItem' value='" + vs.findString("sortItem") + "'>");
			sb.append("<input type='hidden' id='sortType' name='sortType' value='" + vs.findString("sortType") + "'>");

			Integer startPage = (Integer) vs.findValue("startPage");
			Integer endPage = (Integer) vs.findValue("endPage");
			Integer currentPage = (Integer) vs.findValue("currentPage");
			Long pageNumber = (Long) vs.findValue("pageNumber");

			sb.append("<ul class='pagination'>");
			if (currentPage == 1) {
				sb.append("<li class='disabled'><a href='#'>«</a></li>");
			} else {
				sb.append("<li><a href='javascript:gotoPrePage(\"" + currentPage + "\")'>«</a></li>");
			}
			for (int i = startPage; i <= endPage; i++) {
				if (i == currentPage) {
					sb.append("<li class='active'><a href='#'>" + i + "</a></li>");
				} else {
					sb.append("<li><a href='javascript:gotoPage(" + i + ")'>" + i + "</a></li>");
				}
			}

			if (currentPage == pageNumber.intValue()) {
				sb.append("<li class='disabled'><a href='#'>»</a></li>");
			} else {
				sb.append("<li><a href='javascript:gotoNextPage(\"" + currentPage + "\")'>»</a></li>");
			}
			sb.append("</ul>");
			sb.append("</div>");
			JspWriter jw = pageContext.getOut();
			jw.print(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return super.doEndTag();
	}

	/**
	 * 根据属性名生成相应的Get方法名
	 * 
	 * @param fieldName
	 */
	private String makeupGetMethodName(String fieldName) {
		if (StringUtils.isEmpty(fieldName)) {
			return StringUtils.EMPTY;
		}
		return "get" + StringUtil.toggleFirstLetter(fieldName);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

}
