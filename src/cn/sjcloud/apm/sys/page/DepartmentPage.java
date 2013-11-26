package cn.sjcloud.apm.sys.page;

import java.util.List;

import cn.sjcloud.apm.core.base.BasePage;
import cn.sjcloud.apm.core.entity.TDepartment;

public class DepartmentPage extends BasePage {
	
	private List<TDepartment> entityList;
	
	private Integer id;
	private String departmentName;
	private String departmentDesc;
	
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentDesc() {
		return departmentDesc;
	}
	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
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
