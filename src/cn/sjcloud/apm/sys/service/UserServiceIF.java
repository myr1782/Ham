package cn.sjcloud.apm.sys.service;

import cn.sjcloud.apm.sys.page.UserPage;

public interface UserServiceIF {
	void find(UserPage page);

	void findByConditon(UserPage page);

	void findEntity(UserPage page);

	void save(UserPage page);

	void del(UserPage page);
}
