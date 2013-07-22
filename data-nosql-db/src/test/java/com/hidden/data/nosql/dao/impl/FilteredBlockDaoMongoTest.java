package com.hidden.data.nosql.dao.impl;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import com.hidden.data.nosql.dao.FilteredBlockDao;
import com.hidden.data.nosql.dao.impl.FilteredBlockDaoMongo;
import com.hidden.data.nosql.model.FilteredBlock;

public class FilteredBlockDaoMongoTest {

	private static final FilteredBlock FILTERED_BLOCK = new FilteredBlock(1, 1,
			1, Collections.<String> emptyList());
	@Mock
	private MongoOperations mongoOperations;
	private FilteredBlock resultFilteredBlock;

	@InjectMocks
	private FilteredBlockDao victim = new FilteredBlockDaoMongo();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findOneAndRemove_CallsFindAndRemove() {
		whenFindOneAndRemove();
		thenFindAndRemoveShouldHaveBeenCalled();
	}

	private void whenFindOneAndRemove() {
		Mockito.when(
				mongoOperations.findAndRemove(Matchers.any(Query.class),
						Matchers.eq(FilteredBlock.class))).thenReturn(
				FILTERED_BLOCK);
		resultFilteredBlock = victim.findOneAndRemove();
	}

	private void thenFindAndRemoveShouldHaveBeenCalled() {
		Mockito.verify(mongoOperations).findAndRemove(
				Matchers.any(Query.class), Matchers.eq(FilteredBlock.class));
	}

	@Test
	public void findOneAndRemove_ReturnsFindAnRemoveResult() {
		whenFindOneAndRemove();
		thenfindAndRemoveResultShouldHaveBeenReturned();
	}

	private void thenfindAndRemoveResultShouldHaveBeenReturned() {
		Assert.assertEquals(FILTERED_BLOCK, resultFilteredBlock);
	}

}
