package com.hidden.data.db.dao.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hidden.data.db.dao.AuthorDao;
import com.hidden.data.db.model.Author;
import com.hidden.data.db.model.PersistentEntity;

public class CurdDaoHibernateTest extends InMemoryDaoTest {

	@Autowired
	private AuthorDao victim;

	private PersistentEntity entity;
	private Integer previousEntityId;
	private List<? extends PersistentEntity> entities;

	@Test
	public void save_NewEntity_SetsNewId() {
		givenNewEntity();
		whenSave();
		thenEntityShoudHaveNewId();
	}

	private void givenNewEntity() {
		Author author = Author.createEmptyAuthor();
		author.setName(AuthorDaoHibernateTest.NOT_EXISTING_AUTHOR_NAME);
		entity = author;
	}

	private void whenSave() {
		previousEntityId = entity.getId();
		entity = victim.save((Author) entity);
	}

	private void thenEntityShoudHaveNewId() {
		Assert.assertNull(previousEntityId);
		Assert.assertNotNull(entity.getId());
	}

	@Test
	public void save_ExistingEntity_DoesNotModifyId() {
		givenExistingEntity();
		whenSave();
		thenEntityShoulHaveSameId();
	}

	private void givenExistingEntity() {
		entity = victim.findByName(AuthorDaoHibernateTest.EXISTING_AUTHOR_NAME);
	}

	private void thenEntityShoulHaveSameId() {
		Assert.assertNotNull(previousEntityId);
		Assert.assertEquals(previousEntityId, entity.getId());
	}

	@Test
	public void loadAll_ReturnsNotEmptyList() {
		whenLoadAll();
		thenEntityListShouldNotBeEmpty();
	}

	private void whenLoadAll() {
		entities = victim.loadAll();
	}

	private void thenEntityListShouldNotBeEmpty() {
		Assert.assertFalse(entities.isEmpty());
	}

}
