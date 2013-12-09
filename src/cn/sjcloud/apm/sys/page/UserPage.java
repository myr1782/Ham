package cn.sjcloud.apm.sys.page;

import java.util.List;

import cn.sjcloud.apm.core.base.BasePage;
import cn.sjcloud.apm.core.entity.TUser;

public class UserPage extends BasePage {
	
	private List<TUser> entityList;
	
	private Integer id;
	
	private String Username;
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String Username) {
		this.Username = Username;
	}
	private String Password;
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String Password) {
		this.Password = Password;
	}
	private String Telphone;
	
	public String getTelphone() {
		return Telphone;
	}
	public void setTelphone(String Telphone) {
		this.Telphone = Telphone;
	}
	private String Email;
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String Email) {
		this.Email = Email;
	}
	
	public List<TUser> getEntityList() {
		return entityList;
	}
	
	public void setEntityList(List<TUser> entityList) {
		this.entityList = entityList;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
}
