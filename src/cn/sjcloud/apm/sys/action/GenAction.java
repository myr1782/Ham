package cn.sjcloud.apm.sys.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import cn.sjcloud.apm.core.base.BaseAction;
import cn.sjcloud.apm.sys.page.GenPage;
import cn.sjcloud.apm.sys.service.GenServiceIF;

import com.opensymphony.xwork2.ModelDriven;

@Action(value = "gen", results = { @Result(name = "success", location = "/page/sys/gen.jsp") })
public class GenAction extends BaseAction implements ModelDriven<GenPage> {
	/** 序列化ID */
	private static final long serialVersionUID = 1778631424688840978L;
	/** 画面对应Model */
	private GenPage page;
	/** Service，注解注入实例 */
	@Autowired
	private GenServiceIF service;

	/**
	 * 取得画面对应Model的实例
	 */
	@Override
	public GenPage getModel() {
		if (page == null) {
			page = new GenPage();
		}
		page.setBizCode(this.getBizCode());
		return page;
	}

	/**
	 * Description:画面初期化
	 */
	public String doInit() {
		service.init(page);
		return SUCCESS;
	}
	
	public String doRead() {
		service.read(page);
		return SUCCESS;
	}
	
	public String doGen() {
		service.gen(page);
		return SUCCESS;
	}

}
