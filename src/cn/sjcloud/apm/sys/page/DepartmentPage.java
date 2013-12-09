package cn.sjcloud.apm.sys.page;

import java.util.List;

import cn.sjcloud.apm.core.base.BasePage;
import cn.sjcloud.apm.core.entity.TDepartment;

public class DepartmentPage extends BasePage {
	
	private List<TDepartment> entityList;
	
	private Integer id;
	
	private String DepartmentName;
	
	public String getDepartmentName() {
		return DepartmentName;
	}
	public void setDepartmentName(String DepartmentName) {
		this.DepartmentName = DepartmentName;
	}
	private String DepartmentDesc;
	
	public String getDepartmentDesc() {
		return DepartmentDesc;
	}
	public void setDepartmentDesc(String DepartmentDesc) {
		this.DepartmentDesc = DepartmentDesc;
	}
	
	public List<TDepartment> getEntityList() {
		return entityList;
	}
	
	public void setEntityList(List<TDepartment> entityList) {
		this.entityList = entityList;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
}
