package com.hidden.data.web.controller;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.web.dto.PatternExampleDto;
import com.hidden.data.web.service.ShowExampleService;

public class ShowExampleControllerTest {

	private static final PatternExampleDto RANDOM_EXAMPLE = new PatternExampleDto(
			"I,robot", "Isaac Asimov", "3x3 column",
			Collections.singletonList("Lorem ipsum"));

	@Mock
	private ShowExampleService showExampleService;

	@InjectMocks
	private ShowExampleController victim = new ShowExampleController();

	private String pageResult;
	private PatternExampleDto resultRandomExample;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void welcomePage_ReturnsWelcomePage() {
		whenWelcomePage();
		thenPageResultShouldBe("ShowExample");
	}

	private void whenWelcomePage() {
		pageResult = victim.welcomePage();
	}

	private void thenPageResultShouldBe(String expectedPage) {
		Assert.assertEquals(expectedPage, pageResult);
	}

	@Test
	public void getPatternsChart_ReturnsServiceChart() {
		givenChartFromService(RANDOM_EXAMPLE);
		whenGetRandomExample();
		thenResultExmapleShouldBe(RANDOM_EXAMPLE);
	}

	private void givenChartFromService(PatternExampleDto chart) {
		Mockito.when(showExampleService.getRandomExmaple()).thenReturn(chart);
	}

	private void whenGetRandomExample() {
		resultRandomExample = victim.getRandomExample();
	}

	private void thenResultExmapleShouldBe(PatternExampleDto expectedExample) {
		Assert.assertEquals(expectedExample, resultRandomExample);
	}

}
