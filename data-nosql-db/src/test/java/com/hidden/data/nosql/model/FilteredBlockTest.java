package com.hidden.data.nosql.model;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.common.reflection.Reflection;
import com.common.test.AccessorVerifier;

public class FilteredBlockTest {

	private static final int START_LINE_NUMBER = 3;
	private static final int BOOK_ID = 2;
	private static final int PATTERN_ID = 1;
	private static final String BLOCK_ID = "AD3245";
	private static final List<String> LINES = Collections.nCopies(5,
			"Lorem ipsum dolor");

	private FilteredBlock victim;

	@Test
	public void verifyDirectGetters() {
		createVictim();
		AccessorVerifier verifier = new AccessorVerifier(victim);
		verifier.addGetterToVerify("getId", "id", BLOCK_ID);
		verifier.addGetterToVerify("getPatternId", "patternId", PATTERN_ID);
		verifier.addGetterToVerify("getBookId", "bookId", BOOK_ID);
		verifier.addGetterToVerify("getStartLineNumber", "startLineNumber",
				START_LINE_NUMBER);
		verifier.addGetterToVerify("getLines", "lines", LINES);
		verifier.verifyDirectGetters();
	}

	private void createVictim() {
		victim = new FilteredBlock(PATTERN_ID, BOOK_ID, START_LINE_NUMBER,
				LINES);
		Reflection.getInstance().setField(victim, "id", BLOCK_ID);
	}

}
