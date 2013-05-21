package com.hidden.data.db;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.hidden.data.db.model.Book;

@Repository
public class BookDao extends HibernateDaoSupport {

	@Autowired
	protected BookDao(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public void doSomething() {
		List<Book> books = getHibernateTemplate().loadAll(Book.class);
		for (Book book : books) {
			System.out.println("Something done: " + book.getTitle());
		}
	}

}
