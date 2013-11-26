package cn.sjcloud.apm.core.taglib;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class GridColTag extends TagSupport {

	private static final long serialVersionUID = -7781052981352484129L;

	private String title;

	private String field;

	private String sortable;

	@Override
	public int doStartTag() throws JspException {
		try {
			@SuppressWarnings("unchecked")
			Map<String, List<String>> itemMap = (Map<String, List<String>>) pageContext.getRequest().getAttribute(
					"itemMap");
			// 标题
			List<String> titleList = itemMap.get("title");
			if (titleList == null) {
				titleList = new ArrayList<String>();
				itemMap.put("title", titleList);
			}
			titleList.add(title);
			// 属性名
			List<String> fieldList = itemMap.get("field");
			if (fieldList == null) {
				fieldList = new ArrayList<String>();
				itemMap.put("field", fieldList);
			}
			fieldList.add(field);
			// 是否可排序
			List<String> sortableList = itemMap.get("sortable");
			if (sortableList == null) {
				sortableList = new ArrayList<String>();
				itemMap.put("sortable", sortableList);
			}
			sortableList.add(sortable);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getSortable() {
		return sortable;
	}

	public void setSortable(String sortable) {
		this.sortable = sortable;
	}

}
