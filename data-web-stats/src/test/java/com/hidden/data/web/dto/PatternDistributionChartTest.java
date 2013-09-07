package com.hidden.data.web.dto;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.hidden.data.common.test.AccessorVerifier;

public class PatternDistributionChartTest {

	private static final List<String> BOOK_TITLES = Collections
			.singletonList("I, robot");
	private static final List<List<Integer>> PATTERN_DISTRIBUTIONS = Collections
			.singletonList(Collections.singletonList(Integer.valueOf(0)));
	private static final String BOOK_TITLE = "Hyperion";
	private static final List<Integer> PATTERN_DISTRIBUTION = Collections
			.singletonList(Integer.valueOf(2));
	private PatternDistributionChart victim;

	@Test
	public void verifyDirectGetters() {
		givenEmptyVictim();
		AccessorVerifier verifier = new AccessorVerifier(victim);
		verifier.addGetterToVerify("getBookTitles", "bookTitles", BOOK_TITLES);
		verifier.addGetterToVerify("getPatternsDistributionList",
				"patternDistributionsList", PATTERN_DISTRIBUTIONS);
		verifier.verifyDirectGetters();
	}

	private void givenEmptyVictim() {
		victim = new PatternDistributionChart();
	}

	@Test
	public void addPatternsDistribution_AddsBookTitle() {
		givenEmptyVictim();
		whenAddPatternsDistribution();
		thenChartShouldContainBookTitle(BOOK_TITLE);
	}

	private void whenAddPatternsDistribution() {
		victim.addPatternsDistribution(BOOK_TITLE, PATTERN_DISTRIBUTION);
	}

	private void thenChartShouldContainBookTitle(String expectedBookTitle) {
		Assert.assertTrue(victim.getBookTitles().contains(expectedBookTitle));
	}

	@Test
	public void addPatternsDistribution_AddsPatternDistribution() {
		givenEmptyVictim();
		whenAddPatternsDistribution();
		thenChartShouldContainDistribution(PATTERN_DISTRIBUTION);
	}

	private void thenChartShouldContainDistribution(
			List<Integer> expectedDistribution) {
		Assert.assertTrue(victim.getPatternsDistributionList().contains(
				expectedDistribution));
	}

}
