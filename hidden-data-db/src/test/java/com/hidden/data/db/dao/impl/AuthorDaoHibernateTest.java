package com.hidden.data.db.dao.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hidden.data.db.HsqlDatabase;
import com.hidden.data.db.dao.AuthorDao;
import com.hidden.data.db.model.Author;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class AuthorDaoHibernateTest {

	private static final String AUTHOR_NAME = "Frank Herbert";

	private static HsqlDatabase database;

	@Autowired
	private AuthorDao victim;

	@BeforeClass
	public static void beforeClass() {
		database = new HsqlDatabase();
		database.setUp("");
	}

	@Test
	public void findByname() {
		Author author = Author.createEmptyAuthor();
		author.setName(AUTHOR_NAME);
		victim.save(author);
		Author findByName = victim.findByName(AUTHOR_NAME);
		System.out.println(findByName.toString());
	}

}
