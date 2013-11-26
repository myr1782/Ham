package cn.sjcloud.apm.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TProject entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_project", catalog = "apm")
public class TProject implements java.io.Serializable {

	// Fields

	private Integer id;
	private String projectName;
	private String projectDesc;

	// Constructors

	/** default constructor */
	public TProject() {
	}

	/** full constructor */
	public TProject(String projectName, String projectDesc) {
		this.projectName = projectName;
		this.projectDesc = projectDesc;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "project_name", length = 50)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "project_desc", length = 80)
	public String getProjectDesc() {
		return this.projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

}