package com.hidden.data.db.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hidden.data.db.dao.AuthorDao;
import com.hidden.data.db.model.Author;

public class AuthorDaoHibernateTest extends InMemoryDaoTest {

	protected static final String NOT_EXISTING_AUTHOR_NAME = "notExisting";
	protected static final String EXISTING_AUTHOR_NAME = "Issac Asimov";

	private String authorName;
	private Author author;

	@Autowired
	private AuthorDao victim;

	@Test
	public void findByname_Existing_ReturnsAuthorSameName() {
		givenAuthorName(EXISTING_AUTHOR_NAME);
		whenFindByName();
		thenExpectAuthorWithName(EXISTING_AUTHOR_NAME);
	}

	private void givenAuthorName(String name) {
		authorName = name;
	}

	private void whenFindByName() {
		author = victim.findByName(authorName);
	}

	private void thenExpectAuthorWithName(String name) {
		Assert.assertEquals(name, author.getName());
	}

	@Test
	public void findByname_Existing_ReturnsNotEmptyAuthor() {
		givenAuthorName(EXISTING_AUTHOR_NAME);
		whenFindByName();
		thenAuthorShouldNotBeEmpty();
	}

	private void thenAuthorShouldNotBeEmpty() {
		Assert.assertFalse(author.isEmpty());
	}

	@Test
	public void findByName_NotExisting_ReturnsEmptyAuthor() {
		givenAuthorName(NOT_EXISTING_AUTHOR_NAME);
		whenFindByName();
		thenAuthorShouldBeEmpty();
	}

	private void thenAuthorShouldBeEmpty() {
		Assert.assertTrue(author.isEmpty());
	}

}
