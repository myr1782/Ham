package cn.sjcloud.apm.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sjcloud.apm.core.base.BaseDaoIF;
import cn.sjcloud.apm.core.entity.TMenu;
import cn.sjcloud.apm.sys.page.HomePage;
import cn.sjcloud.apm.sys.service.HomeServiceIF;

@Service
public class HomeServiceImpl implements HomeServiceIF {
	/** DAO操作类 */
	@Autowired
	private BaseDaoIF<TMenu> dao;
	
	@Transactional
	public void init(HomePage page) {
		// 检索菜单
		StringBuilder hql = new StringBuilder();
		hql.append(" from TMenu");
		// TODO 使用二级缓存
		page.setMenuList(dao.find(hql.toString()));
		
	}
}

