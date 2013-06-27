package com.hidden.data.db.dao.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import com.hidden.data.db.dao.AuthorDao;
import com.hidden.data.db.exception.DaoException;
import com.hidden.data.db.model.Author;
import com.hidden.data.db.model.PersistentEntity;

public class CrudDaoHibernateTest extends InMemoryDaoTest {

	private static final Integer NOT_EXISTING_ID = Integer.valueOf(-999);
	private static final Integer EXISTING_ID = Integer.valueOf(1);

	@Autowired
	private AuthorDao victim;

	private PersistentEntity entity;
	private Integer previousEntityId;
	private List<? extends PersistentEntity> entities;
	private Integer id;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

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

	@Test
	public void findById_NotExistingId_ThrowsDaoEx() {
		expectedException.expect(DaoException.class);
		givenNotExistingId();
		whenFindById();
	}

	private void givenNotExistingId() {
		id = NOT_EXISTING_ID;
	}

	private void whenFindById() {
		entity = victim.findById(id);
	}

	@Test
	public void findById_ExistingId_ReturnsEntityExistingId() {
		givenExistingId();
		whenFindById();
		thenEntityShouldHaveExistingId();
	}

	private void givenExistingId() {
		id = EXISTING_ID;
	}

	private void thenEntityShouldHaveExistingId() {
		Assert.assertEquals(EXISTING_ID, entity.getId());
	}

}
