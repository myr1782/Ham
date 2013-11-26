package cn.sjcloud.apm.sys.service;

import cn.sjcloud.apm.sys.page.ProjectPage;

public interface ProjectServiceIF {
	void find(ProjectPage page);

	void findByConditon(ProjectPage page);

	void findEntity(ProjectPage page);

	void save(ProjectPage page);

	void del(ProjectPage page);
}
