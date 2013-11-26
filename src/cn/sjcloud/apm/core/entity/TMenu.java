package cn.sjcloud.apm.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_menu", catalog = "apm")
public class TMenu implements java.io.Serializable {

	// Fields

	private Integer id;
	private String menuName;
	private Integer parentId;
	private String isLink;
	private String linkUrl;

	// Constructors

	/** default constructor */
	public TMenu() {
	}

	/** minimal constructor */
	public TMenu(String menuName) {
		this.menuName = menuName;
	}

	/** full constructor */
	public TMenu(String menuName, Integer parentId, String isLink,
			String linkUrl) {
		this.menuName = menuName;
		this.parentId = parentId;
		this.isLink = isLink;
		this.linkUrl = linkUrl;
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

	@Column(name = "menu_name", nullable = false, length = 50)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "is_link", length = 1)
	public String getIsLink() {
		return this.isLink;
	}

	public void setIsLink(String isLink) {
		this.isLink = isLink;
	}

	@Column(name = "link_url", length = 100)
	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

}