package com.hidden.data.queue.model;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class FilterItemTest {

	private static final int FIRST_LINE_NUMBER = 23;
	private static final Integer BOOK_ID = Integer.valueOf(34);
	private static final Integer PATTERN_ID = Integer.valueOf(34);
	private static final List<String> LINES = Collections
			.singletonList("a line");

	private FilterItem victim;

	@Test
	public void getLines_ReturnLines() {
		givenFilterItem();
		Assert.assertEquals(LINES, victim.getLines());
	}

	private void givenFilterItem() {
		victim = new FilterItem(LINES, FIRST_LINE_NUMBER, BOOK_ID, PATTERN_ID);
	}

	@Test
	public void getFirstLineNumber_ReturnFirstLineNumber() {
		givenFilterItem();
		Assert.assertEquals(FIRST_LINE_NUMBER, victim.getFirstLineNumber());
	}

	@Test
	public void getBookId_ReturnBookId() {
		givenFilterItem();
		Assert.assertEquals(BOOK_ID, victim.getBookId());
	}

	@Test
	public void getPatternId_ReturnPatternId() {
		givenFilterItem();
		Assert.assertEquals(PATTERN_ID, victim.getPatternId());
	}
}
