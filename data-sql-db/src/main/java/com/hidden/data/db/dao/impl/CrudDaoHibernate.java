package com.hidden.data.db.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hidden.data.db.dao.CrudDao;
import com.hidden.data.db.model.PersistentEntity;

class CrudDaoHibernate<T extends PersistentEntity> extends HibernateDaoSupport
		implements CrudDao<T> {

	private Class<T> modelClass;

	protected CrudDaoHibernate(Class<T> modelClass) {
		this.modelClass = modelClass;
	}

	@Override
	public T save(T item) {
		getHibernateTemplate().saveOrUpdate(item);
		return item;
	}

	@Override
	public List<T> loadAll() {
		return getHibernateTemplate().loadAll(modelClass);
	}

	@Override
	public T findById(Integer id) {
		return getHibernateTemplate().load(modelClass, id);
	}
}
