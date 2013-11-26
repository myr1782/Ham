package cn.sjcloud.apm.sys.service;

import cn.sjcloud.apm.sys.page.GenPage;

public interface GenServiceIF {
	void init(GenPage page);
	void read(GenPage page);
	void gen(GenPage page);
}