package com.hidden.data.web.service.impl;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.nosql.dao.BookDiscoveryDao;
import com.hidden.data.nosql.model.discovery.BookDiscovery;
import com.hidden.data.nosql.model.discovery.Line;
import com.hidden.data.nosql.model.discovery.PatternDiscovery;
import com.hidden.data.web.dto.PatternExampleDto;
import com.hidden.data.web.service.ShowExampleService;

public class ShowExampleServiceImplTest {

	private static final List<Line> LINES = Collections.singletonList(new Line(
			1, "Lorem ipsum"));
	private static final PatternDiscovery PATTERN = new PatternDiscovery(
			"patternName", Collections.<List<Character>> emptyList());
	private static final BookDiscovery BOOK_DISCOVERY = new BookDiscovery(
			"I, robot", "Isaac Asimov", LINES, PATTERN, 1);

	@Mock
	private BookDiscoveryDao bookDiscoveryDao;
	@InjectMocks
	private ShowExampleService victim = new ShowExampleServiceImpl();

	private PatternExampleDto dto;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getRandomExample_ReturnsSameValuesThanFindOneRandom() {
		givenRandomExmaple();
		whenGetRandomExample();
		thenDtoShouldBeLikeFindOneRandom();
	}

	private void givenRandomExmaple() {
		Mockito.when(bookDiscoveryDao.findOneRandom()).thenReturn(
				BOOK_DISCOVERY);
	}

	private void whenGetRandomExample() {
		dto = victim.getRandomExmaple();
	}

	private void thenDtoShouldBeLikeFindOneRandom() {
		Assert.assertEquals(BOOK_DISCOVERY.getBookTitle(), dto.getBookTitle());
		Assert.assertEquals(BOOK_DISCOVERY.getAuthor(), dto.getAuthor());
		Assert.assertEquals(BOOK_DISCOVERY.getPattern().getName(),
				dto.getPatternName());
		Assert.assertEquals(BOOK_DISCOVERY.getLinesContent(), dto.getLines());
	}

}
