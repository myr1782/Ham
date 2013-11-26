package cn.sjcloud.apm.core.base;

import org.apache.commons.lang3.StringUtils;

public class BaseService {
	/** 第一页 */
	public final static int FIRST_PAGE = 1;
	
	/**
	 * Description:分页的计算
	 */
	protected void calculatePagination(BasePage page) {
		// 计算总页数
		Long pageNumber = page.calculatePageNumber();

		/**
		 * 分页最多显示10页数据，总页面小于10页，则显示所有页。
		 * 总页面大于10页，当前页超过第6页，则从第2页开始显示10页（从第2页到第10页），以此类推。 
		 * 最后第5页开始，只显示最后10页数
		 */
		if (pageNumber < 10) {
			page.setStartPage(1);
			page.setEndPage(pageNumber.intValue());
		} else {
			int currentPage = page.getCurrentPage();
			if (currentPage > pageNumber - 5) { // 最后5页
				page.setStartPage(new Long(pageNumber - 10 + 1).intValue());
				page.setEndPage(pageNumber.intValue());
			} else { // 不是最后5页
				if (currentPage < 6) {
					page.setStartPage(1);
					page.setEndPage(10);
				} else {
					page.setStartPage(currentPage - 5);
					page.setEndPage(currentPage + 5 - 1);
				}
			}
		}
	}
	
	protected String appendSortSQL(StringBuilder sql, BasePage page) {
		if (StringUtils.isEmpty(page.getSortType())) {
			return sql.toString();
		}
		sql.append(" order by ");
		sql.append(page.getSortItem());
		sql.append(" ");
		sql.append(page.getSortType());
		return sql.toString();
	}
}
