package cn.sjcloud.apm.core.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TDepartment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_department", catalog = "apm")
public class TDepartment implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -4844619251132685700L;
	private Integer id;
	private Integer parentId;
	private String departmentName;
	private String departmentCode;
	private String departmentDesc;
	private String createUser;
	private Timestamp createTime;
	private String updateUser;
	private Timestamp updateTime;
	private String isAbled;

	// Constructors

	/** default constructor */
	public TDepartment() {
	}

	/** full constructor */
	public TDepartment(Integer parentId, String departmentName,
			String departmentCode, String departmentDesc, String createUser,
			Timestamp createTime, String updateUser, Timestamp updateTime,
			String isAbled) {
		this.parentId = parentId;
		this.departmentName = departmentName;
		this.departmentCode = departmentCode;
		this.departmentDesc = departmentDesc;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.isAbled = isAbled;
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

	@Column(name = "parent_id")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "department_name", length = 100)
	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(name = "department_code", length = 100)
	public String getDepartmentCode() {
		return this.departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	@Column(name = "department_desc", length = 200)
	public String getDepartmentDesc() {
		return this.departmentDesc;
	}

	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}

	@Column(name = "create_user", length = 100)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "create_time", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_user", length = 100)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "update_time", length = 19)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "is_abled", length = 1)
	public String getIsAbled() {
		return this.isAbled;
	}

	public void setIsAbled(String isAbled) {
		this.isAbled = isAbled;
	}

}