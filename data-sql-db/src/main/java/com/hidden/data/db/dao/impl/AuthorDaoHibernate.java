package com.hidden.data.db.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hidden.data.db.dao.AuthorDao;
import com.hidden.data.db.model.Author;

@Repository
public class AuthorDaoHibernate extends CrudDaoHibernate<Author> implements
		AuthorDao {

	@Autowired
	protected AuthorDaoHibernate(SessionFactory sessionFactory) {
		super(Author.class);
		setSessionFactory(sessionFactory);
	}

	@Override
	public Author findByName(String name) {
		Query query = getSession()
				.createQuery("from Author where name = :name");
		query.setParameter("name", name);
		Author author = (Author) query.uniqueResult();
		if (author == null) {
			return Author.createEmptyAuthor();
		}
		return author;
	}
}
