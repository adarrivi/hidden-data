package com.hidden.data.web.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hidden.data.web.dto.PatternDistributionChart;
import com.hidden.data.web.service.StatisticsService;

public class PatternDistributionControllerTest {

	private static final PatternDistributionChart CHART = new PatternDistributionChart();

	@Mock
	private StatisticsService statisticsService;

	@InjectMocks
	private PatternDistributionController victim = new PatternDistributionController();

	private String pageResult;
	private PatternDistributionChart resultChart;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void welcomePage_ReturnsWelcomePage() {
		whenWelcomePage();
		thenPageResultShouldBe("PatternDistribution");
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

	private void givenChartFromService(PatternDistributionChart chart) {
		Mockito.when(statisticsService.getPatternDistributionPerAllBooks())
				.thenReturn(chart);
	}

	private void whenGetPatternsChart() {
		resultChart = victim.getPatternsChart();
	}

	private void thenResultChartShouldBe(PatternDistributionChart expectedChart) {
		Assert.assertEquals(expectedChart, resultChart);
	}

}
