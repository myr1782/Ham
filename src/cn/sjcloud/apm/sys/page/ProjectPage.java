package cn.sjcloud.apm.sys.page;

import java.util.List;

import cn.sjcloud.apm.core.base.BasePage;
import cn.sjcloud.apm.core.entity.TProject;

public class ProjectPage extends BasePage {
	
	private List<TProject> entityList;
	
	private Integer id;
	
	private String projectName;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	private String projectDesc;
	
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	
	public List<TProject> getEntityList() {
		return entityList;
	}
	
	public void setEntityList(List<TProject> entityList) {
		this.entityList = entityList;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
}
