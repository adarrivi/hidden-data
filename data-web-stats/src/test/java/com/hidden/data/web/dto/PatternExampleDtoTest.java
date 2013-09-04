package com.hidden.data.web.dto;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.hidden.data.common.test.AccessorVerifier;

public class PatternExampleDtoTest {

	private static final String PATTERN_NAME = "3x3 X column";
	private static final String AUTHOR = "Isaac Asimov";
	private static final String BOOK_TITLE = "I, robot";
	private static final List<String> LINES = Collections.nCopies(4,
			"Lorem ipsum dolor");
	private PatternExampleDto victim;

	@Test
	public void verifyDirectGetters() {
		givenPatternExample();
		AccessorVerifier verifier = new AccessorVerifier(victim);
		verifier.addGetterToVerify("getBookTitle", "bookTitle", BOOK_TITLE);
		verifier.addGetterToVerify("getAuthor", "author", AUTHOR);
		verifier.addGetterToVerify("getPatternName", "patternName",
				PATTERN_NAME);
		verifier.addGetterToVerify("getLines", "lines", LINES);
		verifier.verifyDirectGetters();
	}

	private void givenPatternExample() {
		victim = new PatternExampleDto(BOOK_TITLE, AUTHOR, PATTERN_NAME, LINES);
	}

}
