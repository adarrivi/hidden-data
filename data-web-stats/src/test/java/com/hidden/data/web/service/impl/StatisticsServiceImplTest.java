package com.hidden.data.web.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.nosql.dao.BookDiscoveryDao;
import com.hidden.data.nosql.model.discovery.BookDiscovery;
import com.hidden.data.nosql.model.discovery.Line;
import com.hidden.data.nosql.model.discovery.PatternDiscovery;
import com.hidden.data.web.dto.DatabaseInfoDto;
import com.hidden.data.web.dto.PatternDistributionChart;
import com.hidden.data.web.dto.PatternsPerBookChart;
import com.hidden.data.web.service.StatisticsService;

public class StatisticsServiceImplTest {

	private static final List<String> BOOKS = Collections
			.singletonList("I, robot");
	private static final List<String> AUTHORS = Arrays.asList("Isaac Asivmo",
			"Frank Herbert");
	private static final List<String> PATTERNS = Arrays.asList("3x3 column",
			"3x3 cross", "5x5 pyramid");

	@Mock
	private BookDiscoveryDao bookDiscoveryDao;

	@InjectMocks
	private StatisticsService victim = new StatisticsServiceImpl();

	private DatabaseInfoDto dbInfoResult;
	private PatternsPerBookChart ppbChartResult;
	private PatternDistributionChart pdChartResult;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getDatabaseStats_ReturnsDbStats() {
		givenDb();
		whenGetDatabaseStats();
		thenDbInfoResultShoulContainDbInfo();
	}

	private void givenDb() {
		Mockito.when(bookDiscoveryDao.getDifferentBooks()).thenReturn(BOOKS);
		Mockito.when(bookDiscoveryDao.getDifferentAuthors())
				.thenReturn(AUTHORS);
		Mockito.when(bookDiscoveryDao.getDifferentPatterns()).thenReturn(
				PATTERNS);
	}

	private void whenGetDatabaseStats() {
		dbInfoResult = victim.getDatabaseStats();
	}

	private void thenDbInfoResultShoulContainDbInfo() {
		Assert.assertEquals(BOOKS.size(), dbInfoResult.getNumberOfBooks());
		Assert.assertEquals(AUTHORS.size(), dbInfoResult.getNumberOfAuthors());
		Assert.assertEquals(PATTERNS.size(), dbInfoResult.getNumberOfPatterns());
	}

	@Test
	public void getPatternsPerBook_ReturnsPatternsPerBook() {
		givenDb();
		whenGetPatternsPerBook();
		thenPpbChartContainsAllBooksAndPatterns();
	}

	private void whenGetPatternsPerBook() {
		ppbChartResult = victim.getPatternsPerBook();
	}

	private void thenPpbChartContainsAllBooksAndPatterns() {
		Assert.assertTrue(BOOKS.containsAll(ppbChartResult.getBookTitles()));
		Assert.assertEquals(BOOKS.size(), ppbChartResult.getBookTitles().size());
		Assert.assertTrue(PATTERNS.containsAll(ppbChartResult.getPatternNames()));
		Assert.assertEquals(PATTERNS.size(), ppbChartResult.getPatternNames()
				.size());
	}

	@Test
	public void getPatternsPerBook_LooksForAllPatternRepetitions() {
		givenDb();
		whenGetPatternsPerBook();
		thenShoulHaveLookForAllPatternRepetitions();
	}

	private void thenShoulHaveLookForAllPatternRepetitions() {
		for (String patternName : PATTERNS) {
			for (String bookTitle : BOOKS) {
				Mockito.verify(bookDiscoveryDao)
						.findBookDiscoveriesByBookAndPattern(bookTitle,
								patternName);
			}
		}
	}

	@Test
	public void getPatternDistributionPerAllBooks_ChartContainsAllBooks() {
		givenDbWithPDistributions();
		whenGetPatternDistributionPerAllBooks();
		thenPDistributionChartShouldContainAllBooks();
	}

	private void givenDbWithPDistributions() {
		givenDb();
		Line line = new Line(1, "Lorem ipsum");
		PatternDiscovery pattern = new PatternDiscovery("1 line",
				Collections.singletonList(Collections.singletonList(Character
						.valueOf('a'))));
		BookDiscovery bookDiscovery = new BookDiscovery("bookTitle", "author",
				Collections.singletonList(line), pattern, 10);

		Mockito.when(bookDiscoveryDao.findPatternsPerBook(Matchers.anyString()))
				.thenReturn(Collections.singletonList(bookDiscovery));
	}

	private void whenGetPatternDistributionPerAllBooks() {
		pdChartResult = victim.getPatternDistributionPerAllBooks();
	}

	private void thenPDistributionChartShouldContainAllBooks() {
		Assert.assertEquals(BOOKS.size(), pdChartResult.getBookTitles().size());
		Assert.assertTrue(BOOKS.containsAll(pdChartResult.getBookTitles()));
	}

}
