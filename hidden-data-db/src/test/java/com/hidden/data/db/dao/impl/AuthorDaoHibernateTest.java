package com.hidden.data.db.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hidden.data.db.dao.AuthorDao;
import com.hidden.data.db.model.Author;

public class AuthorDaoHibernateTest extends InMemoryDaoTest {

	private static final String AUTHOR_NAME = "Frank Herbert";

	@Autowired
	private AuthorDao victim;

	@Test
	public void findByname() {
		Author author = Author.createEmptyAuthor();
		author.setName(AUTHOR_NAME);
		victim.save(author);
		Author findByName = victim.findByName(AUTHOR_NAME);
		System.out.println(findByName.toString());
	}
}
