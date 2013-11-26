package cn.sjcloud.apm.sys.page;

import java.util.List;

import cn.sjcloud.apm.core.base.BasePage;
import cn.sjcloud.apm.core.entity.TMenu;

public class HomePage extends BasePage {
	
	private List<TMenu> menuList;
	
	public List<TMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<TMenu> menuList) {
		this.menuList = menuList;
	}
	
}
