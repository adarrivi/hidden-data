package com.hidden.data.nosql.dao.impl;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hidden.data.nosql.dao.FilteredBlockDao;
import com.hidden.data.nosql.model.FilteredBlock;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationNoSqlDbContext-test.xml" })
public class FilteredBlockDaoMongoIntegrationTest {

	private static final int FILTERED_BLOCKS_NUMBER = 2;

	private List<FilteredBlock> filteredBlocks;

	@Autowired
	private ApplicationContext applicationContext;
	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule()
			.defaultSpringMongoDb("db");
	@Autowired
	private FilteredBlockDao victim;

	private FilteredBlock newFilteredBlock;

	// TODO I am not able to make @ShouldMatchDataSet, and also remove
	// operations are not working.
	@Test
	@UsingDataSet(locations = "filteredBlockInit.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void loadAll_ReturnsAllBlocks() {
		whenLoadAll();
		thenShouldReturnAllBlocks();
	}

	private void whenLoadAll() {
		filteredBlocks = victim.loadAll();
	}

	private void thenShouldReturnAllBlocks() {
		Assert.assertEquals(FILTERED_BLOCKS_NUMBER, filteredBlocks.size());
	}

	@Test
	@UsingDataSet(locations = "filteredBlockInit.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void save_AddsNewOne() {
		givenNewFilteredBlock();
		whenSave();
		thenNewBlockSholdBeAdded();
	}

	private void givenNewFilteredBlock() {
		newFilteredBlock = new FilteredBlock(2, 2, 20,
				Collections.singletonList("line content 1"));
	}

	private void whenSave() {
		victim.save(newFilteredBlock);
	}

	private void thenNewBlockSholdBeAdded() {
		Assert.assertEquals(FILTERED_BLOCKS_NUMBER + 1, victim.loadAll().size());
	}

}
