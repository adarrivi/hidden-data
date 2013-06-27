package com.hidden.data.queue.model;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.common.test.AccessorVerifier;

public class FilterItemTest {

	private static final int FIRST_LINE_NUMBER = 23;
	private static final Integer BOOK_ID = Integer.valueOf(34);
	private static final Integer PATTERN_ID = Integer.valueOf(34);
	private static final List<String> LINES = Collections
			.singletonList("a line");
	private FilterItem victim;

	@Test
	public void verifyDirectGetters() {
		createVictim();
		AccessorVerifier verifier = new AccessorVerifier(victim);
		verifier.addGetterToVerify("getLines", "lines", LINES);
		verifier.addGetterToVerify("getFirstLineNumber", "firstLineNumber",
				FIRST_LINE_NUMBER);
		verifier.addGetterToVerify("getBookId", "bookId", BOOK_ID);
		verifier.addGetterToVerify("getPatternId", "patternId", PATTERN_ID);
		verifier.verifyDirectGetters();
	}

	private void createVictim() {
		victim = new FilterItem(LINES, FIRST_LINE_NUMBER, BOOK_ID, PATTERN_ID);
	}
}
