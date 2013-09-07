package com.hidden.data.web.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.web.dto.PatternsPerBookChart;
import com.hidden.data.web.service.StatisticsService;

public class PatternsPerBookControllerTest {

	private static final PatternsPerBookChart CHART = new PatternsPerBookChart();

	@Mock
	private StatisticsService statisticsService;

	@InjectMocks
	private PatternsPerBookController victim;

	private String pageResult;
	private PatternsPerBookChart resultChart;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void welcomePage_ReturnsWelcomePage() {
		whenWelcomePage();
		thenPageResultShouldBe("PatternsPerBook");
	}

	private void whenWelcomePage() {
		pageResult = victim.welcomePage();
	}

	private void thenPageResultShouldBe(String expectedPage) {
		Assert.assertEquals(expectedPage, pageResult);
	}

	@Test
	public void getPatternsChart_ReturnsServiceChart() {
		givenChartFromService(CHART);
		whenGetPatternsChart();
		thenResultChartShouldBe(CHART);
	}

	private void givenChartFromService(PatternsPerBookChart chart) {
		Mockito.when(statisticsService.getPatternsPerBook()).thenReturn(chart);
	}

	private void whenGetPatternsChart() {
		resultChart = victim.getPatternsChart();
	}

	private void thenResultChartShouldBe(PatternsPerBookChart expectedChart) {
		Assert.assertEquals(expectedChart, resultChart);
	}

}
