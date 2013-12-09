package cn.sjcloud.apm.sys.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import cn.sjcloud.apm.core.base.BaseAction;
import cn.sjcloud.apm.sys.page.UserPage;
import cn.sjcloud.apm.sys.service.UserServiceIF;

import com.opensymphony.xwork2.ModelDriven;

/**
 * Description:部门管理Action
 * @author gu.kaiming
 * @version 1.0
 */
@Action(value = "user", results = { @Result(name = "success", location = "/page/sys/user.jsp"),
		@Result(name = "input", location = "/page/sys/user.jsp"),
		@Result(name = "edit", location = "/page/sys/userEdit.jsp"),
		@Result(name = "init", type = "redirect", location = "user!doInit.action"), })
public class UserAction extends BaseAction implements ModelDriven<UserPage> {
	/** 序列化ID */
	private static final long serialVersionUID = 175797690272212968L;
	/** 画面对应Model */
	private UserPage page;
	/** Service，注解注入实例 */
	@Autowired
	private UserServiceIF service;

	/**
	 * 取得画面对应Model的实例
	 */
	@Override
	public UserPage getModel() {
		if (page == null) {
			page = new UserPage();
		}
		page.setBizCode(this.getBizCode());
		return page;
	}

	/**
	 * Description:画面初期化
	 */
	public String doInit() {
		service.find(page);
		return SUCCESS;
	}

	/**
	 * Description:编辑弹出画面初期化
	 */
	public String doInitEdit() {
		// 编辑的场合，加载编辑的值
		if (!StringUtils.isEmpty(getRequest().getParameter("id"))) {
			service.findEntity(page);
		}
		return EDIT;
	}

	/**
	 * Description:保存操作
	 */
	public String doSave() {
		service.save(page);
		// 重新检索数据
		service.find(page);
		return INIT;
	}

	/**
	 * Description:删除操作
	 */
	public String doDel() {
		service.del(page);
		// 重新检索数据
		service.find(page);
		return INIT;
	}

	/**
	 * Description:检索操作
	 */
	public String doSearch() {
		service.findByConditon(page);
		return SUCCESS;
	}

	/**
	 * Description:分页Bar的跳转页面操作
	 */
	public String doGotoPage() {
		service.findByConditon(page);
		return SUCCESS;
	}
	
	/**
	 * Description:列表的排序操作
	 */
	public String doSort() {
		service.findByConditon(page);
		return SUCCESS;
	}
}
