package cn.sjcloud.apm.sys.page;

import cn.sjcloud.apm.core.base.BasePage;

public class GenPage extends BasePage {
	
	private String genBizCode;
	private String genBizName;
	private String tableName;
	
	public String getGenBizCode() {
		return genBizCode;
	}

	public void setGenBizCode(String genBizCode) {
		this.genBizCode = genBizCode;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getGenBizName() {
		return genBizName;
	}

	public void setGenBizName(String genBizName) {
		this.genBizName = genBizName;
	}
	
}
