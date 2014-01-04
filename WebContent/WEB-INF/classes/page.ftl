package cn.sjcloud.apm.sys.page;

import java.util.List;

import cn.sjcloud.apm.core.base.BasePage;
import cn.sjcloud.apm.core.entity.${entityName};

public class ${fileName}Page extends BasePage {
	
	private List<${entityName}> entityList;
	
	private Integer id;
	
	<#list itemList as item>
	private String ${item[0]};
	
	public String get${item[1]}() {
		return ${item[0]};
	}
	public void set${item[1]}(String ${item[0]}) {
		this.${item[0]} = ${item[0]};
	}
	</#list>
	
	public List<${entityName}> getEntityList() {
		return entityList;
	}
	
	public void setEntityList(List<${entityName}> entityList) {
		this.entityList = entityList;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
}
