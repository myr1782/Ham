package cn.sjcloud.apm.core.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao<T> implements BaseDaoIF<T> {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T o) {
		this.getCurrentSession().save(o);
	}

	@Override
	public void update(T o) {
		this.getCurrentSession().update(o);
	}

	@Override
	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	@Override
	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	@SuppressWarnings("unchecked")
	public T load(Class<T> clazz, Serializable id) {
		return (T) this.getCurrentSession().load(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql) {
		Session session = this.getCurrentSession();
		Query q = session.createQuery(hql);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object... params) {
		Session session = this.getCurrentSession();
		Query q = session.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return q.list();
	}

	/**
	 * Description:带分页的检索
	 * 
	 * @param hql
	 *            HQL 语句
	 * @param currentPage
	 *            当前所在页
	 * @param pageRecords
	 *            每页显示记录数
	 * @param params
	 *            检索条件
	 * @return List 查询结果
	 */
	@SuppressWarnings("unchecked")
	public List<T> findWithPaging(String hql, int currentPage, int pageRecords, Object... params) {
		Session session = this.getCurrentSession();
		Query q = session.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return q.setFirstResult((currentPage - 1) * pageRecords).setMaxResults(pageRecords).list();
	}

	public Long count(String hql, Object... param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return (Long) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findBySql(String sql) {
		Session session = this.getCurrentSession();
		Query q = session.createSQLQuery(sql);
		return (List<Object[]>) q.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findBySql(String sql, Object... params) {
		Session session = this.getCurrentSession();
		Query q = session.createSQLQuery(sql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return (List<Object[]>) q.list();
	}

}
