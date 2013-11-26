package cn.sjcloud.apm.core.base;

import java.util.ArrayList;
import java.util.List;

import cn.sjcloud.apm.core.constant.Constants;


public class BasePage {

	/** 当前所在页 */
	private int currentPage = 1;
	/** 每页显示记录数 */
	private int pageRecords;
	/** 排序字段名 */
	private String sortItem;
	/** 排序方式（ASC,DESC） */
	private String sortType;

	/** 总记录数 */
	private long totalRecords;
	/** 总页数 */
	private Long pageNumber;

	/** 开始页码 */
	private int startPage;
	/** 结束页码 */
	private int endPage;
	
	/** 功能代码 */
	private String bizCode;
	
	/**
	 * 错误消息
	 */
	private List<String> errorMsgs;

	/**
	 * Description: 计算总页数
	 */
	public long calculatePageNumber() {
		long pageNumber = (this.getTotalRecords() + Constants.PAGE_RECORDS - 1) / Constants.PAGE_RECORDS;
		this.setPageNumber(pageNumber);
		return pageNumber;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageRecords() {
		return pageRecords;
	}

	public void setPageRecords(int pageRecords) {
		this.pageRecords = pageRecords;
	}

	public String getSortItem() {
		return sortItem;
	}

	public void setSortItem(String sortItem) {
		this.sortItem = sortItem;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}


	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public Long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public List<String> getErrorMsgs() {
		return errorMsgs;
	}

	public void setErrorMsgs(List<String> errorMsgs) {
		this.errorMsgs = errorMsgs;
	}
	
	public void addErrorMsg(String msg) {
		if(errorMsgs == null) {
			errorMsgs = new ArrayList<String>();
		}
		errorMsgs.add(msg);
	}
	
}
