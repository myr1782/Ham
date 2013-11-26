package cn.sjcloud.apm.sys.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import cn.sjcloud.apm.core.base.BaseAction;
import cn.sjcloud.apm.sys.page.HomePage;
import cn.sjcloud.apm.sys.service.HomeServiceIF;

import com.opensymphony.xwork2.ModelDriven;

@Action(value = "home", results = { @Result(name = "success", location = "/page/sys/home.jsp"), })
public class HomeAction extends BaseAction implements ModelDriven<HomePage> {
	/** 序列化ID */
	private static final long serialVersionUID = -118415731550710589L;
	/** 画面对应Model */
	private HomePage page;
	/** Service，注解注入实例 */
	@Autowired
	private HomeServiceIF service;

	/**
	 * 取得画面对应Model的实例
	 */
	@Override
	public HomePage getModel() {
		if (page == null) {
			page = new HomePage();
		}
		return page;
	}
	
	/**
	 * Description:画面初期化
	 */
	public String doInit() {
		service.init(page);
		this.getSession().setAttribute("menuList", page.getMenuList());
		return SUCCESS;
	}

}
