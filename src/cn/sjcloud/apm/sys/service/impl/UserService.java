package cn.sjcloud.apm.sys.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sjcloud.apm.core.base.BaseDaoIF;
import cn.sjcloud.apm.core.base.BaseService;
import cn.sjcloud.apm.core.constant.Constants;
import cn.sjcloud.apm.core.entity.TUser;
import cn.sjcloud.apm.sys.page.UserPage;
import cn.sjcloud.apm.sys.service.UserServiceIF;

/**
 * Description:部门管理Service
 * @author gu.kaiming
 * @version 1.0
 */
@Service
public class UserService extends BaseService implements UserServiceIF {
	/** DAO操作类 */
	@Autowired
	private BaseDaoIF<TUser> dao;

	/**
	 * Description:不带条件的检索
	 * @param page 画面Model
	 */
	@Override
	@Transactional
	public void find(UserPage page) {
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
	public void findByConditon(UserPage page) {
		// 检索条件Map
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		// 检索条件
		if (!StringUtils.isEmpty(page.getUsername())) {
			conditionMap.put("Username", page.getUsername());
		}
		if (!StringUtils.isEmpty(page.getPassword())) {
			conditionMap.put("Password", page.getPassword());
		}
		if (!StringUtils.isEmpty(page.getTelphone())) {
			conditionMap.put("Telphone", page.getTelphone());
		}
		if (!StringUtils.isEmpty(page.getEmail())) {
			conditionMap.put("Email", page.getEmail());
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
	public void findEntity(UserPage page) {
		TUser entity = dao.load(TUser.class, page.getId());
		try {
			BeanUtils.copyProperties(page, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Description:编辑画面或新增画面保存数据
	 * @param page 画面Model
	 */
	@Override
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public void save(UserPage page) {
		TUser entity = null;
		if (page.getId() != null) { // 编辑的场合
			entity = dao.load(TUser.class, (Serializable) (page.getId()));
		} else { // 新增的场合
			entity = new TUser();
		}
		try {
			BeanUtils.copyProperties(entity, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.save(entity);
	}

	/**
	 * Description:删除数据
	 * @param page 画面Model（主键）
	 */
	@Override
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public void del(UserPage page) {
		TUser entity = dao.load(TUser.class, (Serializable) (page.getId()));
		dao.delete(entity);
	}
	
	/**
	 * Description:组拼SQL，执行DAO检索操作
	 * @param page 画面Model（主键）
	 * @param conditionMap 检索条件Map
	 */
	private void findActually(UserPage page, Map<String, Object> conditionMap) {
		StringBuilder hql = new StringBuilder();
		StringBuilder hqlPrefix = new StringBuilder("select count(*) ");
		hql.append(" from TUser ");
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
