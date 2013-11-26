package cn.sjcloud.apm.sys.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import cn.sjcloud.apm.core.base.BaseAction;
import cn.sjcloud.apm.sys.page.LoginPage;
import cn.sjcloud.apm.sys.service.LoginServiceIF;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Action(value = "login", results = { @Result(name = "success", location = "/page/sys/login.jsp"),
		@Result(name = "home", type = "redirect", location = "home!doInit.action"),
		@Result(name = "input", location = "/page/sys/login.jsp") })
public class LoginAction extends BaseAction implements ModelDriven<LoginPage> {

	private static final long serialVersionUID = -4213823278336886271L;

	private LoginPage loginPage;

	@Autowired
	private LoginServiceIF service;

	@Override
	public LoginPage getModel() {
		if (loginPage == null) {
			loginPage = new LoginPage();
		}
		return loginPage;
	}

	public String doInit() {
		return SUCCESS;
	}

	@Validations(requiredStrings = {
			@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "username", message = "${getText('required', {getText('username')})}"),
			@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "password", message = "${getText('required', {getText('password')})}") })
	public String doLogin() {
		if (service.checkValidLogin(loginPage.getUsername(), loginPage.getPassword())) {
			return HOME;
		} else {
			this.addActionError(this.getText("loginFail"));
			return INPUT;
		}
	}

}
