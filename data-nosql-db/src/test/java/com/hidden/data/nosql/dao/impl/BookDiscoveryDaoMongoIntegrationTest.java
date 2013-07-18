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

import com.hidden.data.nosql.dao.BookDiscoveryDao;
import com.hidden.data.nosql.model.discovery.BookDiscovery;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationNoSqlDbContext-test.xml" })
public class BookDiscoveryDaoMongoIntegrationTest {

	@Autowired
	private ApplicationContext applicationContext;
	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule()
			.defaultSpringMongoDb("db");
	@Autowired
	private BookDiscoveryDao victim;

	private List<BookDiscovery> bookDiscoveries;
	private String bookTitle;
	private String patternName;

	@Test
	@UsingDataSet(locations = "bookDiscoveryInit.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void findBookDiscoveries_Existing_ReturnsSameBookTitle() {
		givenBook();
		whenFindBookDiscoveries();
		thenBookTitleShouldBeTheSame();
	}

	private void givenBook() {
		bookTitle = "A Treatise of Human Nature";
		patternName = "3x3 Column in middle";
	}

	private void whenFindBookDiscoveries() {
		bookDiscoveries = victim.findBookDiscoveries(bookTitle, patternName);
	}

	private void thenBookTitleShouldBeTheSame() {
		for (BookDiscovery bookDiscovery : bookDiscoveries) {
			Assert.assertEquals(bookTitle, bookDiscovery.getBookTitle());
		}
	}

	@Test
	@UsingDataSet(locations = "bookDiscoveryInit.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void findBookDiscoveries_Existing_ReturnsNotEmpty() {
		givenBook();
		whenFindBookDiscoveries();
		thenBookDiscoveriesShouldNotBeEmpty();
	}

	private void thenBookDiscoveriesShouldNotBeEmpty() {
		Assert.assertFalse(bookDiscoveries.isEmpty());
	}

	@Test
	@UsingDataSet(locations = "bookDiscoveryInit.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void findBookDiscoveries_Existing_ReturnsSamePatternName() {
		givenBook();
		whenFindBookDiscoveries();
		thenPatternNameShouldBeTheSame();
	}

	private void thenPatternNameShouldBeTheSame() {
		for (BookDiscovery bookDiscovery : bookDiscoveries) {
			Assert.assertEquals(patternName, bookDiscovery.getPattern()
					.getName());
		}
	}

}
