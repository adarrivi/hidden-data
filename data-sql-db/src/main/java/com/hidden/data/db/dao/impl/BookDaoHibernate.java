package com.hidden.data.db.dao.impl;

import java.util.Collection;

import org.hibernate.Query;
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
		Query query = getSession()
				.createQuery("from Book where title = :title");
		query.setParameter("title", title);
		Book book = (Book) query.uniqueResult();
		if (book == null) {
			return Book.createEmptyBook();
		}
		return book;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<String> getNotProcessedBookTitles() {
		Query query = getSession().createQuery(
				"select title from Book where processed = :processed");
		query.setParameter("processed", false);
		return query.list();
	}
}
