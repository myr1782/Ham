package cn.sjcloud.apm.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sjcloud.apm.core.base.BaseService;
import cn.sjcloud.apm.sys.service.LoginServiceIF;

@Service
public class LoginService extends BaseService implements LoginServiceIF {
	@Autowired
//	private BaseDaoIF<TUser> userDao;

	@Transactional
	public boolean checkValidLogin(String username, String password) {
		/*StringBuilder hql = new StringBuilder();
		hql.append("from TUser u where u.username = ? and u.password = ?");
		List<TUser> userList = userDao.find(hql.toString(), new Object[] { username, password });
		return (userList == null || userList.isEmpty()) ? false : true;*/
		return true;
	}
}
