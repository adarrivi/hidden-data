package com.hidden.data.db.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hidden.data.db.dao.BookDao;
import com.hidden.data.db.model.Book;

@Repository
public class BookDaoHibernate extends CrudDaoHibernate<Book> implements BookDao {

	@Autowired
	protected BookDaoHibernate(SessionFactory sessionFactory) {
		super(Book.class);
		setSessionFactory(sessionFactory);
	}

	@Override
	public Book findByTitle(String title) {
		return null;
	}
}
