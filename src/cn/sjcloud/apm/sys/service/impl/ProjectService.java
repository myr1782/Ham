package cn.sjcloud.apm.sys.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sjcloud.apm.core.base.BaseDaoIF;
import cn.sjcloud.apm.core.base.BaseService;
import cn.sjcloud.apm.core.constant.Constants;
import cn.sjcloud.apm.core.entity.TProject;
import cn.sjcloud.apm.sys.page.ProjectPage;
import cn.sjcloud.apm.sys.service.ProjectServiceIF;

/**
 * Description:部门管理Service
 * @author gu.kaiming
 * @version 1.0
 */
@Service
public class ProjectService extends BaseService implements ProjectServiceIF {
	/** DAO操作类 */
	@Autowired
	private BaseDaoIF<TProject> dao;

	/**
	 * Description:不带条件的检索
	 * @param page 画面Model
	 */
	@Override
	@Transactional
	public void find(ProjectPage page) {
		// 设置为第一页
		page.setCurrentPage(FIRST_PAGE);
		// 清空排序项
		page.setSortItem(StringUtils.EMPTY);
		page.setSortType(StringUtils.EMPTY);
		// 设置每页显示记录数
		page.setPageRecords(Constants.PAGE_RECORDS);
		// 执行DAO检索
		findActually(page, null);
	}

	/**
	 * Description:画面输入条件的检索
	 * @param page 画面Model
	 */
	@Override
	@Transactional
	public void findByConditon(ProjectPage page) {
		// 检索条件Map
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		// 检索条件
		if (!StringUtils.isEmpty(page.getProjectName())) {
			conditionMap.put("projectName", page.getProjectName());
		}
		if (!StringUtils.isEmpty(page.getProjectDesc())) {
			conditionMap.put("projectDesc", page.getProjectDesc());
		}
		// 执行DAO检索
		findActually(page, conditionMap);
	}
	
	/**
	 * Description:编辑画面根据主键加载所有编辑项的值
	 * @param page 画面Model（主键）
	 */
	@Override
	@Transactional
	public void findEntity(ProjectPage page) {
		TProject entity = dao.load(TProject.class, page.getId());
		BeanUtils.copyProperties(entity, page);
	}
	
	/**
	 * Description:编辑画面或新增画面保存数据
	 * @param page 画面Model
	 */
	@Override
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public void save(ProjectPage page) {
		TProject entity = null;
		if (page.getId() != null) { // 编辑的场合
			entity = dao.load(TProject.class, (Serializable) (page.getId()));
		} else { // 新增的场合
			entity = new TProject();
		}
		BeanUtils.copyProperties(page, entity);
		dao.save(entity);
	}

	/**
	 * Description:删除数据
	 * @param page 画面Model（主键）
	 */
	@Override
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public void del(ProjectPage page) {
		TProject entity = dao.load(TProject.class, (Serializable) (page.getId()));
		dao.delete(entity);
	}
	
	/**
	 * Description:组拼SQL，执行DAO检索操作
	 * @param page 画面Model（主键）
	 * @param conditionMap 检索条件Map
	 */
	private void findActually(ProjectPage page, Map<String, Object> conditionMap) {
		StringBuilder hql = new StringBuilder();
		StringBuilder hqlPrefix = new StringBuilder("select count(*) ");
		hql.append(" from TProject ");
		// 按条件检索
		if (conditionMap != null && !conditionMap.isEmpty()) {
			hql.append(" where ");
			List<Object> paramList = new ArrayList<Object>();
			for (Entry<String, Object> curEntry : conditionMap.entrySet()) {
				hql.append(curEntry.getKey()).append(" like ? ");
				hql.append(" and ");
				paramList.add("%" + curEntry.getValue() + "%");
			}
			// 删除最后一个"and"字符串
			if (hql.lastIndexOf("and") != -1) {
				hql.delete(hql.lastIndexOf("and"), hql.length());
			}
			page.setTotalRecords(dao.count(hqlPrefix.append(hql).toString(), paramList.toArray()));
			page.setEntityList(dao.findWithPaging(appendSortSQL(hql, page), page.getCurrentPage(),
					Constants.PAGE_RECORDS, paramList.toArray()));
		} else { // 全部检索
			page.setTotalRecords(dao.count(hqlPrefix.append(hql).toString()));
			page.setEntityList(dao.findWithPaging(appendSortSQL(hql, page), page.getCurrentPage(),
					Constants.PAGE_RECORDS));
		}
		// 分页的计算
		calculatePagination(page);
	}
	
}
