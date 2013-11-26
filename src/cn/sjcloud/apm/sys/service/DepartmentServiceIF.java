package cn.sjcloud.apm.sys.service;

import cn.sjcloud.apm.sys.page.DepartmentPage;

public interface DepartmentServiceIF {
	void find(DepartmentPage page);

	void findByConditon(DepartmentPage page);

	void findEntity(DepartmentPage page);

	void save(DepartmentPage page);

	void del(DepartmentPage page);
}
