package com.hidden.data.web.dto;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.hidden.data.common.test.AccessorVerifier;

public class PatternsPerBookChartTest {

	private static final List<String> BOOK_TITLES = Collections
			.singletonList("I, robot");
	private static final List<String> PATTERN_NAMES = Collections
			.singletonList("3x3 column");
	private static final List<List<Integer>> PATTERN_REPETITIONS = Collections
			.singletonList(Collections.singletonList(Integer.valueOf(0)));
	private static final List<Integer> PATTERN_REPETITION = Collections
			.singletonList(Integer.valueOf(2));

	private PatternsPerBookChart victim;

	@Test
	public void verifyDirectGetters() {
		givenEmptyVictim();
		AccessorVerifier verifier = new AccessorVerifier(victim);
		verifier.addGetterToVerify("getBookTitles", "bookTitles", BOOK_TITLES);
		verifier.addGetterToVerify("getPatternNames", "patternNames",
				PATTERN_NAMES);
		verifier.addGetterToVerify("getPatternRepetitions",
				"patternRepetitions", PATTERN_REPETITIONS);
		verifier.verifyDirectGetters();
	}

	private void givenEmptyVictim() {
		victim = new PatternsPerBookChart();
	}

	@Test
	public void verifyDirectSetters() {
		givenEmptyVictim();
		AccessorVerifier verifier = new AccessorVerifier(victim);
		verifier.addSetterToVerify("setBookTitles", "bookTitles", BOOK_TITLES);
		verifier.addSetterToVerify("setPatternNames", "patternNames",
				PATTERN_NAMES);
		verifier.verifyDirectSetters();
	}

	@Test
	public void addPatternRepetitions_AddsPattern() {
		givenEmptyVictim();
		whenAddPatternRepetitions();
		thenPatternRepetitionsShouldContaion(PATTERN_REPETITION);
	}

	private void whenAddPatternRepetitions() {
		victim.addPatternRepetitions(PATTERN_REPETITION);
	}

	private void thenPatternRepetitionsShouldContaion(
			List<Integer> expectedPatternRepetition) {
		Assert.assertTrue(victim.getPatternRepetitions().contains(
				expectedPatternRepetition));
	}

}
