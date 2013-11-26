package cn.sjcloud.apm.core.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("apm")
@Namespace("/")
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 8658255097308727695L;

	/** 主页面 */
	public final static String HOME = "home";
	/** 编辑页面 */
	public final static String EDIT = "edit";
	/** 初始化页面 */
	public final static String INIT = "init";

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * Description:根据Action类名得到功能Code
	 */
	protected String getBizCode() {
		String bizCode = this.getClass().getSimpleName().replaceAll("Action", StringUtils.EMPTY);
		return bizCode.replaceFirst(bizCode.substring(0), bizCode.substring(0).toLowerCase());
	}

}
