package com.hidden.data.nosql.dao.impl;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;

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
public class FilteredBlockDaoIntegrationTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb(
			"test");

	@Autowired
	private FilteredBlockDao victim;

	@Test
	@UsingDataSet(locations = "filteredBlockInit.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void loadAll_isNotEmpty() {
		List<FilteredBlock> filteredBlocks = victim.loadAll();
		Assert.assertEquals(2, filteredBlocks.size());
	}

}
