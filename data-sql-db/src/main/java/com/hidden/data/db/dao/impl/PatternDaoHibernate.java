package com.hidden.data.db.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hidden.data.db.dao.PatternDao;
import com.hidden.data.db.model.Pattern;

@Repository
public class PatternDaoHibernate extends CrudDaoHibernate<Pattern> implements
		PatternDao {

	@Autowired
	protected PatternDaoHibernate(SessionFactory sessionFactory) {
		super(Pattern.class);
		setSessionFactory(sessionFactory);
	}
}
