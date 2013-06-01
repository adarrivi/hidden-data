package com.hidden.data.db.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hidden.data.db.dao.AuthorDao;
import com.hidden.data.db.model.Author;
import com.hidden.data.db.model.PersistentEntity;

public class CurdDaoHibernateTest extends InMemoryDaoTest {

	private static final String NOT_EXISTING_NAME = "Frank Herbert";

	@Autowired
	private AuthorDao victim;

	private PersistentEntity entity;

	@Test
	public void save_NewEntity_SetsNewId() {
		givenNewEntity();
		whenSave();
		thenEntityShoudHaveNewId();
	}

	private void givenNewEntity() {
		Author author = Author.createEmptyAuthor();
		author.setName(NOT_EXISTING_NAME);
		entity = author;
	}

	private void whenSave() {
		entity = victim.save((Author) entity);
	}

	private void thenEntityShoudHaveNewId() {
		Assert.assertNotNull(entity.getId());
	}

}
