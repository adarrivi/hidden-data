package com.hidden.data.nosql.model.discovery;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.common.reflection.Reflection;
import com.common.test.AccessorVerifier;

public class BookDiscoveryTest {

	private static final String BOOK_ID = "A1224BC03";
	private static final String AUTHOR = "James Joyce";
	private static final String BOOK_TITLE = "Ulysses";
	private static final int BOOK_TOTAL_LINES = 100;
	private static final List<Line> LINES = Collections.nCopies(5, LineTest
			.getInstance().createVictim());
	private static final PatternDiscovery PATTERN = PatternDiscoveryTest
			.getInstance().createVictim();

	private BookDiscovery victim;

	@Test
	public void verifyDirectGetters() {
		createVictim();
		AccessorVerifier verifier = new AccessorVerifier(victim);
		verifier.addGetterToVerify("getId", "id", BOOK_ID);
		verifier.addGetterToVerify("getBookTitle", "bookTitle", BOOK_TITLE);
		verifier.addGetterToVerify("getAuthor", "author", AUTHOR);
		verifier.addGetterToVerify("getLines", "lines", LINES);
		verifier.addGetterToVerify("getPattern", "pattern", PATTERN);
		verifier.addGetterToVerify("getBookTotalLines", "bookTotalLines",
				BOOK_TOTAL_LINES);
		verifier.verifyDirectGetters();
	}

	private void createVictim() {
		victim = new BookDiscovery(BOOK_TITLE, AUTHOR, LINES, PATTERN,
				BOOK_TOTAL_LINES);
		Reflection.getInstance().setField(victim, "id", BOOK_ID);
	}
}
