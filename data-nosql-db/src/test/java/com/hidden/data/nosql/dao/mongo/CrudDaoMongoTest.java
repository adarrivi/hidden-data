package com.hidden.data.nosql.dao.mongo;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoOperations;

public class CrudDaoMongoTest {

	private static final String ENTITY = "entity";
	private static final List<String> MULTIPLE_ENTITIES = Collections.nCopies(
			4, ENTITY);
	private String saveResult;
	private List<String> loadAllResult;
	@Mock
	private MongoOperations mongoOperations;
	@InjectMocks
	private CrudDaoMongo<String> victim = new CrudDaoMongo<String>(String.class);

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void save_CallsMoSave() {
		whenSave();
		thenVerifyMoSave();
	}

	private void whenSave() {
		saveResult = victim.save(ENTITY);
	}

	private void thenVerifyMoSave() {
		Mockito.verify(mongoOperations).save(ENTITY);
	}

	@Test
	public void save_ReturnsInitialEntity() {
		whenSave();
		thenShouldReturnInitialEntity();
	}

	private void thenShouldReturnInitialEntity() {
		Assert.assertEquals(ENTITY, saveResult);
	}

	@Test
	public void loadAll_CallsMoFindAll() {
		whenLoadAll();
		thenVerifyMoFindAll();
	}

	private void whenLoadAll() {
		loadAllResult = victim.loadAll();
	}

	private void thenVerifyMoFindAll() {
		Mockito.verify(mongoOperations).findAll(String.class);
	}

	@Test
	public void loadAll_ReturnsMoFindAllResult() {
		givenLoadAllReturnsMultipleEntities();
		whenLoadAll();
		thenShouldReturnsFindAllRetult();
	}

	private void givenLoadAllReturnsMultipleEntities() {
		Mockito.when(mongoOperations.findAll(String.class)).thenReturn(
				MULTIPLE_ENTITIES);
	}

	private void thenShouldReturnsFindAllRetult() {
		Assert.assertEquals(MULTIPLE_ENTITIES, loadAllResult);
	}
}
