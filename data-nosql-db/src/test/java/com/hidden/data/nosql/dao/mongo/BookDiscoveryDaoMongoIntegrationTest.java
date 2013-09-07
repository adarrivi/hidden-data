package com.hidden.data.nosql.dao.mongo;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@UsingDataSet(locations = "bookDiscoveryInit.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
public class BookDiscoveryDaoMongoIntegrationTest {

	@Autowired
	private ApplicationContext applicationContext;
	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule()
			.defaultSpringMongoDb("db");
	@Autowired
	private BookDiscoveryDao victim;

	private List<BookDiscovery> bookDiscoveries;
	private BookDiscovery bookDiscovery;
	private String bookTitle;
	private String patternName;
	private List<String> stringResultList;

	@Test
	public void findBookDiscoveriesByBookAndPattern_Existing_ReturnsSameBookTitle() {
		givenBook();
		whenFindBookDiscoveriesByBookAndPattern();
		thenBookTitleShouldBeTheSame();
	}

	private void givenBook() {
		bookTitle = "A Treatise of Human Nature";
		patternName = "3x3 Column in middle";
	}

	private void whenFindBookDiscoveriesByBookAndPattern() {
		bookDiscoveries = victim.findBookDiscoveriesByBookAndPattern(bookTitle,
				patternName);
	}

	private void thenBookTitleShouldBeTheSame() {
		for (BookDiscovery bookDiscovery : bookDiscoveries) {
			Assert.assertEquals(bookTitle, bookDiscovery.getBookTitle());
		}
	}

	@Test
	@UsingDataSet(locations = "bookDiscoveryInit.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void findBookDiscoveriesByBookAndPattern_Existing_ReturnsNotEmpty() {
		givenBook();
		whenFindBookDiscoveriesByBookAndPattern();
		thenBookDiscoveriesShouldBeEmpty(false);
	}

	private void thenBookDiscoveriesShouldBeEmpty(boolean expectedValue) {
		Assert.assertEquals(expectedValue, bookDiscoveries.isEmpty());
	}

	@Test
	public void findBookDiscoveriesByBookAndPattern_Existing_ReturnsSamePatternName() {
		givenBook();
		whenFindBookDiscoveriesByBookAndPattern();
		thenPatternNameShouldBeTheSame();
	}

	private void thenPatternNameShouldBeTheSame() {
		for (BookDiscovery bookDiscovery : bookDiscoveries) {
			Assert.assertEquals(patternName, bookDiscovery.getPattern()
					.getName());
		}
	}

	@Test
	public void findPatternPerBook_Existing_ReturnsSameBookTitle() {
		givenBook();
		whenFindPatternsPerBook();
		thenBookTitleShouldBeTheSame();
	}

	private void whenFindPatternsPerBook() {
		bookDiscoveries = victim.findPatternsPerBook(bookTitle);
	}

	@Test
	public void findPatternPerBook_NotExisting_ReturnsEmpty() {
		givenNotExistingBook();
		whenFindPatternsPerBook();
		thenBookDiscoveriesShouldBeEmpty(true);
	}

	private void givenNotExistingBook() {
		bookTitle = "I robot";
		patternName = "3x3 Column in middle";
	}

	@Test
	public void findOneRandom_ReturnsNotEmpty() {
		whenFindOneRandom();
		thenBookDiscoveryShouldNotBeEmpty();
	}

	private void whenFindOneRandom() {
		bookDiscovery = victim.findOneRandom();
	}

	private void thenBookDiscoveryShouldNotBeEmpty() {
		Assert.assertNotNull(bookDiscovery);
	}

	@Test
	public void getDifferentBooks_DoesntReturnEmpty() {
		whenGetDifferentBooks();
		thenStringResultListShouldNotBeEmpty();
	}

	private void whenGetDifferentBooks() {
		stringResultList = victim.getDifferentBooks();
	}

	private void thenStringResultListShouldNotBeEmpty() {
		Assert.assertFalse(stringResultList.isEmpty());
	}

	@Test
	public void getDifferentBooks_AllTitlesShouldBeDifferent() {
		whenGetDifferentBooks();
		thenStringResultListShouldBeAllDifferent();
	}

	private void thenStringResultListShouldBeAllDifferent() {
		Set<String> differentTitles = new HashSet<String>(stringResultList);
		Assert.assertEquals(differentTitles.size(), stringResultList.size());
	}

	@Test
	public void getDifferentAuthors_ShoulNotBeEmpty() {
		whenGetDifferentAuthors();
		thenStringResultListShouldNotBeEmpty();
	}

	private void whenGetDifferentAuthors() {
		stringResultList = victim.getDifferentAuthors();
	}

	@Test
	public void getDifferentAuthors_AllTitlesShouldBeDifferent() {
		whenGetDifferentAuthors();
		thenStringResultListShouldBeAllDifferent();
	}

	@Test
	public void getDifferentPatterns_ShoulNotBeEmpty() {
		whenGetDifferentPatterns();
		thenStringResultListShouldNotBeEmpty();
	}

	private void whenGetDifferentPatterns() {
		stringResultList = victim.getDifferentPatterns();
	}

	@Test
	public void getDifferentPatterns_AllTitlesShouldBeDifferent() {
		whenGetDifferentPatterns();
		thenStringResultListShouldBeAllDifferent();
	}

}
