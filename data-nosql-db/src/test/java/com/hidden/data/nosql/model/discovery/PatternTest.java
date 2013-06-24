package com.hidden.data.nosql.model.discovery;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PatternTest {

	private static final PatternTest INSTANCE = new PatternTest();
	private static final String PATTERN_NAME = "3x3 middle column";
	private String stringResult;
	private List<List<Character>> LINES;
	private List<List<Character>> listResult;
	private Pattern victim;

	protected static PatternTest getInstance() {
		return INSTANCE;
	}

	protected Pattern createVictim() {
		List<Character> line = Collections.nCopies(5, new Character('a'));
		LINES = Collections.nCopies(5, line);
		victim = new Pattern(PATTERN_NAME, LINES);
		return victim;
	}

	@Test
	public void getName_ReturnsCreationName() {
		createVictim();
		whenGetName();
		thenNameShouldBeAsInCreation();
	}

	private void whenGetName() {
		stringResult = victim.getName();
	}

	private void thenNameShouldBeAsInCreation() {
		Assert.assertEquals(PATTERN_NAME, stringResult);
	}

	@Test
	public void getLines_ReturnsCreationLines() {
		createVictim();
		whenGetLines();
		thenLinesShouldBeAsInCreation();
	}

	private void whenGetLines() {
		listResult = victim.getLines();
	}

	private void thenLinesShouldBeAsInCreation() {
		Assert.assertEquals(LINES, listResult);

	}
}
